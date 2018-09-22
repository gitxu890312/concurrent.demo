package com.example.concurrent.commonUnsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.example.concurrent.annotation.NotThreadSafe;
@NotThreadSafe
public class ArrayListExample {
	private static final int count = 5000;
	private static final int maxNum = 200;
	private static List<Integer> lists = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		CountDownLatch countDown = new CountDownLatch(count);
		Semaphore semaphore = new Semaphore(maxNum);
		for (int i = 0; i < count; i++) {
			pools.submit(() -> {
				try {
					semaphore.acquire();
					parse();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDown.countDown();
			});
		}
		countDown.await();
		pools.shutdown();
		System.out.println(lists.size());
	}

	public static void parse() {
		lists.add(1);
	}
}
