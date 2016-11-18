package com.yanxiu.test;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

public class TestngRetry implements IRetryAnalyzer {

	private Logger log = Logger.getLogger(TestngRetry.class);
	private int retryCount;
	private static int maxRetryCount = 1;
	
	
	@Override
	public boolean retry(ITestResult result) {
		   if (retryCount < maxRetryCount) {
	            String message = "running retry for  '" + result.getName() + "' on class " + this.getClass().getName() + " Retrying "
	                    + retryCount + " times";
	            log.info(message);
	            Reporter.setCurrentTestResult(result);
	            Reporter.log("RunCount=" + (retryCount + 1));
	            retryCount++;
	            return true;
	        }
	        return false;

	}

}
