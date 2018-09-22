package com.example.concurrent.commonUnsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.concurrent.annotation.NotThreadSafe;
@NotThreadSafe
public class HashSetExample {
	private static final int count = 5000;
	private static final int maxNum = 200;
	private static Map<Integer,Integer> maps = new HashMap<>();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		CountDownLatch countDown = new CountDownLatch(count);
		Semaphore semaphore = new Semaphore(maxNum);
		for (int i = 0; i < count; i++) {
			final int num = i;
			pools.submit(() -> {
				try {
					semaphore.acquire();
					parse(num);
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDown.countDown();
			});
		}
		countDown.await();
		pools.shutdown();
		System.out.println(maps.size());
	}

	public static void parse(int num) {
		maps.put(num, num);
	}
}
