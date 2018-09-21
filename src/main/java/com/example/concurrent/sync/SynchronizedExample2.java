package com.example.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public  class   SynchronizedExample2 {

	public void test1(int num) {
		//1.修饰类
		synchronized (SynchronizedExample2.class) {//作用于所有对象
//			synchronized (this) {//作用于当前对象
			for (int i = 0; i < 10; i++) {
				System.out.println("test1(){} - " + num + " " + i);
			}
		}
	}

	public static void main(String[] args) {
		SynchronizedExample2 example1 = new SynchronizedExample2();
		SynchronizedExample2 example2 = new SynchronizedExample2();
		ExecutorService pool = Executors.newCachedThreadPool();
		
		pool.submit(()->example1.test1(1));
		pool.submit(()->example2.test1(2));
		pool.shutdown();
	}
}
