package com.thinkfly.plugins.coludladder.utils;

import com.doraemon.p027eg.CheckUtils;
import java.net.MalformedURLException;
import java.net.URL;

public class HostUtils {
    public static String getRoot(String host) {
        if (CheckUtils.isNullOrEmpty(host)) {
            return null;
        }
        try {
            URL url = new URL(host);
            int port = url.getPort();
            if (port == -1 || port == 80) {
                return url.getProtocol() + "://" + url.getHost();
            }
            return url.getProtocol() + "://" + url.getHost() + ":" + port;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            String tag = "com";
            if (host.contains("com")) {
                tag = "com";
            } else if (host.contains("xyz")) {
                tag = "xyz";
            } else if (host.contains("net")) {
                tag = "net";
            } else if (host.contains("top")) {
                tag = "top";
            } else if (host.contains("tech")) {
                tag = "tech";
            } else if (host.contains("org")) {
                tag = "org";
            } else if (host.contains("gov")) {
                tag = "gov";
            } else if (host.contains("edu")) {
                tag = "edu";
            } else if (host.contains(".ink")) {
                tag = ".ink";
            } else if (host.contains("red")) {
                tag = "red";
            } else if (host.contains("pub")) {
                tag = "pub";
            } else if (host.contains("cn")) {
                tag = "cn";
            }
            return host.split(tag)[0] + tag;
        }
    }
}
