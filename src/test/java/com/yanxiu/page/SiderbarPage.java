package com.yanxiu.page;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SiderbarPage extends PageBase{
	public SiderbarPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(id="tv_hotspot")
	@iOSFindBy(xpath="//UIATableCell[contains(@name,'热点')]")
	private MobileElement hotPot;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"资源\")")
	@iOSFindBy(xpath="//UIATableCell[contains(@name,'资源')]")
	private MobileElement resource;
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"我的工作坊\")")
	private MobileElement myWorkShop;
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"消息动态\")")
	private MobileElement messages;
	
	@AndroidFindAll(@AndroidFindBy(id = "tv_news_feed_content"))
	private List<MobileElement> messageContents;
	@AndroidFindAll(@AndroidFindBy(id = "tv_news_feed_time"))
	private List<MobileElement> timeOfMessages;
	
	public String getTitleOfLatestMessage(){
		return messageContents.get(0).getText();
	}
	public String getTimeofLatestMessage(){
		return timeOfMessages.get(0).getText();
	}
	
	private MobileElement ll_setting;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"退出登录\")")
	private MobileElement logoutButton;
	
	//左侧栏个人中心头像
	private MobileElement img_user_avatar;
	
	//个人中心页面头像
	private MobileElement iv_profile_portrait;
	
	public void enterHotPotPage() throws InterruptedException{
		hotPot.click();
		Thread.sleep(3000);
	}
	
	public void enterResourcePage() throws InterruptedException{
		resource.click();
		Thread.sleep(2000);
	}
	
	public void enterMyWorkShopPage() throws InterruptedException{
		myWorkShop.click();
		Thread.sleep(3000);
	}
	
	public void enterMessagesPage() throws InterruptedException{
		messages.click();
		Thread.sleep(3000);
	}
	public void enterUserInfoPage(){
		img_user_avatar.click();
		waitForElementVisible(iv_profile_portrait);
	}
	
	public void enterSettingPage(){
		ll_setting.click();
		waitForElementVisible(logoutButton);
	}
	
	public void logout(){
		logoutButton.click();
	}
	//我是学员
	private MobileElement rl_normal;
	//我是坊主
	private MobileElement rl_leader;
	
	public void switchToStudent() throws InterruptedException{
		rl_normal.click();
		Thread.sleep(2000);
	}
	
	public void switchToFangzhu() throws InterruptedException{
		rl_leader.click();
		Thread.sleep(2000);
	}
}
