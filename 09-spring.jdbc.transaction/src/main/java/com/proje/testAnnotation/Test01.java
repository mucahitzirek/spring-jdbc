package com.proje.testAnnotation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.proje.config.AppConfig;
import com.proje.model.User;
import com.proje.service.UserService;
import com.proje.service.impl.UserServiceImpl;

public class Test01 {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		UserService userService = applicationContext.getBean(UserServiceImpl.class);

		
		User user = userService.findWithUserDetailById(100);

		System.out.println(user.getUserDetail());

		applicationContext.close();

	}

}
