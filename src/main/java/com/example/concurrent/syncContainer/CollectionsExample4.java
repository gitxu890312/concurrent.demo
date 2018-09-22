package com.example.concurrent.syncContainer;

import java.util.Iterator;
import java.util.List;

import com.example.concurrent.annotation.ThreadSafe;
import com.google.common.collect.Lists;
@ThreadSafe
public class CollectionsExample4 {
	private static void test1(List<Integer> lists) {
		for(Integer i:lists) {
			if(i==3) {
				lists.remove(i);
			}
		}
	}
	private static void test2(List<Integer> lists) {
		Iterator<Integer> iterator = lists.iterator();
		while(iterator.hasNext()) {
			Integer next = iterator.next();
			if(next ==3) {
				lists.remove(next);
			}
		}
	}
	//success
	private static void test3(List<Integer> lists) {
		for(int i=0;i<lists.size();i++) {
			Integer integer = lists.get(i);
			if(integer==3) {
				lists.remove(integer);
			}
		}
	}
	public static void main(String[] args) {
		List<Integer> lists = Lists.newArrayList(1,2,3);
		//test1 test2报错 是因为遍历的过程中不能 删除集合中的元素
//		test1(lists);
//		test2(lists);
		test3(lists);
	}
}
