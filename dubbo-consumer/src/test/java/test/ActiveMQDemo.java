package test;


import com.alibaba.fastjson.JSON;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * activeMq消费者
 */
public class ActiveMQDemo {
    // 消息消费者
    private static MessageConsumer consumer;
    // broker链接地址
    private static final String URL = "tcp://localhost:61616";
    // 点对点模式的队列名
    private static final String P2P_QUEUE = "p2p_queue";
    // 发布&订阅主题
    private static final String TOPIC = "messageTopic";

    // 初始化
    private static void init(boolean isTopic) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 设置消息监听类型和名称
            Destination destination = isTopic ? session.createTopic(TOPIC)
                    : session.createQueue(P2P_QUEUE);
            //消息消费（接收）者
            consumer = session.createConsumer(destination);
        } catch (Exception e) {
            System.out.println("activeMq 初始化失败：" + e.getMessage());
        }
    }

    // 接收消息测试
    public static void receiveTest(boolean isTopic) {
        init(isTopic);
        System.out.println("activeMq 监听消息已启动。");
        try {
            if (isTopic) {
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        System.out.println("接收到的消息 :");
                        try {
                            if (message instanceof TextMessage) { //接收文本消息
                                System.out.println(((TextMessage) message).getText());
                            } else if (message instanceof ObjectMessage) { //接收键值对消息
                                System.out.println(JSON.toJSONString(((ObjectMessage) message).getObject()));
                            } else {
                                System.out.println(message.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                while (true) {
                    ObjectMessage message = (ObjectMessage) consumer.receive(1000);
                    if (null != message) {
                        System.out.println(message);
                        HashMap<Serializable, Serializable> requestParam =
                                (HashMap<Serializable, Serializable>) message.getObject();

                        System.out.println("接收到的消息 : ／r/n" + requestParam.toString());
                        for (Map.Entry<Serializable, Serializable> entry : requestParam.entrySet()) {
                            System.out.println(entry.getKey() + ":" + entry.getValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("activeMq 接收消息测试失败：" + e.getMessage());
        }
    }
}
