package com.thoughtworks.test.matchers;

import static com.thoughtworks.test.matchers.Matchers.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.thoughtworks.test.PrivateAccessor;

public class PrivateFieldMatcher<T> extends BaseVerifyingMatcher<T> {
    private final String fieldName;
    private final Matcher<Object> matcher;

    public PrivateFieldMatcher(final String fieldName, final Matcher<Object> matcher) {
        this.fieldName = fieldName;
        this.matcher = matcher;
    }

    public void verifyMatches(final T item) throws VerificationError {
        final Object privateField;
        try {
            privateField = PrivateAccessor.getPrivateField(item, fieldName);
        } catch (final NoSuchFieldException e) {
            failVerification("no field named '" + fieldName + "'");
            return;
        }
        verifyThat(privateField, matcher);
    }

    public void describeTo(final Description description) {
        description.appendText("has field ").appendValue(fieldName).appendText(" that ").appendDescriptionOf(matcher);
    }

}
