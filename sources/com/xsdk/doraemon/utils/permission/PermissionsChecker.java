package com.xsdk.doraemon.utils.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.xsdk.doraemon.utils.logger.SDKLoggerUtil;

public class PermissionsChecker {
    private static final String PACKAGE_URL_SCHEME = "package:";
    /* access modifiers changed from: private */
    public final Activity mActivity;

    public PermissionsChecker(Activity activity) {
        this.mActivity = activity;
    }

    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(this.mActivity, permission) == -1;
    }

    public void createPermissionDialog(int messageStringResID) {
        new AlertDialog.Builder(this.mActivity).setMessage(messageStringResID).setTitle("Permission required").setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PermissionsChecker.this.startAppSettings();
            }
        }).setNegativeButton("cancel", (DialogInterface.OnClickListener) null).show();
    }

    public void showMissingPermissionDialog(int messageStringResID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle("WARNING");
        builder.setMessage(messageStringResID);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    PermissionsChecker.this.mActivity.finish();
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setPositiveButton("setting permission", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                PermissionsChecker.this.startAppSettings();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == -1) {
                return false;
            }
        }
        return true;
    }

    public void startAppSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + this.mActivity.getPackageName()));
        this.mActivity.startActivity(intent);
    }

    public void requestPermissions(int requestCode, String... permissions) {
        if (this.mActivity instanceof Activity) {
            ActivityCompat.requestPermissions(this.mActivity, permissions, requestCode);
        } else {
            SDKLoggerUtil.getLogger().mo19502e("is a not activity", new Object[0]);
        }
    }
}
