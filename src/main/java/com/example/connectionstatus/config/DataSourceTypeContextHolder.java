package com.example.connectionstatus.config;

public class DataSourceTypeContextHolder {

	   private static  DataSourceType contextHolder;
		
	   public static void setCustomerType(DataSourceType customerType) {
	      contextHolder = customerType;
	   }

	   public static DataSourceType getCustomerType() {
	      return contextHolder;
	   }

	   public static void clearCustomerType() {
	      contextHolder=null;
	   }
	}