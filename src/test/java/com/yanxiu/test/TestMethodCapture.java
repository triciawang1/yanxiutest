package com.yanxiu.test;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;


public class TestMethodCapture implements IInvokedMethodListener {
	
	private static String methodName;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		methodName = method.getTestMethod().getMethodName();
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}
	

	public static String getMethodName(){
		return methodName;
	}
}
