package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

	protected AppiumDriver<MobileElement> driver;
	public PageBase(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
	}
}
