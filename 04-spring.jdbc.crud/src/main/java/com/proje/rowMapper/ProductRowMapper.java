package com.proje.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.proje.model.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		int productId = rs.getInt(1);
		String name = rs.getString("name");
		double price = rs.getDouble("price");
		int avaible = rs.getInt("avaible");
		Date addDate = rs.getDate("addDate");

		Product p = new Product(productId, name, price, avaible, addDate);

		return p;
	}

}
