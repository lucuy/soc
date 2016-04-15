package com.soc.junit.test.base;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	
	private BeanFactory factory;
	
	@Before
	public void init(){
		factory =  new ClassPathXmlApplicationContext("file:D://workspace//soc_junshi//src//spring//applicationContext-*.xml");
	}
	
	public Object getBean(String str){
		return factory.getBean(str);
	}
	
}
