package com.proje.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.proje.repository.UserDetailRepository;
import com.proje.repository.UserRepository;
import com.proje.repository.impl.UserDetailRepositoryIpml;
import com.proje.repository.impl.UserRepositoryImpl;

@Configuration
@EnableTransactionManagement
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
	public UserRepository userRepository() {

		UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
		userRepositoryImpl.setDataSource(this.dataSource());

		return userRepositoryImpl;
	}

	@Bean
	public UserDetailRepository userDetailRepository() {

		UserDetailRepositoryIpml userDetailRepositoryImpl = new UserDetailRepositoryIpml();
		userDetailRepositoryImpl.setDataSource(this.dataSource());

		return userDetailRepositoryImpl;
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {

		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(this.dataSource());

		return dataSourceTransactionManager;
	}

}
