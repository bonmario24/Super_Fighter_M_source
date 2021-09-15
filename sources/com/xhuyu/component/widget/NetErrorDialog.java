package com.xhuyu.component.widget;

import android.app.Activity;
import com.xhuyu.component.widget.TwoButtonsDialog;

public class NetErrorDialog extends TwoButtonsDialog {
    public NetErrorDialog(Activity activity, TwoButtonsDialog.OnButtonCallbackListener listener) {
        super(activity, listener);
        setButtonOneText("huyu_retry");
        setButtonTwoText("exit");
        seTitleText("huyu_title_warning");
        seTipDetailText("network_error");
    }
}
