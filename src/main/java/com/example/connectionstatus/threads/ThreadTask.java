package com.example.connectionstatus.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ThreadTask implements Callable<Object> {
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public String task() throws InterruptedException {
		System.out.println("Performing task for job>>>"+jobName);
		if(jobName.equals("job1")) {
			System.out.println("Waiting for Job>>"+jobName);
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Completed Job>>"+jobName);
		}
		return jobName;
	}

	@Override
	public Object call() throws Exception {
		String resp = task();
		System.out.println("Response received is "+resp);
		return resp;
	}

}