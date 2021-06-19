package com.proje.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl4 implements ProductRepository {

	@SuppressWarnings("unused")
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Product product) {

		
		return false;
	}

	@Override
	public boolean update(Product product) {

		return false;

	}

	@Autowired
	public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
