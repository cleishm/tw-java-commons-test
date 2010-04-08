package com.thoughtworks.test.page;

import com.thoughtworks.test.matchers.VerificationError;

public abstract class Page {

    public Page() {
        checkVisible();
    }

    public Page(final String location) {
        openLocation(location);
        checkVisible();
    }

    protected void checkVisible() {
        try {
            verifyVisible();
        } catch (final VerificationError e) {
            throw new PageNotVisibleException(e);
        }
    }
    
    protected abstract void openLocation(final String location);

    protected abstract void verifyVisible() throws VerificationError;
}

