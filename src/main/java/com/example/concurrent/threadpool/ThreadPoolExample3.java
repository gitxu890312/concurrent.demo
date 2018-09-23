package com.example.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ThreadPoolExample3 {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<10;i++) {
			final int num = i;
			pool.execute(()->{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("thread si run {}"+num);
			});
		}
		pool.shutdown();
	}
}
