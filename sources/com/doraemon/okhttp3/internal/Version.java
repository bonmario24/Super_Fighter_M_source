package com.doraemon.okhttp3.internal;

public final class Version {
    public static String userAgent() {
        return "okhttp/${project.version}";
    }

    private Version() {
    }
}
