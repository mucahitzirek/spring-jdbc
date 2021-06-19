package com.proje.test;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl4;

public class Test4 {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl4",
				ProductRepositoryImpl4.class);

//		
//		Product product = productRepository.findProductById(102);
//		System.out.println(product);

		List<Product> products = productRepository.findProducts();

		products.forEach(System.out::println);


		applicationContext.close();
	}

}
