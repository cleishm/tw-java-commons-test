package com.thoughtworks.test.matchers;

import java.io.IOException;
import java.io.Reader;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.thoughtworks.test.ConsumingReader;


public class ReaderWithContent extends TypeSafeMatcher<Reader> {
    private final String expected;

    public ReaderWithContent(final String expected) {
        this.expected = expected;
    }

    public boolean matchesSafely(final Reader reader) {
        final String string;
        try {
            final ConsumingReader consumingReader = new ConsumingReader(reader);
            string = consumingReader.readAll();
            consumingReader.reset();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return expected.equals(string);
    }

    public void describeTo(final Description description) {
        description.appendText("reader containing '" + expected + "'");
    }
}
