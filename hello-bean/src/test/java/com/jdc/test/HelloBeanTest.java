package com.jdc.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.jdc.hello.MyBean;

public class HelloBeanTest {
	
	@Test
	void test() {
		try(var context = new GenericApplicationContext()){
			
		//	context.setAllowBeanDefinitionOverriding(false);
			
			var beanDefinition = new GenericBeanDefinition();
			beanDefinition.setBeanClass(MyBean.class);			
			context.registerBeanDefinition("mybean", beanDefinition);
			
			var d2 = new GenericBeanDefinition();
			d2.setBeanClass(String.class);			
			context.registerBeanDefinition("mybean", d2);
			
			context.refresh();
			
			var bean = context.getBean(MyBean.class);
			
			assertNotNull(bean);
			
			assertEquals("Hello Spring Bean Message", bean.getMessage());
		}
	}

}
