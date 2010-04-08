package com.thoughtworks.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.thoughtworks.test.WebDriverFixture;

public abstract class WebDriverPage extends Page {

    public WebDriverPage() {
        super();
    }

    public WebDriverPage(final String location) {
    	super(buildURLFromLocation(location));
    }

	private static String buildURLFromLocation(final String location) {
		final String url = WebDriverFixture.getApplicationRoot();
    	if (location.isEmpty()) {
    		return url;
    	}
    	return url + ((location.startsWith("/"))? "" : "/") + location;
	}
    
	@Override
	protected void openLocation(final String location) {
		driver().get(location);
	}
	
	@Override
	protected void checkVisible() {
		try {
			super.checkVisible();
		} catch (final WebDriverException e) {
			throw new PageNotVisibleException(e);
		}
	}
	
	protected WebDriver driver() {
		return WebDriverFixture.getDriver();
	}

}
