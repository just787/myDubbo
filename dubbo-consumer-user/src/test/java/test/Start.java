package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dubbo.service.UserService;

/**
 * 消费者调用
 */
public class Start {
	public static void main(String[] args) {
		// 加载配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();

		System.out.println("consumer user start");
		UserService userService = context.getBean(UserService.class);
		System.out.println("consumer user");
		System.out.println(userService.getName("Jordan"));
	}
}
