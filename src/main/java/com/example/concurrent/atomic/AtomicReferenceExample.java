package com.example.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.example.concurrent.annotation.ThreadSafe;
import com.example.concurrent.model.User;

@ThreadSafe
public class AtomicReferenceExample {
	private static User zhangsan = new User("zhangsan");
	private static User lisi = new User("lisi");
	private static User wangwu = new User("wangwu");
	private static User zhaoliu = new User("zhaoliu");
	private static User tianqi = new User("tianqi");
	
	//是同一个引用的时候可以修改
	private static AtomicReference<User> atomicUser = new AtomicReference<User>(zhangsan);
	
	public static void main(String[] args) {
		atomicUser.compareAndSet(zhangsan,lisi);//lisi
		atomicUser.compareAndSet(zhangsan, wangwu);//no
		atomicUser.compareAndSet(wangwu, zhaoliu);//no
		atomicUser.compareAndSet(lisi, tianqi);//tianqi
		atomicUser.get().setName("hahaha");
		System.out.println(atomicUser.get());
	}
}
