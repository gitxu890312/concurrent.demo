package com.example.concurrent.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @author admin
 * synchronized 继承性demo
 */
public class SyncExtendsExample extends SynchronizedExample1 {

	// 不会将父类的synchronized 继承过来，若要同步，需要显示声明synchronized
	public void test2(int num) {
		for (int i = 0; i < 10; i++) {
			System.out.println("test2(){} - " + num + " " + i);
		}
	}

	public static void main(String[] args) {
		SyncExtendsExample eone = new SyncExtendsExample();
		ExecutorService pools = Executors.newCachedThreadPool();

		pools.submit(() -> eone.test2(1));

		pools.submit(() -> eone.test2(2));
		pools.shutdown();
	}
}
