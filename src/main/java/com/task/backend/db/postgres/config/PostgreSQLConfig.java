package com.task.backend.db.postgres.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(

		entityManagerFactoryRef="entityManagerFactoryBean",
		basePackages={"com.task.backend.db.postgres.repositories"},
		transactionManagerRef="transactionManager"
)
public class PostgreSQLConfig {

	@Autowired
	Environment environment;
	
	//datasource
	@Primary
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource source=new DriverManagerDataSource();
		source.setUrl(environment.getProperty("db1.datasource.url"));
		source.setUsername(environment.getProperty("db1.datasource.username"));
		source.setPassword(environment.getProperty("db1.datasource.password"));
		source.setDriverClassName(environment.getProperty("db1.datasource.driver-class-name"));
		
		return source;
	}
	
	//entityManagerFactory
	@Primary
	@Bean(name="entityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	{
		LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		
		HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		
		
		Map<String,String> props=new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		bean.setJpaPropertyMap(props);
		bean.setPackagesToScan("com.task.backend.db.postgres.entities");
		return bean;
	}
	
	//platformTransactionManager
	@Primary
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager manager=new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return manager;
	}
}
