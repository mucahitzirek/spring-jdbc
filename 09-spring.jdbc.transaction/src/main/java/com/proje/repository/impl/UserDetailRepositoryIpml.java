package com.proje.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.transaction.annotation.Transactional;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;

@Transactional
public class UserDetailRepositoryIpml extends NamedParameterJdbcDaoSupport implements UserDetailRepository {

	@Override
	public boolean save(int userId, UserDetail userDetail) {

		String querySaveUserDetail = "INSERT INTO userDetail(userDetailId , firstName, lastName, birthOfDate)"
				+ "VALUES (:userDetailId, :firstName, :lastName, :birthOfDate)";

		String queryUpdateUser = "UPDATE user SET userDetailId = :userDetailId WHERE userId = :userId";

		try {

			// User Detail tablosuna eklenir
			SqlParameterSource sourceSaveUserDetail = new MapSqlParameterSource("userDetailId",
					userDetail.getUserDetailId()).addValue("firstName", userDetail.getFirstName())
							.addValue("lastName", userDetail.getLastName())
							.addValue("birthOfDate", userDetail.getBirthOfDate());

			this.getNamedParameterJdbcTemplate().update(querySaveUserDetail, sourceSaveUserDetail);

			// User tablosundan userDetailId degistiriyoruz
			SqlParameterSource sourceUpdateUser = new MapSqlParameterSource("userId", userId).addValue("userDetailId",
					userDetail.getUserDetailId());

			this.getNamedParameterJdbcTemplate().update(queryUpdateUser, sourceUpdateUser);

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public boolean update(UserDetail userDetail) {

		String query = "UPDATE userDetail SET firstName = :firstName, lastName = :lastname, birthOfDate = :birthOfDate,"
				+ "WHERE userDetailId = : userDetailId";

		try {

			SqlParameterSource source = new MapSqlParameterSource("firstName", userDetail.getFirstName())
					.addValue("lastName", userDetail.getLastName()).addValue("birthOfDate", userDetail.getBirthOfDate())
					.addValue("userDetailId", userDetail.getUserDetailId());

			this.getNamedParameterJdbcTemplate().update(query, source);

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return false;
		}

		return true;
	}

	@Override
	public UserDetail findById(int id) {

		String query = "SELECT * FROM userDetail WHERE userDetailId = :userDetailId";

		UserDetail userDetail = null;

		try {

			userDetail = this.getNamedParameterJdbcTemplate().queryForObject(query,
					new MapSqlParameterSource("userDetailId", id), new BeanPropertyRowMapper<>(UserDetail.class));

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return userDetail;
	}

	@Override
	public List<UserDetail> findUserDetails() {

		String query = "SELECT * FROM userDetail";
		List<UserDetail> userDetails = null;

		try {

			userDetails = this.getNamedParameterJdbcTemplate().query(query,
					new BeanPropertyRowMapper<>(UserDetail.class));

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);

		}

		return userDetails;
	}

}
