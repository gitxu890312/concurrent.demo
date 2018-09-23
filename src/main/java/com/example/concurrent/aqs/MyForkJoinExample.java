package com.example.concurrent.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyForkJoinExample extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7307203847763490463L;
	private int minElement = 100;
	private int start;
	private int end;

	public MyForkJoinExample(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if (end - start <= minElement) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle = (start + end) / 2;
			MyForkJoinExample left = new MyForkJoinExample(start, middle);
			MyForkJoinExample right = new MyForkJoinExample(middle + 1, end);
			left.fork();
			right.fork();

			Integer join = left.join();
			Integer join2 = right.join();
			sum = join + join2;
		}
		return sum;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		MyForkJoinExample task = new MyForkJoinExample(0, 100);
		ForkJoinTask<Integer> submit = pool.submit(task);
		Integer integer = submit.get();
		System.out.println(integer);
	}
}
