package com.example.concurrent.commonUnsafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.concurrent.annotation.NotThreadSafe;

@NotThreadSafe
public class DateFormatExample1 {
	private static final int count = 5000;
	private static final int maxNum = 200;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		CountDownLatch countDown = new CountDownLatch(count);
		Semaphore semaphore = new Semaphore(maxNum);
		for(int i=0;i<count;i++) {
			pools.submit(() -> {
				try {
					semaphore.acquire();
					parse();
					semaphore.release();
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		countDown.await();
		pools.shutdown();
	}
	public static void parse() {
		try {
			System.out.println(format.parse("20181212"));;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
