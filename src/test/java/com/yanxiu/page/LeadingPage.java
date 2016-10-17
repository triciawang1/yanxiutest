package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;

public class LeadingPage extends PageBase{
	
	@AndroidFindBy(id="vp_welcome")
	@iOSFindBy(className="UIAScrollView")
	private MobileElement scrollView;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'开始体验')]")
	@iOSFindBy(className="UIAButton")
	private MobileElement exButton;
	
	public LeadingPage(AppiumDriver<MobileElement> driver){
		super(driver);
	}
	
	public void skipLeadingPage(){
		waitForElementVisible(scrollView);
		for(int i=0;i<2;i++){
			
			scrollView.swipe(SwipeElementDirection.LEFT, 100, 100, 500);
		}
		exButton.click();
	}

}
