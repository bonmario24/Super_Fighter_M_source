package com.doraemon.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import com.doraemon.constant.PermissionConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class PermissionUtils {
    private static final List<String> PERMISSIONS = getPermissions();
    /* access modifiers changed from: private */
    public static PermissionUtils sInstance;
    /* access modifiers changed from: private */
    public static SimpleCallback sSimpleCallback4DrawOverlays;
    /* access modifiers changed from: private */
    public static SimpleCallback sSimpleCallback4WriteSettings;
    private FullCallback mFullCallback;
    private OnRationaleListener mOnRationaleListener;
    private Set<String> mPermissions = new LinkedHashSet();
    private List<String> mPermissionsDenied;
    private List<String> mPermissionsDeniedForever;
    private List<String> mPermissionsGranted;
    /* access modifiers changed from: private */
    public List<String> mPermissionsRequest;
    private SimpleCallback mSimpleCallback;
    /* access modifiers changed from: private */
    public ThemeCallback mThemeCallback;

    public interface FullCallback {
        void onDenied(List<String> list, List<String> list2);

        void onGranted(List<String> list);
    }

    public interface OnRationaleListener {

        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(ShouldRequest shouldRequest);
    }

    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    public static List<String> getPermissions(String packageName) {
        try {
            String[] permissions = Utils.getApp().getPackageManager().getPackageInfo(packageName, 4096).requestedPermissions;
            if (permissions == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(permissions);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean isGranted(String... permissions) {
        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGranted(String permission) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.getApp(), permission) == 0;
    }

    @RequiresApi(api = 23)
    public static boolean isGrantedWriteSettings() {
        return Settings.System.canWrite(Utils.getApp());
    }

    @RequiresApi(api = 23)
    public static void requestWriteSettings(SimpleCallback callback) {
        if (!isGrantedWriteSettings()) {
            sSimpleCallback4WriteSettings = callback;
            PermissionActivity.start(Utils.getApp(), 2);
        } else if (callback != null) {
            callback.onGranted();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public static void startWriteSettingsActivity(Activity activity, int requestCode) {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!isIntentAvailable(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    @RequiresApi(api = 23)
    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(Utils.getApp());
    }

    @RequiresApi(api = 23)
    public static void requestDrawOverlays(SimpleCallback callback) {
        if (!isGrantedDrawOverlays()) {
            sSimpleCallback4DrawOverlays = callback;
            PermissionActivity.start(Utils.getApp(), 3);
        } else if (callback != null) {
            callback.onGranted();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public static void startOverlayPermissionActivity(Activity activity, int requestCode) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!isIntentAvailable(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (isIntentAvailable(intent)) {
            Utils.getApp().startActivity(intent.addFlags(268435456));
        }
    }

    public static PermissionUtils permission(String... permissions) {
        return new PermissionUtils(permissions);
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private PermissionUtils(String... permissions) {
        for (String permission : permissions) {
            for (String aPermission : PermissionConstants.getPermissions(permission)) {
                if (PERMISSIONS.contains(aPermission)) {
                    this.mPermissions.add(aPermission);
                }
            }
        }
        sInstance = this;
    }

    public PermissionUtils rationale(OnRationaleListener listener) {
        this.mOnRationaleListener = listener;
        return this;
    }

    public PermissionUtils callback(SimpleCallback callback) {
        this.mSimpleCallback = callback;
        return this;
    }

    public PermissionUtils callback(FullCallback callback) {
        this.mFullCallback = callback;
        return this;
    }

    public PermissionUtils theme(ThemeCallback callback) {
        this.mThemeCallback = callback;
        return this;
    }

    public void request() {
        this.mPermissionsGranted = new ArrayList();
        this.mPermissionsRequest = new ArrayList();
        if (Build.VERSION.SDK_INT < 23) {
            this.mPermissionsGranted.addAll(this.mPermissions);
            requestCallback();
            return;
        }
        for (String permission : this.mPermissions) {
            if (isGranted(permission)) {
                this.mPermissionsGranted.add(permission);
            } else {
                this.mPermissionsRequest.add(permission);
            }
        }
        if (this.mPermissionsRequest.isEmpty()) {
            requestCallback();
        } else {
            startPermissionActivity();
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 23)
    public void startPermissionActivity() {
        this.mPermissionsDenied = new ArrayList();
        this.mPermissionsDeniedForever = new ArrayList();
        PermissionActivity.start(Utils.getApp(), 1);
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 23)
    public boolean rationale(final Activity activity) {
        boolean isRationale = false;
        if (this.mOnRationaleListener != null) {
            Iterator<String> it = this.mPermissionsRequest.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (activity.shouldShowRequestPermissionRationale(it.next())) {
                        getPermissionsStatus(activity);
                        this.mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() {
                            public void again(boolean again) {
                                activity.finish();
                                if (again) {
                                    PermissionUtils.this.startPermissionActivity();
                                } else {
                                    PermissionUtils.this.requestCallback();
                                }
                            }
                        });
                        isRationale = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.mOnRationaleListener = null;
        }
        return isRationale;
    }

    private void getPermissionsStatus(Activity activity) {
        for (String permission : this.mPermissionsRequest) {
            if (isGranted(permission)) {
                this.mPermissionsGranted.add(permission);
            } else {
                this.mPermissionsDenied.add(permission);
                if (!activity.shouldShowRequestPermissionRationale(permission)) {
                    this.mPermissionsDeniedForever.add(permission);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void requestCallback() {
        if (this.mSimpleCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mSimpleCallback.onGranted();
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mSimpleCallback.onDenied();
            }
            this.mSimpleCallback = null;
        }
        if (this.mFullCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mFullCallback.onGranted(this.mPermissionsGranted);
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mFullCallback.onDenied(this.mPermissionsDeniedForever, this.mPermissionsDenied);
            }
            this.mFullCallback = null;
        }
        this.mOnRationaleListener = null;
        this.mThemeCallback = null;
    }

    /* access modifiers changed from: private */
    public void onRequestPermissionsResult(Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }

    @RequiresApi(api = 23)
    public static class PermissionActivity extends Activity {
        private static final String TYPE = "TYPE";
        public static final int TYPE_DRAW_OVERLAYS = 3;
        public static final int TYPE_RUNTIME = 1;
        public static final int TYPE_WRITE_SETTINGS = 2;

        public static void start(Context context, int type) {
            Intent starter = new Intent(context, PermissionActivity.class);
            starter.addFlags(268435456);
            starter.putExtra(TYPE, type);
            context.startActivity(starter);
        }

        /* access modifiers changed from: protected */
        public void onCreate(@Nullable Bundle savedInstanceState) {
            getWindow().addFlags(262160);
            int byteExtra = getIntent().getIntExtra(TYPE, 1);
            if (byteExtra == 1) {
                if (PermissionUtils.sInstance == null) {
                    super.onCreate(savedInstanceState);
                    Log.e("PermissionUtils", "request permissions failed");
                    finish();
                    return;
                }
                if (PermissionUtils.sInstance.mThemeCallback != null) {
                    PermissionUtils.sInstance.mThemeCallback.onActivityCreate(this);
                }
                super.onCreate(savedInstanceState);
                if (!PermissionUtils.sInstance.rationale((Activity) this) && PermissionUtils.sInstance.mPermissionsRequest != null) {
                    int size = PermissionUtils.sInstance.mPermissionsRequest.size();
                    if (size <= 0) {
                        finish();
                    } else {
                        requestPermissions((String[]) PermissionUtils.sInstance.mPermissionsRequest.toArray(new String[size]), 1);
                    }
                }
            } else if (byteExtra == 2) {
                super.onCreate(savedInstanceState);
                PermissionUtils.startWriteSettingsActivity(this, 2);
            } else if (byteExtra == 3) {
                super.onCreate(savedInstanceState);
                PermissionUtils.startOverlayPermissionActivity(this, 3);
            }
        }

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (!(PermissionUtils.sInstance == null || PermissionUtils.sInstance.mPermissionsRequest == null)) {
                PermissionUtils.sInstance.onRequestPermissionsResult(this);
            }
            finish();
        }

        public boolean dispatchTouchEvent(MotionEvent ev) {
            finish();
            return true;
        }

        /* access modifiers changed from: protected */
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 2) {
                if (PermissionUtils.sSimpleCallback4WriteSettings != null) {
                    if (PermissionUtils.isGrantedWriteSettings()) {
                        PermissionUtils.sSimpleCallback4WriteSettings.onGranted();
                    } else {
                        PermissionUtils.sSimpleCallback4WriteSettings.onDenied();
                    }
                    SimpleCallback unused = PermissionUtils.sSimpleCallback4WriteSettings = null;
                } else {
                    return;
                }
            } else if (requestCode == 3) {
                if (PermissionUtils.sSimpleCallback4DrawOverlays != null) {
                    Utils.runOnUiThreadDelayed(new Runnable() {
                        public void run() {
                            if (PermissionUtils.isGrantedDrawOverlays()) {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onGranted();
                            } else {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onDenied();
                            }
                            SimpleCallback unused = PermissionUtils.sSimpleCallback4DrawOverlays = null;
                        }
                    }, 100);
                } else {
                    return;
                }
            }
            finish();
        }
    }
}
