package com.example.concurrent.singleton;

import com.example.concurrent.annotation.NotRecommend;
import com.example.concurrent.annotation.ThreadSafe;

/**
 * 懒汉模式
 * @author xum890312
 * 不推荐的 
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

	private static SingletonExample3 singleton = null;
	
	private SingletonExample3() {
		
	}
	
	public synchronized static SingletonExample3 getInstance() {
		if(singleton==null) {
			singleton = new SingletonExample3();
		}
		return singleton;
	}
}
