package com.yanxiu.page;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageBase {
	
	@AndroidFindBy(id = "com.yanxiu.gphone.training.teacher:id/edt_login_number")
	@iOSFindBy(className = "UIATextField")
	private MobileElement username_text;
	
	@AndroidFindBy(id = "com.yanxiu.gphone.training.teacher:id/edt_login_password")
	@iOSFindBy(className = "UIASecureTextField")
	private MobileElement password_text;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'登录')]")
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIAScrollView[1]/UIAButton[3]")
	private MobileElement loginButton;
	
	@AndroidFindBy(id="com.yanxiu.gphone.training.teacher:id/img_left")
	@iOSFindBy(xpath="//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
	private MobileElement avatorImage;
	
	public LoginPage(AppiumDriver<MobileElement> driver){
		super(driver);		
	}

	public void login(String username, String password) {
		username_text.sendKeys(username);
		password_text.sendKeys(password);
		loginButton.click();
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(avatorImage));
		} catch (Exception e) {

		}
	}
    
    public boolean isAvatorDisplayed(){
    	return avatorImage.isDisplayed();
    }
    
    public boolean isLoginButtonDisplayed(){
    	return loginButton.isDisplayed();
    }
	
    public void loginWithDefaultUser(){
    	login("XY02735506@yanxiu.com","123456");
    }
	
	

}
