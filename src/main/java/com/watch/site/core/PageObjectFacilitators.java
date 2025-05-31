package com.watch.site.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * 	Likely contains common utility methods or shared WebDriver logic.
 	Example: PageObjectFacilitator have reusable methods like:
 	clickElement()
 	waitForVisibility()
 	getTextOfElement()
 	… which can be used across all page classes like Homepage, LoginPage, etc.
 */
public class PageObjectFacilitators {

	WebDriver driver;

	public PageObjectFacilitators(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToGetVisible(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}

	public void clickButton(By element) {
		driver.findElement(element).click();
	}

	public void setTextInField(By element, String text) {
		driver.findElement(element).sendKeys(text);
	}

	public ArrayList<String> getArrayOfString(By element) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> strings = driver.findElements(element);
		for (int i = 0; i < strings.size(); i++) {
			arrayList.add(strings.get(i).getText());
		}
		return arrayList;
	}

	public String getTextFromElement(By element) {
		return driver.findElement(element).getText();
	}

	public String getTextFromElements(By element, int number) {
		return driver.findElements(element).get(number).getText();
	}

	public boolean isElementDisplayed(By element) {
		waitForElementToGetVisible(element);
		return driver.findElement(element).isDisplayed();
	}

}
