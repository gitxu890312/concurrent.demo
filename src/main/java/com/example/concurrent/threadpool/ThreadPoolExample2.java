package com.example.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ThreadPoolExample2 {

	/**
	 * 定长线程池，队列满后再提交任务会，会将任务插入到队列 不会丢弃任务
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		
		for(int i=0;i<10;i++) {
			final int num = i;
			pool.execute(()->{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.info("thread si run {}"+num);
			});
		}
		pool.shutdown();
	}
}
