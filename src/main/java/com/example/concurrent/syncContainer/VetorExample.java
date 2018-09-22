package com.example.concurrent.syncContainer;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import com.example.concurrent.annotation.NotThreadSafe;
@NotThreadSafe
public class VetorExample {
	
	public static void main(String[] args) throws InterruptedException {
		Vector<Integer> v = new Vector<>();
		for(int i=0;i<1000;i++) {
			v.add(i);
		}
		CountDownLatch countDown = new CountDownLatch(2);
		Runnable run = () -> {
			for (int i = 0; i < v.size(); i++) {
				v.remove(i);
			}
			countDown.countDown();
		};
		Runnable run1 = () -> {
			for (int i = 0; i < v.size(); i++) {
				v.get(i);
			}
			countDown.countDown();
		};
		new Thread(run1).start();
		new Thread(run).start();
		countDown.await();
	}
}
