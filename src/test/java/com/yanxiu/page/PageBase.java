package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

	@iOSFindBy(xpath = "//UIAButton[contains(@name,'返回')]")
	private MobileElement backButton;

	@AndroidFindBy(id = "img_left")
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]")
	private MobileElement avatorImage;

	@AndroidFindBy(id = "notice_try_again")
	private MobileElement refreshButton;

	protected AppiumDriver<MobileElement> driver;

	public PageBase(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void pressBackButton() {
		if (driver instanceof AndroidDriver) {
			((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
		} else {
			backButton.click();
		}
	}

	public boolean isAvatorDisplayed() {
		return avatorImage.isDisplayed();
	}

	public void waitForMainPageLoaded() {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(avatorImage));
		} catch (Exception e) {

		}
	}

	public void gotoSiderbar() {
		avatorImage.click();
	}

	protected Boolean isPageLoaded(MobileElement pageTitle) {
		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(pageTitle));
		} catch (Exception e) {

		}

		return pageTitle.isDisplayed();
	}

	protected void waitForElementVisible(MobileElement element) {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {

		}
	}

	protected boolean isRefreshButtonShown() {
		try {
			return refreshButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	protected void refreshToTry() {
		if (isRefreshButtonShown()) {
			refreshButton.click();
		}
	}
	public void clickElementAfterElementDisplayed(MobileElement element){
		waitForElementVisible(element);
		element.click();
		
	}
}
