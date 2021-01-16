package com.w2a.pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePageLocators {
	
	@FindBy( css ="a[aria-controls='wizard-hotel-pwa-v2']")
	public WebElement stays;
	
	@FindBy( css= "#uitk-tabs-button-container > li:nth-child(2) > a > svg")
	public WebElement flight;
	
	@FindBy( css="button[data-stid='location-field-leg1-origin-menu-trigger']")
	public WebElement fromLocation;
	
	@FindAll({
		@FindBy( css="button[data-stid='location-field-leg1-destination-menu-triggerr']"),
		@FindBy( css="button[aria-label='Going to']")
	})
	public WebElement toLocaton;
	
	@FindBy( css="button#d1-btn.uitk-faux-input.uitk-form-field-trigger[data-stid='open-date-picker']")
	public WebElement departDate;
	
	
	@FindBy( css="button#d2-btn.uitk-faux-input.uitk-form-field-trigger[data-stid='open-date-picker']")
	public WebElement returningDate;
	
	@FindBys({
		@FindBy( css=".uitk-tabs-content"),
		@FindBy( css="button[data-testid='submit-button']")
		})
	public WebElement searchBtn;
	
	@FindBy( css=".uitk-tab.uitk-tab-icon-text")
	public List<WebElement> tabCount;

}
