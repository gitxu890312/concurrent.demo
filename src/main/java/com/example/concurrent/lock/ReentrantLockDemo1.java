package com.example.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 implements Runnable{

	private ReentrantLock lock = new ReentrantLock();
	
	private static int index = 0;
	@Override
	public void run() {
		for(int i=0;i<10000;i++) {
			try {
				//同一个线程可以多次加锁，但是释放的时，也要释放相同的次数
				lock.lock();
				lock.lock();
//				System.out.println(lock.getHoldCount()); 获取当前线程保持锁的次数
				int queueLength = lock.getQueueLength(); //返回郑等待获取此锁的线程估计数
				System.out.println(queueLength);
				index++;
			}finally {
				lock.unlock();
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ReentrantLockDemo1 run = new ReentrantLockDemo1();
		
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(index);
		
	}
}
