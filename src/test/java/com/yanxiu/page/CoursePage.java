package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CoursePage extends PageBase{

	public CoursePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'小学')]")
	private MobileElement primarySchool;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'初中')]")
	private MobileElement juniorSchool;
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'高中')]")
	private MobileElement highSchool;
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'学科')]")
	private MobileElement subject;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'物理')]")
	private MobileElement physics;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'数学')]")
	private MobileElement math;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'技术素养类')]")
	private MobileElement tech;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'案例')]")
	private MobileElement casetype;
	
	public void switchSegment(){
		primarySchool.click();
		highSchool.click();
		
	}
	public void switchSubject(){
		subject.click();
		physics.click();
		
	}
	
	public void switchStage(){
		tech.click();
		casetype.click();
	}
	
	public void switchSubject2(){
		subject.click();
		math.click();
	}
	public void switchSegment2(){
		primarySchool.click();
		juniorSchool.click();
	}
}
