package com.thoughtworks.test;

import java.io.IOException;
import java.io.Reader;

public class ConsumingReader extends Reader {
    private final Reader delegateReader;

    public ConsumingReader(final Reader delegateReader) {
        this.delegateReader = delegateReader;
    }

    @Override
    public void close() throws IOException {
        delegateReader.close();
    }

    @Override
    public int read(final char[] arg0, final int arg1, final int arg2) throws IOException {
        return delegateReader.read(arg0, arg1, arg2);
    }

    @Override
    public void reset() throws IOException {
        delegateReader.reset();
    }

    public String readAll() throws IOException {
        final StringBuffer buffer = new StringBuffer();
        final char[] cbuf = new char[1000];
        int n;
        while ((n = delegateReader.read(cbuf, 0, 1000)) != -1) {
            buffer.append(cbuf, 0, n);
        }
        return buffer.toString();
    }

}
