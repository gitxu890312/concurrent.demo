package com.example.concurrent.immutable;

import java.util.Collections;
import java.util.Map;

import com.example.concurrent.annotation.ThreadSafe;
import com.google.common.collect.Maps;


/**
 * 
 * @author xum890312 
 * 不可变的集合
 */
@ThreadSafe
public class ImmutableExample1 {

	private static final Map<Integer,Integer> maps = Maps.newHashMap();
	
	private static void mapTest() {
		maps.put(1, 1);
		maps.put(2, 2);
		Map<Integer, Integer> unmodifiableMap = Collections.unmodifiableMap(maps);
		maps.put(3, 3);
		unmodifiableMap.put(3, 4);
	}
	public static void main(String[] args) {
		mapTest();
	}
}
