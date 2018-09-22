package com.example.concurrent.vola;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import com.example.concurrent.annotation.NotThreadSafe;
@NotThreadSafe
public class CountExample3 {

	private static final int threadnum = 200;
	
	private static final int totalnum = 5000;
	/**
	 * volatile可以保证可见性可部分有序性
	 * 不能保证原子性，导致++不是一个线程安全的
	 */
	private static volatile int count =0;
	
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
		/**
		 * 1.获取count值
		 * 2.count+1
		 * 3.将count写入到主存中
		 */
		count++;
	}
}
