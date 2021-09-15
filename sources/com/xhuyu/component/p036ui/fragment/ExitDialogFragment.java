package com.xhuyu.component.p036ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.xhuyu.component.core.api.SDKEventPost;
import com.xhuyu.component.core.manager.UserManager;
import com.xsdk.doraemon.base.BaseDialogFragment;
import com.xsdk.doraemon.utils.UiCalculateUtil;

/* renamed from: com.xhuyu.component.ui.fragment.ExitDialogFragment */
public class ExitDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private Button btnBack;
    private Button btnExit;
    private View mContentView;
    private RelativeLayout mRelContains;

    public static ExitDialogFragment newInstance() {
        Bundle args = new Bundle();
        ExitDialogFragment fragment = new ExitDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.mContentView = getContentView("layout_exit");
        this.mRelContains = (RelativeLayout) getViewByName(this.mContentView, "rel_contains");
        setupUI();
        autoInflaterUI();
        return this.mContentView;
    }

    private void setupUI() {
        this.btnExit = (Button) getViewByName(this.mContentView, "btn_exit");
        this.btnBack = (Button) getViewByName(this.mContentView, "btn_back");
        this.btnExit.setOnClickListener(this);
        this.btnBack.setOnClickListener(this);
    }

    private void autoInflaterUI() {
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(UiCalculateUtil.calculateTheLayoutWidth(this.mActivity), -2);
        rlp.addRule(13);
        this.mRelContains.setLayoutParams(rlp);
    }

    public void onClick(View v) {
        if (v.getId() == this.btnBack.getId()) {
            dismiss();
        } else if (v.getId() == this.btnExit.getId()) {
            SDKEventPost.post(7, new Object[0]);
            UserManager.getInstance().exitGame();
            dismiss();
        }
    }
}
