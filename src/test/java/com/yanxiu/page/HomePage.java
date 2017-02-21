package com.yanxiu.page;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.internal.Coordinates;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends PageBase{

	public HomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	private MobileElement container;
	
	@AndroidFindBy(id="tv_tab_exam")
	private MobileElement tabExamine;
	
	@AndroidFindBy(id="tv_tab_task")
	private MobileElement tabTask;
	
	@AndroidFindBy(id="tv_tab_notice")
	private MobileElement tabNotice;
	
	@AndroidFindBy(id="tv_tab_bulletin")
	private MobileElement tabBulletin;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"作业\")")
	private MobileElement homeWorkItem;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"课程\")")
	private MobileElement course;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"活动\")")
	private MobileElement activity;
	
	public void tapTask(){
		tabTask.click();
	}
	public void tapNotice() throws InterruptedException{
		tabNotice.click();
		Thread.sleep(2000);
	}
	public void tapBulletin() throws InterruptedException{
		tabBulletin.click();
		Thread.sleep(2000);
	}
	
	public void enterHomeworkPage(){
		homeWorkItem.click();
	}
	
	public void enterCoursePage() throws InterruptedException{
		tabTask.click();
		course.click();
		Thread.sleep(4000);
	}

	public Rectangle getContainer(){

		return new Rectangle(container.getLocation(),container.getSize());
	}
	
	private MobileElement tv_title;
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"安徽合肥\")")
	private MobileElement projectName;
	
	public void switchTo15(){
		tv_title.click();
		projectName.click();
	}
	
	public void enterActivityPage() throws InterruptedException{
		tabTask.click();
		activity.click();
		Thread.sleep(4000);
	}

}
