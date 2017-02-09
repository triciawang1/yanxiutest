package com.yanxiu.test.cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yanxiu.test.TestMethodCapture;

@Listeners(value = TestMethodCapture.class)
public class TestBeijingProject extends BaseCase{

	@BeforeMethod
	public void setUpBJProject() throws UnsupportedEncodingException{
		mocoServer.response("bjgetEditUserInfo.json", "/getEditUserInfo");
		mocoServer.response("bjlogin.json", "/login.json");
		mocoServer.response("bjtrainlist.json", "/trainlist");
		mocoServer.response("checkedMobileUser.json", "/checkedMobileUser");
		mocoServer.response("bjtaskList.json", "/taskList");
		String jsonFile = "bjexamine.json";
		String requestUri = "/examine";
		mocoServer.response(jsonFile, requestUri);
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
		mocoServer.response(jsonFile, requestUri);
		
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
}
