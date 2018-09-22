package com.example.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SemaphoreExample2 {

	private static int num = 20;
	private static Logger logger = LoggerFactory.getLogger(SemaphoreExample2.class);

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		// 控制并发的线程数量
		Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < num; i++) {
			final int index = i;
			pool.execute(() -> {
				try {
					//尝试获取资源 没有获取到不执行
					if(semaphore.tryAcquire(2, TimeUnit.SECONDS)) {
						test(index);
						semaphore.release();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		pool.shutdown();
	}
	public static void test(int num) throws InterruptedException {
		logger.info("{}",num);
		Thread.sleep(1000);
	}
}
