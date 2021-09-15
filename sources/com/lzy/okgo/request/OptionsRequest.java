package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OptionsRequest<T> extends BodyRequest<T, OptionsRequest<T>> {
    public OptionsRequest(String url) {
        super(url);
    }

    public HttpMethod getMethod() {
        return HttpMethod.OPTIONS;
    }

    public Request generateRequest(RequestBody requestBody) {
        return generateRequestBuilder(requestBody).method("OPTIONS", requestBody).url(this.url).tag(this.tag).build();
    }
}
