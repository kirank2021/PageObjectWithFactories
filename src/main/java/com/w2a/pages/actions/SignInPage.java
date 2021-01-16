package com.w2a.pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.w2a.base.Page;
import com.w2a.pages.locators.SignInPageLocators;

public class SignInPage extends Page {
	
	public SignInPageLocators signIn;
	public SignInPage() {
		this.signIn= new SignInPageLocators();
		AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory,this.signIn);
		
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	public void doSignIn(String username,String pwd) {
		
		type(signIn.username,username);
		type(signIn.pwd,pwd);
		click(signIn.submitBtn);
	}
}
