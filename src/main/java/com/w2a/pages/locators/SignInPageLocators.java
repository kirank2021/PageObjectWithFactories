package com.w2a.pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPageLocators {
	
	
	
	@FindBy(css="#signin-loginid")
	public WebElement username;
	
	@FindBy(css="#signin-password" )
	public WebElement pwd;
	
	@FindBy(css="#submitButton" )
	public WebElement submitBtn;

}
