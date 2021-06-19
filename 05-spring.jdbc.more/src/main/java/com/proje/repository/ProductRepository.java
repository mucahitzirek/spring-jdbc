package com.proje.repository;

import java.util.List;

import com.proje.model.Product;

public interface ProductRepository {

	Product findProductById(int id);

	List<Product> findProducts();

}
