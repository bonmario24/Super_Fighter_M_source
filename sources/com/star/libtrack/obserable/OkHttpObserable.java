package com.star.libtrack.obserable;

import com.star.libtrack.event.Event;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpObserable extends BaseObserable {
    public static final int CODE_REQUEST = 0;
    public static final int CODE_RESPONSE = 1;
    public static final String OKHTTP_TAG = "track_tag";
    private OkHttpClient client;

    public OkHttpClient observer(OkHttpClient client2) {
        OkHttpClient newClient = client2.newBuilder().addInterceptor(new HttpObserverInterceptor()).build();
        this.client = newClient;
        return newClient;
    }

    private class HttpObserverInterceptor implements Interceptor {
        private HttpObserverInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            String tag = request.header(OkHttpObserable.OKHTTP_TAG);
            OkHttpObserable.this.dispatch(new Event(0, request, (String) null));
            Response response = chain.proceed(request).newBuilder().header(OkHttpObserable.OKHTTP_TAG, tag).build();
            OkHttpObserable.this.dispatch(new Event(1, response, (String) null));
            return response;
        }
    }
}
