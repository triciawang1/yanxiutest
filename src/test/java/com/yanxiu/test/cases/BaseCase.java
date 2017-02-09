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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;

import static com.github.dreamhead.moco.Runner.runner;
import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Moco.uri;

import com.yanxiu.common.AnyProxy;
import com.yanxiu.common.AppiumServer;
import com.yanxiu.common.AppiumServerLog;
import com.yanxiu.common.CommonUtil;
import com.yanxiu.common.ElementHelper;
import com.yanxiu.common.MocoServer;
import com.yanxiu.common.ScreenshotUtil;
import com.yanxiu.common.WifiProxy;
import com.yanxiu.page.ExaminePage;
import com.yanxiu.page.YanxiuTrain;
import com.yanxiu.test.TestMethodCapture;

public class BaseCase {

	private Logger log = Logger.getLogger(BaseCase.class);
	protected DesiredCapabilities capabilities = new DesiredCapabilities();

	protected AppiumDriver<MobileElement> driver;
	// protected String appiumServer = "http://192.168.7.141:4723/wd/hub";
	protected YanxiuTrain app;
	protected String appiumServer = "http://127.0.0.1:4723/wd/hub";
	protected IOSDriver<MobileElement> iosDriver = null;
	protected AppiumServer Server = new AppiumServer();
	protected Runner runner;
	protected HttpServer server;
	protected AnyProxy proxy = new AnyProxy();
	protected MocoServer mocoServer = new MocoServer();
	private File baseDir;
	protected Rectangle container;

	protected IOSDriver<MobileElement> getIOSDriver(String udid) throws MalformedURLException {
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformName", "Mac");
		capabilities.setCapability("deviceName", "iPhone 4s");
		capabilities.setCapability("platformVersion", "10.0");
		capabilities.setCapability("fullReset", true);
		capabilities.setCapability("app",
				"/Users/admin/.jenkins/jobs/BuildIOSIpa/lastSuccessful/archive/TrainApp/build/app.ipa");

		capabilities.setCapability("udid", udid);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		log.info("create an iosDriver");
		driver = new IOSDriver<MobileElement>(new URL(appiumServer), capabilities);
		// driver.resetApp();
		log.info(driver + "driver now");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return (IOSDriver<MobileElement>) driver;
	}

	protected AndroidDriver<MobileElement> getAndroidDriver(String deviceName)
			throws InterruptedException, IOException {
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("appPackage", "com.yanxiu.gphone.training.teacher");
		capabilities.setCapability("appActivity", "com.yanxiu.yxtrain_android.activity.login.WelcomeActivity");

		driver = new AndroidDriver<MobileElement>(new URL(appiumServer), capabilities);

		// driver.resetApp();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return (AndroidDriver<MobileElement>) driver;
	}

	public void startAppiumServer() throws IOException, InterruptedException {
		Boolean isRemoteRun = isRemoteRun();
		if (Server.isServerStarted(isRemoteRun)) {
			Server.stopServer(isRemoteRun);

		}
		if (isRemoteRun) {
			Server.startServerRemotely();
		} else {
			Server.startServer();
		}

	}

	public void startProxy() throws ExecuteException, IOException {

		proxy.startServer();
	}

	@AfterSuite()
	public void stopAppiumSever() throws IOException {
		Server.stopServer(isRemoteRun());
		WifiProxy.clearWifiProxy();
	}

	@BeforeSuite()
	public void prepairEnv() throws IOException, InterruptedException, URISyntaxException {
		if (CommonUtil.isAndroidDevicePluggin()) {
			CommonUtil.reinstallApk();
		}
		CommonUtil.installProxySetterApk();
		WifiProxy.setWifiProxy();
		startAppiumServer();
		startProxy();
		// AppiumServerLog serverLogThread = AppiumServerLog.getServer();
		// serverLogThread.start();
		startMocoServer();


		screenShotPrepare();

	}

	
	public void screenShotPrepare() throws IOException {
		String baseDirPath = System.getProperty("user.dir");
		String actualDir = "actual";

		log.info("current directory is:"+baseDirPath);

		baseDir = new File(baseDirPath, actualDir);

		if (!baseDir.exists()) {
			log.info("actual file folder does not exist, create one");
			baseDir.mkdirs();
			

		}
		for (File file : baseDir.listFiles()) {
			file.delete();
		}
		
		String failDir = "fail";
		File failDirPath = new File(baseDirPath, failDir);
		if (!failDirPath.exists()) {
			failDirPath.mkdirs();

		}
		for (File file : failDirPath.listFiles()) {
			file.delete();
		}
	}

	public void startMocoServer() throws InterruptedException {
		mocoServer.startServer();
	}

	public void stopMocoServer() {
		mocoServer.stopServer();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException, IOException {
		log.info("before method");
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
		setConnection();

		mockStartupData();

		app = new YanxiuTrain(driver);
		app.leadingPage().skipLeadingPage();
	}

	private void mockStartupData() throws UnsupportedEncodingException {
		if(mocoServer==null){
			mocoServer = new MocoServer();
		}
//		mocoServer.response("login.json", "/login.json");
		mocoServer.response("initialize.json", "/initialize");
//		mocoServer.response("getEditUserInfo.json", "/getEditUserInfo");

//		mocoServer.response("trainlist.json", "/trainlist");
		mocoServer.response("noticeList.json", "/noticeList");
		mocoServer.response("briefList.json", "/briefList");
//		mocoServer.response("taskList.json", "/taskList");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		log.info("After method");
		if (driver instanceof AndroidDriver) {
			CommonUtil.clearAndroidData();
		}
		driver.quit();

	}

	public void setConnection() throws InterruptedException {

		if (driver instanceof AndroidDriver) {
			AndroidDriver<MobileElement> a_driver = ((AndroidDriver<MobileElement>) driver);
			Connection conn = a_driver.getConnection();
			if (conn != Connection.WIFI && conn != Connection.ALL) {
				log.info("set wifi connection for Android");
				a_driver.setConnection(Connection.WIFI);
			}
		}
	}

	public Boolean isRemoteRun() {
		if (appiumServer.contains("127.0.0.1"))
			return false;
		return true;
	}

	protected void takeScreenShotAndAssert(String screenshotFileName) throws IOException {

		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		
		File localScreenshot = new File(baseDir, screenshotFileName);

		FileUtils.copyFile(screenshot, localScreenshot);
		
		
		
		BufferedImage subImage = ImageIO.read(localScreenshot).getSubimage(container.x, container.y, container.width,container.height);
	    ImageIO.write(subImage, "png", localScreenshot);
	    
	    Assert.assertFalse(ScreenshotUtil.hasDiff(screenshotFileName));
	}

	protected void pageDown() throws InterruptedException {
		if (driver instanceof AndroidDriver) {
			((AndroidDriver<MobileElement>) driver).pressKeyCode(93);
		}
		Thread.sleep(2000);
	}
	
	
}
