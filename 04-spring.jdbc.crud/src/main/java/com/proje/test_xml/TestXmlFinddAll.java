package com.proje.test_xml;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

public class TestXmlFinddAll {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl",
				ProductRepository.class);

		List<Product> products = productRepository.findProducts();

		/*
		 * for (Product product : products) {
		 * 
		 * System.out.println(product); }
		 */
		products.forEach(System.out::println);
		applicationContext.close();

	}

}
