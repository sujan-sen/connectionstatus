package com.example.connectionstatus.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.example.connectionstatus.repository" })
public class DatasourceConfig {

	private HikariConfig hikariConfig;

	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}

	// @Primary
	// @Bean(name = "primaryDatasource")
	public DataSource datasource() {
//		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
//				.url("jdbc:mysql://localhost:3306/myDb").username("user1").password("pass").build();
		hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/sys");
		hikariConfig.setUsername("testuser1");
		hikariConfig.setPassword("sujan123");
		hikariConfig.setMaxLifetime(30000L);
		hikariConfig.setPoolName("testPoolPrimary");
		hikariConfig.setIdleTimeout(10000L);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setAllowPoolSuspension(true);
		// hikariConfig.setDataSourceClassName("com.mysql.cj.jdbc.Driver");
		try {
			return new HikariDataSource(hikariConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// @Primary
	// @Bean(name = "secondaryDatasource")
	public DataSource backupDatasource() {
//		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
//				.url("jdbc:mysql://localhost:3306/myDb").username("user1").password("pass").build();
		hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/sys");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("a3!BM!DNA");
		hikariConfig.setMaxLifetime(30000L);
		hikariConfig.setPoolName("testPoolBackup");
		hikariConfig.setIdleTimeout(10000L);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setAllowPoolSuspension(true);
		try {
			return new HikariDataSource(hikariConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public DataSource dataSource() {

		DatasourceRouter router = new DatasourceRouter();
		router.initBean(this.datasource(), this.backupDatasource());

		return router;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource()).packages("com.example.connectionstatus.repository").build();
	}

	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager(
			@Autowired @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}

}
