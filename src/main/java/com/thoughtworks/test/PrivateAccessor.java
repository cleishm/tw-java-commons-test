package com.thoughtworks.test;

import java.lang.reflect.Field;

public final class PrivateAccessor {
    private PrivateAccessor() {
    }

    public static Object getPrivateField(final Object obj, final String fieldName) throws NoSuchFieldException {
        try {
            final Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPrivateField(final Object obj, final String fieldName, final Object value)
            throws NoSuchFieldException {
        try {
            final Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
