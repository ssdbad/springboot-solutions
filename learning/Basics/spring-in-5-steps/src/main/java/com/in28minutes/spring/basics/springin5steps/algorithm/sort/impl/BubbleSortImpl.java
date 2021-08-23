package com.in28minutes.spring.basics.springin5steps.algorithm.sort.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.in28minutes.spring.basics.springin5steps.algorithm.sort.Sort;

@Component
@Primary
public class BubbleSortImpl implements Sort{
	
	@Override
	public int[] sort(int[] array) {
		int n = array.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++)
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
		}
		return array;
	}
}