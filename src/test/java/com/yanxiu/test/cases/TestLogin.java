package com.yanxiu.test.cases;




import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;





public class TestLogin extends BaseCase{


	@Test(groups = "BVT")
	public void testLogin() throws InterruptedException, IOException {				
		app.loginPage().login("XY02735506@yanxiu.com","123456");
		Assert.assertTrue(app.loginPage().isAvatorDisplayed());
//		WebElement avatorImage = ElementHelper.waitForElement(driver,By.id("com.yanxiu.gphone.training.teacher:id/img_left"));
		
//		Assert.assertTrue(avatorImage.isDisplayed());
	}
					
	
	@Test
	public void testLoginWithIncorrectPass(){
		
		app.loginPage().login("XY02735506@yanxiu.com","123457");
		app.loginPage().isLoginButtonDisplayed();
		
//		AndroidElement avatorImage = ElementHelper.waitForElement(driver,By.id("com.yanxiu.gphone.training.teacher:id/img_left"));
		
//		Assert.assertFalse(avatorImage.isDisplayed());
		
//		AndroidDriver a_driver = (AndroidDriver)driver;
//		String activity = a_driver.currentActivity();
//		Assert.assertEquals(activity, "com.yanxiu.yxtrain_android.activity.login.LoginActivity");
	}
	
	@Test
	public void testLoginWithNoexistUser(){
	
		app.loginPage().login("XY0273550@yanxiu.com","123457");
		app.loginPage().isLoginButtonDisplayed();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		AndroidDriver a_driver = (AndroidDriver)driver;
//		String activity = a_driver.currentActivity();
//		Assert.assertEquals(activity, "com.yanxiu.yxtrain_android.activity.login.LoginActivity");
	}


	
}
