package com.yanxiu.page;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomeworkPage extends PageBase{

	
	public HomeworkPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@AndroidFindBy(id="tv_ilknow")
	private MobileElement knownButton;
	
	@AndroidFindAll(@AndroidFindBy(id="rl_homework_item"))
	private List<MobileElement> homeworkItems;
	
	public void tapKnownButton(){
		knownButton.click();
	}
	
	public void tapAllHomeworkItem(){
		for(int i=0;i<homeworkItems.size();i++){
			homeworkItems.get(i).click();
	        pressBackButton();
		}
	}
}