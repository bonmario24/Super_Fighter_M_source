package com.xhuyu.component.mvp.presenter.impl;

import android.graphics.Bitmap;
import com.xhuyu.component.core.api.OnDownloadListener;
import com.xhuyu.component.mvp.model.NoticeBean;
import com.xhuyu.component.mvp.presenter.NoticePresenter;
import com.xhuyu.component.mvp.view.NoticeView;
import com.xhuyu.component.network.NetBasicUtil;

public class NoticePresenterImpl implements NoticePresenter {
    /* access modifiers changed from: private */
    public NoticeView mView;

    public NoticePresenterImpl(NoticeView view) {
        this.mView = view;
    }

    public void doInitNotice(NoticeBean.NoticeData paramsBean) {
        if (paramsBean == null) {
            this.mView.onFinishActivity();
            return;
        }
        switch (paramsBean.getNotice_type()) {
            case 1:
                this.mView.openTextNotice(paramsBean.getNotice_content());
                return;
            case 2:
                downloadImage(paramsBean.getImage_url());
                return;
            default:
                return;
        }
    }

    private void downloadImage(String imageUrl) {
        this.mView.showDialog();
        NetBasicUtil.downloadImage(imageUrl, new OnDownloadListener() {
            public void onResult(Object object) {
                NoticePresenterImpl.this.mView.closeLoadingDialog();
                if (object instanceof Bitmap) {
                    NoticePresenterImpl.this.mView.openImageNotice((Bitmap) object);
                } else {
                    NoticePresenterImpl.this.mView.onFinishActivity();
                }
            }

            public void onError(String failMessage) {
                NoticePresenterImpl.this.mView.closeLoadingDialog();
                NoticePresenterImpl.this.mView.onFinishActivity();
            }
        });
    }
}
