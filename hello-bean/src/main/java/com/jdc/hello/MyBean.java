package com.jdc.hello;

import org.springframework.stereotype.Component;

@Component("annotationBean")
public class MyBean {

	private String message;

	public MyBean() {
		this.message = "Hello Spring Bean Message";
	}

	public String getMessage() {
		return message;
	}

}
