package com.yanxiu.common;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementHelper {
//	public static WebElement waitForElement(AppiumDriver<WebElement> driver, final By by){
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		WebElement e= wait.until(new  ExpectedCondition<WebElement>() {
//				@Override
//				public WebElement apply(WebDriver d) {
//					
//					return d.findElement(by);
//				}
//	        });
//	    return e;
//	}
	
	public static void clearEditBoxText(AndroidDriver<MobileElement> driver){		
	    driver.pressKeyCode(29,4096);
	    driver.pressKeyCode(112);
	}
	
	public static List<WebElement> findsByClass(AppiumDriver<WebElement> driver,String classname){
		return driver.findElementsByClassName(classname);
	}
	
	public static List<WebElement> findsById(AppiumDriver<WebElement> driver,String id){
		return driver.findElementsById(id);
	}
}
