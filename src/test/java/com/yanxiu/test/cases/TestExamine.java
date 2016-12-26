package com.yanxiu.test.cases;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.yanxiu.common.ScreenshotUtil;
import com.yanxiu.test.TestMethodCapture;
import com.yanxiu.test.TestngRetry;

import io.appium.java_client.MobileElement;
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


@Listeners(value=TestMethodCapture.class)
public class TestExamine extends BaseCase{

	private Logger log = Logger.getLogger(TestExamine.class);
//	@Test(groups="BVT",retryAnalyzer=TestngRetry.class)
	@Test(groups="BVT",retryAnalyzer=TestngRetry.class)
	public void testScoreDetail() throws UnsupportedEncodingException, InterruptedException{
	
//		String jsonFile = "examine.json";
//		String body = CommonUtil.getJSONObjectFromFile(jsonFile);
//        log.info(body);
//		server.request(by(uri("/examine"))).response(body);
		
		app.loginPage().loginWithDefaultUser();
		app.examinPage().checkScoreDetai();
		Assert.assertTrue(app.examinPage().isScoreDetailPageLoaded());
		Assert.assertTrue(app.examinPage().currentActivityIsScoreDetailActivity());
		app.examinPage().pressBackButton();
		
		
	}
	
//	@Test(groups="BVT",retryAnalyzer=TestngRetry.class)
	@Test(groups="BVT")
	public void testScoreSummary() throws InterruptedException, IOException{
		String jsonFile = "examine.json";
		String requestUri = "/examine";
		mocoServer.response(jsonFile, requestUri);

		
		app.loginPage().loginWithDefaultUser();
		
		Assert.assertEquals(app.examinPage().getTotalScore(), "42.23");
		Assert.assertEquals(app.examinPage().getTotalBounds(), "137");
		
////		TouchAction tAction = new TouchAction(driver);
//		takeScreenShot(TestMethodCapture.getMethodName().concat(".png"));
//		app.examinPage().scrollUpSummaryPage();
////		((AndroidDriver<MobileElement>) driver).pressKeyCode(92);
////		driver.swipe(10,1870,10,360,1000);
////		tAction.press(120,360).moveTo(1060,1610).release().perform();
//		takeScreenShot(TestMethodCapture.getMethodName().concat("1.png"));
////		((AndroidDriver<MobileElement>) driver).pressKeyCode(92);
//		app.examinPage().scrollUpSummaryPage();
////		driver.swipe(10,1870,10,360,1000);
////		tAction.press(20,360).moveTo(1060,1610).release().perform();
//		takeScreenShot(TestMethodCapture.getMethodName().concat("2.png"));
		takeScreenShot(TestMethodCapture.getMethodName().concat("0.png"));
	


		Assert.assertFalse(ScreenshotUtil.hasDiff(TestMethodCapture.getMethodName().concat("0.png"), app.homePage().getContainer()));
		
		for(int i=1;i<4;i++){
			app.examinPage().scrollUpSummaryPage();
			String fileName = TestMethodCapture.getMethodName().concat(i+".png");
			takeScreenShot(fileName);
			Assert.assertFalse(ScreenshotUtil.hasDiff(fileName, app.homePage().getContainer()));
		}
		

		
	}
	
	public void testCollapseStage(){
		
	}
}
