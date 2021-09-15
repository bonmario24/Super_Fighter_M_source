package com.doraemon.okhttp3.internal.http;

import com.doraemon.okhttp3.Challenge;
import com.doraemon.okhttp3.Cookie;
import com.doraemon.okhttp3.CookieJar;
import com.doraemon.okhttp3.Headers;
import com.doraemon.okhttp3.HttpUrl;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.internal.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpHeaders {
    private static final Pattern AUTHENTICATION_HEADER_VALUE_PATTERN = Pattern.compile("^(?:,[ \\t]*)*[!#$%&'*+.^_`|~\\p{Alnum}-]+(?: +(?:[\\p{Alnum}._~+/-]+=*|(?:,|[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))(?:[ \\t]*,(?:[ \\t]*[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))?)*)?)?(?:[ \\t]*,(?:[ \\t]*[!#$%&'*+.^_`|~\\p{Alnum}-]+(?: +(?:[\\p{Alnum}._~+/-]+=*|(?:,|[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))(?:[ \\t]*,(?:[ \\t]*[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))?)*)?)?)?)*$");
    private static final Pattern AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN = Pattern.compile(AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN_PART);
    private static final String AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN_PART = "(?:[ \\t]*,[ \\t]*)+";
    private static final Pattern AUTH_PARAM_PATTERN = Pattern.compile("^[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\")$");
    private static final String AUTH_PARAM_PATTERN_PART = "[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\")";
    private static final Pattern AUTH_PARAM_SPLIT_PATTERN = Pattern.compile("[ \\t]*=[ \\t]*");
    private static final Pattern AUTH_SCHEME_AND_PARAM_PATTERN = Pattern.compile("^[!#$%&'*+.^_`|~\\p{Alnum}-]+ +[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\")$");
    private static final Pattern AUTH_SCHEME_AND_TOKEN68_PATTERN = Pattern.compile("^[!#$%&'*+.^_`|~\\p{Alnum}-]+ +[\\p{Alnum}._~+/-]+=*$");
    private static final Pattern AUTH_SCHEME_PATTERN = Pattern.compile("^[!#$%&'*+.^_`|~\\p{Alnum}-]+$");
    private static final String CHALLENGE_PATTERN_PART = "[!#$%&'*+.^_`|~\\p{Alnum}-]+(?: +(?:[\\p{Alnum}._~+/-]+=*|(?:,|[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))(?:[ \\t]*,(?:[ \\t]*[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*(?:[!#$%&'*+.^_`|~\\p{Alnum}-]+|\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"))?)*)?)?";
    private static final String OWS_PATTERN_PART = "[ \\t]*";
    private static final Pattern QUOTED_PAIR_PATTERN = Pattern.compile(QUOTED_PAIR_PATTERN_PART);
    private static final String QUOTED_PAIR_PATTERN_PART = "\\\\([\\t \\p{Graph}\\x80-\\xFF])";
    private static final Pattern QUOTED_STRING_AUTH_PARAM_AT_END_PATTERN = Pattern.compile("[!#$%&'*+.^_`|~\\p{Alnum}-]+[ \\t]*=[ \\t]*\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"$");
    private static final String QUOTED_STRING_PATTERN_PART = "\"(?:[\\t \\x21\\x23-\\x5B\\x5D-\\x7E\\x80-\\xFF]|\\\\([\\t \\p{Graph}\\x80-\\xFF]))*\"";
    private static final String TOKEN68_PATTERN_PART = "[\\p{Alnum}._~+/-]+=*";
    private static final Pattern TOKEN_PATTERN = Pattern.compile("^[!#$%&'*+.^_`|~\\p{Alnum}-]+$");
    private static final String TOKEN_PATTERN_PART = "[!#$%&'*+.^_`|~\\p{Alnum}-]+";
    private static final Pattern WHITESPACE_SPLIT_PATTERN = Pattern.compile(" +");

    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_LENGTH));
    }

    private static long stringToLong(String s) {
        if (s == null) {
            return -1;
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean varyMatches(Response cachedResponse, Headers cachedRequest, Request newRequest) {
        for (String field : varyFields(cachedResponse)) {
            if (!Util.equal(cachedRequest.values(field), newRequest.headers(field))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    public static boolean hasVaryAll(Headers responseHeaders) {
        return varyFields(responseHeaders).contains("*");
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Set<String> varyFields(Headers responseHeaders) {
        Set<String> result = Collections.emptySet();
        int size = responseHeaders.size();
        for (int i = 0; i < size; i++) {
            if ("Vary".equalsIgnoreCase(responseHeaders.name(i))) {
                String value = responseHeaders.value(i);
                if (result.isEmpty()) {
                    result = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                for (String varyField : value.split(",")) {
                    result.add(varyField.trim());
                }
            }
        }
        return result;
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
        Set<String> varyFields = varyFields(responseHeaders);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder result = new Headers.Builder();
        int size = requestHeaders.size();
        for (int i = 0; i < size; i++) {
            String fieldName = requestHeaders.name(i);
            if (varyFields.contains(fieldName)) {
                result.add(fieldName, requestHeaders.value(i));
            }
        }
        return result.build();
    }

    public static List<Challenge> parseChallenges(Headers responseHeaders, String challengeHeader) {
        List<Challenge> challenges = new ArrayList<>();
        for (String header : responseHeaders.values(challengeHeader)) {
            if (AUTHENTICATION_HEADER_VALUE_PATTERN.matcher(header).matches()) {
                List<Challenge> headerChallenges = new ArrayList<>();
                String[] challengeParts = AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN.split(header);
                String authScheme = null;
                Map<String, String> authParams = new LinkedHashMap<>();
                int i = 0;
                int j = challengeParts.length;
                while (true) {
                    if (i >= j) {
                        headerChallenges.add(new Challenge(authScheme, authParams));
                        challenges.addAll(headerChallenges);
                        break;
                    }
                    String challengePart = challengeParts[i];
                    if (!challengePart.isEmpty()) {
                        String newAuthScheme = null;
                        String authParam = null;
                        if (AUTH_SCHEME_PATTERN.matcher(challengePart).matches()) {
                            newAuthScheme = challengePart;
                        } else if (AUTH_SCHEME_AND_TOKEN68_PATTERN.matcher(challengePart).matches()) {
                            String[] authSchemeAndToken68 = WHITESPACE_SPLIT_PATTERN.split(challengePart, 2);
                            newAuthScheme = authSchemeAndToken68[0];
                            if (authParams.put((Object) null, authSchemeAndToken68[1]) != null) {
                                throw new AssertionError();
                            }
                        } else if (AUTH_SCHEME_AND_PARAM_PATTERN.matcher(challengePart).matches()) {
                            String[] authSchemeAndParam = WHITESPACE_SPLIT_PATTERN.split(challengePart, 2);
                            newAuthScheme = authSchemeAndParam[0];
                            authParam = authSchemeAndParam[1];
                        } else if (AUTH_PARAM_PATTERN.matcher(challengePart).matches()) {
                            authParam = challengePart;
                        } else {
                            StringBuilder patternBuilder = new StringBuilder();
                            patternBuilder.append('^').append(Pattern.quote(challengeParts[0]));
                            for (int i2 = 1; i2 < i; i2++) {
                                patternBuilder.append(AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN_PART).append(Pattern.quote(challengeParts[i2]));
                            }
                            while (true) {
                                int i3 = i + 1;
                                patternBuilder.append(AUTHENTICATION_HEADER_VALUE_SPLIT_PATTERN_PART).append(Pattern.quote(challengeParts[i]));
                                Matcher matcher = Pattern.compile(patternBuilder.toString()).matcher(header);
                                if (!matcher.find()) {
                                    throw new AssertionError();
                                }
                                Matcher quotedStringAuthParamAtEndMatcher = QUOTED_STRING_AUTH_PARAM_AT_END_PATTERN.matcher(matcher.group());
                                if (quotedStringAuthParamAtEndMatcher.find()) {
                                    authParam = quotedStringAuthParamAtEndMatcher.group();
                                    i = i3;
                                    break;
                                }
                                i = i3;
                            }
                        }
                        if (newAuthScheme != null) {
                            if (authScheme != null) {
                                headerChallenges.add(new Challenge(authScheme, authParams));
                                authParams.clear();
                            }
                            authScheme = newAuthScheme;
                        }
                        if (authParam != null) {
                            String[] authParamPair = AUTH_PARAM_SPLIT_PATTERN.split(authParam, 2);
                            String authParamKey = authParamPair[0].toLowerCase(Locale.US);
                            String authParamValue = authParamPair[1];
                            if (!TOKEN_PATTERN.matcher(authParamValue).matches()) {
                                authParamValue = QUOTED_PAIR_PATTERN.matcher(authParamValue.substring(1, authParamValue.length() - 1)).replaceAll("$1");
                            }
                            if (authParams.put(authParamKey, authParamValue) != null) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
            }
        }
        return challenges;
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl url, Headers headers) {
        if (cookieJar != CookieJar.NO_COOKIES) {
            List<Cookie> cookies = Cookie.parseAll(url, headers);
            if (!cookies.isEmpty()) {
                cookieJar.saveFromResponse(url, cookies);
            }
        }
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals("HEAD")) {
            return false;
        }
        int responseCode = response.code();
        if ((responseCode < 100 || responseCode >= 200) && responseCode != 204 && responseCode != 304) {
            return true;
        }
        if (contentLength(response) != -1 || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    public static int skipUntil(String input, int pos, String characters) {
        while (pos < input.length() && characters.indexOf(input.charAt(pos)) == -1) {
            pos++;
        }
        return pos;
    }

    public static int skipWhitespace(String input, int pos) {
        while (pos < input.length() && ((c = input.charAt(pos)) == ' ' || c == 9)) {
            pos++;
        }
        return pos;
    }

    public static int parseSeconds(String value, int defaultValue) {
        try {
            long seconds = Long.parseLong(value);
            if (seconds > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (seconds < 0) {
                return 0;
            }
            return (int) seconds;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
