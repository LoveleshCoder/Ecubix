package com.watch.site.contact_us;

import org.testng.annotations.Test;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.watch.pages.contact_us.ContactUsPage;
import com.watch.pages.homepage.HomePage;
import com.watch.site.core.DriverManagementCore;

public class TestContactUsPage extends DriverManagementCore {

	@Test(priority = 1, description = "Test the navigation to Contact Us page")
	public void testNavigationToContactUsPage() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homepage = new HomePage(driver);
		ContactUsPage contactUsPage = homepage.goToContactUsPage();
		Assert.assertEquals(contactUsPage.getPageTitle(), "Contact Us");

	}

	@Test(priority = 2, description = "Test that empty form can not be submitted")
	public void testEmptyFormSubmission() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homepage = new HomePage(driver);
		ContactUsPage contactUsPage = homepage.goToContactUsPage();
		contactUsPage.clickOnContinue();
		Assert.assertTrue(contactUsPage.isErrorMessageDisplayed("name"));
		Assert.assertTrue(contactUsPage.isErrorMessageDisplayed("email"));
		Assert.assertTrue(contactUsPage.isErrorMessageDisplayed("enquiry"));
	}

	@Test(priority = 3, description = "Test that Contact Us form submission sent a message")
	public void testSuccessfulFormSubmission() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homepage = new HomePage(driver);
		ContactUsPage contactUsPage = homepage.goToContactUsPage();
		contactUsPage.enterName("test");
		contactUsPage.enterEmail("test@123.com");
		contactUsPage.enterEnquiry("Testing");
		contactUsPage.clickOnContinue();
		Assert.assertEquals(contactUsPage.getSuccessfulMessage(), "Message is sent");
	}

}
