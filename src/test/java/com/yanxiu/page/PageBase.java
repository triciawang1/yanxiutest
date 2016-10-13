package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

	@iOSFindBy(xpath="//UIAButton[contains(@name,'返回')]")
	private MobileElement backButton;
	
	@AndroidFindBy(id="com.yanxiu.gphone.training.teacher:id/img_left")
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
	private MobileElement avatorImage;
	
	protected AppiumDriver<MobileElement> driver;
	public PageBase(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
	}
	
	public void pressBackButton(){
		backButton.click();
	}
	
    public boolean isAvatorDisplayed(){
    	return avatorImage.isDisplayed();
    }
    
    public void waitForMainPageLoaded(){
    	try {
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(avatorImage));
		} catch (Exception e) {

		}
    }
    
    public void gotoSiderbar(){
    	avatorImage.click();
    }
    
    protected Boolean isPageLoaded(MobileElement pageTitle){
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOf(pageTitle));
		} catch (Exception e) {

		}
		
		return pageTitle.isDisplayed();
	}
	
}
