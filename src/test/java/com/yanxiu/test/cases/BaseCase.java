package com.yanxiu.test.cases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.Connection;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import com.yanxiu.common.AppiumServer;
import com.yanxiu.common.CommonUtil;
import com.yanxiu.common.ElementHelper;
import com.yanxiu.page.ExaminePage;
import com.yanxiu.page.YanxiuTrain;

public class BaseCase {

	private Logger log = Logger.getLogger(BaseCase.class);
	protected DesiredCapabilities capabilities = new DesiredCapabilities();

	protected AppiumDriver<MobileElement> driver;
//	protected String appiumServer = "http://192.168.7.141:4723/wd/hub";
	protected YanxiuTrain app;
	protected String appiumServer = "http://127.0.0.1:4723/wd/hub";
	protected IOSDriver<MobileElement> iosDriver = null;
	protected AppiumServer Server = new AppiumServer();

	protected IOSDriver<MobileElement> getIOSDriver(String udid) throws MalformedURLException {
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformName", "Mac");
		capabilities.setCapability("deviceName", "iPhone 4s");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("fullReset", true);
		capabilities.setCapability("app", "/Users/admin/Downloads/app.ipa");

		capabilities.setCapability("udid", udid);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		log.info("create an iosDriver");
		driver = new IOSDriver<MobileElement>(new URL(appiumServer), capabilities);
		// driver.resetApp();
		log.info(driver+"driver now");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return (IOSDriver<MobileElement>) driver;
	}

	protected AndroidDriver<MobileElement> getAndroidDriver(String deviceName) throws MalformedURLException {
		capabilities.setCapability("platformName", "Android");
		
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("appPackage", "com.yanxiu.gphone.training.teacher");
		capabilities.setCapability("appActivity", "com.yanxiu.yxtrain_android.activity.login.WelcomeActivity");
		driver = new AndroidDriver<MobileElement>(new URL(appiumServer), capabilities);
		// driver.resetApp();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return (AndroidDriver<MobileElement>) driver;
	}

	@BeforeSuite()
	public void startAppiumServer() throws IOException {
//		Boolean isRemoteRun = isRemoteRun();
//		if (Server.isServerStarted(isRemoteRun)) {
//			Server.stopServer(isRemoteRun);
//
//		}
//		Server.startServer(isRemoteRun);
	}

	@AfterSuite()
	public void stopAppiumSever() {
//		Server.stopServer(isRemoteRun());
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException, IOException {

		Boolean isRemoteRun = isRemoteRun();
		Map<String, String> devices_android = CommonUtil.getAndroidDevices(isRemoteRun);
		Map<String, String> devices_ios = CommonUtil.getIOSDevices(isRemoteRun);


		if (!devices_android.isEmpty()) {

			driver = getAndroidDriver(devices_android.get("deviceName"));
		} else if (!devices_ios.isEmpty()) {

			driver = getIOSDriver(devices_ios.get("udid"));
		} else {
			log.info("no device is connected, please plug in a device");
			System.exit(0);
		}
		app = new YanxiuTrain(driver);
		app.leadingPage().skipLeadingPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		if (driver instanceof AndroidDriver) {
			CommonUtil.clearAndroidData();
		}
		driver.quit();
		
	}

	@BeforeClass
	public void setConnection() {
		if (driver instanceof AndroidDriver) {
			log.info("set wifi connection for android device");
			AndroidDriver<MobileElement> a_driver = (AndroidDriver<MobileElement>) driver;
			Connection con = a_driver.getConnection();
			if (con != Connection.WIFI) {
				a_driver.setConnection(Connection.WIFI);
			}
		}
	}

	public Boolean isRemoteRun() {
		if (appiumServer.contains("127.0.0.1"))
			return false;
		return true;
	}
}
