package com.example.connectionstatus.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadExecuterServiceConfig {

	private int maximumThreadPoolSize = 10;
	private int corePoolSize=1;
	
	@Bean
	public ExecutorService executerService() {
		return Executors.newFixedThreadPool(maximumThreadPoolSize);
	}
	@Bean
	public ThreadPoolExecutor threadPoolExecutor() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(maximumThreadPoolSize, maximumThreadPoolSize, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		return threadPool;
	}
}

