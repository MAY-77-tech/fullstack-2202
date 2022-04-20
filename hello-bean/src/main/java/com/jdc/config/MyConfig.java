package com.jdc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdc.hello.MyBean;

@Configuration//("annotationMyConfig")
public class MyConfig {
	
	@Bean({
		"javaBaseBean","javaBaseBean1"
	})
	MyBean helloBean() {
		return new MyBean();
	}

}
