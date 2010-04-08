package com.thoughtworks.test.matchers;

import org.hamcrest.SelfDescribing;

public interface VerifyingMatcher<T> extends SelfDescribing {
    void verifyMatches(T item) throws VerificationError;

    void dontImplementVerifyingMatcherInsteadExtendBaseVerifyingMatcher();
}
