package com.yanxiu.test.cases;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestTheme extends BaseCase{
	@BeforeMethod
	public void setUpExamine() throws UnsupportedEncodingException {
		MocoServer.response("login.json", "/login.json");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("trainlistfortheme.json", "/trainlist");
		MocoServer.response("taskList.json", "/taskList");
		String jsonFile = "examine.json";
		String requestUri = "/examine";
		MocoServer.response(jsonFile, requestUri);

		app.loginPage().login("xy03117231", "123456");

		container = app.homePage().getContainer();
	}
	
	@Test
	@TakeScreenshotAndAssert
	public void testCoursewithoutTheme() throws InterruptedException{
		app.homePage().enterCoursePage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	public void testHomeworkwithoutTheme() throws InterruptedException{
		app.homePage().enterHomeworkPage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	public void testActivitywithoutTheme() throws InterruptedException{
		app.homePage().enterActivityPage();
	}
}
