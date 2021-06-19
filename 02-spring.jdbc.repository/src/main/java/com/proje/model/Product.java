package com.proje.model;

public class Product {

	private int productId;

	private String name;

	private String price;

	public Product() {

	}

	public Product(int productId, String name, String price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
