package com.example.concurrent.commonUnsafe;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.example.concurrent.annotation.ThreadSafe;

@ThreadSafe
public class DateFormatExample3 {
	private static final int count = 5000;
	private static final int maxNum = 200;
	
	private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
	
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
		Date date = DateTime.parse("20181212", format).toDate();
		System.out.println(date);
	}
}
