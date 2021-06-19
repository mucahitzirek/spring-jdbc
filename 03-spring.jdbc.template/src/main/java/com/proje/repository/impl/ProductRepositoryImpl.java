package com.proje.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();

	@Override
	public boolean save(Product product) {
		
		jdbcTemplate.setDataSource(dataSource);

		
		return false;
	}

	@Override
	public boolean update(Product product) {

		return false;
	}

}
