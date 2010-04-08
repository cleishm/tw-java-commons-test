package com.thoughtworks.test.matchers;

import static com.thoughtworks.test.matchers.Matchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Description;

public class SameLinesOfTextMatcher extends BaseVerifyingMatcher<String> {
    private final String[] expectedLines;

    public SameLinesOfTextMatcher(final String expectedLines) {
        this.expectedLines = expectedLines.split("\n");
    }

    public void verifyMatches(final String string) throws VerificationError {
        final String[] actualLines = string.split("\n");
        verifyThat("different number of lines", actualLines.length, is(expectedLines.length));
        for (int i = 0; i < actualLines.length; i++) {
            final String actualLine = actualLines[i].trim();
            final String expectedLine = expectedLines[i].trim();
            verifyThat("line " + i + " doesn't match", actualLine, is(expectedLine));
        }
    }

    public void describeTo(final Description description) {
        description.appendValue(expectedLines);
    }

}
