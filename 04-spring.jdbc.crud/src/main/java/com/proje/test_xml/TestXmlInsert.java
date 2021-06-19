package com.proje.test_xml;

import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;


public class TestXmlInsert {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl",
				ProductRepository.class);

		Product product = new Product(100, "Iphone 7", 3500, 6, new Date());

		productRepository.save(product);

		applicationContext.close();

	}

}
