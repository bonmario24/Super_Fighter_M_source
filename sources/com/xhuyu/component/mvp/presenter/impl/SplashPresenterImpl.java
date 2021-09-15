package com.xhuyu.component.mvp.presenter.impl;

import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.config.TrackEventKey;
import com.xhuyu.component.mvp.presenter.SplashPresenter;
import com.xhuyu.component.mvp.view.SplashView;
import com.xhuyu.component.network.NetBasicUtil;
import org.json.JSONObject;

public class SplashPresenterImpl implements SplashPresenter {
    private static final int ENTER_TYPE_LINE = 1;
    private static final int ENTER_TYPE_OTHER = 2;
    SplashView mView;

    public SplashPresenterImpl(SplashView view) {
        this.mView = view;
    }

    public void doRequestDuplicate() {
        this.mView.showDialog();
        NetBasicUtil.doRequestDuplicate(new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                SplashPresenterImpl.this.mView.closeLoadingDialog();
                int entryType = dataJson.optInt("entry", 0);
                String url = dataJson.optString("url");
                SplashPresenterImpl.this.postTrack(entryType);
                switch (entryType) {
                    case 1:
                        SplashPresenterImpl.this.mView.jumpGame();
                        return;
                    case 2:
                        SplashPresenterImpl.this.mView.loadUrl(url);
                        return;
                    default:
                        return;
                }
            }

            public void onFail(String message, int code) {
                SplashPresenterImpl.this.postTrack(0);
                SplashPresenterImpl.this.mView.showToastMessage(message);
                SplashPresenterImpl.this.mView.closeLoadingDialog();
                SplashPresenterImpl.this.mView.openFailDialog("network_error");
            }
        });
    }

    /* access modifiers changed from: private */
    public void postTrack(int enter_type) {
        SDKEventPost.postTrack(TrackEventKey.ON_DUPLICATE_RESULT, Integer.valueOf(enter_type));
    }
}
