package com.proje.repository;

import java.util.List;

import com.proje.model.Personel;

public interface PersonelRepository {

	boolean createPersonelTable();

	Personel save(Personel personel);

	Personel update(Personel personel);

	boolean deleteById(int id);

	Personel findById(int id);

	List<Personel> findPersonels();

	List<Personel> findPersonelsByName(String firstName);

}
