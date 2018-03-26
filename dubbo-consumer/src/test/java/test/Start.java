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

        /**
         * 调用方式 1
         */
        /*DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.getPermissions(1L));*/

        /**
         * 调用方式2
         */
        ConsumerService consumerService = context.getBean(ConsumerService.class);
        System.out.println(consumerService.getPermissions(1L));

        System.out.println("可以继续输入参数,按回车进行调用...");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            long param = Long.valueOf(scanner.nextLine());
            System.out.println(consumerService.getPermissions(param));
        }
    }
}
