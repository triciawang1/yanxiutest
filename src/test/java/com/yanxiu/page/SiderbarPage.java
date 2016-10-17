package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SiderbarPage extends PageBase{
	public SiderbarPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(id="tv_hotspot")
	@iOSFindBy(xpath="//UIATableCell[contains(@name,'热点')]")
	private MobileElement hotPot;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"资源\")")
	@iOSFindBy(xpath="//UIATableCell[contains(@name,'资源')]")
	private MobileElement resource;
	
	public void enterHotPotPage(){
		hotPot.click();
	}
	
	public void enterResourcePage(){
		resource.click();
	}
}
