package com.proje.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl3 implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProductById(int id) {

		String sorgu = "SELECT * FROM product WHERE productId = ?";

		Product product = new Product();

		try {

			this.jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

					PreparedStatement preparedStatement = connection.prepareStatement(sorgu);
					preparedStatement.setInt(1, id);
					// execute islemini springe ele aliyor
					return preparedStatement;
				}
			}, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {

					int productId = rs.getInt("productId");
					String name = rs.getString("name");
					double price = rs.getDouble("price");
					int avaible = rs.getInt("avaible");
					Date addDate = rs.getDate("addDate");

					product.setProductId(productId);
					product.setName(name);
					product.setPrice(price);
					product.setAvaible(avaible);
					product.setAddDate(addDate);

				}
			});

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return product;
	}

	@Override
	public List<Product> findProducts() {

		String sorgu = "SELECT * FROM product";
		List<Product> products = new ArrayList<>();

		try {

			this.jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

					PreparedStatement preparedStatement = con.prepareStatement(sorgu);

					return preparedStatement;
				}
			}, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {

					do {

						int productId = rs.getInt("productId");
						String name = rs.getString("name");
						double price = rs.getDouble("price");
						int avaible = rs.getInt("avaible");
						Date addDate = rs.getDate("addDate");

						Product product = new Product(productId, name, price, avaible, addDate);

						products.add(product);

					}while(rs.next());

				}
			});

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
