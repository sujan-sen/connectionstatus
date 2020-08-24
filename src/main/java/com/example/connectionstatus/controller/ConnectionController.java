package com.example.connectionstatus.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.connectionstatus.repository.ConnectionStatus;
import com.example.connectionstatus.repository.ConnectionStatusRepository;
import com.example.connectionstatus.threads.ThreadTaskUtil;

@RestController
public class ConnectionController {
	@Autowired
	private ConnectionStatusRepository repository;
	@Autowired
	private ThreadTaskUtil taskUtil;
	
	@GetMapping("/addconnection")
	public int addConnectionCount() throws Exception {
		taskUtil.executeJob();
		Optional<ConnectionStatus> connectionStatus = repository.findById(1);
		ConnectionStatus conStatus = null;
		if(connectionStatus.isPresent()) {
			conStatus = connectionStatus.get();
			conStatus.setConnectionCount(conStatus.getConnectionCount()+1);
		}else {
			conStatus = new ConnectionStatus();
			conStatus.setConnectionId(1);
			conStatus.setConnectionCount(1);
		}
		repository.save(conStatus);
		System.out.println("Added connection By>>>"+Thread.currentThread().getName()+" count now>>> "+conStatus.getConnectionCount());
		return conStatus.getConnectionCount();
	}
	
	@GetMapping("/removeconnection")
	public int removeConnectionCount() {
		Optional<ConnectionStatus> connectionStatus = repository.findById(1);
		ConnectionStatus conStatus = null;
		if(connectionStatus.isPresent()) {
			conStatus = connectionStatus.get();
			conStatus.setConnectionCount(conStatus.getConnectionCount()-1);
		}else {
			conStatus = new ConnectionStatus();
			conStatus.setConnectionId(1);
			conStatus.setConnectionCount(1);
		}
		repository.save(conStatus);
		System.out.println("Removed connection By>>>"+Thread.currentThread().getName()+" count now>>> "+conStatus.getConnectionCount());
		return conStatus.getConnectionCount();
	}
}