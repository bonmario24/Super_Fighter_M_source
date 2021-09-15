package com.bun.miitmdid.core;

import android.content.Context;
import android.support.annotation.Keep;
import com.bun.lib.C0971a;
import com.bun.miitmdid.p011a.C0978b;
import com.bun.miitmdid.p014c.C0989a;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
public class MdidSdk implements SupplierListener {
    @Keep
    private IIdentifierListener _InnerListener;
    @Keep
    private C0978b _setting;

    @Keep
    /* renamed from: com.bun.miitmdid.core.MdidSdk$a */
    static /* synthetic */ class C1013a {
        @Keep

        /* renamed from: a */
        static final /* synthetic */ int[] f792a = new int[C0989a.values().length];

        static {
            try {
                f792a[C0989a.XIAOMI.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f792a[C0989a.BLACKSHARK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f792a[C0989a.VIVO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f792a[C0989a.HUA_WEI.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f792a[C0989a.OPPO.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f792a[C0989a.ONEPLUS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f792a[C0989a.MOTO.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f792a[C0989a.LENOVO.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f792a[C0989a.ASUS.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f792a[C0989a.SAMSUNG.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f792a[C0989a.MEIZU.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f792a[C0989a.NUBIA.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f792a[C0989a.ZTE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f792a[C0989a.FREEMEOS.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f792a[C0989a.SSUIOS.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    @Keep
    public MdidSdk() {
        try {
            C0971a.m427a(true);
        } catch (Exception e) {
            C0971a.m431b("mdidsdk", "extractor exception!", e);
        }
    }

    @Keep
    public MdidSdk(boolean z) {
        try {
            C0971a.m427a(z);
        } catch (Exception e) {
            C0971a.m431b("mdidsdk", "extractor exception!", e);
        }
    }

    @Keep
    private native int _InnerFailed(int i, IdSupplier idSupplier);

    @Keep
    public native int InitSdk(Context context, IIdentifierListener iIdentifierListener);

    @Keep
    public native void OnSupport(boolean z, IdSupplier idSupplier);

    @Keep
    public native void UnInitSdk();
}
