package com.lzy.okgo.request.base;

import com.lzy.okgo.request.base.NoBodyRequest;
import com.lzy.okgo.utils.HttpUtils;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class NoBodyRequest<T, R extends NoBodyRequest> extends Request<T, R> {
    private static final long serialVersionUID = 1200621102761691196L;

    public NoBodyRequest(String url) {
        super(url);
    }

    public RequestBody generateRequestBody() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Request.Builder generateRequestBuilder(RequestBody requestBody) {
        this.url = HttpUtils.createUrlFromParams(this.baseUrl, this.params.urlParamsMap);
        return HttpUtils.appendHeaders(new Request.Builder(), this.headers);
    }
}
