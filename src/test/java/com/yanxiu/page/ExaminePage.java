package com.yanxiu.page;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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
	private MobileElement tv_total_bounds;
	private MobileElement tv_binger_score;
	private MobileElement tv_extra_score;

	private MobileElement scrollView;
	
	private MobileElement iv_indicator;
	
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
	
	public String getTotalBounds(){
		waitForElementVisible(tv_total_bounds);
		return tv_total_bounds.getText();
	}
	
	public void ScrollDownPage(){
		scrollView.swipe(SwipeElementDirection.UP, 100, 100, 500);
	}
	
	public String getbingerScore(){
		return tv_binger_score.getText();
	}
	
	public String getextraScore(){
		return tv_extra_score.getText();
	}
	
	public void tapToCollapseOrExpande() throws InterruptedException{
		iv_indicator.click();
		Thread.sleep(2000);
	}
	
}
