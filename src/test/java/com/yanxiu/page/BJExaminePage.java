package com.yanxiu.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BJExaminePage extends PageBase{

	public BJExaminePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	private MobileElement tv_technology_literacy;
	private MobileElement tv_special_topic;
	private MobileElement tv_synthesis;
	private MobileElement tv_professional_development;
	private MobileElement tv_case;
	
	private List<MobileElement> courses = Arrays.asList(tv_technology_literacy,tv_special_topic,tv_synthesis,tv_professional_development,tv_case);
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'活动')]")
	private MobileElement activities;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'作业')]")
	private MobileElement homework;

	
	
	public void tapActivities(){
		waitForElementVisible(activities);
		activities.click();
	}
	
	public void tapHomework(){
		waitForElementVisible(homework);
		homework.click();
	}
	
	public void tapCourse(int index) throws InterruptedException{
		MobileElement course = courses.get(index);
		waitForElementVisible(course);
		course.click();
		Thread.sleep(4000);
	}
}
