package com.mohammadhendy.catfacts.base.Utils;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public final class Preconditions {
    private Preconditions() {
        throw new IllegalStateException("No Instances");
    }

    public static <T> T checkNotNull(T o) {
        if (o == null)
            throw new NullPointerException("instance can't be null");
        else
            return o;
    }

    public static <T> T checkNotNull(T o, String msg) {
        if (o == null)
            throw new NullPointerException(msg);
        else
            return o;
    }
}
