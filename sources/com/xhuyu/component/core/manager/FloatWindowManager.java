package com.xhuyu.component.core.manager;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import com.doraemon.util.SizeUtils;
import com.xhuyu.component.core.api.HuYuApi;
import com.xhuyu.component.core.config.Constant;
import com.xhuyu.component.utils.ActivityUtil;
import com.xsdk.doraemon.apkreflect.ReflectResource;
import com.xsdk.doraemon.utils.ContextUtil;
import com.xsdk.doraemon.utils.notch.callback.OnDisplayCutoutListener;
import com.xsdk.doraemon.utils.notch.utils.DisplayCutoutUtil;
import com.xsdk.doraemon.widget.floatball.FloatWindow;
import java.util.ArrayList;
import java.util.List;

public class FloatWindowManager {
    private static FloatWindowManager instance = null;
    private OnDisplayCutoutListener listener = new OnDisplayCutoutListener() {
        public void onDisplayCutout(DisplayCutout cutout, boolean isNotch) {
            if (Build.VERSION.SDK_INT >= 28) {
                FloatWindowManager.this.rectArray.clear();
                if (isNotch && cutout != null) {
                    List<Rect> rects = cutout.getBoundingRects();
                    if (rects.size() == 0) {
                        Log.e(Constant.LOG_TAG, "rects.size()==0, is not notch screen");
                    }
                    FloatWindowManager.this.rectArray.addAll(rects);
                }
                FloatWindowManager.this.refreshFloatLocation();
            }
        }
    };
    private FloatWindow mFloatWindow;
    /* access modifiers changed from: private */
    public List<Rect> rectArray = new ArrayList();

    private FloatWindowManager() {
    }

    public static FloatWindowManager getInstance() {
        if (instance == null) {
            synchronized (FloatWindowManager.class) {
                if (instance == null) {
                    instance = new FloatWindowManager();
                }
            }
        }
        return instance;
    }

    public void init(Activity activity) {
        ContextUtil.getInstance().setGameMainActivity(activity);
    }

    private FloatWindow getFloatWindow() {
        if (this.mFloatWindow == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                DisplayCutoutUtil.controlView(ContextUtil.getInstance().getActivity(), this.listener);
            }
            Drawable normal = ReflectResource.getInstance(ContextUtil.getInstance().getActivity()).getDrawable("float_normal");
            Drawable drawing = ReflectResource.getInstance(ContextUtil.getInstance().getActivity()).getDrawable("float_press");
            this.mFloatWindow = new FloatWindow(ContextUtil.getInstance().getActivity(), FloatWindow.createSimpleImageView(ReflectResource.getInstance(ContextUtil.getInstance().getActivity()).getProxyContext(), normal, SizeUtils.dp2px(45.0f)));
            this.mFloatWindow.setDrawableDraging(drawing);
            this.mFloatWindow.setDrawableNormal(normal);
            this.mFloatWindow.setSideEnable(6);
            this.mFloatWindow.getContentView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Activity activity = ContextUtil.getInstance().getActivity();
                    if (!FloatWindowManager.this.isDestroy(activity)) {
                        FloatWindowManager.this.destroy();
                        HuYuApi.getInstance().doOpenUserCenter(activity);
                    }
                }
            });
        }
        return this.mFloatWindow;
    }

    public void show() {
        try {
            if (UserManager.getInstance().getLoginUser() != null) {
                Activity activity = ContextUtil.getInstance().getActivity();
                if (!isDestroy(activity) && !ActivityUtil.isSDKActivity(activity)) {
                    FloatWindow floatWindow = getFloatWindow();
                    if (!floatWindow.isAttach()) {
                        floatWindow.attach(activity);
                    }
                    floatWindow.show();
                }
            }
        } catch (Exception e) {
            release();
        }
    }

    public void dismiss() {
        try {
            if (!isDestroy(ContextUtil.getInstance().getActivity())) {
                FloatWindow floatWindow = getFloatWindow();
                if (floatWindow.isAttach()) {
                    floatWindow.dismiss();
                }
            }
        } catch (Exception e) {
            release();
        }
    }

    public void release() {
        destroy();
        this.mFloatWindow = null;
    }

    /* access modifiers changed from: private */
    public void refreshFloatLocation() {
        try {
            if (!isDestroy(ContextUtil.getInstance().getActivity())) {
                FloatWindow floatWindow = getFloatWindow();
                floatWindow.setRectArray(this.rectArray);
                if (floatWindow.isAttach()) {
                    floatWindow.scrollToStartXY();
                }
            }
        } catch (Exception e) {
            release();
        }
    }

    public void destroy() {
        try {
            FloatWindow floatWindow = getFloatWindow();
            if (floatWindow != null) {
                floatWindow.dismiss();
                floatWindow.detach();
            }
        } catch (Exception e) {
            this.mFloatWindow = null;
        }
    }

    public List<Rect> getRectArray() {
        return this.rectArray;
    }

    /* access modifiers changed from: private */
    public boolean isDestroy(Activity activity) {
        return activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed());
    }
}
