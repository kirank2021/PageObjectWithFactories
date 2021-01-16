package com.w2a.testcases;



import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.errorCollectors.ErrorCollector;
import com.w2a.pages.actions.HomePage;
import com.w2a.pages.locators.HomePageLocators;

import com.w2a.utilities.Utilities;


public class FlightSearchTest {
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")

	public void flightSearchTest(Hashtable<String,String> data) {
		
		Page.initConfiguration();
		if(!Page.isTestRunnable("FlightSearchTest",Page.excel)) {
			throw new SkipException("Skipping the testcase as the runmode is N");
		}
		if( data.get("runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the testcase as the runmode is N");

			
		}
		HomePage home1= new HomePage();
		ErrorCollector.verifyEquals(home1.countOfTabs(), 5);
		ErrorCollector.verifyEquals(home1.countOfTabs(), 5);

		home1.goToStays();
		//home1.goToFlights().Book2WayTicket("Pune (PNQ - Lohegaon)", "Mumbai (BOM - Chhatrapati Shivaji Intl.)", "departureDt", "returnDt");;
		home1.goToFlights().Book2WayTicket(data.get("from"),data.get("to"),data.get("departureDt"),data.get("returnDt"));

	}
	
}
