package cpm.proje.testXML;

import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.User;
import com.proje.service.UserService;
import com.proje.service.impl.UserServiceImpl;

public class Test01 {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		UserService userService = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);

		User user = new User(100, "crazyBoy01", "1234", new Date());

		userService.save(user);

		applicationContext.close();
	}

}
