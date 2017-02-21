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
public class TestNotice extends BaseCase {
	@BeforeMethod
	public void setNotice() throws UnsupportedEncodingException {
		MocoServer.response("login.json", "/login.json");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("trainlist.json", "/trainlist");
		MocoServer.response("taskList.json", "/taskList");
		MocoServer.response("noticeList.json", "/noticeList");
		String jsonFile = "examine.json";
		String requestUri = "/examine";
		MocoServer.response(jsonFile, requestUri);

		app.loginPage().loginWithDefaultUser();

		container = app.homePage().getContainer();
	}
	
	@Test
	@TakeScreenshotAndAssert
	public void testNoticeList() throws InterruptedException{
		app.homePage().tapNotice();
	}

}
