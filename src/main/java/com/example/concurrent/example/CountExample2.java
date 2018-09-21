package com.example.concurrent.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.concurrent.annotation.ThreadSafe;
@ThreadSafe
public class CountExample2 {

	private static final int threadnum = 200;
	
	private static final int totalnum = 5000;
	
	private static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(totalnum);
		Semaphore semaphore = new Semaphore(threadnum);
		for (int i = 0; i < totalnum; i++) {
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		System.out.println("count=" + count);
	}
	private static  void  add() {
		count.incrementAndGet();
	}
}
