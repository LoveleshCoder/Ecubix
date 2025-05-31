package com.watch.site.product;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import com.watch.pages.cartpage.CartPage;
import com.watch.pages.homepage.HomePage;
import com.watch.pages.searchProduct.SearchProduct;
import com.watch.site.core.DriverManagementCore;

public class TestProductAddedToCart extends DriverManagementCore {

	@Test(description = "Test that product is successfully added in the cart")
	public void testSuccessfulProductAddToCart() {
		System.out.println(Thread.currentThread().threadId());
		WebDriver driver = getDriver();
		HomePage homePage = new HomePage(driver);
		SearchProduct searchProduct = homePage.goToHerProductPage();
		String expectedProductName = searchProduct.getFirstProductName();
		CartPage cartPage = searchProduct.addFirstProductToBasket();
		String actualProductName = cartPage.getProductName();
		Assert.assertEquals(actualProductName, expectedProductName);
	}
}
