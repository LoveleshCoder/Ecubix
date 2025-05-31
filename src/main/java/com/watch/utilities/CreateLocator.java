package com.watch.utilities;

import org.openqa.selenium.By;

/*
 *  The CreateLocator class is a utility that dynamically creates Selenium By locators based on values stored in a JSON file.
 *  It extends the ReadLocators class, which handles loading and reading the JSON locator repository.
 *  This helps in keeping your framework:
 	Modular (separates locators from test logic),
 	Maintainable (easily update locators without touching code),
 	Reusable (supports multiple locator types and pages dynamically)
 */

public class CreateLocator extends ReadLoactors {

	private By locator; 
	private String locatorType; 
	private String locatorValue; 

	public By getLocator(String pageName, String locatorName) {
		// These two lines fetch the type and value of the locator from the jsonFile by calling method readlocator class
		locatorType = getType(pageName, locatorName);
		locatorValue = getValue(pageName, locatorName);

		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		case "className":
			locator = By.className(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		default:
			locator = null;
		}
		return locator;

	}

}
