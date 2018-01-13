package com.tyres.sales;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tyres.sales.dao.SalesDAO;
import com.tyres.sales.dao.SalesDAOImpl;

@SpringBootApplication
@PropertySource(value = {
		"classpath:${env}/application.properties"} )

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String usr;
	
	@Value("${spring.datasource.password}")
	private String pwd;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(usr);
		dataSource.setPassword(pwd);
		
		return dataSource;
	}
	
	@Bean
	public SalesDAO getSalesDAO() {
		return new SalesDAOImpl(getDataSource());
	}

	public static void main(String[] args) {
		log.info("Starting application within environment: " + System.getProperty("env"));
		log.debug("Debug level activated");
		SpringApplication.run(Main.class);
		
	}
}