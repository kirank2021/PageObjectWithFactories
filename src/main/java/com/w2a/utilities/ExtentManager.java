package com.w2a.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	public static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter
				(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("W2A Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("automation tester", "sahu kiran");
		extent.setSystemInfo("organisation", "MyTestingCompany");
		extent.setSystemInfo("Build no", "W@a-1234");

		return extent;
		

		}
	}
	

