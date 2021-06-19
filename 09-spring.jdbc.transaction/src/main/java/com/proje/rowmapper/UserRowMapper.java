package com.proje.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.proje.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		int userId = resultSet.getInt("userId");

		String username = resultSet.getString("userName");

		String password = resultSet.getString("password");

		Date creationDate = resultSet.getDate("createionDate");

		User user = new User(userId, username, password, creationDate);

		return user;
	}

}
