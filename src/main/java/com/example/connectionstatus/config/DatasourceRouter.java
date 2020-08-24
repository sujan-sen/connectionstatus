package com.example.connectionstatus.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DatasourceRouter extends AbstractRoutingDataSource {

	private DataSource primaryDatasource;
	private DataSource secondaryDatasource;
	
	
	public DataSource getPrimaryDatasource() {
		return primaryDatasource;
	}

	public void setPrimaryDatasource(DataSource primaryDatasource) {
		this.primaryDatasource = primaryDatasource;
	}

	public DataSource getSecondaryDatasource() {
		return secondaryDatasource;
	}

	public void setSecondaryDatasource(DataSource secondaryDatasource) {
		this.secondaryDatasource = secondaryDatasource;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		if (DataSourceTypeContextHolder.getCustomerType()!=null && DataSourceTypeContextHolder.getCustomerType().equals(DataSourceType.PRIMARY)) {
			System.out.println("PRIMARY>>>>>>>>>>>");
			return "primaryDatasource";
		} else {
			System.out.println("SECONDARY>>>>>>>");
			return "secondaryDatasource";
		}

	}
	
	public void initBean(DataSource primaryDatasource,DataSource secondaryDatasource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("primaryDatasource", primaryDatasource);
		targetDataSources.put("secondaryDatasource", secondaryDatasource);
		this.setTargetDataSources(targetDataSources);
		this.setDefaultTargetDataSource(primaryDatasource);
		this.primaryDatasource=primaryDatasource;
		this.secondaryDatasource=secondaryDatasource;
	}

}
