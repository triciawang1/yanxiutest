package com.yanxiu.test.cases;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.yanxiu.common.CommonUtil;
import com.yanxiu.test.TestngRetry;

import static com.github.dreamhead.moco.Moco.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;



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
	public void testScoreSummary(Method method) throws InterruptedException, IOException{
		String jsonFile = "examine.json";
		String requestUri = "/examine";
		mocoServer.response(jsonFile, requestUri);

		
		app.loginPage().loginWithDefaultUser();
		
		Assert.assertEquals(app.examinPage().getTotalScore(), "42.23");
		Assert.assertEquals(app.examinPage().getTotalBounds(), "137");
		
		
		takeScreenShot(method.getName().concat(".png"));
		
	}
}
