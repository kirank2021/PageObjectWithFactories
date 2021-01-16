package com.w2a.rough;



import com.w2a.base.Page;
import com.w2a.pages.actions.HomePage;


public class Rough {

	public static void main(String[] args) {
		
		Page.initConfiguration();
		HomePage home1= new HomePage();
		home1.goToStays();
		home1.goToFlights().Book2WayTicket("Pune (PNQ - Lohegaon)", "Mumbai (BOM - Chhatrapati Shivaji Intl.)", "departureDt", "returnDt");;

	}
	
}
