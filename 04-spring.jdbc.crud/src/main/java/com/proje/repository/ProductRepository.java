package com.proje.repository;

import java.util.List;

import com.proje.model.Product;

public interface ProductRepository {

	boolean createProductTable();

	boolean save(Product product);

	boolean saveBatch(List<Product> products);

	Product findById(int ıd);

	List<Product> findProducts();

	boolean update(Product product);

	boolean deleteById(int id);

}
