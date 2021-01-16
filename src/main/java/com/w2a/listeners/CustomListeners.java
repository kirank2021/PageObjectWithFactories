package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w2a.base.Page;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener,ISuiteListener {
	
	public String messageBody;

	public void onTestStart(ITestResult result) {
		test = extent
				.createTest(result.getTestClass().getName() + "   @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TestBase.test.log(LogStatus. , markup);
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {

		final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

		try {
			// C:\Users\LENOVO\eclipse-workspace\DataDrivenFramework\test-output\html
			Utilities.CaptureScreenshot(
					System.getProperty("user.dir")+"\\PageObjectWithFactories\\test-output\\html\\");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.setProperty(ESCAPE_PROPERTY, "false");

		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<br>");

		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">"
				+ "<img src=\"+TestUtil.screenshotName+\" height=300 width=300></a>");

		// *********Extent Report *********************//

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());

		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		try { 
			// String p = "System.getProperty(\"user.dir\")+\"\\target\\surefire-reports\\html\\";

			Utilities.CaptureScreenshot(System.getProperty("user.dir")+"\\PageObjectWithFactories\\target\\surefire-reports\\html\\");
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}

	}
	public void onStart(ISuite suite) {
		MonitoringMail mail=new MonitoringMail();
		try {
			messageBody = "http://"+ InetAddress.getLocalHost().getHostAddress()+
					":8080/job/PageObjectWithFactories/HTML_20Report/";
					// http://localhost:8080/job/PageObjectWithFactories/HTML_20Report/
		}catch(UnknownHostException e) {
			e.printStackTrace();
			
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		  
	public void onFinish(ISuite suite) {
		    // not implemented
	}

}
