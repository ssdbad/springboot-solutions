package com.in28minutes.spring.basics.springin5steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.spring.basics.cdi.CdiBusiness;
import com.in28minutes.spring.basics.cdi.CdiDao;

@Configuration
@ComponentScan(basePackageClasses  = {CdiBusiness.class, CdiDao.class})
public class SpringIn5StepsCDIApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(SpringIn5StepsCDIApplication.class);
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringIn5StepsCDIApplication.class);
		CdiBusiness cdiBusiness = applicationContext.getBean(CdiBusiness.class);
		
		LOGGER.info("{}",cdiBusiness);
		LOGGER.info("{}",cdiBusiness.getCdiDao());
	}

}
