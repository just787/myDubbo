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
        System.out.println(context.getDisplayName() + ": starting...");
        context.start();
        System.out.println("provider started.");
        System.in.read();
    }
}
