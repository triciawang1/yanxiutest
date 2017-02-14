package com.yanxiu.test.cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

//@Listeners(value = TestMethodCapture.class)
@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestBeijingProject extends BaseCase{

	@BeforeMethod
	public void setUpBJProject() throws UnsupportedEncodingException{
		MocoServer.response("bjgetEditUserInfo.json", "/getEditUserInfo");
		MocoServer.response("bjlogin.json", "/login.json");
		MocoServer.response("bjtrainlist.json", "/trainlist");
		MocoServer.response("checkedMobileUser.json", "/checkedMobileUser");
		MocoServer.response("bjtaskList.json", "/taskList");
		String jsonFile = "bjexamine.json";
		String requestUri = "/examine";
		MocoServer.response(jsonFile, requestUri);
		app.loginPage().loginWithBeijingAccount();
		container = app.homePage().getContainer();
	}
	
	@Test
	public void testBasicInfo() throws InterruptedException, IOException{
		for (int i = 0; i < 2; i++) {
			String fileName = TestMethodCapture.getMethodName().concat(i + ".png");
			takeScreenShotAndAssert(fileName);
			app.examinPage().scrollDownPageForBJProject();
		

		}
	}
	
	@Test
	public void testTapBJCourse() throws IOException, InterruptedException{
		String jsonFile = "courselist.json";
		String requestUri = "/courselist";
		MocoServer.response(jsonFile, requestUri);
		
		for(int i=0;i<5;i++){
			if(i==4){
				app.examinPage().scrollDownPageForBJProject();
			}
			app.bjexaminePage().tapCourse(i);
			String fileName = TestMethodCapture.getMethodName().concat(i + ".png");
			takeScreenShotAndAssert(fileName);
			app.coursePage().pressBackButton();
		}
		app.examinPage().scrollDownPageForBJProject();

	}
	
	@Test
	public void testTapBJActives() throws IOException, InterruptedException{
		String jsonFile = "bjactives.json";
		String requestUri = "/actives";
		MocoServer.response(jsonFile, requestUri);
		MocoServer.response("condition.json", "/condition");
		
		app.examinPage().scrollDownPageForBJProject();
		app.bjexaminePage().tapActivities();
		String fileName = TestMethodCapture.getMethodName().concat(".png");
		takeScreenShotAndAssert(fileName);
	}
	
	@Test
	@MocoServerConfig(responseJsonFile="homeworkInfo.json",requestUri="/homeworkInfo")
	public void testTapHomework() throws InterruptedException, IOException{
		
		app.examinPage().scrollDownPageForBJProject();
		
		app.bjexaminePage().tapHomework();
		app.homeworkPage().tapKnownButton();
		String fileName = TestMethodCapture.getMethodName().concat(".png");
		takeScreenShotAndAssert(fileName);
	
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="courselist.json",requestUri="/courselist")
	public void testCourseFromTask() throws InterruptedException{
		app.homePage().enterCoursePage();
	}
}
