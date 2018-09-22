package com.example.concurrent.publish;

import java.util.Arrays;

import com.example.concurrent.annotation.NotThreadSafe;
/**
 * 
 * @author xum890312
 * 对象发布：类的成员变量的引用可以被其他对象获取。
 */
@NotThreadSafe
public class UnsafePublish {

	private String[] state =  {"a","b","c"};

	public String[] getState() {
		return state;
	}
	
	public static void main(String[] args) {
		UnsafePublish publish = new UnsafePublish();
		System.out.println(Arrays.toString(publish.getState()));
	}
}
