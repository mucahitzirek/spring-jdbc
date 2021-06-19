package com.proje.test;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl2;

public class Test2 {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl2",
				ProductRepositoryImpl2.class);

		Product product = productRepository.findProductById(102);
		System.out.println(product);
//
//		List<Product> products = productRepository.findProducts();
//
//		products.forEach(System.out::println);


		applicationContext.close();
	}

}
