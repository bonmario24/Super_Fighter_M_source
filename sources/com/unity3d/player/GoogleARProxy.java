package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

class GoogleARProxy extends C0767b {

    /* renamed from: f */
    private boolean f90f = false;

    GoogleARProxy(C0770d dVar) {
        super("Google AR", dVar);
    }

    /* renamed from: a */
    public static boolean m34a() {
        try {
            Class<?> loadClass = UnityPlayer.class.getClassLoader().loadClass("com.unity3d.unitygar.GoogleAR");
            C0784m mVar = new C0784m(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            mVar.mo12526a("getClassVersion", new Class[0]);
            if (((Number) mVar.mo12525a("getClassVersion", new Object[0])).intValue() > 0) {
                Log.d("Unity", "Loading ARCore V1+ path.");
                return false;
            }
            Log.d("Unity", "Loading ARCore Preview path (Version <= 1).");
            return true;
        } catch (Exception e) {
            Log.d("Unity", "Loading ARCore Preview path.");
            return true;
        }
    }

    /* renamed from: a */
    private boolean m35a(ClassLoader classLoader) {
        if (this.f90f) {
            return true;
        }
        try {
            Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygar.GoogleAR");
            C0784m mVar = new C0784m(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            mVar.mo12526a("initialize", new Class[]{Activity.class});
            mVar.mo12526a("create", new Class[0]);
            mVar.mo12526a("pause", new Class[0]);
            mVar.mo12526a("resume", new Class[0]);
            this.f231a = mVar;
            this.f90f = true;
            return true;
        } catch (Exception e) {
            this.f232b.reportError("Google AR Error", e.toString() + e.getLocalizedMessage());
            return false;
        }
    }

    private final native void tangoOnCreate(Activity activity);

    private final native void tangoOnServiceConnected(IBinder iBinder);

    private final native void tangoOnStop();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo12346a(final Activity activity, Context context) {
        if (m35a(UnityPlayer.class.getClassLoader())) {
            this.f233c = context;
            runOnUiThread(new Runnable() {
                public final void run() {
                    try {
                        if (GoogleARProxy.this.f231a != null) {
                            GoogleARProxy.this.f231a.mo12525a("initialize", activity);
                        }
                    } catch (Exception e) {
                        GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.f235e + " VR on UI Thread. " + e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo12347b() {
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    if (GoogleARProxy.this.f231a != null) {
                        GoogleARProxy.this.f231a.mo12525a("create", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.f235e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo12348c() {
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    if (GoogleARProxy.this.f231a != null) {
                        GoogleARProxy.this.f231a.mo12525a("pause", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception pausing " + GoogleARProxy.this.f235e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo12349d() {
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    if (GoogleARProxy.this.f231a != null) {
                        GoogleARProxy.this.f231a.mo12525a("resume", new Object[0]);
                    }
                } catch (Exception e) {
                    GoogleARProxy.this.reportError("Exception resuming " + GoogleARProxy.this.f235e + " VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }

    /* renamed from: e */
    public final boolean mo12350e() {
        return this.f90f;
    }
}
