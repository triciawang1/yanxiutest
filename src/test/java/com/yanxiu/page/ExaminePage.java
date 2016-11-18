package com.yanxiu.page;

import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ExaminePage extends PageBase {
	private Logger log = Logger.getLogger(ExaminePage.class);
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIATableView[1]/UIATableCell[1]")
	@AndroidFindBy(id="tv_total")
	private MobileElement summaryPart;
	
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
	@AndroidFindBy(id="tv_title")
	private MobileElement detailPageTitle;
	
	private MobileElement tv_total_score;
	
	
	public ExaminePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void checkScoreDetai(){
		waitForElementVisible(summaryPart);
		summaryPart.click();
	}
	
	public boolean isScoreDetailPageLoaded(){
		waitForElementVisible(detailPageTitle);
		return detailPageTitle.getText().equals("成绩详情");
	}
	public boolean currentActivityIsScoreDetailActivity(){
		log.info("-----------------------------------------------");
		log.info("current activity is:"+((AndroidDriver) driver).currentActivity());
		log.info("-----------------------------------------------");
		if(driver instanceof AndroidDriver){
			return ((AndroidDriver) driver).currentActivity().equals("com.yanxiu.yxtrain_android.activity.exam.ExamDetailActivity");
		}
		return false;
	}
	

	public String getTotalScore(){
		waitForElementVisible(tv_total_score);
		return tv_total_score.getText();
	}
	
}
