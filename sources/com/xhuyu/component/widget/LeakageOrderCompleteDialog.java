package com.xhuyu.component.widget;

import android.app.Activity;
import com.xhuyu.component.utils.ResourceUtil;
import com.xhuyu.component.widget.TwoButtonsDialog;

public class LeakageOrderCompleteDialog extends TwoButtonsDialog {
    public LeakageOrderCompleteDialog(Activity activity, TwoButtonsDialog.OnButtonCallbackListener listener) {
        super(activity, listener);
        seTitleText("huyu_title_warning");
        setButtonOneText("huyu_cancel_pay");
        setBtnOneBackgroundResource(ResourceUtil.getDrawableId("selector_btn_gray"));
        setButtonTwoText("huyu_next_pay");
        setBtnTwoBackgroundResource(ResourceUtil.getDrawableId("selector_btn_red"));
    }

    public void setMessage(String orderID) {
        seTipDetailText("huyu_leakage_tip", orderID);
    }
}
