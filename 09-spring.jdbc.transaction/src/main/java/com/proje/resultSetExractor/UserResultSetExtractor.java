package com.proje.resultSetExractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.proje.model.User;
import com.proje.model.UserDetail;

public class UserResultSetExtractor implements ResultSetExtractor<User> {

	// BİR ÇOK KEZ KULLANİLACAKSA FARKLİ BİR CLASS'TA OLUSTUR

	@Override
	public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		User user = null;

		UserDetail userDetail = null;

		if (resultSet.next()) {

			int userId = resultSet.getInt("userId");
			String username = resultSet.getString("userName");
			String password = resultSet.getString("password");
			Date creationDate = resultSet.getDate("createionDate");

			int userDetailId = resultSet.getInt("userDetailId");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			Date birthOfDate = resultSet.getDate("birthOfDate");

			user = new User(userId, username, password, creationDate);
			userDetail = new UserDetail(userDetailId, firstName, lastName, birthOfDate);

			user.setUserDetail(userDetail);
		}

		return user;
	}

}
