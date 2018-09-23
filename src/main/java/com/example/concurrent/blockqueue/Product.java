package com.example.concurrent.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Product implements Callable<String>{

	private BlockingQueue<String> blockQueue;
	
	private AtomicInteger atomic;
	private long threadTime;
	public Product(BlockingQueue<String> blockQueue,AtomicInteger atomic,long threadTime) {
		this.blockQueue = blockQueue;
		this.atomic =atomic;
		this.threadTime = threadTime;
	}
	@Override
	public String call() throws Exception {
		while (true) {
			if(threadTime>0)
				Thread.sleep(threadTime);
			String msg = Thread.currentThread().getName() + ":" + atomic.incrementAndGet();
			log.info("product:"+msg);
			blockQueue.put(msg);
		}
	}

}
