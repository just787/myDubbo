package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.service.DemoService;

/**
 * 消费者调用
 */
public class Start {
	public static void main(String[] args) {
		// 加载配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.start();

		System.out.println("consumer demo start");
		DemoService demoService = context.getBean(DemoService.class);
		System.out.println("consumer demo");
		System.out.println(demoService.getPermissions(1L));
	}
}
