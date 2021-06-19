package com.proje.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.proje.model.Personel;

public class PersonelRowMapper implements RowMapper<Personel> {

	@Override
	public Personel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		int personelId = resultSet.getInt("personelId");

		String firstName = resultSet.getString("firstName");

		String lastName = resultSet.getString("lastName");

		Date birthOfDate = resultSet.getDate("birthOfDate");

		String personelNumber = resultSet.getString("personelNumber");

		Personel personel = new Personel(personelId, firstName, lastName, birthOfDate, personelNumber);

		return personel;
	}

}
