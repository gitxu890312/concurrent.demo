package com.example.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.example.concurrent.annotation.ThreadSafe;

@ThreadSafe
public class AtomicFieldExample2 {
	//修改对象中的 成员变量
	private static AtomicIntegerFieldUpdater<AtomicFieldExample2> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldExample2.class, "count");
	
	// volatile必须 不能是static
	private volatile int count = 100;
	
	
	public static void main(String[] args) {
		 AtomicFieldExample2 example = new AtomicFieldExample2();
		if(fieldUpdater.compareAndSet(example, 100, 120)) {
			System.out.println("success:"+example.count);
		}
		if(fieldUpdater.compareAndSet(example, 100, 150)) {
			System.out.println("success:"+example.count);
		}else {
			System.out.println("faile:"+example.count);
			
		}
		
	}
}
