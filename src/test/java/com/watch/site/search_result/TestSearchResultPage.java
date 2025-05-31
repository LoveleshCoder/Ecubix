package com.watch.site.search_result;

import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.watch.pages.homepage.HomePage;
import com.watch.pages.searchProduct.SearchProduct;
import com.watch.site.core.DriverManagementCore;

public class TestSearchResultPage extends DriverManagementCore {

	@Test(priority = 1, description = "Verify that the search functionality returns relevant results for valid keywords")
	public void testSearchFunctionalityWRTMatchingProduct() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homePage = new HomePage(driver);
		SearchProduct searchProduct = homePage.searchProduct("Casio");
		Assert.assertTrue(searchProduct.verifySearchResult("Casio"));
	}

	@Test(priority = 2, description = "Verify that search results shows no results when there are no matching products")
	public void testSearchFunctionalityWRTNoMatchingProduct() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homePage = new HomePage(driver);
		SearchProduct searchProduct = homePage.searchProduct("Rolex");
		Assert.assertTrue(searchProduct.verifyNoProductFound());
	}

}
