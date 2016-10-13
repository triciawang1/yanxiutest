package com.yanxiu.test.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class TestSiderbar extends BaseCase {

	@BeforeMethod
	public void setUp(){
		app.loginPage().loginWithDefaultUser();
		app.mainPage().gotoSiderbar();
	}
	
	@Test(groups = "BVT")
	public void testHotpotEntrance(){

		app.siderbarPage().enterHotPotPage();
		Assert.assertTrue(app.hotpotPage().isPageLoaded());
		app.mainPage().pressBackButton();
	}
	
	@Test(groups = "BVT")
	public void testResourceEntrance(){

		app.siderbarPage().enterResourcePage();
		Assert.assertTrue(app.hotpotPage().isPageLoaded());
		app.mainPage().pressBackButton();
	}
}
