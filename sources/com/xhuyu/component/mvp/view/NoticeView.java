package com.xhuyu.component.mvp.view;

import android.graphics.Bitmap;
import com.xhuyu.component.core.base.BaseView;

public interface NoticeView extends BaseView {
    void onFinishActivity();

    void openImageNotice(Bitmap bitmap);

    void openTextNotice(String str);
}
