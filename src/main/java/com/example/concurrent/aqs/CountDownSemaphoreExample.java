package com.example.concurrent.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 
 * @author admin
 * Semaphore 控制最大并发度
 * CountDownLatch控制多线程同步状态
 * 
 * 假如有100个任务，同时最多执行10个，并且100个任务都执行完成后再继续后面的操作
 */
public class CountDownSemaphoreExample {

	/**
	 * 任务数量
	 */
	private static int taskNum = 30;
	/**
	 * 任务的最大并发度
	 */
	private static int parallelNum = 5;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newCachedThreadPool();
		
		CountDownLatch latch = new CountDownLatch(taskNum);
		Semaphore sema = new Semaphore(parallelNum);
		
		for(int i=0;i<taskNum;i++){
			final int index = i;
			pool.execute(()->{
				try{
					sema.acquire(1);
					test(index);
				}catch(Exception e){
					
				}finally{
					sema.release(1);
					latch.countDown();
				}
			});
		}
		
		latch.await();
		System.out.println("end");
		
		
	}
	
	private static void  test(int num) throws InterruptedException{
		System.out.println("test"+num);
		Thread.sleep(1000);
	}
}
