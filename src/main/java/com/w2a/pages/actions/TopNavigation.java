package com.w2a.pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.w2a.base.Page;
import com.w2a.pages.locators.TopNavigationLocators;

public class TopNavigation {

	public TopNavigationLocators topNavigation;
	
	public TopNavigation(WebDriver driver) {

		this.topNavigation = new TopNavigationLocators();
		
		AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.topNavigation);

		
	}

	public void goToSupport() {
		
		Page.click(topNavigation.support);
		
	}
	
	public void goToTrips() {
		Page.click(topNavigation.trips);
	}

	public SignInPage goToSign() {
		Page.click(topNavigation.signin);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Page.click(topNavigation.signinBtn);
		
			System.out.println("error in gotosign");
		
		return new SignInPage();

	}
	
	public void goToCreateAccount() {
		Page.click(topNavigation.createaccountBtn);
	}

}
