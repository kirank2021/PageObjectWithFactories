package com.w2a.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopNavigationLocators {
	
	@FindBy(css="#support-cs")
	public WebElement support;
	
	@FindBy(css="#itinerary")
	public WebElement trips;
	
	@FindBy(css="#gc-custom-header-nav-bar-acct-menu")
	public WebElement signin;
	
	@FindBy(css=".uitk-button.uitk-button-small.uitk-button-fullWidth.uitk-button-has-text.uitk-button-primary")
	public WebElement signinBtn;
	
	// uitk-button uitk-button-small uitk-button-fullWidth uitk-button-has-text uitk-button-primary

	@FindBy(css="#account-register")
	public WebElement createaccountBtn;

}
