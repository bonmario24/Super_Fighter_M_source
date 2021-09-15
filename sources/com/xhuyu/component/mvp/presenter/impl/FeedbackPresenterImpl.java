package com.xhuyu.component.mvp.presenter.impl;

import com.thinkfly.star.utils.CheckUtils;
import com.xhuyu.component.core.api.GameSDKListener;
import com.xhuyu.component.mvp.presenter.FeedbackPresenter;
import com.xhuyu.component.mvp.view.FeedbackView;
import com.xhuyu.component.network.NetBasicUtil;
import org.json.JSONObject;

public class FeedbackPresenterImpl implements FeedbackPresenter {
    FeedbackView mView;

    public FeedbackPresenterImpl(FeedbackView view) {
        this.mView = view;
    }

    public void doSubmitFeedback(String content) {
        if (CheckUtils.isNullOrEmpty(content)) {
            this.mView.toastMessage("huyu_feedback_content_empty", true);
            return;
        }
        this.mView.showDialog();
        NetBasicUtil.doRequestFeedback(content, new GameSDKListener() {
            public void onSuccess(JSONObject dataJson, String message, Object... args) {
                FeedbackPresenterImpl.this.mView.closeLoadingDialog();
                FeedbackPresenterImpl.this.mView.toastMessage(message, false);
                FeedbackPresenterImpl.this.mView.onFinishActivity();
            }

            public void onFail(String message, int code) {
                FeedbackPresenterImpl.this.mView.closeLoadingDialog();
                FeedbackPresenterImpl.this.mView.toastMessage(message, false);
            }
        });
    }
}
