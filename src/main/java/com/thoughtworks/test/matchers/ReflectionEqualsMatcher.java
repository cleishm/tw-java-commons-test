package com.thoughtworks.test.matchers;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ReflectionEqualsMatcher<T> extends BaseMatcher<T> {
    private final T object;

    public ReflectionEqualsMatcher(final T object) {
        this.object = object;
    }

    public boolean matches(final Object other) {
        return EqualsBuilder.reflectionEquals(object, other);
    }

    public void describeTo(final Description description) {
        description.appendValue(object);
    }
}
