package com.yanxiu.test.cases;



import java.util.List;

import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yanxiu.common.ElementHelper;


public class TestHomework extends BaseCase {

	@BeforeMethod
	public void openHomework(){
		app.loginPage().loginWithDefaultUser();
		app.homePage().tapTask();
		app.homePage().enterHomeworkPage();
		app.homeworkPage().tapKnownButton();
	}
	
	@Test(groups = "BVT")
	public void testHomeworkList(){
		
		app.homeworkPage().tapAllHomeworkItem();
	}
	
	@Test(groups = "BVT")
	public void testNormalHomework(){
		
		app.homeworkPage().findNormalHomework();
		Assert.assertTrue(app.homeworkPage().isNormalHomeworkShownCorrectly());
	}
	@Test(groups = "BVT")
	public void testVideoHomework(){
		app.homeworkPage().findVideoHomework();
		Assert.assertTrue(app.homeworkPage().isVideoHomeworkShownCorrectly());
	}
}
