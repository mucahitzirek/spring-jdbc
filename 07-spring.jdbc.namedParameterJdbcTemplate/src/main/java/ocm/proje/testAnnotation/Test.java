package ocm.proje.testAnnotation;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.proje.config.AppConfig;
import com.proje.model.Personel;
import com.proje.repository.PersonelRepository;
import com.proje.repository.impl.PersonelRepositoryImpl;

public class Test {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		PersonelRepository personelRepository = applicationContext.getBean("personelRepositoryImpl",
				PersonelRepositoryImpl.class);

		Personel personel = new Personel(111, "MYZ", "ZYM", createCustomDate(12, 12, 1111), "12121221");

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
