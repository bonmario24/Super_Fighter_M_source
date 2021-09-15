package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.unity3d.player.GoogleVrVideo;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

class GoogleVrProxy extends C0767b implements GoogleVrVideo {

    /* renamed from: f */
    private boolean f97f = false;

    /* renamed from: g */
    private boolean f98g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public Runnable f99h = null;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public Vector f100i = new Vector();

    /* renamed from: j */
    private SurfaceView f101j = null;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public C0733a f102k = new C0733a();

    /* renamed from: l */
    private Thread f103l = null;

    /* renamed from: m */
    private Handler f104m = new Handler(Looper.getMainLooper()) {
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 135711:
                    switch (message.arg1) {
                        case 2147483645:
                            Iterator it = GoogleVrProxy.this.f100i.iterator();
                            while (it.hasNext()) {
                                ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onFrameAvailable();
                            }
                            return;
                        case 2147483646:
                            Surface surface = (Surface) message.obj;
                            Iterator it2 = GoogleVrProxy.this.f100i.iterator();
                            while (it2.hasNext()) {
                                ((GoogleVrVideo.GoogleVrVideoCallbacks) it2.next()).onSurfaceAvailable(surface);
                            }
                            return;
                        default:
                            super.handleMessage(message);
                            return;
                    }
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };

    /* renamed from: com.unity3d.player.GoogleVrProxy$a */
    class C0733a {

        /* renamed from: a */
        public boolean f116a = false;

        /* renamed from: b */
        public boolean f117b = false;

        /* renamed from: c */
        public boolean f118c = false;

        /* renamed from: d */
        public boolean f119d = false;

        /* renamed from: e */
        public boolean f120e = true;

        /* renamed from: f */
        public boolean f121f = false;

        C0733a() {
        }

        /* renamed from: a */
        public final boolean mo12374a() {
            return this.f116a && this.f117b;
        }

        /* renamed from: b */
        public final void mo12375b() {
            this.f116a = false;
            this.f117b = false;
            this.f119d = false;
            this.f120e = true;
            this.f121f = false;
        }
    }

    public GoogleVrProxy(C0770d dVar) {
        super("Google VR", dVar);
        initVrJni();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m46a(boolean z) {
        this.f102k.f119d = z;
    }

    /* renamed from: a */
    private static boolean m47a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    private boolean m48a(ClassLoader classLoader) {
        try {
            Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            C0784m mVar = new C0784m(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            mVar.mo12526a("initialize", new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class});
            mVar.mo12526a("deinitialize", new Class[0]);
            mVar.mo12526a("load", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class});
            mVar.mo12526a("enable", new Class[]{Boolean.TYPE});
            mVar.mo12526a("unload", new Class[0]);
            mVar.mo12526a("pause", new Class[0]);
            mVar.mo12526a("resume", new Class[0]);
            mVar.mo12526a("getGvrLayout", new Class[0]);
            mVar.mo12526a("getVideoSurfaceId", new Class[0]);
            mVar.mo12526a("getVideoSurface", new Class[0]);
            this.f231a = mVar;
            return true;
        } catch (Exception e) {
            reportError("Exception initializing GoogleVR from Unity library. " + e.getLocalizedMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public boolean m51d() {
        return this.f102k.f119d;
    }

    /* renamed from: e */
    private void m53e() {
        Activity activity = (Activity) this.f233c;
        if (this.f98g && !this.f102k.f121f && activity != null) {
            this.f102k.f121f = true;
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] fArr);

    /* renamed from: a */
    public final void mo12355a(Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.f98g = true;
        }
    }

    /* renamed from: a */
    public final boolean mo12356a() {
        return this.f102k.f116a;
    }

    /* renamed from: a */
    public final boolean mo12357a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        boolean z;
        if (activity == null || context == null || surfaceView == null || runnable == null) {
            reportError("Invalid parameters passed to Google VR initiialization.");
            return false;
        }
        this.f102k.mo12375b();
        this.f233c = context;
        this.f99h = runnable;
        if (!m47a(19)) {
            reportError("Google VR requires a device that supports an api version of 19 (KitKat) or better.");
            return false;
        } else if (this.f98g && !m47a(24)) {
            reportError("Daydream requires a device that supports an api version of 24 (Nougat) or better.");
            return false;
        } else if (!m48a(UnityPlayer.class.getClassLoader())) {
            return false;
        } else {
            try {
                z = ((Boolean) this.f231a.mo12525a("initialize", activity, context, surfaceView, Boolean.valueOf(this.f98g), this.f104m)).booleanValue();
            } catch (Exception e) {
                reportError("Exception while trying to intialize Unity Google VR Library. " + e.getLocalizedMessage());
                z = false;
            }
            if (!z) {
                reportError("Unable to initialize GoogleVR library.");
                return false;
            }
            this.f101j = surfaceView;
            this.f102k.f116a = true;
            this.f234d = "";
            return true;
        }
    }

    /* renamed from: b */
    public final void mo12358b() {
        resumeGvrLayout();
    }

    /* renamed from: c */
    public final void mo12359c() {
        if (this.f101j != null) {
            this.f101j.getHolder().setSizeFromLayout();
        }
    }

    public void deregisterGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f100i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.f100i.remove(googleVrVideoCallbacks);
        }
    }

    /* access modifiers changed from: protected */
    public Object getVideoSurface() {
        if (!m51d() || this.f102k.f120e) {
            return null;
        }
        try {
            return this.f231a.mo12525a("getVideoSurface", new Object[0]);
        } catch (Exception e) {
            reportError("Exception caught while Getting GoogleVR Video Surface. " + e.getLocalizedMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public int getVideoSurfaceId() {
        if (!m51d() || this.f102k.f120e) {
            return -1;
        }
        try {
            return ((Integer) this.f231a.mo12525a("getVideoSurfaceId", new Object[0])).intValue();
        } catch (Exception e) {
            reportError("Exception caught while getting Video Surface ID from GoogleVR. " + e.getLocalizedMessage());
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public long loadGoogleVr(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!this.f102k.f116a) {
            return 0;
        }
        final AtomicLong atomicLong = new AtomicLong(0);
        this.f234d = (z || z2) ? "Daydream" : "Cardboard";
        final boolean z6 = z;
        final boolean z7 = z2;
        final boolean z8 = z3;
        final boolean z9 = z4;
        final boolean z10 = z5;
        if (!runOnUiThreadWithSync(new Runnable() {
            public final void run() {
                try {
                    atomicLong.set(((Long) GoogleVrProxy.this.f231a.mo12525a("load", Boolean.valueOf(z6), Boolean.valueOf(z7), Boolean.valueOf(z8), Boolean.valueOf(z9), Boolean.valueOf(z10), GoogleVrProxy.this.f99h)).longValue());
                    GoogleVrProxy.this.f102k.f117b = true;
                } catch (Exception e) {
                    GoogleVrProxy.this.reportError("Exception caught while loading GoogleVR. " + e.getLocalizedMessage());
                    atomicLong.set(0);
                }
            }
        }) || atomicLong.longValue() == 0) {
            reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }

    /* access modifiers changed from: protected */
    public void pauseGvrLayout() {
        if (this.f102k.mo12374a() && !this.f102k.f120e) {
            if (m51d()) {
                Iterator it = this.f100i.iterator();
                while (it.hasNext()) {
                    ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onSurfaceUnavailable();
                }
            }
            if (this.f231a != null) {
                this.f231a.mo12525a("pause", new Object[0]);
            }
            this.f102k.f120e = true;
        }
    }

    public void registerGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.f100i.contains(googleVrVideoCallbacks)) {
            this.f100i.add(googleVrVideoCallbacks);
            Surface surface = (Surface) getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void resumeGvrLayout() {
        if (this.f102k.mo12374a() && this.f102k.f120e) {
            if (this.f231a != null) {
                this.f231a.mo12525a("resume", new Object[0]);
            }
            this.f102k.f120e = false;
        }
    }

    /* access modifiers changed from: protected */
    public void setGoogleVrModeEnabled(final boolean z) {
        if (this.f102k.mo12374a() && this.f232b != null && this.f233c != null) {
            if (!z && isQuiting()) {
                m53e();
            }
            runOnUiThread(new Runnable() {
                public final void run() {
                    if (z != GoogleVrProxy.this.m51d()) {
                        try {
                            if (!z || GoogleVrProxy.this.m51d()) {
                                if (!z && GoogleVrProxy.this.m51d()) {
                                    GoogleVrProxy.this.m46a(false);
                                    if (GoogleVrProxy.this.f231a != null) {
                                        GoogleVrProxy.this.f231a.mo12525a("enable", false);
                                    }
                                    if (GoogleVrProxy.this.f231a != null && GoogleVrProxy.this.f232b != null) {
                                        GoogleVrProxy.this.f232b.removeViewFromPlayer((View) GoogleVrProxy.this.f231a.mo12525a("getGvrLayout", new Object[0]));
                                    }
                                }
                            } else if (GoogleVrProxy.this.f231a == null || GoogleVrProxy.this.f232b == null || GoogleVrProxy.this.f232b.addViewToPlayer((View) GoogleVrProxy.this.f231a.mo12525a("getGvrLayout", new Object[0]), true)) {
                                if (GoogleVrProxy.this.f231a != null) {
                                    GoogleVrProxy.this.f231a.mo12525a("enable", true);
                                }
                                GoogleVrProxy.this.m46a(true);
                            } else {
                                GoogleVrProxy.this.reportError("Unable to add Google VR to view hierarchy.");
                            }
                        } catch (Exception e) {
                            GoogleVrProxy.this.reportError("Exception enabling Google VR on UI Thread. " + e.getLocalizedMessage());
                        }
                    }
                }
            });
        }
    }

    public void setVideoLocationTransform(float[] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, new int[]{4, 4});
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                fArr2[i][i2] = fArr[(i * 4) + i2];
            }
        }
        setVrVideoTransform(fArr2);
    }

    /* access modifiers changed from: protected */
    public void unloadGoogleVr() {
        if (this.f102k.f119d) {
            setGoogleVrModeEnabled(false);
        }
        if (this.f102k.f118c) {
            this.f102k.f118c = false;
        }
        this.f101j = null;
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    if (GoogleVrProxy.this.f231a != null) {
                        GoogleVrProxy.this.f231a.mo12525a("unload", new Object[0]);
                        GoogleVrProxy.this.f231a.mo12525a("deinitialize", new Object[0]);
                        GoogleVrProxy.this.f231a = null;
                    }
                    GoogleVrProxy.this.f102k.f117b = false;
                } catch (Exception e) {
                    GoogleVrProxy.this.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }
}
