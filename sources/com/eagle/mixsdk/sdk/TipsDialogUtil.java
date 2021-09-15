package com.eagle.mixsdk.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.eagle.mixsdk.sdk.utils.ResPluginUtil;

public class TipsDialogUtil {
    private static TipsDialogUtil instance;

    private TipsDialogUtil() {
    }

    public static TipsDialogUtil getInstance() {
        if (instance != null) {
            return instance;
        }
        TipsDialogUtil tipsDialogUtil = new TipsDialogUtil();
        instance = tipsDialogUtil;
        return tipsDialogUtil;
    }

    public void createDialog(Activity context, String tips) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(ResPluginUtil.getStringByName("xeagle_tip_title"));
        builder.setMessage(tips);
        builder.setCancelable(true);
        builder.setPositiveButton(ResPluginUtil.getStringByName("xeagle_confirm"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }
}
