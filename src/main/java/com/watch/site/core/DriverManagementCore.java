package com.watch.site.core;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class DriverManagementCore {

	private static ThreadLocal<WebDriver> t_driver = new ThreadLocal<WebDriver>();
	/*
	 * This threadLocalDriver variable will help to ensure thread safety by
	 * providing an independent thread for each test execution. This means it would
	 * give two thread IDs, each containing a copy of the instantiated browser
	 * instance for all parallel executing test cases.
	 */
	private FileInputStream fis;
	private Properties prop;
	private String browser;
	private String url;

	/*
	 * Inside the setDriver() function, we use the set() method of ThreadLocal to
	 * assign the Driver reference to this driver thread local variable
	 */
	public void setDriver(WebDriver driver) {
		t_driver.set(driver);
	}

	/*
	 * This is used to return the driver variable reference using the get() method
	 * of ThreadLocal
	 */
	public WebDriver getDriver() {
		return t_driver.get();
	}

	public void loadProperties() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
		prop = new Properties();
		prop.load(fis);
		// browser = prop.getProperty("browser");
		browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		url = prop.getProperty("url");
	}

	public WebDriver initiateDriver(String browser) {
		/*
		 * The Options class in Selenium provides a way to customize browser behavior by
		 * modifying different settings and preferences. Common use cases: 1. Running
		 * Chrome in Headless Mode 2. Disabling Browser Notifications 3. Setting Browser
		 * Window Size and Position 4. Running Tests in Incognito Mode
		 */
		WebDriver driver = null;
		switch (browser) {
		case "chrome":
			ChromeOptions options_c = new ChromeOptions();
			options_c.addArguments("--incognito");
//			options_c.addArguments("--headless");
			driver = new ChromeDriver(options_c);
			return driver;
		case "firefox":
			FirefoxOptions options_f = new FirefoxOptions();
			options_f.addArguments("--incognito");
			driver = new FirefoxDriver(options_f);
			return driver;
		default:
			return null;
		}
	}

	@BeforeMethod
	public void launchWebApplication() throws IOException {
		loadProperties();
		setDriver(initiateDriver(browser));
		getDriver().get(url);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//screenshots//" + testCaseName + ".png";
	}
	
	public void cleanDirectory(String directory) throws IOException {
		File folder = new File(directory);
		FileUtils.cleanDirectory(folder);
	}

	@AfterMethod
	public void tearDown() {
		getDriver().quit();
		t_driver.remove();
		/*
		 * Using the remove() function helps to prevent memory leaks by clearing the
		 * assigned memory to the ThreadLocal variable. Thus, it prevents degradation in
		 * system performance by maintaining available memory for execution
		 */
	}
	
	@BeforeSuite
	public void cleanData() throws IOException {
		cleanDirectory(System.getProperty("user.dir") + "//screenshots");
	}

}
