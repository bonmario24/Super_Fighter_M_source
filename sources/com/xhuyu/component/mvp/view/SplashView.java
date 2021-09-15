package com.xhuyu.component.mvp.view;

import com.xhuyu.component.core.base.BaseView;

public interface SplashView extends BaseView {
    void jumpGame();

    void loadUrl(String str);

    void openFailDialog(String str);

    void showToastMessage(String str);
}
