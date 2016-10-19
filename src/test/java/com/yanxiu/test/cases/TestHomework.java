package com.yanxiu.test.cases;



import java.util.List;

import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yanxiu.common.ElementHelper;


public class TestHomework extends BaseCase {

	@BeforeMethod
	public void login(){
		app.loginPage().loginWithDefaultUser();
	}
	
	@Test(groups = "BVT")
	public void testHomeworkList(){
//		WebElement taskTab = ElementHelper.waitForElement(driver, By.id("com.yanxiu.gphone.training.teacher:id/tv_tab_task"));
//		
//		List<WebElement> taskList = ElementHelper.findsById(driver,"com.yanxiu.gphone.training.teacher:id/child_tv");
//		
//		for(int i=0;i<taskList.size();i++){
//			taskList.get(i).click();
//			driver.navigate().back();
//		}
//		
//		taskTab.click();
//		ElementHelper.waitForElement(driver, By.xpath("//android.widget.TextView[contains(@text,'��ҵ')]")).click();
//		ElementHelper.waitForElement(driver, By.id("com.yanxiu.gphone.training.teacher:id/tv_ilknow")).click();
		app.homePage().tapTask();
		app.homePage().enterHomeworkPage();
		app.homeworkPage().tapKnownButton();
		app.homeworkPage().tapAllHomeworkItem();

	}
}
