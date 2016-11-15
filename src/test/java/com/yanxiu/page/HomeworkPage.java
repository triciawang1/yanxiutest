package com.yanxiu.page;

import java.util.List;

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

	public void tapKnownButton() {
		knownButton.click();
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