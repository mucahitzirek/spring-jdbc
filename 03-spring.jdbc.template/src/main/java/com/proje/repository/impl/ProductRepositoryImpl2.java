package com.proje.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl2 implements ProductRepository {

	@Autowired
	private DataSource dataSource;

	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Product product) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		return false;
	}

	@Override
	public boolean update(Product product) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);

		return false;
	}

}
