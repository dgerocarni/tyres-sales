package com.tyres.sales;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.tyres.sales.dao.SalesDAO;
import com.tyres.sales.dao.SalesDAOImpl;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.tyres.sales.*"})
public class ContextConfigurationClass {	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("jdbc:h2:mem:testDb;MODE=MySQL")
                .addScript("createTableTyresSales.sql")
                .build();
		
	}
	
	@Bean
	public SalesDAO getSalesDAO() {
		return new SalesDAOImpl(getDataSource());
	}
}