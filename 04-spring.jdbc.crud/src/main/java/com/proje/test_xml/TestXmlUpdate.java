package com.proje.test_xml;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.repository.impl.ProductRepositoryImpl;

public class TestXmlUpdate {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		ProductRepository productRepository = applicationContext.getBean("productRepositoryImpl",
				ProductRepositoryImpl.class);

		Product product = productRepository.findById(100);

		product.setAvaible(4);
		product.setPrice(3700);

		boolean sonuc = productRepository.update(product);

		if (sonuc) {
			System.out.println("Ürün güncellendi.");
			
		} else {
			System.out.println("Ürün güncellenirken hata meydana geldi");
		}

		applicationContext.close();

	}

}
