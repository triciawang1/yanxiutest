package com.yanxiu.test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.yanxiu.common.MocoServer;

public class MocoServerConfigListener implements IInvokedMethodListener {

	private String responseJsonFile;
	private String requestUri;
	private static Logger log = Logger.getLogger(MocoServerConfigListener.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		log.info("start:"+method.getTestMethod().getMethodName());
		if (method.isTestMethod() && annotationPresent(method, MocoServerConfig.class)) {
			log.info("beforeAnnotation...");
			responseJsonFile = method.getTestMethod().getConstructorOrMethod().getMethod()
					.getAnnotation(MocoServerConfig.class).responseJsonFile();
			requestUri = method.getTestMethod().getConstructorOrMethod().getMethod()
					.getAnnotation(MocoServerConfig.class).requestUri();
			log.info("responseJsonFile: " + responseJsonFile + " requestUri: " + requestUri);

			try {
				MocoServer.response(responseJsonFile, requestUri);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private boolean screenshotAnnotationPresent(IInvokedMethod method, Class<TakeScreenshotAndAssert> class1) {
		
		return method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(class1) ? true : false;
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod() && screenshotAnnotationPresent(method,TakeScreenshotAndAssert.class)){
			try {
				Method takescreenshotAndAssert = method.getTestMethod().getRealClass().getMethod("takeScreenShotAndAssert",String.class);
				takescreenshotAndAssert.invoke(testResult.getInstance(), method.getTestMethod().getMethodName()+".png");
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				Throwable t = e.getTargetException();
				t.printStackTrace();
				testResult.setStatus(2);
				
			}
			
		}
		log.info("end:"+method.getTestMethod().getMethodName());

	}

	private boolean annotationPresent(IInvokedMethod method, Class<MocoServerConfig> clazz) {

		return method.getTestMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(clazz) ? true : false;
	}
}
