package com.example.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample1 {

	public void test1(int num) {
		//1.修饰代码块：大括号括起来的代码，作用于调用对象
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.err.println("test1(){} - "+num+" " + i);
			}
		}
	}

	public synchronized void test2(int num) {
		for (int i = 0; i < 10; i++) {
			System.out.println("test2(){} - "+num+" " + i);
		}
	}
	public static void main(String[] args) {
		SynchronizedExample1 example1 = new SynchronizedExample1();
		SynchronizedExample1 example2 = new SynchronizedExample1();
		ExecutorService pool = Executors.newCachedThreadPool();
		
		 //修饰代码块 ,作用于对象
		//相同的对象，同步执行
//		pool.execute(()->example1.test1(1));
//		pool.execute(()->example1.test1(2));
		//不同的对象，并行执行
//		pool.execute(()->example1.test1(1));
//		pool.execute(()->example2.test1(2));
		
		//修饰函数
		//相同的对象，同步执行
		pool.execute(()->example1.test2(1));
		pool.execute(()->example1.test2(2));
		pool.shutdown();
	}
}
