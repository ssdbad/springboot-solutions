package com.in28minutes.spring.basics.springin5steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.spring.basics.scan.ScanJdbcConnection;
import com.in28minutes.spring.basics.scan.ScanPersonDao;

@Configuration
@ComponentScan(basePackageClasses = {ScanPersonDao.class,ScanJdbcConnection.class})// we can provide base package hierarchy also
public class SpringIn5StepsScanApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringIn5StepsScanApplication.class);
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringIn5StepsScanApplication.class);
		ScanPersonDao scanPersonDao = applicationContext.getBean(ScanPersonDao.class);
		LOGGER.info("{}",scanPersonDao);
		LOGGER.info("{}",scanPersonDao.getScanJdbcConnection());
	}
}