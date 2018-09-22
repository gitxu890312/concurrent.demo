package com.example.concurrent.commonUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.concurrent.annotation.NotThreadSafe;
@NotThreadSafe
public class StringExample1 {

	private static final int count = 5000;
	private static final int maxNum = 200;
	
	private static StringBuilder sBuilder = new StringBuilder();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		CountDownLatch countDown = new CountDownLatch(count);
		Semaphore semaphore = new Semaphore(maxNum);
		for(int i=0;i<count;i++) {
			pools.submit(()->{
				try {
					semaphore.acquire();
					sBuilder.append("1");
					countDown.countDown();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDown.await();
		System.out.println(sBuilder.length());
		pools.shutdown();
	}
}
