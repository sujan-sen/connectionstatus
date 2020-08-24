package com.example.connectionstatus.threads;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.connectionstatus.config.DataSourceType;
import com.example.connectionstatus.config.DataSourceTypeContextHolder;
import com.example.connectionstatus.config.DatasourceRouter;
import com.example.connectionstatus.repository.ConnectionStatusRepository;
import com.zaxxer.hikari.HikariDataSource;

@EnableScheduling
@Configuration
public class TestConnection {

	@Autowired
	private DataSource datasource;

	@Autowired
	private ConnectionStatusRepository repository;
	static int flag = 0;

	@Scheduled(fixedRate = 10000)
	public void testConnection() {

		try {
			System.out.println("Hitting count query");
			repository.count();
			if (flag == 0)
				System.out.println("Got connection from primary");
			else
				System.out.println("Got connection from secondary");
		} catch (Exception e) {
			System.out.println("Failed to get connection from primary");
			e.printStackTrace();
			if (flag == 0) {
				flag = 1;
				DatasourceRouter routerDatasource = (DatasourceRouter) datasource;
				((HikariDataSource) routerDatasource.getPrimaryDatasource()).close();
				System.out.println("Closing primary datasource since it is down");
				DataSourceTypeContextHolder.setCustomerType(DataSourceType.SECONDARY);
				System.out.println("Selecting secondary datasource");
			}
		}
	}

}
