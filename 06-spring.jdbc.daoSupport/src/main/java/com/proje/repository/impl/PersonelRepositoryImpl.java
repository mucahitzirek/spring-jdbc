package com.proje.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.proje.model.Personel;
import com.proje.repository.PersonelRepository;
import com.proje.rowMapper.PersonelRowMapper;

public class PersonelRepositoryImpl extends JdbcDaoSupport implements PersonelRepository {

	@Override
	public boolean createPersonelTable() {

		String sorgu = "CREATE TABLE personel(personelId INT NOT NULL, firstName VARCHAR(25), lastName VARCHAR(25), birthofDate DATE, personelNumber VARCHAR(12), PRIMARY KEY(personelId))";

		try {

			this.getJdbcTemplate().execute(sorgu);
			System.out.println("Personel Tablosu Olusturuldu.");
		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public Personel save(Personel personel) {

		String sorgu = "INSERT INTO personel(personelId, firstName, lastName, birthOfDate, personelNumber) VALUES (?, ?, ?, ?, ?)";
		try {

			this.getJdbcTemplate().update(sorgu, new Object[] { personel.getPersonelId(), personel.getFirstName(),
					personel.getLastName(), personel.getBirthOfDate(), personel.getPersonelNumber() });

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return null;
		}

		return personel;
	}

	@Override
	public Personel update(Personel personel) {

		String sorgu = "UPDATE personel SET firstName = ? , lastName = ?, birthOfDate = ?, personelNumber = ? WHERE personelId = ?";

		try {

			this.getJdbcTemplate().update(sorgu, new Object[] { personel.getFirstName(), personel.getLastName(),
					personel.getBirthOfDate(), personel.getPersonelNumber() });

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);

			return null;
		}

		return personel;
	}

	@Override
	public boolean deleteById(int id) {

		String sorgu = "DELETE FROM personel WHERE personelId = ? ";

		try {

			this.getJdbcTemplate().update(sorgu, new Object[] { id });

		} catch (RuntimeException e) {

			System.out.println("Hata : " + e);
			return false;
		}

		return true;
	}

	@Override
	public Personel findById(int id) {

		String sorgu = "SELECT * FROM personel WHERE personelId = ?";
		Personel personel = null;
		try {
			personel = this.getJdbcTemplate().queryForObject(sorgu, new Object[] { id },
					new BeanPropertyRowMapper<>(Personel.class));

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

			personels = this.getJdbcTemplate().query(sorgu, new PersonelRowMapper());

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

			personels = this.getJdbcTemplate().query(sorgu, new Object[] { firstName }, new PersonelRowMapper());

		} catch (RuntimeException e) {
			System.out.println("Hata : " + e);

		}

		return personels;
	}

}
