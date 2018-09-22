package com.example.concurrent.singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.concurrent.annotation.Recommend;
import com.example.concurrent.annotation.ThreadSafe;

/**
 * 
 * @author xum890312
 * 枚举模式实现单利
 */
@ThreadSafe
@Recommend
public class SingletonExample5 {

	private SingletonExample5() {

	}

	public static SingletonExample5 getInstance() {
		return Singleton.INSTANCE.getInstance();
	}

	public enum Singleton {
		INSTANCE;
		private SingletonExample5 singleton;

		Singleton() {
			singleton = new SingletonExample5();
			System.out.println("aaa");
		}

		public SingletonExample5 getInstance() {
			return singleton;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch count = new CountDownLatch(100);
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			newCachedThreadPool.submit(() -> {
				SingletonExample5.getInstance();
				count.countDown();

			});
		}
		count.await();
		newCachedThreadPool.shutdown();
	}
}
