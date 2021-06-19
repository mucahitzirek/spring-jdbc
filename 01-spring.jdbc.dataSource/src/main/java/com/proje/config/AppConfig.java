package com.proje.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.proje.db.Database;

@Configuration
public class AppConfig {

	public Database database() {

		Database database = new Database();

		database.setDriverClassName("MYSQL");
		database.setUrl("mysql:jdbc");
		database.setUsername("root");
		database.setPassword("213165");

		return database;

	}
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("213165");
		return dataSource;
	}

}
