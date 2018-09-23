package com.example.concurrent.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FutureExample {

	private static Logger logger = LoggerFactory.getLogger(FutureExample.class);
	static class MyCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			logger.info("callable run");
			Thread.sleep(5000);
			return "done";
		}
		
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pools = Executors.newCachedThreadPool();
		Future<String> submit = pools.submit(new MyCallable());
		logger.info("dosomething in main");
		Thread.sleep(1000);
		String string = submit.get();
		logger.info(string);
	}
}
