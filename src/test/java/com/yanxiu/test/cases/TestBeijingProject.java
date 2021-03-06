package com.yanxiu.test.cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.yanxiu.common.MocoServer;
import com.yanxiu.test.MocoServerConfig;
import com.yanxiu.test.MocoServerConfigListener;
import com.yanxiu.test.TakeScreenshotAndAssert;
import com.yanxiu.test.TestMethodCapture;

//@Listeners(value = TestMethodCapture.class)
@Listeners({TestMethodCapture.class,MocoServerConfigListener.class})
public class TestBeijingProject extends BaseCase{
	private Logger log = Logger.getLogger(TestBeijingProject.class);
	@BeforeMethod
	public void setUpBJProject() throws UnsupportedEncodingException{
		log.info("setUpBJProject");
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
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="courselist.json",requestUri="/courselist")
	public void testSwitchCourseFiltration() throws InterruptedException{
		app.homePage().enterCoursePage();
		app.coursePage().switchSegment();
		app.coursePage().switchSubject();
		app.coursePage().switchStage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="courselist.json",requestUri="/courselist")
	public void testSwitchCourseFiltration2() throws InterruptedException{
		app.homePage().enterCoursePage();
		app.coursePage().switchStage();
		app.coursePage().switchSubject2();
		app.coursePage().switchSegment2();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="homeworkInfo.json",requestUri="/homeworkInfo")
	public void testHomworkFromTask() throws InterruptedException{
		app.homePage().tapTask();
		app.homePage().enterHomeworkPage();
		app.homeworkPage().tapKnownButton();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactives.json",requestUri="/actives")
	public void testActivitiesFromTask() throws InterruptedException, UnsupportedEncodingException{
		MocoServer.response("condition.json", "/condition");
		app.homePage().enterActivityPage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactives.json",requestUri="/actives")
	public void testSwitchActivityFiltration() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("condition.json", "/condition");
		app.homePage().enterActivityPage();

		app.activityPage().switchSegment();
		app.activityPage().switchSubject();
		app.activityPage().switchStage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactives.json",requestUri="/actives")
	public void testSwitchActivityFiltration2() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("condition.json", "/condition");
		app.homePage().enterActivityPage();

		app.activityPage().switchStage();
		app.activityPage().switchSubject2();
		app.activityPage().switchSegment2();
	}
	
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactive.json",requestUri="/active")
	public void testActivityDetail() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("bjactives.json", "/actives");
		MocoServer.response("condition.json", "/condition");
		app.homePage().enterActivityPage();
		app.activityPage().enterActiveDetailPage();
		

	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactive.json",requestUri="/active")
	public void testStepsDetail() throws InterruptedException, UnsupportedEncodingException{
		MocoServer.response("bjactives.json", "/actives");
		MocoServer.response("condition.json", "/condition");
		app.homePage().enterActivityPage();
		app.activityPage().enterActiveDetailPage();
		app.activityPage().enterStepDetailPage();
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactive.json",requestUri="/active")
	public void testReplyList() throws InterruptedException, UnsupportedEncodingException{
		MocoServer.response("bjactives.json", "/actives");
		MocoServer.response("condition.json", "/condition");
		MocoServer.response("replies.json", "/replies");
		
		app.homePage().enterActivityPage();
		app.activityPage().enterActiveDetailPage();
		app.activityPage().enterStepDetailPage();
		app.activityPage().enterReplyListPage();
		
	}
	
	@Test
	@TakeScreenshotAndAssert
	@MocoServerConfig(responseJsonFile="bjactive.json",requestUri="/active")
	public void testPostReply() throws UnsupportedEncodingException, InterruptedException{
		MocoServer.response("bjactives.json", "/actives");
		MocoServer.response("condition.json", "/condition");
		MocoServer.response("replies.json", "/replies");
		MocoServer.response("reply.json", "/reply");
		
		app.homePage().enterActivityPage();
		app.activityPage().enterActiveDetailPage();
		app.activityPage().enterStepDetailPage();
		app.activityPage().enterReplyListPage();
		app.activityPage().postReply();
	}
	
	@Test
	@TakeScreenshotAndAssert
	public void testBJBriefList() throws InterruptedException{
		app.homePage().tapBulletin();
	}
	
	
	@Test
	@TakeScreenshotAndAssert
	public void testBJNoticeList() throws InterruptedException{
		app.homePage().tapNotice();
	}
}
