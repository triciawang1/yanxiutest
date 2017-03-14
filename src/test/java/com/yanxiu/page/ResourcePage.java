package com.yanxiu.page;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class ResourcePage extends PageBase{

	public ResourcePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(uiAutomator="new UiSelector().text(\"全部资源\")")
	@iOSFindBy(xpath="//UIAButton[contains(@name,'全部资源')]")
	private MobileElement pageTitle;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"我的资源\")")
	@iOSFindBy(xpath="//UIAButton[contains(@name,'我的资源')]")
	private MobileElement myResource;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"默认排序\")")
	private MobileElement sortion;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"使用最多\")")
	private MobileElement mostuse;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"年级\")")
	private MobileElement grade;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"小学一年级\")")
	private MobileElement yinianji;
	

	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"学科\")")
	private MobileElement subject;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"数学\")")
	private MobileElement math;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"版本\")")
	private MobileElement version;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"鲁教版\")")
	private MobileElement lujiaoban;
	
    //	搜索按钮
	private MobileElement title_right;
	
	//搜索输入框
	private MobileElement et_serach;
	
	public boolean isPageLoaded(){
		return super.isPageLoaded(pageTitle);
	}
	
	public void setFiterOptions(){
		sortion.click();
		mostuse.click();
		grade.click();
	    yinianji.click();
	    subject.click();
	    math.click();
	    version.click();
	    lujiaoban.click();
	}
	public void tapMyResource() throws InterruptedException{
		myResource.click();
		Thread.sleep(2000);
	}
	
	public void searchResouce(){
		title_right.click();
		et_serach.sendKeys("word");
		if (driver instanceof AndroidDriver) {
			((AndroidDriver<MobileElement>) driver).pressKeyCode(66);
		}
		
	}
}
