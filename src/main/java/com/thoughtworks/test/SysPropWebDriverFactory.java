package com.thoughtworks.test;

import java.lang.Class;
import java.lang.ClassLoader;
import org.openqa.selenium.WebDriver;

public class SysPropWebDriverFactory implements WebDriverFactory {
    private static final String CLASS_NAME = System.getProperty(
    		"webdriver.class", "org.openqa.selenium.htmlunit.HtmlUnitDriver");

    @SuppressWarnings("unchecked")
	public WebDriver buildDriver() {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class clazz;
		try {
			clazz = classLoader.loadClass(CLASS_NAME);
			return (WebDriver)clazz.newInstance();
		} catch (final Exception e) {
			throw new RuntimeException("Cannot initialize webdriver.class", e);
		}
    }

}
