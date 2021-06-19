package cpm.proje.testXML;

import java.util.Calendar;
import java.util.Date;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.proje.model.UserDetail;
import com.proje.service.UserDetailService;
import com.proje.service.impl.UserDetailServiceImpl;

public class Test02 {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		UserDetailService userDetailService = applicationContext.getBean("userDetailServiceImpl",
				UserDetailServiceImpl.class);

		UserDetail userDetail = new UserDetail(500, "Müco", "Zir", createCustomDate(12, 04, 1991));

		userDetailService.save(100, userDetail);

		applicationContext.close();
	}

	public static Date createCustomDate(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

}
