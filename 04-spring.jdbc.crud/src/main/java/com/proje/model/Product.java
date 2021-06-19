package com.proje.model;

import java.util.Date;

public class Product {

	private int productId;

	private String name;

	private double price;

	private int avaible;

	private Date addDate;

	public Product() {

	}

	public Product(int productId, String name, double price, int avaible, Date addDate) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.avaible = avaible;
		this.addDate = addDate;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAvaible() {
		return avaible;
	}

	public void setAvaible(int avaible) {
		this.avaible = avaible;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", avaible=" + avaible
				+ ", addDate=" + addDate + "]";
	}

}
