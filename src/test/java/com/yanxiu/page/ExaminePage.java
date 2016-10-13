package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ExaminePage extends PageBase {
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]")
	private MobileElement summaryPart;
	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
	private MobileElement detailPageTitle;
	
	public ExaminePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScoreDetai(){
		summaryPart.click();
	}
	
	public boolean isScoreDetailPageLoaded(){
		return detailPageTitle.getText().equals("成绩详情");
	}

	
}
