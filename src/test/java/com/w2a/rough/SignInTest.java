package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.actions.SignInPage;
import com.w2a.pages.actions.TopNavigation;

public class SignInTest {
	
	public int i=10;
	
	public static void main(String[] args) {
			Page.initConfiguration();
			SignInPage signInV=Page.topNav.goToSign();
			signInV.doSignIn("kirankumarsahu", "Kiran");

		
			System.out.println(new SignInTest().i);
		
		
	}

}
