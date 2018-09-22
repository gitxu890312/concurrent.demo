package com.example.concurrent.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.concurrent.annotation.Recommend;
import com.example.concurrent.annotation.ThreadSafe;

/**
 * 懒汉模式
 * @author xum890312
 */
@ThreadSafe
@Recommend
public class SingletonExample4 {

	private static  SingletonExample4 singleton = null;

	public static List<SingletonExample4> lists = new CopyOnWriteArrayList<>();
	private SingletonExample4() {

	}
	//双重检查机制
	public static SingletonExample4 getInstance() {
		if (singleton == null) {
			synchronized (SingletonExample4.class) {
				if (singleton == null) {
					singleton = new SingletonExample4();
					lists.add(singleton);
				}
			}
		}
		return singleton;
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch count = new CountDownLatch(100);
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			newCachedThreadPool.submit(() -> {
				SingletonExample4.getInstance();
				count.countDown();

			});
		}
		count.await();
		newCachedThreadPool.shutdown();
		System.out.println(lists.size());
	}
}
