package com.proje.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl2 implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProductById(int id) {

		String sorgu = "SELECT * FROM product WHERE productId = ?";

		Product product = null;

		try {

			Map<String, Object> map = this.jdbcTemplate.queryForMap(sorgu, new Object[] { id });

			int productId = (int) map.get("productId");
			String name = (String) map.get("name");
			double price = (double) map.get("price");
			int avaible = (int) map.get("avaible");
			Date addDate = (Date) map.get("addDate");

			product = new Product(productId, name, price, avaible, addDate);

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return product;
	}

	@Override
	public List<Product> findProducts() {

		String sorgu = "SELECT * FROM product";
		List<Product> products = new ArrayList<>();

		try {

			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sorgu);

			for (Map<String, Object> map : list) {

				int productId = (int) map.get("productId");
				String name = (String) map.get("name");
				double price = (double) map.get("price");
				int avaible = (int) map.get("avaible");
				Date addDate = (Date) map.get("addDate");

				Product product = new Product(productId, name, price, avaible, addDate);

				products.add(product);
			}

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);
		}

		return products;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
