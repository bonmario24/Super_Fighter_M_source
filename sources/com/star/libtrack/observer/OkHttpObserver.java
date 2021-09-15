package com.star.libtrack.observer;

import com.star.libtrack.event.Event;
import okhttp3.Request;
import okhttp3.Response;

public abstract class OkHttpObserver extends BaseObserver {
    public abstract void onHttpRequest(Request request);

    public abstract void onHttpResponse(Response response);

    public void handEvent(Event event) {
        switch (event.getCode()) {
            case 0:
                onHttpRequest((Request) event.getExt());
                return;
            case 1:
                onHttpResponse((Response) event.getExt());
                return;
            default:
                return;
        }
    }
}
