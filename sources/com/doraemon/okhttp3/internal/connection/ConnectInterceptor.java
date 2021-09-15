package com.doraemon.okhttp3.internal.connection;

import com.doraemon.okhttp3.Interceptor;
import com.doraemon.okhttp3.OkHttpClient;
import com.doraemon.okhttp3.Request;
import com.doraemon.okhttp3.Response;
import com.doraemon.okhttp3.internal.http.RealInterceptorChain;
import java.io.IOException;

public final class ConnectInterceptor implements Interceptor {
    public final OkHttpClient client;

    public ConnectInterceptor(OkHttpClient client2) {
        this.client = client2;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Request request = realChain.request();
        StreamAllocation streamAllocation = realChain.streamAllocation();
        return realChain.proceed(request, streamAllocation, streamAllocation.newStream(this.client, chain, !request.method().equals("GET")), streamAllocation.connection());
    }
}
