package com.in28minutes.spring.basics.springin5steps.algorithm.sort.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.in28minutes.spring.basics.springin5steps.algorithm.sort.Sort;

@Component
@Qualifier(value = "quick")
public class QuickSortImpl implements Sort {

	@Override
	public int[] sort(int[] array) {
		return null;
	}

}
