package com.example.concurrent.singleton;

import com.example.concurrent.annotation.ThreadSafe;

/**
 * 恶汉模式
 * @author xum890312
 * 当构造函数逻辑较少时，可以使用
 */
@ThreadSafe
public class SingletonExample2 {

	private static SingletonExample2 singleton = new SingletonExample2();
	
	private SingletonExample2() {
		
	}
	
	public static SingletonExample2 getInstance() {
		return singleton;
	}
}
