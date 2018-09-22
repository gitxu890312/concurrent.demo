package com.example.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import com.example.concurrent.annotation.ThreadSafe;
@ThreadSafe
public class ReentrantLockExample2 {

	private Map<String,Data> datas = new HashMap<>();
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private ReadLock readLock = lock.readLock();
	
	private WriteLock writeLock = lock.writeLock();
	
	public Data getData(String key) {
		readLock.lock();
		try {
			return datas.get(key);
		}finally {
			readLock.unlock();
		}
	}
	public Set<String> keys(){
		readLock.lock();
		try {
			return datas.keySet();
		}finally {
			readLock.unlock();
		}
	}
	//在获取写锁时，不能有其他线程获取读锁，既写锁与读锁时互斥的
	public void put(String key,Data data) {
		writeLock.lock();
		try {
			datas.put(key, data);
		}finally {
			writeLock.unlock();
		}
	}
	class Data{
		
	}
}
