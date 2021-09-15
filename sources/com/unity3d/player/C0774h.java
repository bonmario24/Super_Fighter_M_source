package com.unity3d.player;

import android.os.Build;

/* renamed from: com.unity3d.player.h */
public final class C0774h {

    /* renamed from: a */
    static final boolean f241a = (Build.VERSION.SDK_INT >= 19);

    /* renamed from: b */
    static final boolean f242b = (Build.VERSION.SDK_INT >= 21);

    /* renamed from: c */
    static final boolean f243c;

    /* renamed from: d */
    static final C0769c f244d;

    static {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 23) {
            z = false;
        }
        f243c = z;
        f244d = z ? new C0772f() : null;
    }
}
