package com.yanxiu.page;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeworkPage extends PageBase {

	public HomeworkPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(id = "tv_ilknow")
	private MobileElement knownButton;

	@AndroidFindAll(@AndroidFindBy(id = "rl_homework_item"))
	private List<MobileElement> homeworkItems;

	@AndroidFindBy(id = "rcv_homeworklist")
	private MobileElement listView;

	@AndroidFindAll(@AndroidFindBy(id = "tv_homework_name"))
	private List<MobileElement> homeworkNames;

	@AndroidFindBy(id = "tv_web_title")
	private MobileElement homeworkTitleinDetailPage;

	@AndroidFindBy(id = "tv_mtitle")
	private MobileElement videoTitle;

	@AndroidFindBy(id = "rl_record")
	private MobileElement recordButton;

	@AndroidFindBy(id = "rl_upload_video")
	private MobileElement uploadButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'成绩')]")
	private MobileElement textScore;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'截止日期')]")
	private MobileElement textEndDate;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'作业状态')]")
	private MobileElement textStatus;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'作业要求')]")
	private MobileElement textRequirement;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'作业成绩为')]")
	private MobileElement normalHomework;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'测试作业1')]")
	private MobileElement videoHomework;
	
	//视频录制页面开始、暂停按钮
	private MobileElement img_record_statue;
	
	//视频录制页面放弃已录制视频按钮
	private MobileElement img_record_del;
	
	//视频录制页面保存视频按钮
	private MobileElement img_record_save;
	
	//放弃视频确认弹窗上的放弃按钮
	private MobileElement tv_right;
	
	//放弃视频确认弹窗上的取消按钮
	private MobileElement tv_left;
	
	private MobileElement rl_record;
	
	//上传视频按钮
	private MobileElement rl_upload_video;
	
	//填写作业信息页面，作业标题文本框
	private MobileElement et_title;
	//填写作业信息页面，学段
	private MobileElement rl_period;
	
	//填写作业信息页面，学科
	private MobileElement rl_subjects;
	
	//填写作业信息页面，版本
	private MobileElement rl_version;
	//填写作业信息页面，书册
	private MobileElement rl_book;
	
	//填写作业信息页面，内容
	private MobileElement et_knowledge;
	
	private MobileElement tv_upload;
	public void tapKnownButton() {
		knownButton.click();
	}
	
	public void enterHomeworkDetailPage(){
		normalHomework.click();
		waitForElementVisible(textScore);
	}
	
	public void enterVideoWorkDetailPage(){
		videoHomework.click();
		waitForElementVisible(textScore);
	}

	public void startRecord(){
		rl_record.click();
		waitForElementVisible(img_record_statue);
	}
	
	public void pauseRecord() throws InterruptedException{
		img_record_statue.click();
		Thread.sleep(2000);
		img_record_statue.click();
	}
	public void giveupRecord(){
		img_record_del.click();
		waitForElementVisible(tv_right);
		tv_right.click();
	}
	
	public void cancelGiveupRecord(){
		img_record_del.click();
		waitForElementVisible(tv_left);
		tv_left.click();
	}
	
	public boolean isGiveupButtonShown(){
		boolean isShown = false;
		try{
			isShown = img_record_del.isDisplayed();
		}catch(NoSuchElementException e){
			e.printStackTrace();
			isShown = false;
		}
		return isShown;
	}
	
	public boolean isRecordButtonShown(){
		waitForElementVisible(img_record_statue);
		return img_record_statue.isDisplayed();
	}
	
	public void saveRecord(){
		img_record_save.click();
		waitForElementVisible(textScore);
	}
	
	public String getTextUploadStatus(){
		return tv_upload.getText();
	}

	public void uploadVideo() throws InterruptedException{
		rl_upload_video.click();
		waitForElementVisible(et_title);
		et_title.sendKeys("this is title");
		rl_period.click();
		Thread.sleep(2000);;
		System.out.println(driver.getPageSource());
		
		
	}
	public void tapAllHomeworkItem() {

		String lastHomeworkName = "";
		do {
			lastHomeworkName = homeworkNames.get(homeworkNames.size() - 1).getText();
			for (int i = 0; i < homeworkNames.size(); i++) {

				homeworkNames.get(i).click();
				waitForElementVisible(homeworkTitleinDetailPage);
				pressBackButton();

			}
			listView.swipe(SwipeElementDirection.UP, 10, 600, 500);
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//			System.out.println("##########" + lastHomeworkName);
//			System.out.println("##########" + homeworkNames.get(homeworkNames.size() - 1).getText());

		} while (!lastHomeworkName.equals(homeworkNames.get(homeworkNames.size() - 1).getText()));
	}

	
	public void findNormalHomework() {
		String lastHomeworkName = "";
		do {
			lastHomeworkName = homeworkNames.get(homeworkNames.size() - 1).getText();
			for (int i = 0; i < homeworkNames.size(); i++) {
				homeworkNames.get(i).click();
				waitForElementVisible(homeworkTitleinDetailPage);
				if (!isVideoHomework()) {
					return;
				}
				pressBackButton();

			}
			listView.swipe(SwipeElementDirection.UP, 10, 600, 500);
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		} while (!lastHomeworkName.equals(homeworkNames.get(homeworkNames.size() - 1).getText()));
	}

	public Boolean isVideoHomework() {
		Boolean isVideo = false;

		try {
			if (videoTitle.isDisplayed() || recordButton.isDisplayed() || uploadButton.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			isVideo = false;
		}
		return isVideo;
	}

	public Boolean isNormalHomeworkShownCorrectly() {
		try{
		return textScore.isDisplayed() && textEndDate.isDisplayed() && textStatus.isDisplayed()
				&& textRequirement.isDisplayed();
		}catch(Exception e){
			return false;
		}
	}

	public void findVideoHomework() {
		String lastHomeworkName = "";
		do {
			lastHomeworkName = homeworkNames.get(homeworkNames.size() - 1).getText();
			for (int i = 0; i < homeworkNames.size(); i++) {
				homeworkNames.get(i).click();
				waitForElementVisible(homeworkTitleinDetailPage);
				if (isVideoHomework()) {
					return;
				}
				pressBackButton();

			}
			listView.swipe(SwipeElementDirection.UP, 10, 600, 500);
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		} while (!lastHomeworkName.equals(homeworkNames.get(homeworkNames.size() - 1).getText()));
	}
	
	public Boolean isVideoHomeworkShownCorrectly() {
		try{
		return videoTitle.isDisplayed() || recordButton.isDisplayed() || uploadButton.isDisplayed()
			;
		}catch(Exception e){
			return false;
		}
	}
	
}