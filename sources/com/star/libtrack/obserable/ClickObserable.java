package com.star.libtrack.obserable;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.star.libtrack.core.ReflectObject;
import com.star.libtrack.event.Event;
import java.lang.ref.WeakReference;

public class ClickObserable extends BaseObserable {
    private WeakReference<Activity> activity;

    private ClickObserable(Activity activity2) {
        this.activity = new WeakReference<>(activity2);
    }

    public static ClickObserable observer(Activity activity2) {
        return new ClickObserable(activity2);
    }

    public void detach() {
        this.activity = null;
    }

    public boolean isDetach() {
        return this.activity == null || this.activity.get() == null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View getClickedView() {
        /*
            r6 = this;
            r4 = 0
            boolean r3 = r6.isDetach()
            if (r3 == 0) goto L_0x0009
            r0 = r4
        L_0x0008:
            return r0
        L_0x0009:
            java.lang.ref.WeakReference<android.app.Activity> r3 = r6.activity
            java.lang.Object r3 = r3.get()
            android.app.Activity r3 = (android.app.Activity) r3
            android.view.Window r3 = r3.getWindow()
            android.view.View r3 = r3.getDecorView()
            r5 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r3.findViewById(r5)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            if (r1 != 0) goto L_0x0026
            r0 = r4
            goto L_0x0008
        L_0x0026:
            java.lang.Object r2 = r6.getTouchTarget(r1)
            if (r2 != 0) goto L_0x002e
            r0 = r4
            goto L_0x0008
        L_0x002e:
            java.lang.Class r3 = r2.getClass()
            java.lang.String r5 = "child"
            java.lang.Object r0 = com.star.libtrack.core.ReflectObject.getFieldValue((java.lang.Class) r3, (java.lang.Object) r2, (java.lang.String) r5)
            android.view.View r0 = (android.view.View) r0
            if (r0 != 0) goto L_0x003e
            r0 = r4
            goto L_0x0008
        L_0x003e:
            boolean r3 = r0 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x0008
            r3 = r0
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            java.lang.Object r2 = r6.getTouchTarget(r3)
            if (r2 != 0) goto L_0x002e
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.star.libtrack.obserable.ClickObserable.getClickedView():android.view.View");
    }

    private Object getTouchTarget(ViewGroup vp) {
        return ReflectObject.getFieldValue(ViewGroup.class, (Object) vp, "mFirstTouchTarget");
    }

    public void dispatchTouchEvent(MotionEvent ev) {
        View target;
        if (ev.getAction() == 1 && (target = getClickedView()) != null) {
            dispatch(new Event(-1, target, (String) null));
        }
    }
}
