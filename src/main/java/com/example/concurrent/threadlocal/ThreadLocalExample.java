package com.example.concurrent.threadlocal;

/**
 * 
 * @author xum890312
 * threadLocal封闭对象
 */
public class ThreadLocalExample {

	private static ThreadLocal<Object> formats = new ThreadLocal<>();
	
	public static void set(Object object) {
		formats.set(object);
	}
	public static Object get() {
		return formats.get();
	}
	public static void remove() {
		formats.remove();
	}
}
