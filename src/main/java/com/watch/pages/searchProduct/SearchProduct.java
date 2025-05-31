package com.watch.pages.searchProduct;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.watch.pages.cartpage.CartPage;
import com.watch.site.core.PageObjectFacilitators;
import com.watch.utilities.CreateLocator;

public class SearchProduct extends PageObjectFacilitators {

	WebDriver driver;

	public SearchProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public Boolean verifySearchResult(String productName) {
		CreateLocator createLocator = new CreateLocator();
		By searchedProducts = createLocator.getLocator("search_page", "product_names");
		By title = createLocator.getLocator("search_page", "page_title");
		waitForElementToGetVisible(title);
		ArrayList<String> products = getArrayOfString(searchedProducts);
		for (String product : products) {
			if (!product.contains(productName)) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean verifyNoProductFound() {
		CreateLocator createLocator = new CreateLocator();
		By noProduct = createLocator.getLocator("search_page", "no_product");
		return isElementDisplayed(noProduct);
	}
	
	public String getFirstProductName() {
		String productName = null;
		CreateLocator createLocator = new CreateLocator();
		By products = createLocator.getLocator("search_page", "product");
		productName = getTextFromElements(products, 0);
		return productName;
	}
	
	public CartPage addFirstProductToBasket() {
		CreateLocator createLocator = new CreateLocator();
		By addToBasket = createLocator.getLocator("search_page", "add_to_basket");
		By cartPopUp = createLocator.getLocator("cart_popUp", "pop_up");
		By goToCartBtn = createLocator.getLocator("cart_popUp", "goTo_cart");
		By cartPage = createLocator.getLocator("cart_page", "page");
		clickButton(addToBasket);
		waitForElementToGetVisible(cartPopUp);
		clickButton(goToCartBtn);
		waitForElementToGetVisible(cartPage);
		return new CartPage(driver);
	}

}
