package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SiderbarPage extends PageBase{
	public SiderbarPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@iOSFindBy(xpath="//UIATableCell[contains(@name,'热点')]")
	private MobileElement hotPot;
	
	public void enterHotPotPage(){
		hotPot.click();
	}
}
