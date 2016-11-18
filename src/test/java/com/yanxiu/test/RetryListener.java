package com.yanxiu.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.IAnnotationTransformer;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;
import org.testng.log4testng.Logger;

public class RetryListener extends TestListenerAdapter implements IAnnotationTransformer {

	private Logger log = Logger.getLogger(RetryListener.class);
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		log.info("!!!!!!!!!!!!");
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(TestngRetry.class);
		}

	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("@@@@@@@@@@@@@@@@@@@");
//		Iterator<ITestResult> listOfFailedTests = iTestContext.getFailedTests().getAllResults().iterator();
//		while (listOfFailedTests.hasNext()) {
//			ITestResult failedTest = listOfFailedTests.next();
//			ITestNGMethod method = failedTest.getMethod();
//			if (iTestContext.getFailedTests().getResults(method).size() > 1) {
//				listOfFailedTests.remove();
//			} else {
//				if (iTestContext.getPassedTests().getResults(method).size() > 0) {
//					listOfFailedTests.remove();
//				}
//			}
//		}

		removeSkippedTestsInTestNG(iTestContext);
	}
	
	
	private void removeSkippedTestsInTestNG(ITestContext test) {  
		  IResultMap passCases = test.getPassedTests();
		  IResultMap returnValue = test.getSkippedTests();
		  IResultMap failedCases = test.getFailedTests();
		  log.info("#########3"+returnValue);
		  log.info("########"+passCases);
		  log.info("#######"+test.getFailedTests());
		  for (ITestResult resultToCheck : failedCases.getAllResults()) {
		 
		  for (ITestResult result : returnValue.getAllResults()) {
			  System.out.println("AAAAAAAAAAA"+result.getMethod()+"BBBBBBBbbbb"+resultToCheck.getMethod());
		    if (result.getMethod().equals(resultToCheck.getMethod())) {
		
		        returnValue.removeResult(result.getMethod());
		        test.getSkippedConfigurations().removeResult(
		        result.getMethod());
		        
		      }
		    }
		  }
		}

}
