package test;

import com.wdl.service.ConsumerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 消费者调用
 */
public class Start {
    public static void main(String[] args) {
        // 加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();

        System.out.println("consumer user started.");
        /*UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getName("Jordan"));*/

        ConsumerService consumerService = context.getBean(ConsumerService.class);
        consumerService.getName("wdl");

    }
}
