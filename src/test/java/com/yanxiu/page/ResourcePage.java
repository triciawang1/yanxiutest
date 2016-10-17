package com.yanxiu.page;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ResourcePage extends PageBase{

	public ResourcePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(uiAutomator="new UiSelector().text('全部资源')")
	@iOSFindBy(xpath="//UIAButton[contains(@name,'全部资源')]")
	private MobileElement pageTitle;
	
	public boolean isPageLoaded(){
		return super.isPageLoaded(pageTitle);
	}
}
