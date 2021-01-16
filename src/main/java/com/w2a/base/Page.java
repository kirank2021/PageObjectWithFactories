package com.w2a.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.w2a.pages.actions.TopNavigation;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static TopNavigation topNav;
	// public static TopMenu menu;
	
	public static void initConfiguration() {
		System.out.println("initconfiguartion");
		if(Constants.browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver();

			log.debug("Chrome is launched");

		}
		else if(Constants.browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.debug("Firefox is launched");

			
		}
		else if(Constants.browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.debug("Internet explorer is launched");


			
		}

		
		driver.get(Constants.testSite);
		log.debug("URL is loaded");
		/*
		 * driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty
		 * ("implicit.wait")),TimeUnit.SECONDS);
		 */
		topNav = new TopNavigation(driver);

	}
	
	public static void click(WebElement element) {
		
		 element.click();
		//test.log(Status.INFO, "clicking on"+locator);
		testReport.get().log(Status.INFO, "clicking on the  : " + element );

	}

	
	public static void type(WebElement element,String value ) {
		//driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);

		element.sendKeys(value);
		 
		testReport.get().log(Status.INFO, "Sending keys on the  : " + element );



		
	}
	public static WebElement dropdown;
	public void select(String locator,String value) {
		dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));

		Select select= new Select(dropdown);
		select.selectByVisibleText(value);
		System.out.println(locator);
		System.out.println(value);

		testReport.get().log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);
	}
	
	public static boolean isTestRunnable(String TestName,ExcelReader excel) {
		
		int row=excel.getRowCount("test_suite");
		
		for(int rNum=2; rNum<=row; rNum++) {
			
			String testCase=excel.getCellData("test_suite", "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(TestName)) {
				
				String runMode=excel.getCellData("test_suite", "Runmode", rNum);
				
				if(runMode.equalsIgnoreCase("Y")) {
					return true;
				}else {
					return false;
				}
				
			}
		}
		return false;
		
	}

	
	public static void quit() {
		
		// driver.quit();
		
	}
	
	public static void verifyEquals(String actual,String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		}catch(Throwable t) {
			  
			  
				  //  C:\Users\LENOVO\eclipse-workspace\DataDrivenFramework\test-output\html
			Utilities.CaptureScreenshot(System.getProperty("user.dir")+"\\PageObjectModelBasics"
				  		+ "\\test-output\\html\\"); 
			  
			 Reporter.log("<br>"+"Verification failure"+t.getMessage()+"<br>");
			 Reporter.log("<br>");

		  
			 Reporter.log("Click to see failed assert screenshot");
			 Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+ ">Error assert screenshot</a>"); 
			 //////Extent Report///
			 try {
				 testReport.get().fail("<b>"+"Verification failed"+t.getMessage()+"<b>");
				 Utilities.CaptureScreenshot(System.getProperty("user.dir")+"PageObjectModelBasics\\target\\surefire-reports\\html\\");
				 testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failed assert" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
			} catch (IOException e) {
				 e.printStackTrace();
			}
			  
		}
		
	}


}
