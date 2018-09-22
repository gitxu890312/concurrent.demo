package com.example.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyclicBarrierExample1 {

	private static Logger logger = LoggerFactory.getLogger(CyclicBarrierExample1.class);
	
	private static CyclicBarrier cycBarrier = new CyclicBarrier(5);
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int num = i;
			Thread.sleep(1000);
			pools.submit(() -> {
				try {
					test(num);
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}
		pools.shutdown();
	}
	private static void test(int num) throws InterruptedException, BrokenBarrierException {
		logger.info("{} is ready",num);
		cycBarrier.await();//
		logger.info("{} is continue",num);
	}
}
