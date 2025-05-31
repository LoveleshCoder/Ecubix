package com.watch.pages.cartpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.watch.site.core.PageObjectFacilitators;
import com.watch.utilities.CreateLocator;

public class CartPage extends PageObjectFacilitators {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getProductName() {
		String product_name = null;
		CreateLocator createLocator = new CreateLocator();
		By productName = createLocator.getLocator("cart_page", "product_name");
		product_name = getTextFromElement(productName);
		return product_name;
	}

}
