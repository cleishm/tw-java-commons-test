package com.thoughtworks.test;

import org.openqa.selenium.WebDriver;

public class WebDriverFixture {
	private static WebDriverFactory factory = new SysPropWebDriverFactory();
	private static WebDriver driver;
	private static String applicationRoot = System.getProperty(
    		"webdriver.application.root", "http://localhost:8080");
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				resetDriver();
			}
		});
	}
	
	public static void setDriverFactory(final WebDriverFactory factory) {
		WebDriverFixture.factory = factory;
		resetDriver();
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = factory.buildDriver();
		}
		return driver;
	}

	public static void resetDriver() {
		if (driver == null) {
			return;
		}
		driver.close();
		driver = null;
	}
	
	public static void setApplicationRoot(final String applicationRoot) {
		WebDriverFixture.applicationRoot = applicationRoot;
	}
	
	public static String getApplicationRoot() {
		return applicationRoot;
	}
}
