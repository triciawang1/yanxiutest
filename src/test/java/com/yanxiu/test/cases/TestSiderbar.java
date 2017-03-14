package com.yanxiu.test.cases;

import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;


@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestSiderbar extends BaseCase {

	@BeforeMethod
	public void setupSiderbar() throws UnsupportedEncodingException{
		MocoServer.response("login.json", "/login.json");
		MocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("trainlist16.json", "/trainlist");
		MocoServer.response("taskList.json", "/taskList");
	
		MocoServer.response("examine.json", "/examine");
		
		app.loginPage().loginWithDefaultUser();
		container = app.homePage().getContainer();
		app.mainPage().gotoSiderbar();
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="getHotspots.json",requestUri="/getHotspots")
	public void testHotpotEntrance() throws InterruptedException{

		app.siderbarPage().enterHotPotPage();

	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="stagecataelelist.json",requestUri="/stagecataelelist")
	@MocoServerConfig(responseJsonFile="search.json",requestUri="/search")
	@MocoServerConfig(responseJsonFile="myResourceList.json",requestUri="/myResourceList")
	public void testResourceEntrance() throws InterruptedException{

		app.siderbarPage().enterResourcePage();

	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="stagecataelelist.json",requestUri="/stagecataelelist")
	@MocoServerConfig(responseJsonFile="search.json",requestUri="/search")
	@MocoServerConfig(responseJsonFile="myResourceList.json",requestUri="/myResourceList")
	public void testFiteration() throws InterruptedException{
		app.siderbarPage().enterResourcePage();
		app.resourcePage().setFiterOptions();
	}
	
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="stagecataelelist.json",requestUri="/stagecataelelist")
	@MocoServerConfig(responseJsonFile="search.json",requestUri="/search")
	@MocoServerConfig(responseJsonFile="myResourceList.json",requestUri="/myResourceList")
	public void testMyResource() throws InterruptedException{
		app.siderbarPage().enterResourcePage();
		app.resourcePage().tapMyResource();
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="stagecataelelist.json",requestUri="/stagecataelelist")
	@MocoServerConfig(responseJsonFile="search.json",requestUri="/search")
	@MocoServerConfig(responseJsonFile="myResourceList.json",requestUri="/myResourceList")
	public void testSearchResource() throws InterruptedException{
		app.siderbarPage().enterResourcePage();
		app.resourcePage().searchResouce();
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="cooperatelist.json",requestUri="/cooperate/list")
	public void testMyWorkShop() throws InterruptedException{
		app.siderbarPage().enterMyWorkShopPage();
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="cooperatelist.json",requestUri="/cooperate/list")
	@MocoServerConfig(responseJsonFile="detail.json",requestUri="/detail")
	@MocoServerConfig(responseJsonFile="memberlist.json",requestUri="/memberlist")
	public void testWorkShopDetail() throws InterruptedException{
		app.siderbarPage().enterMyWorkShopPage();
		app.workshopPage().enterEachWorkShop();
		
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="getMessages.json",requestUri="/getMessages")
	public void testMessages() throws InterruptedException{
		app.siderbarPage().enterMessagesPage();
	}
	
	@Test(groups = "BVT")
	@TakeScreenshotAndAssert
	public void testUserInfo(){
		app.siderbarPage().enterUserInfoPage();
	}
	
	
}
