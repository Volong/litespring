package org.litespring.litespring;

public class Assert {

    public static void notNull(String path, String message) {

        if (path == null) {
            throw new IllegalArgumentException(message);
        }

    }
}
