package com.yanxiu.test.cases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.yanxiu.common.AppiumServer;
import com.yanxiu.common.CommonUtil;
import com.yanxiu.common.ElementHelper;
import com.yanxiu.page.YanxiuTrain;

public class BaseCase {
	protected DesiredCapabilities capabilities = new DesiredCapabilities();

	protected  AppiumDriver<MobileElement> driver;
//	protected String appiumServer = "http://192.168.7.117:4723/wd/hub";
	protected YanxiuTrain app;
	protected String appiumServer = "http://127.0.0.1:4723/wd/hub";
	protected IOSDriver<MobileElement> iosDriver = null;
	protected AppiumServer Server = new AppiumServer();
	
	protected IOSDriver<MobileElement> getIOSDriver() throws MalformedURLException{
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
        capabilities.setCapability("platformName", "Mac");
        capabilities.setCapability("deviceName", "iPhone 4s");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("fullReset",true);
        capabilities.setCapability("app", "/Users/admin/Downloads/app.ipa");
        capabilities.setCapability("udid","32c3071f469fc67fd9df445ead34958c5e21d6e6");
        driver = new IOSDriver<MobileElement> (new URL(appiumServer),
                capabilities);
//        driver.resetApp();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return (IOSDriver<MobileElement>)driver;
	}
	
	protected AndroidDriver<MobileElement> getAndroidDriver(String deviceName) throws MalformedURLException{
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("appPackage",
				"com.yanxiu.gphone.training.teacher");
		capabilities.setCapability("appActivity",
				"com.yanxiu.yxtrain_android.activity.WelcomeActivity");
		driver = new AndroidDriver<MobileElement>(new URL(
				appiumServer), capabilities);
		driver.resetApp();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return (AndroidDriver<MobileElement>)driver;
	}

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws InterruptedException, IOException {

	         Map<String,String> devices_android = CommonUtil.getAndroidDevices();
	         Map<String,String> devices_ios = CommonUtil.getIOSDevices();
	         Server.startServer();
	         if(!devices_android.isEmpty()){
	        	 
	        	 
	        	 driver = getAndroidDriver(devices_android.get("deviceName"));
	         }
	         else if(!devices_ios.isEmpty()){
	        	
	        	 driver = getIOSDriver();
	         }
	         app = new YanxiuTrain(driver);
	         app.leadingPage().skipLeadingPage();
	    }

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		Server.stopServer();
	}
}
