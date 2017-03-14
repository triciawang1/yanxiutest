package com.yanxiu.page;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WorkShopPage extends PageBase {

	public WorkShopPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindAll({@AndroidFindBy(id = "com.yanxiu.gphone.training.teacher:id/tv_workshop_name")})
	private List<MobileElement> workshops;

	public void enterEachWorkShop() throws InterruptedException{
		for(int i=0;i<workshops.size();i++){
			workshops.get(i).click();
			pressBackButton();
		}
		workshops.get(0).click();
		Thread.sleep(2000);
	}
}
