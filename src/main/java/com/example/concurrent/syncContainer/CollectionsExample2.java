package com.example.concurrent.syncContainer;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


import com.example.concurrent.annotation.ThreadSafe;
import com.google.common.collect.Sets;
@ThreadSafe
public class CollectionsExample2 {
	private static final int count = 5000;
	private static final int maxNum = 200;
	private static Set<Integer> sets = Collections.synchronizedSet(Sets.newHashSet());

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
		System.out.println(sets.size());
	}

	public static void parse(int num) {
		sets.add(num);
	}
}
