package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HotpotPage extends PageBase {

	public HotpotPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@iOSFindBy(xpath="//*[contains(@name,'热点')]")
	private MobileElement pageTitle;
	
	public boolean isPageLoaded(){
		return pageTitle.isDisplayed();
	}
}
