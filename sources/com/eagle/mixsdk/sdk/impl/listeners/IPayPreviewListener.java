package com.eagle.mixsdk.sdk.impl.listeners;

import com.eagle.mixsdk.sdk.PayParams;

public interface IPayPreviewListener {
    void onPreview(PayParams payParams, IPayPreviewCallBack iPayPreviewCallBack);
}
