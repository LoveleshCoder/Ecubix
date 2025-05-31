package com.watch.pages.contact_us;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.watch.site.core.PageObjectFacilitators;
import com.watch.utilities.CreateLocator;

public class ContactUsPage extends PageObjectFacilitators {

	WebDriver driver;

	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getPageTitle() {
		String title = null;
		CreateLocator createLocator = new CreateLocator();
		By pageTitle = createLocator.getLocator("contactUs_page", "page_title");
		title = getTextFromElement(pageTitle);
		return title;
	}

	public void clickOnContinue() {
		CreateLocator createLocator = new CreateLocator();
		By continue_btn = createLocator.getLocator("contactUs_page", "continue_btn");
		acceptTC();
		clickButton(continue_btn);
	}

	public void acceptTC() {
		CreateLocator createLocator = new CreateLocator();
		By button = createLocator.getLocator("contactUs_page", "TandC_toggle");
		clickButton(button);
	}

	public boolean isErrorMessageDisplayed(String field) {
		CreateLocator createLocator = new CreateLocator();
		By error_name = createLocator.getLocator("contactUs_page", "error_name");
		By error_email = createLocator.getLocator("contactUs_page", "error_email");
		By error_enquiry = createLocator.getLocator("contactUs_page", "error_enquiry");
		switch (field) {
		case "name":
			return isElementDisplayed(error_name);
		case "email":
			return isElementDisplayed(error_email);
		case "enquiry":
			return isElementDisplayed(error_enquiry);
		default:
			return false;
		}
	}

	public void enterName(String name) {
		CreateLocator createLocator = new CreateLocator();
		By name_field = createLocator.getLocator("contactUs_page", "name");
		setTextInField(name_field, name);
	}

	public void enterEmail(String email) {
		CreateLocator createLocator = new CreateLocator();
		By email_field = createLocator.getLocator("contactUs_page", "email");
		setTextInField(email_field, email);
	}

	public void enterEnquiry(String enquiry) {
		CreateLocator createLocator = new CreateLocator();
		By enquiry_field = createLocator.getLocator("contactUs_page", "enquiry");
		setTextInField(enquiry_field, enquiry);
	}

	public String getSuccessfulMessage() {
		CreateLocator createLocator = new CreateLocator();
		By message = createLocator.getLocator("contactUs_page", "message");
		waitForElementToGetVisible(message);
		return getTextFromElement(message);
	}
}
