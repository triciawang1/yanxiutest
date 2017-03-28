package com.yanxiu.page;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RolePage extends PageBase {

	public RolePage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private MobileElement rcv_leaderexam;

	public void pageDown() {
		rcv_leaderexam.swipe(SwipeElementDirection.UP, 100, 100, 500);
	}

	private MobileElement tv_myscore;

	public String getScore() {
		return tv_myscore.getText();
	}

	// 不需要学习提示信息
	private MobileElement tv_tips;

	private String expectedTips = "作为本次培训中的管理者，您不需要进行学员内容的学习，此部分展示仅为方便您了解学员学习内容，您仅需要进行该项目指导即可.";

	public boolean tipsEqualToExpect() {
		return tv_tips.getText().equals(expectedTips);
	}

	// 学员学情
	private MobileElement rl_student_study;

	// 管理tab
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"管理\")")
	private MobileElement management;

	@AndroidFindAll(@AndroidFindBy(className = "android.widget.ImageView"))
	private List<MobileElement> imageViews;

	public void enterXueqingPage() throws InterruptedException {
		management.click();
		rl_student_study.click();
		imageViews.get(0).click();
		Thread.sleep(1000);
	}

	private MobileElement recycler_student_study_list;

	public void scrollDown() {
		recycler_student_study_list.swipe(SwipeElementDirection.UP, 100, 100, 500);
	}

	@AndroidFindAll(@AndroidFindBy(id = "sb_study_notify"))
	private List<MobileElement> sb_study_notify;

	private MobileElement tv_study_notify;

	public void slideElementLeft() {
		sb_study_notify.get(0).swipe(SwipeElementDirection.LEFT, 50, 50, 500);
	}

	public void notifyStudent() throws InterruptedException {

		tv_study_notify.click();
		Thread.sleep(2000);
	}

	private MobileElement fab_all_notify;

	@AndroidFindAll(@AndroidFindBy(id = "iv_study_list_checked"))
	private List<MobileElement> iv_study_list_checked;

	private MobileElement tv_all_notify;

	public void notifyMoreStudents() throws InterruptedException {
		fab_all_notify.click();
		for (int i = 0; i < 4; i++) {
			iv_study_list_checked.get(i).click();
		}
		tv_all_notify.click();
		Thread.sleep(2000);
	}
}
