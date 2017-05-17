package com.yanxiu.test.cases;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestDeyangProject extends BaseCase{
	@BeforeMethod
	public void setUpExamine() throws UnsupportedEncodingException {
		MocoServer.response("login.json", "/login.json");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("trainlistfordeyang.json", "/trainlist");
		MocoServer.response("taskList.json", "/taskList");

		app.loginPage().login("xy03117231", "123456");

		container = app.homePage().getContainer();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile={"myCourseListdeyang1.json","myCourseListdeyang2.json"},requestUri="/myCourseList")
	public void testDeyangCourseList() throws InterruptedException{
		app.homePage().enterCoursePage();
	}
}
