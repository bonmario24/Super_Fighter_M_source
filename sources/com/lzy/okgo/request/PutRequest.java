package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PutRequest<T> extends BodyRequest<T, PutRequest<T>> {
    public PutRequest(String url) {
        super(url);
    }

    public HttpMethod getMethod() {
        return HttpMethod.PUT;
    }

    public Request generateRequest(RequestBody requestBody) {
        return generateRequestBuilder(requestBody).put(requestBody).url(this.url).tag(this.tag).build();
    }
}
