package com.proje.test_xml;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

public class TestXmlBatchInsert {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl",
				ProductRepository.class);

		Product product = new Product(101, "Samsung", 3200, 2, new Date());
		Product product2 = new Product(102, "Asus Zenfone", 2500, 1, new Date());
		Product product3 = new Product(103, "LG G3", 1900, 2, new Date());

		List<Product> products = Arrays.asList(product, product2, product3);

		productRepository.saveBatch(products);

		applicationContext.close();
	}

}
