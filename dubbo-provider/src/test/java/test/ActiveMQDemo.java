package test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/**
 * activeMq生产者
 */
public class ActiveMQDemo {
    //消息发送者
    private MessageProducer producer;
    //一个发送或者接受消息的线程
    private Session session;
    // broker链接地址
    private static final String URL = "tcp://localhost:61616";
    // 点对点模式的队列名
    private static final String P2P_QUEUE = "p2p_queue";
    // 发布&订阅主题
    private static final String TOPIC = "messageTopic";

    // 初始化
    private void init(boolean isTopic) throws Exception {
        //ConnectionFactory连接工厂，JMS用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                URL);
        //Connection：JMS客户端到JMS Provider的连接，从构造工厂中得到连接对象
        Connection connection = connectionFactory.createConnection();
        //启动
        connection.start();
        //获取连接操作
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 设置消息发布类型
        Destination destinatin = isTopic ? session.createTopic(TOPIC)
                : session.createQueue(P2P_QUEUE);
        //得到消息生成（发送）者
        producer = session.createProducer(destinatin);
        //设置不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    // 提交消息
    private void submit(HashMap<Serializable, Serializable> requestParam) throws Exception {
        ObjectMessage message = session.createObjectMessage(requestParam);
        producer.send(message);
        session.commit();
    }

    // 点对点发送消息测试
    public static void sendTest(boolean isTopic) {
        try {
            ActiveMQDemo activeMQDemo = new ActiveMQDemo();
            activeMQDemo.init(isTopic);

            System.out.println("输入消息,按回车进行提交...");
            Scanner scanner = new Scanner(System.in);
            int i = 0;
            while (true) {
                HashMap<Serializable, Serializable> requestParam = new HashMap<Serializable, Serializable>();
                requestParam.put("activeMqTest" + (++i), scanner.nextLine());
                activeMQDemo.submit(requestParam);
                System.out.println("activeMq 提交消息成功。");
            }
        } catch (Exception e) {
            System.out.println("activeMq 提交消息失败：" + e.getMessage());
        }
    }
}
