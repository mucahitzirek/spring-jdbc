package com.proje.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl1;

public class Test {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository1 = applicationContext.getBean("productRepositoryImpl1",
				ProductRepositoryImpl1.class);

		Product product = productRepository1.findProductById(102);

		System.out.println(product);

		applicationContext.close();
	}

}
