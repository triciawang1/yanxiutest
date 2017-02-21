package com.yanxiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ActivityPage extends PageBase {

	public ActivityPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'小学')]")
	private MobileElement primarySchool;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'初中')]")
	private MobileElement juniorSchool;
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'高中')]")
	private MobileElement highSchool;
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'学科')]")
	private MobileElement subject;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'物理')]")
	private MobileElement physics;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'数学')]")
	private MobileElement math;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'技术素养类')]")
	private MobileElement tech;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'案例')]")
	private MobileElement casetype;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'10100100101')]")
	private MobileElement activeItem;
	
	@AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'讨论')]")
	private MobileElement step_discuss;
	
	
	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement commentBox;
	
	private MobileElement btn_send_enable;

	private MobileElement img_member_head;
	
	private MobileElement iv_activity_statue;
	private MobileElement btn_add;
	
	public void switchSegment(){
		primarySchool.click();
		highSchool.click();
		
	}
	public void switchSubject(){
		subject.click();
		physics.click();
		
	}
	
	public void switchStage(){
		tech.click();
		casetype.click();
	}
	
	public void switchSubject2(){
		subject.click();
		math.click();
	}
	public void switchSegment2(){
		primarySchool.click();
		juniorSchool.click();
	}
	
	public void enterActiveDetailPage(){
		activeItem.click();
		waitForElementVisible(iv_activity_statue);
		
	}
	
	public void enterStepDetailPage() throws InterruptedException{
		step_discuss.click();
		Thread.sleep(1000);
	}
	
	public void enterReplyListPage(){
		img_member_head.click();
		waitForElementVisible(btn_add);
	}
	
	public void postReply(){
		btn_add.click();
		commentBox.sendKeys("test");
		btn_send_enable.click();
	}
}
