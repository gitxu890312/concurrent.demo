package com.example.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConditionExample {

	private static Logger logger = LoggerFactory.getLogger(ConditionExample.class);
	
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		//可以用作线程间协调执行
		Condition condition = lock.newCondition();
		
		new Thread(()->{
			lock.lock();
			logger.info("t1 getlock");
			logger.info("t1 waiting singal...");
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("t1 get singal");
			lock.unlock();
		}).start();;
		new Thread(()->{
			lock.lock();
			logger.info("t2 get lock");
			condition.signalAll();
			logger.info("t2 send singal");
			lock.unlock();
			logger.info("t2 unlock");
		} ).start();
	}
}
