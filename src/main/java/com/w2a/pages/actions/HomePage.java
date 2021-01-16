package com.w2a.pages.actions;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.w2a.base.Page;
import com.w2a.pages.locators.HomePageLocators;

public class HomePage extends Page {
	
	public HomePageLocators home;

	public HomePage() {
		
		this.home= new HomePageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.home);

	}

	
	public void goToStays() {
		
		home.stays.click();

	}

	public HomePage goToFlights() {
		home.flight.click();
		return this;

	}

	public void goTOCars() {

	}

	public void goToThingsToDo() {

	}
	
	public void Book2WayTicket(String from,String to,String departureDt,String returnDt) {
		
		Actions action =new Actions(driver);
		//home.fromLocation.sendKeys(from);
		type(home.fromLocation,from);

		action.sendKeys(Keys.ENTER).perform();
		type(home.toLocaton,to);
		action.sendKeys(Keys.ENTER).perform();

		/*
		 * home.departDate.sendKeys(departureDt); home.returningDate.sendKeys(returnDt);
		 */
		click(home.searchBtn);

	}
	public int countOfTabs() {
		return home.tabCount.size();
	}
}
