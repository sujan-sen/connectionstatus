package com.example.connectionstatus.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadTaskUtil {

	@Autowired
	private ExecutorService executerService;
	//@Autowired
	//private ThreadPoolExecutor threadPool;

	public void executeJob() throws Exception {
		System.out.println(">>>>>>>"+executerService);
		ThreadTask task1 = new ThreadTask();
		task1.setJobName("job1");
		ThreadTask task2 = new ThreadTask();
		task2.setJobName("job2");
		ThreadTask task3 = new ThreadTask();
		task3.setJobName("job3");

		List<Callable<Object>> taskList = new ArrayList<>();
		taskList.add(task1);
		taskList.add(task2);
		taskList.add(task3);
		
		
		
		//List<Future<Object>> taskFutures = threadPool.invokeAll(taskList);
		List<Future<Object>> taskFutures = executerService.invokeAll(taskList);
		//System.out.println("Current Size>> "+threadPool.getPoolSize());
		System.out.println(taskFutures.get(0).isDone());
		taskFutures.forEach(System.out::println);
	}
}


