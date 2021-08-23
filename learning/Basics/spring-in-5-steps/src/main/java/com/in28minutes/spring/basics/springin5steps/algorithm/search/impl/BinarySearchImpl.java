package com.in28minutes.spring.basics.springin5steps.algorithm.search.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.in28minutes.spring.basics.springin5steps.algorithm.search.Search;
import com.in28minutes.spring.basics.springin5steps.algorithm.sort.Sort;

@Component
public class BinarySearchImpl implements Search{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier(value = "quick")
	private Sort sort;
	
	@Override
	public int search(int[] numbers, int numberToSearchFor) {
		//Sorting Algorithm
		int sortedNumbers[] = sort.sort(numbers);
		System.out.println(sort);
		//Search Algorithm
		return 3;
	}
	
	@PostConstruct
	private void postConstruct() {
		// If we want to initialized some content of the bean as soon as dependencies are available, we should use @PostConstruct 
		logger.info("****************************************PostConstruct****************************************");
	}
	
	@PreDestroy
	private void preDestroy() {
		// This method runs just before the bean is removed out of the context
		logger.info("****************************************PreDestroy****************************************");
	}
}