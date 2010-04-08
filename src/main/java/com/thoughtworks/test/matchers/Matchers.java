package com.thoughtworks.test.matchers;

import static org.hamcrest.Matchers.*;

import java.io.Reader;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;

public final class Matchers {
    private Matchers() {
    }

    public static <T> void assertThat(final T actual, final VerifyingMatcher<T> verifyingMatcher) {
        assertThat("", actual, verifyingMatcher);
    }

    public static <T> void assertThat(final String reason, final T actual, final VerifyingMatcher<T> verifyingMatcher) {
        try {
            verifyingMatcher.verifyMatches(actual);
        } catch (final VerificationError e) {
            final AssertionError error = new AssertionError(buildDescription(reason, actual, verifyingMatcher)
                    .toString());
            error.initCause(e);
            throw error;
        }
    }

    public static void verify(final boolean result) throws VerificationError {
        verifyThat(result, is(true));
    }

    public static void verify(final String reason, final boolean result) throws VerificationError {
        verifyThat(reason, result, is(true));
    }

    public static <T> void verifyThat(final T actual, final VerifyingMatcher<T> verifyingMatcher)
            throws VerificationError {
        verifyingMatcher.verifyMatches(actual);
    }

    public static <T> void verifyThat(final T actual, final Matcher<T> matcher) throws VerificationError {
        verifyThat("", actual, matcher);
    }

    public static <T> void verifyThat(final String reason, final T actual, final Matcher<T> matcher)
            throws VerificationError {
        if (!matcher.matches(actual)) {
            throw new VerificationError(buildDescription(reason, actual, matcher).toString());
        }
    }

    public static void failVerification(final String reason) throws VerificationError {
        throw new VerificationError(reason);
    }

    private static Description buildDescription(final String reason, final Object actual,
            final SelfDescribing selfDescribing) {
        final Description description = new StringDescription();
        description.appendText(reason).appendText("\nExpected: ").appendDescriptionOf(selfDescribing).appendText(
                "\n     got: ").appendValue(actual).appendText("\n");
        return description;
    }

    public static <T> boolean checkThat(final T actualObject, final Matcher<T> matcher) {
        return matcher.matches(actualObject);
    }

    @Factory
    public static VerifyingMatcher<String> containsSameLinesOfTextAs(final String expectedLines) {
        return new SameLinesOfTextMatcher(expectedLines);
    }

    @Factory
    public static <T> Matcher<T> equalToByReflection(final T object) {
        return new ReflectionEqualsMatcher<T>(object);
    }

    @Factory
    public static Matcher<Reader> readerWithContent(final String expected) {
        return new ReaderWithContent(expected);
    }

    @Factory
    public static <T> VerifyingMatcher<T> hasPrivateField(final String fieldName, final Matcher<Object> matcher) {
        return new PrivateFieldMatcher<T>(fieldName, matcher);
    }

    @Factory
    public static <T> Matcher<T> thatIs(final Matcher<T> matcher) {
        return org.hamcrest.Matchers.is(matcher);
    }

    // proxy methods to save users having to import hamcrest MatcherAssert as well
    public static <T> void assertThat(final T actual, final Matcher<T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
    }

    public static <T> void assertThat(final String reason, final T actual, final Matcher<T> matcher) {
        MatcherAssert.assertThat(reason, actual, matcher);
    }

}
