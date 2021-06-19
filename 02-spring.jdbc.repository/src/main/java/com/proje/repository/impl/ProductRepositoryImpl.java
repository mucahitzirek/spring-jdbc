package com.proje.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;

	@Override
	public boolean save(Product product) {

		return false;
	}

	@Override
	public boolean update(Product product) {

		return false;
	}

}
