package test;

import com.wdl.service.ConsumerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * 消费者调用
 */
public class Start {
    public static void main(String[] args) {
        // 加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
        System.out.println(context.getDisplayName() + ": started.");
        /*UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getName("Jordan"));*/

        ConsumerService consumerService = context.getBean(ConsumerService.class);
        consumerService.getName("wdl");

        System.out.println("可以继续输入参数,按回车进行调用...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            consumerService.getName(scanner.nextLine());
        }
    }
}
