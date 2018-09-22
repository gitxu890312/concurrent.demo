package com.example.concurrent.aqs;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample1 {

	private static final int threadnum = 5000;
	
	private static List<Integer> lists = new CopyOnWriteArrayList<>();
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(threadnum);
		for (int i = 0; i < threadnum; i++) {
			final int num = i;
			pool.submit(() -> {
				try {
					test(num);
				} finally {
					latch.countDown();
				}
			});
		}
//		latch.await();//一直等待
		latch.await(2,TimeUnit.MILLISECONDS);//等待指定的时间
		System.out.println("finish:"+lists.size());
		pool.shutdownNow();
	}
	public static void test(int num) {
		lists.add(num);
	}
}
