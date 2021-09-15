package com.xhuyu.component.mvp.presenter;

public interface AutoLoginFragmentPresenter {
    void createCountDownTimer();

    void dismissCountDown();

    void postLoginResult(int i, String str);
}
