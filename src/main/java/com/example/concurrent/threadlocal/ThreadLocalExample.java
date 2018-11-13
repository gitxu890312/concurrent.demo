package com.example.concurrent.threadlocal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author xum890312
 * threadLocal封闭对象
 */
public class ThreadLocalExample {

	private static ThreadLocal<Object> formats = new ThreadLocal<>();
	
	private static List<Thread> ts = new ArrayList<>();
	public static void set(Object object) {
		formats.set(object);
	}
	public static Object get() {
		return formats.get();
	}
	public static void remove() {
		formats.remove();
	}
	static long index = 0;
	@Autowired
	public static void main(String[] args) {
		while(true) {
			Thread t = new Thread(()->{
				index++;
				set(new ArrayList<Object>(100));
				System.out.println(index);
			});;
			ts.add(t);
			t.start();
		}
	}
}
