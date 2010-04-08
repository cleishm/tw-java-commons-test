package com.thoughtworks.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class ResourceLoader {
    private ResourceLoader() {
    }

    public static String getSystemResourceAsString(final String name, final Charset charset)
            throws IOException {
        final InputStream stream = ClassLoader.getSystemResourceAsStream(name);
        if (stream == null) {
            throw new IOException("Resource not found");
        }

        return new ConsumingReader(new InputStreamReader(stream, charset)).readAll();
    }
}
