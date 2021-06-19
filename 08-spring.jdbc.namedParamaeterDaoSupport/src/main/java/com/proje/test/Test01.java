package com.proje.test;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proje.model.Personel;
import com.proje.repository.PersonelRepository;
import com.proje.repository.impl.PersonelRepositoryImpl;

public class Test01 {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		PersonelRepository personelRepository = applicationContext.getBean("personelRepositoryImpl",
				PersonelRepositoryImpl.class);


		Personel personel = new Personel(104, "Yusuf", "Zirek", createCustomDate(12, 04, 1997), "12555555");

		personelRepository.save(personel);

		applicationContext.close();
	}

	public static Date createCustomDate(int day, int mounth, int year) {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, mounth);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();

	}

}
