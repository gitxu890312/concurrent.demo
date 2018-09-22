package com.example.concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyclicBarrierExample3 {

	private static Logger logger = LoggerFactory.getLogger(CyclicBarrierExample3.class);
	//等待一组线程准备好，之后再继续执行，如果都就绪线程的数量为5个，并且没有超时，会先调用barrierAction线程后，再调用线程组
	private static CyclicBarrier cycBarrier = new CyclicBarrier(5,()->{
		logger.info("线程组就绪，开始执行");
	}) ;
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
			cycBarrier.await();
		} catch (InterruptedException | BrokenBarrierException  e) {
//			e.printStackTrace();
			logger.error("打断等待");
		}//等待指定的时间，数量不够也继续执行
		logger.info("{} is continue",num);
	}
}
