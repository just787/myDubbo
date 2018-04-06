package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 启动服务
 */
public class Start {
    public static void main(String[] args) throws IOException {

        // 加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        System.out.println(context.getDisplayName() + ": started.");
        // 堵塞进程, 防止服务进程运行完毕而死亡
        System.in.read();


        // activeMq 发送消息测试
        //ActiveMQDemo.sendTest(true);
    }
}
