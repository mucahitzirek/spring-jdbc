package com.proje.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.proje.model.User;
import com.proje.repository.UserRepository;
import com.proje.resultSetExractor.UserResultSetExtractor;
import com.proje.rowmapper.UserRowMapper;

@Transactional
public class UserRepositoryImpl extends NamedParameterJdbcDaoSupport implements UserRepository {

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 3, rollbackFor = RuntimeException.class)
	public boolean save(User user) {

		String query = "INSERT INTO user(userId, username, password, createionDate)"
				+ "VALUES(:userId, :username, :password, :createionDate )";

		try {

			SqlParameterSource source = new MapSqlParameterSource("userId", user.getUserId())
					.addValue("username", user.getUsername()).addValue("password", user.getPassword())
					.addValue("createionDate", user.getCreationDate());

			this.getNamedParameterJdbcTemplate().update(query, source);

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean update(User user) {

		String query = "UPDATE user SET username = :username, password = :password, WHERE userId = userId";

		try {

			SqlParameterSource source = new MapSqlParameterSource("username", user.getUsername())
					.addValue("password", user.getPassword()).addValue("userId", user.getUserId());

			this.getNamedParameterJdbcTemplate().update(query, source);

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;

	}

	@Override
	public boolean deleteById(int id) {

		String queryFindUser = "SELECT userDetailId FROM userDetail WHERE userId = :userId";

		String queryDeleteUser = "DELETE FROM user userId = :userId";

		String quryDeleteUserDetail = "DELETE FROM userDetail WHERE userDetailId = :userDetailId";

		try {

			SqlParameterSource sourceFindUser = new MapSqlParameterSource("userId", id);

			Integer userDetailId = this.getNamedParameterJdbcTemplate().query(queryFindUser, sourceFindUser,
					new ResultSetExtractor<Integer>() {

						@Override
						public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {

							Integer userDetailId = resultSet.getInt("userDetailId");

							return userDetailId;
						}
					});

			SqlParameterSource sourceDeleteUser = new MapSqlParameterSource("userId", id);

			this.getNamedParameterJdbcTemplate().update(queryDeleteUser, sourceDeleteUser);

			SqlParameterSource sourceDeleteUserDetail = new MapSqlParameterSource("userDetailId",
					userDetailId.intValue());

			this.getNamedParameterJdbcTemplate().update(quryDeleteUserDetail, sourceDeleteUserDetail);

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return false;
		}

		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(int id) {

		String query = "SELECT * FROM user WHERE userId= :userId";

		User user = null;

		try {

			SqlParameterSource parameterSource = new MapSqlParameterSource("userId", id);

			user = this.getNamedParameterJdbcTemplate().queryForObject(query, parameterSource, new UserRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User findWithUserDetailById(int id) {

		String query = "SELECT * FROM user u LEFT JOIN userDetail ud ON(u.userDetailId = ud.userDetailId) WHERE userId = :userId";

		User user = null;

		try {

			SqlParameterSource source = new MapSqlParameterSource("userId", id);

			user = this.getNamedParameterJdbcTemplate().query(query, source, new UserResultSetExtractor());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);

		}

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findUsers() {

		String query = "SELECT * FROM user";
		List<User> users = null;

		try {

			users = this.getNamedParameterJdbcTemplate().query(query, new UserRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return users;
	}

}
