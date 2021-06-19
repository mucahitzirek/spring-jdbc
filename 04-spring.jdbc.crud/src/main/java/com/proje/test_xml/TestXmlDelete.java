package com.proje.test_xml;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl;

public class TestXmlDelete {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl",
				ProductRepositoryImpl.class);

		productRepository.deleteById(100);

		applicationContext.close();
	}

}
