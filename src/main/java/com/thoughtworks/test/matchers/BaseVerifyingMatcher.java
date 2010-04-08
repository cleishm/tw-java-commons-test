package com.thoughtworks.test.matchers;

import org.hamcrest.StringDescription;

public abstract class BaseVerifyingMatcher<T> implements VerifyingMatcher<T> {

    public void dontImplementVerifyingMatcherInsteadExtendBaseVerifyingMatcher() {
    }

    @Override
    public String toString() {
        return StringDescription.toString(this);
    }

}
