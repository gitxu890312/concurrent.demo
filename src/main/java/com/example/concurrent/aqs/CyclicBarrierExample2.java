package com.example.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyclicBarrierExample2 {

	private static Logger logger = LoggerFactory.getLogger(CyclicBarrierExample2.class);
	//等待一组线程准备好，之后再继续执行
	private static CyclicBarrier cycBarrier = new CyclicBarrier(5);
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pools = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int num = i;
			Thread.sleep(1000);
			pools.submit(() -> {
				test(num);
			});
		}
		pools.shutdown();
	}
	private static void test(int num)  {
		logger.info("{} is ready",num);
		try {
			cycBarrier.await(2,TimeUnit.SECONDS);
		} catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
//			e.printStackTrace();
			logger.error("打断等待");
		}//等待指定的时间，数量不够也继续执行
		logger.info("{} is continue",num);
	}
}
