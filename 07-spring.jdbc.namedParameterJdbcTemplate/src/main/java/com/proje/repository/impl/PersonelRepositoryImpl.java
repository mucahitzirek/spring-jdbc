package com.proje.repository.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.proje.model.Personel;
import com.proje.repository.PersonelRepository;
import com.proje.rowMapper.PersonelRowMapper;

@Repository
public class PersonelRepositoryImpl implements PersonelRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Personel save(Personel personel) {

		// Tavsiye Ediliyor...

		String sorgu = "INSERT INTO personel(personelId, firstName, lastName, birthOfDate, personelNumber) "
				+ "VALUES (:personelId, :firstName, :lastName, :birthOfDate, :personelNumber)";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("personelId", personel.getPersonelId())
					.addValue("firstName", personel.getFirstName()).addValue("lastName", personel.getLastName())
					.addValue("birthOfDate", personel.getBirthOfDate())
					.addValue("personelNumber", personel.getPersonelNumber());

			this.namedParameterJdbcTemplate.update(sorgu, sqlParameterSource);

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return null;
		}

		return personel;
	}

	@Override
	public Personel update(Personel personel) {

		String sorgu = "UPDATE personel SET firstName = :firstName , lastName = :lastName ,"
				+ " birthOfDate = :birthOfDate , personelNumber = :personelNumber" + " WHERE personelId = :personelId";

		try {
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("firstName", personel.getFirstName())
					.addValue("lastName", personel.getLastName()).addValue("birthOfDate", personel.getBirthOfDate())
					.addValue("personelNumber", personel.getPersonelNumber())
					.addValue("personelId", personel.getPersonelId());

			this.namedParameterJdbcTemplate.update(sorgu, sqlParameterSource);

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return null;
		}

		return personel;
	}

	@Override
	public boolean deleteById(int id) {

		String sorgu = "DELETE FROM personel WHERE personelId = :personelId ";

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("personelId", id);

			this.namedParameterJdbcTemplate.update(sorgu, sqlParameterSource);

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public Personel findById(int id) {

		String sorgu = "SELECT * FROM personel WHERE personelId = :personelId";
		Personel personel = null;
		try {
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("personelId", id);

			personel = this.namedParameterJdbcTemplate.queryForObject(sorgu, sqlParameterSource,
					new PersonelRowMapper());

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

		}

		return personel;
	}

	@Override
	public List<Personel> findPersonels() {

		String sorgu = "SELECT * FROM personel";

		List<Personel> personels = null;

		try {

			personels = this.namedParameterJdbcTemplate.query(sorgu, new PersonelRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
		}

		return personels;
	}

	@Override
	public List<Personel> findPersonelsByName(String firstName) {

		String sorgu = "SELECT * FROM personel WHERE firstName = ?";

		List<Personel> personels = null;

		try {

			SqlParameterSource sqlParameterSource = new MapSqlParameterSource("firstName", firstName);

			personels = this.namedParameterJdbcTemplate.query(sorgu, sqlParameterSource, new PersonelRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);

		}

		return personels;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

	}

}
