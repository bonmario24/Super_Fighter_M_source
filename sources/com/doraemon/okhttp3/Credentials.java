package com.doraemon.okhttp3;

import com.doraemon.okhttp3.internal.Util;
import com.doraemon.okio.ByteString;
import java.nio.charset.Charset;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String username, String password) {
        return basic(username, password, Util.ISO_8859_1);
    }

    public static String basic(String username, String password, Charset charset) {
        return "Basic " + ByteString.encodeString(username + ":" + password, charset).base64();
    }
}
