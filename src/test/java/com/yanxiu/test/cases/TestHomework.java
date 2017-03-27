package com.yanxiu.test.cases;



import java.io.UnsupportedEncodingException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.common.ElementHelper;
import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestHomework extends BaseCase {

	@BeforeMethod
	public void setupHomework() throws UnsupportedEncodingException{
		
		MocoServer.response("login.json", "/login.json");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("trainlist16.json", "/trainlist");
		MocoServer.response("taskList.json", "/taskList");
	
		MocoServer.response("examine.json", "/examine");
//		app.loginPage().login("JYY25392@yanxiu.com","123456");
		app.loginPage().loginWithDefaultUser();
		container = app.homePage().getContainer();
		
		
	}
	

	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="homeworkList16.json",requestUri="/homeworkList")
	public void testHomeworkList() throws UnsupportedEncodingException{
		
		gotoHomeworkList();
		
		
	}


	private void gotoHomeworkList() {
		app.homePage().tapTask();
		app.homePage().enterHomeworkPage();
		app.homeworkPage().tapKnownButton();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="homeworkList16.json",requestUri="/homeworkList")
	public void testNormalHomeworkDetail(){
		gotoHomeworkList();
		app.homeworkPage().enterHomeworkDetailPage();

	}
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testVideoHomeworkDetail() throws UnsupportedEncodingException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testRecordVideo() throws UnsupportedEncodingException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		Assert.assertTrue(app.homeworkPage().isRecordButtonShown());
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testPauseRecord() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		app.homeworkPage().pauseRecord();
		Assert.assertTrue(app.homeworkPage().isGiveupButtonShown());
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testGiveupRecord() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		app.homeworkPage().pauseRecord();
		app.homeworkPage().giveupRecord();
		Assert.assertFalse(app.homeworkPage().isGiveupButtonShown());
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testCancleGiveupRecord() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		app.homeworkPage().pauseRecord();
		app.homeworkPage().cancelGiveupRecord();
		Assert.assertTrue(app.homeworkPage().isGiveupButtonShown());
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	public void testSaveRecord() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("homeworkList16.json", "/homeworkList");
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		app.homeworkPage().pauseRecord();
		app.homeworkPage().saveRecord();
		Assert.assertEquals(app.homeworkPage().getTextUploadStatus(),"已录制未上传视频");
	}

	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo16Video.json",requestUri="/homeworkInfo")
	@MocoServerConfig(responseJsonFile="homeworkList16.json",requestUri="/homeworkList")
	@MocoServerConfig(responseJsonFile="genUploadToken.json",requestUri="/genUploadToken")
	@MocoServerConfig(responseJsonFile="listc2.json",requestUri="/listc2")
	@MocoServerConfig(responseJsonFile="cascade2.json",requestUri="/cascade2")
	@MocoServerConfig(responseJsonFile="resource.json",requestUri="/resource")
	@MocoServerConfig(responseJsonFile="newupload.json",requestUri="/newupload")
	public void testUploadVideo() throws InterruptedException{
		gotoHomeworkList();
		app.homeworkPage().enterVideoWorkDetailPage();
		app.homeworkPage().startRecord();
		app.homeworkPage().pauseRecord();
		app.homeworkPage().saveRecord();
		app.homeworkPage().uploadVideo();
		Assert.assertEquals(app.homeworkPage().getVideoTitle(),"this is title");
	}

}
