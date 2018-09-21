package com.example.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

import com.example.concurrent.annotation.ThreadSafe;
@ThreadSafe
public class CountExample5 {

	private static final int threadnum = 200;
	
	private static final int totalnum = 5000;
	
	/**
	 * 控制某段代码只执行一次
	 */
	private static AtomicBoolean happens = new AtomicBoolean();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(totalnum);
		Semaphore semaphore = new Semaphore(threadnum);
		for (int i = 0; i < totalnum; i++) {
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					test();
					semaphore.release();
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		System.out.println("happens:" + happens.get());
	}
	private static  void  test() {
		happens.compareAndSet(false, true);
	}
}
