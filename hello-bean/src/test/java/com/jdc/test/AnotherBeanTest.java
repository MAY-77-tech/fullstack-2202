package com.jdc.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnotherBeanTest {
	
	@Test
	void test() {
		
		try(var context = new GenericXmlApplicationContext("classpath:/application.xml")){
			
			for(var name : context.getBeanDefinitionNames()) {
				
				if(name.startsWith("org.springframework")) {
					continue;
				}
				
				var definition = context.getBeanDefinition(name);
				
				System.out.printf("""
						Bean Name: %s
						Bean Class: %s
						Bean Definition: %s
						-------------------------------------------------------------------
						
						"""
						,name,definition.getBeanClassName(),definition.getClass().getSimpleName());
			}
		}
	}

}
