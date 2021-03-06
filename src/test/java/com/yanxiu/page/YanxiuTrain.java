package com.yanxiu.page;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class YanxiuTrain {

	private AppiumDriver<MobileElement> driver;
	
	public YanxiuTrain(AppiumDriver<MobileElement> driver){
		this.driver = driver;
	}
	
	
	
	public LoginPage loginPage(){
		return new LoginPage(driver);
	}
	public LeadingPage leadingPage(){
		return new LeadingPage(driver);
	}
	public ExaminePage examinPage(){
		return new ExaminePage(driver);
	}
	public SiderbarPage siderbarPage(){
		return new SiderbarPage(driver);
	}
	public PageBase mainPage(){
		return new PageBase(driver);
	}
	public HotpotPage hotpotPage(){
		return new HotpotPage(driver);
	}
	public ResourcePage resourcePage(){
		return new ResourcePage(driver);
	}
	public HomePage homePage(){
		return new HomePage(driver);
	}
	public HomeworkPage homeworkPage(){
		return new HomeworkPage(driver);
	}
	public BJExaminePage bjexaminePage(){
		return new BJExaminePage(driver);
	}
	public CoursePage coursePage(){
		return new CoursePage(driver);
	}
	
	public ActivityPage activityPage(){
		return new ActivityPage(driver);
	}
	
	public WorkShopPage workshopPage(){
		return new WorkShopPage(driver);
	}
	public RolePage rolePage(){
		return new RolePage(driver);
	}
	public LayerPage layerPage(){
		return new LayerPage(driver);
	}
}
