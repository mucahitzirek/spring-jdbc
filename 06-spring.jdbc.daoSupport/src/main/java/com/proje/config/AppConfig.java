package com.proje.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.proje.repository.PersonelRepository;
import com.proje.repository.impl.PersonelRepositoryImpl;

@Configuration
@ComponentScan("com.proje")
public class AppConfig {

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("213165");

		return dataSource;
	}

	@Bean
	public PersonelRepository personelRepository() {

		PersonelRepositoryImpl personelRepositoryImpl = new PersonelRepositoryImpl();

		personelRepositoryImpl.setDataSource(this.dataSource());

		return personelRepositoryImpl;
	}

}
