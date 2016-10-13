package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

//	@iOSFindBy(tagName="返回")
//	private MobileElement backButton;
	
	protected AppiumDriver<MobileElement> driver;
	public PageBase(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
	}
	
	public void pressBackButton(){
//		backButton.click();
	}
}
