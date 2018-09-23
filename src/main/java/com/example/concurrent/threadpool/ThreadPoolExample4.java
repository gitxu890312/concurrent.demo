package com.example.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ThreadPoolExample4 {

	public static void main(String[] args) {
		//调度线程池
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		
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
		pool.schedule(()->{System.out.println("11");}, 4, TimeUnit.SECONDS);
		pool.scheduleWithFixedDelay(()->{System.out.println("22");}, 2, 2, TimeUnit.SECONDS);
//		pool.shutdown();
	}
}
