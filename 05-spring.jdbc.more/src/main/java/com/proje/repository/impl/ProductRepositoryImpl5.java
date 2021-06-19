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
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl5 implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public Product findProductById(int id) {

		String sorgu = "SELECT * FROM product WHERE productId = ?";

		Product product = null;

		try {

			product = this.jdbcTemplate.query(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

					PreparedStatement preparedStatement = connection.prepareStatement(sorgu);

					return preparedStatement;
				}
			}, new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {

					preparedStatement.setInt(1, id);
				}
			}, new ResultSetExtractor<Product>() {

				@Override
				public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {

					Product product = null;

					if (resultSet.next()) {

						int productId = resultSet.getInt("productId");
						String name = resultSet.getString("name");
						double price = resultSet.getDouble("price");
						int avaible = resultSet.getInt("avaible");
						Date addDate = resultSet.getDate("addDate");

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

		@SuppressWarnings("unused")
		String sorgu = "SELECT * FROM product";
		List<Product> products = new ArrayList<>();

		try {

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
