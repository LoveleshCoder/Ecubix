package com.watch.pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.watch.pages.contact_us.ContactUsPage;
import com.watch.pages.searchProduct.SearchProduct;
import com.watch.site.core.PageObjectFacilitators;
import com.watch.utilities.CreateLocator;

public class HomePage extends PageObjectFacilitators {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		/*
		 * Calls the constructor of the parent class PageObjectFacilitator.
		 * super(driver) passes the WebDriver from child to parent.
		 */
		super(driver);
		this.driver = driver;
	}

	public SearchProduct searchProduct(String productName) {
		CreateLocator createLocator = new CreateLocator();
		By searchInput = createLocator.getLocator("home_page", "search_field");
		By searchButton = createLocator.getLocator("home_page", "seach_button");
		setTextInField(searchInput, productName);
		clickButton(searchButton);
		return new SearchProduct(driver);
	}
	
	public SearchProduct goToHerProductPage() {
		CreateLocator createLocator = new CreateLocator();
		By forHer = createLocator.getLocator("home_page", "for_her");
		By pageTitle = createLocator.getLocator("search_page", "page_title");
		clickButton(forHer);
		waitForElementToGetVisible(pageTitle);
		return new SearchProduct(driver);
	}
	
	public ContactUsPage goToContactUsPage() {
		CreateLocator createLocator = new CreateLocator();
		By contactUs = createLocator.getLocator("home_page", "contact_us");
		By pageTitle = createLocator.getLocator("contactUs_page", "page_title");
		clickButton(contactUs);
		waitForElementToGetVisible(pageTitle);
		return new ContactUsPage(driver);
	}

}
