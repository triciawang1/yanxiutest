package com.yanxiu.test.cases;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestSiderbar extends BaseCase {

	@Test
	public void testHotpot(){
		app.loginPage().loginWithDefaultUser();
		app.mainPage().gotoSiderbar();
		app.siderbarPage().enterHotPotPage();
		Assert.assertTrue(app.hotpotPage().isPageLoaded());
		app.mainPage().pressBackButton();
	}
}
