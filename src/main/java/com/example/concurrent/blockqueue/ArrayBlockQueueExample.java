package com.example.concurrent.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockQueueExample {
	/**
	 * 生产慢的场景
	 */
	private static void productSlow() {
		ExecutorService pool = Executors.newCachedThreadPool();
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
		AtomicInteger atomic = new AtomicInteger(1);
		pool.submit(new Product(queue, atomic, 1000));
		pool.submit(new Product(queue, atomic, 1000));
		pool.execute(new Consumer(queue, 0));
	}
	//生产快的场景
	private static void productFast() {
		ExecutorService pool = Executors.newCachedThreadPool();
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
		AtomicInteger atomic = new AtomicInteger(1);
		pool.submit(new Product(queue, atomic, -1));
		pool.execute(new Consumer(queue, 2000));
	}
	public static void main(String[] args) {
//		productSlow();
		productFast();
	}
}
