package com.yanxiu.test.cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestRole extends BaseCase {
	private Logger log = Logger.getLogger(TestRole.class);
	
	@BeforeMethod
	public void setUpTestRole() throws UnsupportedEncodingException{
		log.info("start set up for test role");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("loginForTestRole.json", "/login.json");
		MocoServer.response("trainlistForTestRole.json", "/trainlist");
		MocoServer.response("masterStat.json", "/masterStat");	
		app.loginPage().loginWithfangzhuAccount();
		container = app.homePage().getContainer();
	}
	
	@Test	
	@TakeScreenshotAndAssert
	public void testXueqing() throws IOException, InterruptedException{
		
		Assert.assertEquals(app.rolePage().getScore(), "6.25");
		for(int i=0;i<2;i++){
		app.rolePage().pageDown();
		}
		Thread.sleep(2000);
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="examineForRole.json",requestUri="/examine")
	public void testSwitchRole() throws InterruptedException{
		app.mainPage().gotoSiderbar();
		app.siderbarPage().enterUserInfoPage();
		app.siderbarPage().switchToStudent();
		Assert.assertTrue(app.rolePage().tipsEqualToExpect());
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="examineForRole.json",requestUri="/examine")
	public void testSwitchBackToFangzhu() throws InterruptedException{
		app.mainPage().gotoSiderbar();
		app.siderbarPage().enterUserInfoPage();
		app.siderbarPage().switchToStudent();
		app.mainPage().gotoSiderbar();
		app.siderbarPage().enterUserInfoPage();
		app.siderbarPage().switchToFangzhu();
		Assert.assertEquals(app.rolePage().getScore(), "6.25");
	}
	
	
	@Test
	@MocoServerConfig(responseJsonFile="getStudioList.json",requestUri="/getStudioList")
	@MocoServerConfig(responseJsonFile={"getLearningInfoList.json","getLearningInfoList2.json"},requestUri="/getLearningInfoList")
	public void testStudentList() throws InterruptedException, IOException{
		app.rolePage().enterXueqingPage();
		String fileName = TestMethodCapture.getMethodName().concat("0.png");
		takeScreenShotAndAssert(fileName);
		for(int i=0;i<5;i++){
			app.rolePage().scrollDown();
		}
		
		fileName = TestMethodCapture.getMethodName().concat("1.png");
		takeScreenShotAndAssert(fileName);
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="getStudioList.json",requestUri="/getStudioList")
	@MocoServerConfig(responseJsonFile="getLearningInfoList.json",requestUri="/getLearningInfoList")
	public void testSingleNotify() throws IOException, InterruptedException{
		app.rolePage().enterXueqingPage();
		app.rolePage().slideElementLeft();
		String fileName = TestMethodCapture.getMethodName().concat("0.png");
		takeScreenShotAndAssert(fileName);
		app.rolePage().notifyStudent();
		app.rolePage().pressBackButton();
		stopMocoServer();
		checkMessaageReceived("XY03117218@yanxiu.com", "123456","刚刚");
		
	}

	private void checkMessaageReceived(String username,String pwd,String time) throws InterruptedException {		
		app.siderbarPage().gotoSiderbar();
		app.siderbarPage().enterSettingPage();
		app.siderbarPage().logout();
		app.loginPage().login(username,pwd);
		app.siderbarPage().gotoSiderbar();
		app.siderbarPage().enterMessagesPage();
		Assert.assertEquals(app.siderbarPage().getTimeofLatestMessage(),time);
		Assert.assertEquals(app.siderbarPage().getTitleOfLatestMessage(), "坊主提醒：请尽快完成研修任务");
		app.homePage().pressBackButton();
		Thread.sleep(2000);
		app.homePage().pressBackButton();
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="getStudioList.json",requestUri="/getStudioList")
	@MocoServerConfig(responseJsonFile="getLearningInfoList.json",requestUri="/getLearningInfoList")
	public void testBatchNotify() throws InterruptedException{
		app.rolePage().enterXueqingPage();
		app.rolePage().notifyMoreStudents();
		app.rolePage().pressBackButton();
		stopMocoServer();
		checkMessaageReceived("XY03117218@yanxiu.com", "123456","刚刚");
		checkMessaageReceived("XY03117219@yanxiu.com", "123456","1分钟前");
		checkMessaageReceived("XY03117220@yanxiu.com", "123456","1分钟前");
		checkMessaageReceived("XY03117217@yanxiu.com", "123456","2分钟前");
		
	}
}
