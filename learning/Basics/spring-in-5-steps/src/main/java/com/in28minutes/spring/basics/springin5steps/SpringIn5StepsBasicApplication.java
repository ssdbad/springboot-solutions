package com.in28minutes.spring.basics.springin5steps;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.spring.basics.springin5steps.algorithm.search.impl.BinarySearchImpl;

@Configuration
@ComponentScan
public class SpringIn5StepsBasicApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringIn5StepsBasicApplication.class);
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);
		int result = binarySearchImpl.search(new int[] {10,9,17,11,23,31,8}, 10);
		System.out.println(result+" "+binarySearchImpl);
	}
}