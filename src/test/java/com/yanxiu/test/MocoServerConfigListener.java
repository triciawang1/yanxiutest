package com.yanxiu.test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.yanxiu.common.MocoServer;

public class MocoServerConfigListener implements IInvokedMethodListener {

	private String responseJsonFile;
	private String requestUri;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod() && annotationPresent(method, MocoServerConfig.class)) {
			System.out.println("beforeAnnotation...");
			responseJsonFile = method.getTestMethod().getConstructorOrMethod().getMethod()
					.getAnnotation(MocoServerConfig.class).responseJsonFile();
			requestUri = method.getTestMethod().getConstructorOrMethod().getMethod()
					.getAnnotation(MocoServerConfig.class).requestUri();
			System.out.println("responseJsonFile: " + responseJsonFile + " requestUri: " + requestUri);
			System.out.println(testResult.toString());
			System.out.println(testResult.getTestClass());
			try {
				MocoServer.response(responseJsonFile, requestUri);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}

	private boolean annotationPresent(IInvokedMethod method, Class<MocoServerConfig> clazz) {

		return method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(clazz) ? true : false;
	}
}
