package com.example.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ThreadPoolExample1 {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i=0;i<10;i++) {
			final int num = i;
			pool.execute(()->{
				log.info("thread si run {}"+num);
			});
		}
		pool.shutdown();
	}
}
