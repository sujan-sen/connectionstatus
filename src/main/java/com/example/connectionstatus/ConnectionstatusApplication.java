package com.example.connectionstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.connectionstatus.config.DataSourceType;
import com.example.connectionstatus.config.DataSourceTypeContextHolder;

@SpringBootApplication
public class ConnectionstatusApplication {

	public static void main(String[] args) {
		DataSourceTypeContextHolder.setCustomerType(DataSourceType.PRIMARY);
		SpringApplication.run(ConnectionstatusApplication.class, args);
		
	}

}
