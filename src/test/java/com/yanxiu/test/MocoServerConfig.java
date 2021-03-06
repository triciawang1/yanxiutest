package com.yanxiu.test;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
@Repeatable(MocoServerConfigs.class)
public @interface MocoServerConfig {
	public String[] responseJsonFile();
	public String requestUri();
	
}
