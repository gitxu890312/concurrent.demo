package com.example.concurrent.singleton;

import com.example.concurrent.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * @author xum890312
 *
 */
@NotThreadSafe
public class SingletonExample1 {

	private static SingletonExample1 singleton = null;
	
	private SingletonExample1() {
		
	}
	
	public static SingletonExample1 getInstance() {
		if(singleton==null) {
			singleton = new SingletonExample1();
		}
		return singleton;
	}
}
