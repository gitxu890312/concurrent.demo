package com.example.concurrent.blockqueue;

import java.util.concurrent.BlockingQueue;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Consumer implements Runnable {
	private BlockingQueue<String> blockQueue;

	private long threadTime;
	public Consumer(BlockingQueue<String> blockQueue,long threadTime) {
		this.blockQueue = blockQueue;
		this.threadTime = threadTime;
	}

	@Override
	public void run() {
		while (true) {
			String take;
			try {
				if(threadTime>0)
					Thread.sleep(threadTime);
				take = blockQueue.take();
				log.info("comsume:" + take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
