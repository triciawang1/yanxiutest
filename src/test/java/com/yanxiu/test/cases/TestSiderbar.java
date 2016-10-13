package com.yanxiu.test.cases;

import org.testng.annotations.Test;

public class TestSiderbar extends BaseCase {

	@Test
	public void testHotpot(){
		app.loginPage().loginWithDefaultUser();
		app.siderbarPage().enterHotPotPage();
	}
}
