package com.proje.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.proje.model.Product;
import com.proje.repository.ProductRepository;
import com.proje.rowMapper.ProductRowMapper;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private JdbcTemplate jdbcTemplate;

	// execute(); hazır direk sql üzerinde işlem yapilmadan gönderilecek sorguları
	// çalıştırabiliriz.

	@Override
	public boolean createProductTable() {

		final String query = "CREATE TABLE product(productId INT NOT NULL, name VARCHAR(20), price DOUBLE,avaible INT,"
				+ "addDate DATETIME, PRIMARY KEY(productId))";

		try {
			this.jdbcTemplate.execute(query);
		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	// update(); tablolarin icinde güncellemeler yapilirken kullanilir.

	@Override
	public boolean save(Product product) {

		final String sorgu = "INSERT INTO product(productId, name, price, avaible, addDate) VALUES (?, ?, ?, ?, ?)";

		try {
			// Object her classin atasiydi.

			Object[] objects = new Object[] { product.getProductId(), product.getName(), product.getPrice(),
					product.getAvaible(), product.getAddDate() };

			this.jdbcTemplate.update(sorgu, objects);

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean saveBatch(List<Product> products) {

		final String sorgu = "INSERT INTO product(productId, name, price, avaible, addDate) VALUES(?, ?, ?, ?, ?) ";

		try {

			this.jdbcTemplate.batchUpdate(sorgu, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement pstmt, int i) throws SQLException {

					Product product = products.get(i);

					pstmt.setInt(1, product.getProductId());
					pstmt.setString(2, product.getName());
					pstmt.setDouble(3, product.getPrice());
					pstmt.setInt(4, product.getAvaible());
					pstmt.setTimestamp(5, Timestamp.from(product.getAddDate().toInstant()));
				}

				@Override
				public int getBatchSize() {

					return products.size();
				}
			});

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public Product findById(final int id) {

		String sorgu = "SELECT * FROM product WHERE productId=?";
		Product product = null;
		try {

			/*
			 * product = this.jdbcTemplate.queryForObject(sorgu, new Object[] { id }, new
			 * RowMapper<Product>() {
			 * 
			 * @Override public Product mapRow(ResultSet rs, int rowNum) throws SQLException
			 * {
			 * 
			 * int productId = rs.getInt(1); String name = rs.getString("name"); double
			 * price = rs.getDouble("price"); int avaible = rs.getInt("avaible"); Date
			 * addDate = rs.getDate("addDate");
			 * 
			 * Product p = new Product(productId, name, price, avaible, addDate);
			 * 
			 * return p; }
			 * 
			 * });
			 */

			product = this.jdbcTemplate.queryForObject(sorgu, new Object[] { id }, new ProductRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return product;
	}

	@Override
	public List<Product> findProducts() {

		String sorgu = "SELECT * FROM product";
		List<Product> prodcuts = null;
		try {
			prodcuts = this.jdbcTemplate.query(sorgu, new ProductRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return prodcuts;
	}

	@Override
	public boolean update(Product product) {

		String sorgu = "UPDATE product SET name = ?, price = ?, avaible = ? WHERE productId = ? ";

		try {
			/*
			 * Uzun Yontem this.jdbcTemplate.update(sorgu, new PreparedStatementSetter() {
			 * 
			 * @Override public void setValues(PreparedStatement preparedStatement) throws
			 * SQLException {
			 * 
			 * preparedStatement.setString(1, product.getName());
			 * preparedStatement.setDouble(2, product.getPrice());
			 * preparedStatement.setInt(3, product.getAvaible());
			 * preparedStatement.setInt(4, product.getProductId());
			 * 
			 * } });
			 */

			this.jdbcTemplate.update(sorgu, new Object[] { product.getName(), product.getPrice(), product.getAvaible(),
					product.getProductId() });

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteById(int id) {

		String sorgu = "DELETE FROM product WHERE productId = ?";

		try {

			this.jdbcTemplate.update(sorgu, new Object[] { id });

		} catch (RuntimeException e) {
			System.out.println("Hata : " +e);
			return false;
		}

		return true;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

}
