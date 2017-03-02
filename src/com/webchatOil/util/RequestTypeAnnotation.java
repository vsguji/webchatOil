package com.webchatOil.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestTypeAnnotation {

	RequestType value();
	
	public enum RequestType {
	    /**
	     * post方式的http请求
	     */
	    POST,
	    /**
	     * get方式的http请求
	     */
	    GET
	}
}
