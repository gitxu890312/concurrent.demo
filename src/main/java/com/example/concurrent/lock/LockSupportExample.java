package com.example.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {

	
	public static void main(String[] args) {
		Thread t = new Thread(()-> {
			
			while(true) {
				System.out.println("aaaa");
			}
		});
		t.start();
		
//		LockSupport.park();
	}
}
