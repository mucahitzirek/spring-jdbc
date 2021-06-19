package com.proje.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl1 implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProductById(int id) {

		String sorgu = "SELECT * FROM product WHERE productId = ? ";

		Product product = null;
		// Product propertyleri veritabanindaki tablo ile ayni isimlere sahipse
		// BeanpropertyRowmapper ile islemi yapabiliriz. Springci abiler sagolsun.
		// (ProductRepositoryImpl5) ile ayni sonucu dondurur.
		// Ancak Ayrintili islem yapilmak istendiginde 5'teki gibi kullanilir.
		try {
			product = this.jdbcTemplate.queryForObject(sorgu, new Object[] { id },
					new BeanPropertyRowMapper<>(Product.class));

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return product;
	}

	@Override
	public List<Product> findProducts() {

		String sorgu = "SELECT * FROM product";

		List<Product> products = null;

		try {
			products = this.jdbcTemplate.query(sorgu, new BeanPropertyRowMapper<>(Product.class));
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
