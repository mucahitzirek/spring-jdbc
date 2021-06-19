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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl4 implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProductById(int id) {

		String sorgu = "SELECT * FROM product WHERE productId = ?";

		Product product = new Product();

		try {

			product = this.jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

					PreparedStatement preparedStatement = con.prepareStatement(sorgu);

					preparedStatement.setInt(1, id);

					return preparedStatement;
				}
			}, new ResultSetExtractor<Product>() {

				@Override
				public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
					Product product = null;

					if (rs.next()) {

						int productId = rs.getInt("productId");
						String name = rs.getString("name");
						double price = rs.getDouble("price");
						int avaible = rs.getInt("avaible");
						Date addDate = rs.getDate("addDate");

						product = new Product(productId, name, price, avaible, addDate);
					}
					return product;
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

			products = this.jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

					PreparedStatement preparedStatement = con.prepareStatement(sorgu);

					return preparedStatement;
				}
			}, new ResultSetExtractor<List<Product>>() {

				@Override
				public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {

					List<Product> products = new ArrayList<>();

					while (rs.next()) {

						int productId = rs.getInt("productId");
						String name = rs.getString("name");
						double price = rs.getDouble("price");
						int avaible = rs.getInt("avaible");
						Date addDate = rs.getDate("addDate");

						Product product = new Product(productId, name, price, avaible, addDate);

						products.add(product);

					}

					return products;
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
