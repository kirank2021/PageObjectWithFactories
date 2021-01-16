package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.actions.SignInPage;
import com.w2a.utilities.Utilities;

public class SignInTest {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")

	public void signInTest(Hashtable<String,String> data) {
			Page.initConfiguration();
			if(!Page.isTestRunnable("SignInTest",Page.excel)) {
				throw new SkipException("Skipping the testcase as the runmode is N");
			}
			if( data.get("runmode").equalsIgnoreCase("N")) {
				throw new SkipException("Skipping the testcase as the runmode is N");

				
			}
			SignInPage signInV=Page.topNav.goToSign();
			signInV.doSignIn(data.get("username"), data.get("pwd"));
		
	}

}
