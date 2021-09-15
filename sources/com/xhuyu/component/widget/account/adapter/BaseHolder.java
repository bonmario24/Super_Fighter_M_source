package com.xhuyu.component.widget.account.adapter;

import android.view.View;

public abstract class BaseHolder<T> {
    public abstract void bindView(int i, T t);

    public abstract void initView(View view);
}
