package com.yanxiu.test.cases;

import org.testng.Assert;
import org.testng.annotations.Test;



public class TestExamine extends BaseCase{

	@Test(groups="BVT")
	public void testScoreDetail(){
		app.loginPage().loginWithDefaultUser();
		app.examinPage().checkScoreDetai();
		Assert.assertTrue(app.examinPage().isScoreDetailPageLoaded());
	}
}
