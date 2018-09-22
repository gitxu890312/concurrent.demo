package com.example.concurrent.immutable;

import java.util.List;

import com.example.concurrent.annotation.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;


/**
 * 
 * @author xum890312 
 * 不可变的集合
 */
@ThreadSafe
public class ImmutableExample2 {
	//不允许修改的list
	private static final ImmutableList<Integer> lists = ImmutableList.of(1,2,3);
	private static final List<Integer> lists1 = ImmutableList.of(1,2,3);
	
	private static final ImmutableSet sets = ImmutableSet.copyOf(lists);
	
	private static final ImmutableMap<Object, Object> maps = ImmutableMap
			.builder().put(1, 1).put(2,2).build();
	public static void main(String[] args) {
//		lists.add(1);
//		sets.add(4);
	}
}
