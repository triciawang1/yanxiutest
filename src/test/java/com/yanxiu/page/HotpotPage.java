package com.yanxiu.page;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HotpotPage extends PageBase {

	public HotpotPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@iOSFindBy(xpath="//UIAStaticText[contains(@name,'热点')]")
	private MobileElement pageTitle;
	
	public boolean isPageLoaded(){
		
    	try {
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOf(pageTitle));
		} catch (Exception e) {

		}
		
		return pageTitle.isDisplayed();
	}
}
