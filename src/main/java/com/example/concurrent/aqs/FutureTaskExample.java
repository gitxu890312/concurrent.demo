package com.example.concurrent.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureTaskExample {

	private static Logger logger = LoggerFactory.getLogger(FutureTaskExample.class);
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = new FutureTask<>(() -> {
			logger.info("callable run");
			Thread.sleep(5000);
			return "done";
		}) ;
		ExecutorService pools = Executors.newCachedThreadPool();
		pools.execute(futureTask);
//		new Thread(futureTask).start();
		logger.info("dosomething in main");
		Thread.sleep(1000);
		String string = futureTask.get();
		logger.info(string);
		pools.shutdown();
	}
}
