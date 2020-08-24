package com.example.connectionstatus.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CONNECTION_STATUS",schema = "sys")
@Entity
public class ConnectionStatus {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int connectionId;
	
	@Column(name = "count")
	private int connectionCount;

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public int getConnectionCount() {
		return connectionCount;
	}

	public void setConnectionCount(int connectionCount) {
		this.connectionCount = connectionCount;
	}
}
