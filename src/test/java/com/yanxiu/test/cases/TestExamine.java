package com.yanxiu.test.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.yanxiu.common.ScreenshotUtil;
import com.yanxiu.test.TestMethodCapture;
import com.yanxiu.test.TestngRetry;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

@Listeners(value = TestMethodCapture.class)
public class TestExamine extends BaseCase {

	private Logger log = Logger.getLogger(TestExamine.class);

	@BeforeMethod
	public void setUpExamine() throws UnsupportedEncodingException {
		mocoServer.response("login.json", "/login.json");
		mocoServer.response("getEditUserInfo.json", "/getEditUserInfo");
		mocoServer.response("trainlist.json", "/trainlist");
		mocoServer.response("taskList.json", "/taskList");
		String jsonFile = "examine.json";
		String requestUri = "/examine";
		mocoServer.response(jsonFile, requestUri);

		app.loginPage().loginWithDefaultUser();

		container = app.homePage().getContainer();
	}

	// @Test(groups="BVT",retryAnalyzer=TestngRetry.class)
	@Test(groups = "BVT", retryAnalyzer = TestngRetry.class)
	public void testScoreDetail() throws InterruptedException, IOException {

		// String jsonFile = "examine.json";
		// String body = CommonUtil.getJSONObjectFromFile(jsonFile);
		// log.info(body);
		// server.request(by(uri("/examine"))).response(body);
        log.info("start to run test case:"+TestMethodCapture.getMethodName());
		app.examinPage().checkScoreDetai();
		Assert.assertTrue(app.examinPage().isScoreDetailPageLoaded());
		Assert.assertTrue(app.examinPage().currentActivityIsScoreDetailActivity());

		Assert.assertEquals(app.examinPage().getTotalScore(), "42.23");
		Assert.assertEquals(app.examinPage().getbingerScore(), "32.23");
		Assert.assertEquals(app.examinPage().getextraScore(), "10.0");
		for (int i = 0; i < 3; i++) {

			app.examinPage().ScrollDownPage();

		}
		Thread.sleep(2000);
		String fileName = TestMethodCapture.getMethodName().concat(".png");
		takeScreenShotAndAssert(fileName);
		

	}

	// @Test(groups="BVT",retryAnalyzer=TestngRetry.class)
	@Test(groups = "BVT")
	public void testScoreSummary() throws InterruptedException, IOException {
		log.info("start to run test case:"+TestMethodCapture.getMethodName());
		Assert.assertEquals(app.examinPage().getTotalScore(), "42.23");
		Assert.assertEquals(app.examinPage().getTotalBounds(), "137");

		for (int i = 0; i < 4; i++) {
			pageDown();
			
			String fileName = TestMethodCapture.getMethodName().concat(i + ".png");
			takeScreenShotAndAssert(fileName);
		

		}

	}

	@Test
	public void testCollapseStage() throws IOException, InterruptedException {
		pageDown();
		String fileName;
		
		app.examinPage().tapToCollapseOrExpande();
		
		fileName = TestMethodCapture.getMethodName().concat("1.png");
		takeScreenShotAndAssert(fileName);
		
		app.examinPage().tapToCollapseOrExpande();
	
		fileName = TestMethodCapture.getMethodName().concat("0.png");
		takeScreenShotAndAssert(fileName);
		
		
	}
	
	@Test
	public void testTapHomeworkItem() throws IOException, InterruptedException{
		String jsonFile = "homeworkList.json";
		String requestUri = "/homeworkList";
		mocoServer.response(jsonFile, requestUri);
		
		
		app.examinPage().getGroupHomework().click();
		app.homeworkPage().tapKnownButton();
		takeScreenShotAndAssert(TestMethodCapture.getMethodName().concat(".png"));
		app.homePage().pressBackButton();
		
		app.examinPage().getHomework().click();
		Thread.sleep(2000);
		takeScreenShotAndAssert(TestMethodCapture.getMethodName().concat(".png"));
	}
	
	@Test
	public void testTapCourseItem() throws IOException, InterruptedException{
		
		String jsonFile = "myCourseList.json";
		String requestUri = "/myCourseList";
		mocoServer.responseWithPlainText(jsonFile, requestUri);
		
		app.examinPage().getCourse().click();
		Thread.sleep(4000);
		takeScreenShotAndAssert(TestMethodCapture.getMethodName().concat(".png"));
	}
	

}
