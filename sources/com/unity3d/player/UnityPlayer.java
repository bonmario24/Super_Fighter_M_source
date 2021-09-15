package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import com.appsflyer.internal.referrer.Payload;
import com.unity3d.player.C0779j;
import com.unity3d.player.C0789o;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnityPlayer extends FrameLayout implements C0770d {
    public static Activity currentActivity = null;

    /* renamed from: r */
    private static boolean f133r;

    /* renamed from: a */
    C0761e f134a = new C0761e(this, (byte) 0);

    /* renamed from: b */
    C0775i f135b = null;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f136c = -1;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f137d = false;

    /* renamed from: e */
    private boolean f138e = true;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0783l f139f = new C0783l();

    /* renamed from: g */
    private final ConcurrentLinkedQueue f140g = new ConcurrentLinkedQueue();

    /* renamed from: h */
    private BroadcastReceiver f141h = null;

    /* renamed from: i */
    private boolean f142i = false;

    /* renamed from: j */
    private C0759c f143j = new C0759c(this, (byte) 0);

    /* renamed from: k */
    private TelephonyManager f144k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public C0779j f145l;

    /* renamed from: m */
    private GoogleARProxy f146m = null;

    /* renamed from: n */
    private GoogleARCoreApi f147n = null;

    /* renamed from: o */
    private C0757a f148o = new C0757a();
    /* access modifiers changed from: private */

    /* renamed from: p */
    public Context f149p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public SurfaceView f150q;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public boolean f151s;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public C0789o f152t;

    /* renamed from: com.unity3d.player.UnityPlayer$3 */
    class C07503 extends BroadcastReceiver {

        /* renamed from: a */
        final /* synthetic */ UnityPlayer f175a;

        public void onReceive(Context context, Intent intent) {
            this.f175a.m87c();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$a */
    class C0757a implements SensorEventListener {
        C0757a() {
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$b */
    enum C0758b {
        ;

        static {
            f196a = 1;
            f197b = 2;
            f198c = 3;
            f199d = new int[]{f196a, f197b, f198c};
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$c */
    private class C0759c extends PhoneStateListener {
        private C0759c() {
        }

        /* synthetic */ C0759c(UnityPlayer unityPlayer, byte b) {
            this();
        }

        public final void onCallStateChanged(int i, String str) {
            boolean z = true;
            UnityPlayer unityPlayer = UnityPlayer.this;
            if (i != 1) {
                z = false;
            }
            unityPlayer.nativeMuteMasterAudio(z);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$d */
    enum C0760d {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME
    }

    /* renamed from: com.unity3d.player.UnityPlayer$e */
    private class C0761e extends Thread {

        /* renamed from: a */
        Handler f210a;

        /* renamed from: b */
        boolean f211b;

        /* renamed from: c */
        boolean f212c;

        /* renamed from: d */
        int f213d;

        /* renamed from: e */
        int f214e;

        private C0761e() {
            this.f211b = false;
            this.f212c = false;
            this.f213d = C0758b.f197b;
            this.f214e = 5;
        }

        /* synthetic */ C0761e(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        private void m120a(C0760d dVar) {
            if (this.f210a != null) {
                Message.obtain(this.f210a, 2269, dVar).sendToTarget();
            }
        }

        /* renamed from: a */
        public final void mo12446a() {
            m120a(C0760d.QUIT);
        }

        /* renamed from: a */
        public final void mo12447a(Runnable runnable) {
            if (this.f210a != null) {
                m120a(C0760d.PAUSE);
                Message.obtain(this.f210a, runnable).sendToTarget();
            }
        }

        /* renamed from: b */
        public final void mo12448b() {
            m120a(C0760d.RESUME);
        }

        /* renamed from: b */
        public final void mo12449b(Runnable runnable) {
            if (this.f210a != null) {
                m120a(C0760d.SURFACE_LOST);
                Message.obtain(this.f210a, runnable).sendToTarget();
            }
        }

        /* renamed from: c */
        public final void mo12450c() {
            m120a(C0760d.FOCUS_GAINED);
        }

        /* renamed from: c */
        public final void mo12451c(Runnable runnable) {
            if (this.f210a != null) {
                Message.obtain(this.f210a, runnable).sendToTarget();
                m120a(C0760d.SURFACE_ACQUIRED);
            }
        }

        /* renamed from: d */
        public final void mo12452d() {
            m120a(C0760d.FOCUS_LOST);
        }

        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.f210a = new Handler(new Handler.Callback() {
                /* renamed from: a */
                private void m128a() {
                    if (C0761e.this.f213d == C0758b.f198c && C0761e.this.f212c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        C0761e.this.f213d = C0758b.f196a;
                    }
                }

                public final boolean handleMessage(Message message) {
                    if (message.what != 2269) {
                        return false;
                    }
                    C0760d dVar = (C0760d) message.obj;
                    if (dVar == C0760d.NEXT_FRAME) {
                        return true;
                    }
                    if (dVar == C0760d.QUIT) {
                        Looper.myLooper().quit();
                    } else if (dVar == C0760d.RESUME) {
                        C0761e.this.f211b = true;
                    } else if (dVar == C0760d.PAUSE) {
                        C0761e.this.f211b = false;
                    } else if (dVar == C0760d.SURFACE_LOST) {
                        C0761e.this.f212c = false;
                    } else if (dVar == C0760d.SURFACE_ACQUIRED) {
                        C0761e.this.f212c = true;
                        m128a();
                    } else if (dVar == C0760d.FOCUS_LOST) {
                        if (C0761e.this.f213d == C0758b.f196a) {
                            UnityPlayer.this.nativeFocusChanged(false);
                        }
                        C0761e.this.f213d = C0758b.f197b;
                    } else if (dVar == C0760d.FOCUS_GAINED) {
                        C0761e.this.f213d = C0758b.f198c;
                        m128a();
                    }
                    return true;
                }
            });
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                public final boolean queueIdle() {
                    UnityPlayer.this.executeGLThreadJobs();
                    if (C0761e.this.f211b && C0761e.this.f212c) {
                        if (C0761e.this.f214e >= 0) {
                            if (C0761e.this.f214e == 0 && UnityPlayer.this.m99i()) {
                                UnityPlayer.this.m71a();
                            }
                            C0761e eVar = C0761e.this;
                            eVar.f214e--;
                        }
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.m87c();
                        }
                        Message.obtain(C0761e.this.f210a, 2269, C0760d.NEXT_FRAME).sendToTarget();
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$f */
    private abstract class C0764f implements Runnable {
        private C0764f() {
        }

        /* synthetic */ C0764f(UnityPlayer unityPlayer, byte b) {
            this();
        }

        /* renamed from: a */
        public abstract void mo12441a();

        public final void run() {
            if (!UnityPlayer.this.isFinishing()) {
                mo12441a();
            }
        }
    }

    static {
        new C0782k().mo12515a();
        f133r = false;
        f133r = loadLibraryStatic("main");
    }

    public UnityPlayer(Context context) {
        super(context);
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.f136c = currentActivity.getRequestedOrientation();
        }
        m73a(currentActivity);
        this.f149p = context;
        if (currentActivity != null && m99i()) {
            this.f145l = new C0779j(this.f149p, C0779j.C0781a.m143a()[getSplashMode()]);
            addView(this.f145l);
        }
        if (C0774h.f243c) {
            if (currentActivity != null) {
                C0774h.f244d.mo12499a(currentActivity, new Runnable() {
                    public final void run() {
                        UnityPlayer.this.mo12383a((Runnable) new Runnable() {
                            public final void run() {
                                UnityPlayer.this.f139f.mo12520d();
                                UnityPlayer.this.m93f();
                            }
                        });
                    }
                });
            } else {
                this.f139f.mo12520d();
            }
        }
        m74a(this.f149p.getApplicationInfo());
        if (!C0783l.m147c()) {
            AlertDialog create = new AlertDialog.Builder(this.f149p).setTitle("Failure to initialize!").setPositiveButton(Payload.RESPONSE_OK, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    UnityPlayer.this.m87c();
                }
            }).setMessage("Your hardware does not support this application, sorry!").create();
            create.setCancelable(false);
            create.show();
            return;
        }
        initJni(context);
        this.f150q = m82b();
        this.f150q.setContentDescription(m70a(context));
        addView(this.f150q);
        bringChildToFront(this.f145l);
        this.f151s = false;
        nativeInitWebRequest(UnityWebRequest.class);
        m103k();
        this.f144k = (TelephonyManager) this.f149p.getSystemService("phone");
        this.f134a.start();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (!C0783l.m147c()) {
            C0771e.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
        } else {
            nativeUnitySendMessage(str, str2, str3);
        }
    }

    /* renamed from: a */
    private static String m70a(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", "string", context.getPackageName()));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m71a() {
        mo12383a((Runnable) new Runnable() {
            public final void run() {
                UnityPlayer.this.removeView(UnityPlayer.this.f145l);
                C0779j unused = UnityPlayer.this.f145l = null;
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m72a(int i, Surface surface) {
        if (!this.f137d) {
            m86b(0, surface);
        }
    }

    /* renamed from: a */
    private static void m73a(Activity activity) {
        View decorView;
        if (activity != null && activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) && activity.getWindow() != null && (decorView = activity.getWindow().getDecorView()) != null) {
            decorView.setSystemUiVisibility(7);
        }
    }

    /* renamed from: a */
    private static void m74a(ApplicationInfo applicationInfo) {
        if (f133r && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0783l.m145a();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m75a(android.view.View r5, android.view.View r6) {
        /*
            r4 = this;
            r3 = 0
            com.unity3d.player.l r0 = r4.f139f
            boolean r0 = r0.mo12521e()
            if (r0 != 0) goto L_0x0045
            r4.pause()
            r0 = 1
            r2 = r0
        L_0x000e:
            if (r5 == 0) goto L_0x002f
            android.view.ViewParent r1 = r5.getParent()
            boolean r0 = r1 instanceof com.unity3d.player.UnityPlayer
            if (r0 == 0) goto L_0x001d
            r0 = r1
            com.unity3d.player.UnityPlayer r0 = (com.unity3d.player.UnityPlayer) r0
            if (r0 == r4) goto L_0x002f
        L_0x001d:
            boolean r0 = r1 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x0026
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r1.removeView(r5)
        L_0x0026:
            r4.addView(r5)
            r4.bringChildToFront(r5)
            r5.setVisibility(r3)
        L_0x002f:
            if (r6 == 0) goto L_0x003f
            android.view.ViewParent r0 = r6.getParent()
            if (r0 != r4) goto L_0x003f
            r0 = 8
            r6.setVisibility(r0)
            r4.removeView(r6)
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r4.resume()
        L_0x0044:
            return
        L_0x0045:
            r2 = r3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.UnityPlayer.m75a(android.view.View, android.view.View):void");
    }

    /* renamed from: a */
    private void m76a(C0764f fVar) {
        if (!isFinishing()) {
            m85b((Runnable) fVar);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public SurfaceView m82b() {
        SurfaceView surfaceView = new SurfaceView(this.f149p);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                UnityPlayer.this.m72a(0, surfaceHolder.getSurface());
            }

            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.m72a(0, surfaceHolder.getSurface());
            }

            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                UnityPlayer.this.m72a(0, (Surface) null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    /* renamed from: b */
    private void m85b(Runnable runnable) {
        if (C0783l.m147c()) {
            if (Thread.currentThread() == this.f134a) {
                runnable.run();
            } else {
                this.f140g.add(runnable);
            }
        }
    }

    /* renamed from: b */
    private boolean m86b(final int i, final Surface surface) {
        if (!C0783l.m147c()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        C074517 r0 = new Runnable() {
            public final void run() {
                UnityPlayer.this.nativeRecreateGfxState(i, surface);
                semaphore.release();
            }
        };
        if (i != 0) {
            r0.run();
        } else if (surface == null) {
            this.f134a.mo12449b(r0);
        } else {
            this.f134a.mo12451c(r0);
        }
        if (surface == null && i == 0) {
            try {
                if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                    C0771e.Log(5, "Timeout while trying detaching primary window.");
                }
            } catch (InterruptedException e) {
                C0771e.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m87c() {
        if ((this.f149p instanceof Activity) && !((Activity) this.f149p).isFinishing()) {
            ((Activity) this.f149p).finish();
        }
    }

    /* renamed from: d */
    private void m89d() {
        reportSoftInputStr((String) null, 1, true);
        if (this.f139f.mo12523g()) {
            if (C0783l.m147c()) {
                final Semaphore semaphore = new Semaphore(0);
                this.f134a.mo12447a(isFinishing() ? new Runnable() {
                    public final void run() {
                        UnityPlayer.this.m92e();
                        semaphore.release();
                    }
                } : new Runnable() {
                    public final void run() {
                        if (UnityPlayer.this.nativePause()) {
                            boolean unused = UnityPlayer.this.f151s = true;
                            UnityPlayer.this.m92e();
                            semaphore.release(2);
                            return;
                        }
                        semaphore.release();
                    }
                });
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0771e.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    C0771e.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.f139f.mo12519c(false);
            this.f139f.mo12518b(true);
            if (this.f142i) {
                this.f144k.listen(this.f143j, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m92e() {
        nativeDone();
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m93f() {
        if (this.f139f.mo12522f()) {
            this.f139f.mo12519c(true);
            m85b((Runnable) new Runnable() {
                public final void run() {
                    UnityPlayer.this.nativeResume();
                }
            });
            this.f134a.mo12448b();
        }
    }

    /* renamed from: g */
    private static void m96g() {
        if (C0783l.m147c()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            C0783l.m146b();
        }
    }

    /* renamed from: h */
    private ApplicationInfo m97h() {
        return this.f149p.getPackageManager().getApplicationInfo(this.f149p.getPackageName(), 128);
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public boolean m99i() {
        try {
            return m97h().metaData.getBoolean("unity.splash-enable");
        } catch (Exception e) {
            return false;
        }
    }

    private final native void initJni(Context context);

    /* renamed from: j */
    private boolean m102j() {
        try {
            return m97h().metaData.getBoolean("unity.tango-enable");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: k */
    private void m103k() {
        if (this.f149p instanceof Activity) {
            ((Activity) this.f149p).getWindow().setFlags(1024, 1024);
        }
    }

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            C0771e.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            C0771e.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    private final native void nativeDone();

    /* access modifiers changed from: private */
    public final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    /* access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* access modifiers changed from: private */
    public final native void nativeLowMemory();

    /* access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* access modifiers changed from: private */
    public final native boolean nativePause();

    /* access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* access modifiers changed from: private */
    public final native boolean nativeRender();

    private final native void nativeRestartActivityIndicator();

    /* access modifiers changed from: private */
    public final native void nativeResume();

    /* access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    private final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo12383a(Runnable runnable) {
        if (this.f149p instanceof Activity) {
            ((Activity) this.f149p).runOnUiThread(runnable);
        } else {
            C0771e.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    /* access modifiers changed from: protected */
    public void addPhoneCallListener() {
        this.f142i = true;
        this.f144k.listen(this.f143j, 32);
    }

    public boolean addViewToPlayer(View view, boolean z) {
        boolean z2 = true;
        m75a(view, (View) z ? this.f150q : null);
        boolean z3 = view.getParent() == this;
        boolean z4 = z && this.f150q.getParent() == null;
        boolean z5 = this.f150q.getParent() == this;
        if (!z3 || (!z4 && !z5)) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                C0771e.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                C0771e.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f150q instanceof SurfaceView) {
            this.f150q.getHolder().setSizeFromLayout();
        }
        if (this.f152t != null) {
            this.f152t.mo12557c();
        }
        GoogleVrProxy b = GoogleVrApi.m43b();
        if (b != null) {
            b.mo12359c();
        }
    }

    /* access modifiers changed from: protected */
    public void disableLogger() {
        C0771e.f239a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.f137d = surface != null;
            mo12383a((Runnable) new Runnable() {
                public final void run() {
                    if (UnityPlayer.this.f137d) {
                        UnityPlayer.this.removeView(UnityPlayer.this.f150q);
                    } else {
                        UnityPlayer.this.addView(UnityPlayer.this.f150q);
                    }
                }
            });
        }
        return m86b(i, surface);
    }

    /* access modifiers changed from: protected */
    public void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f140g.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    /* access modifiers changed from: protected */
    public int getSplashMode() {
        try {
            return m97h().metaData.getInt("unity.splash-mode");
        } catch (Exception e) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void hideSoftInput() {
        final C07536 r0 = new Runnable() {
            public final void run() {
                if (UnityPlayer.this.f135b != null) {
                    UnityPlayer.this.f135b.dismiss();
                    UnityPlayer.this.f135b = null;
                }
            }
        };
        if (C0774h.f242b) {
            m76a((C0764f) new C0764f() {
                /* renamed from: a */
                public final void mo12441a() {
                    UnityPlayer.this.mo12383a(r0);
                }
            });
        } else {
            mo12383a((Runnable) r0);
        }
    }

    public void init(int i, boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean initializeGoogleAr() {
        if (this.f146m == null && currentActivity != null && m102j()) {
            if (GoogleARProxy.m34a()) {
                this.f146m = new GoogleARProxy(this);
                this.f146m.mo12346a(currentActivity, this.f149p);
                this.f146m.mo12347b();
                if (!this.f139f.mo12521e()) {
                    this.f146m.mo12349d();
                }
                return this.f146m.mo12350e();
            }
            this.f147n = new GoogleARCoreApi();
            this.f147n.initializeARCore(currentActivity);
            if (!this.f139f.mo12521e()) {
                this.f147n.resumeARCore();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean initializeGoogleVr() {
        final GoogleVrProxy b = GoogleVrApi.m43b();
        if (b == null) {
            GoogleVrApi.m42a(this);
            b = GoogleVrApi.m43b();
            if (b == null) {
                C0771e.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final C073810 r3 = new Runnable() {
            public final void run() {
                UnityPlayer.this.injectEvent(new KeyEvent(0, 4));
                UnityPlayer.this.injectEvent(new KeyEvent(1, 4));
            }
        };
        mo12383a((Runnable) new Runnable() {
            public final void run() {
                if (!b.mo12357a(UnityPlayer.currentActivity, UnityPlayer.this.f149p, UnityPlayer.this.m82b(), r3)) {
                    C0771e.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    b.mo12355a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                return b.mo12356a();
            }
            C0771e.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e) {
            C0771e.Log(5, "UI thread was interrupted while initializing Google VR. " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return nativeInjectEvent(inputEvent);
    }

    /* access modifiers changed from: protected */
    public boolean isFinishing() {
        if (!this.f151s) {
            boolean z = (this.f149p instanceof Activity) && ((Activity) this.f149p).isFinishing();
            this.f151s = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void kill() {
        Process.killProcess(Process.myPid());
    }

    /* access modifiers changed from: protected */
    public boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        m85b((Runnable) new Runnable() {
            public final void run() {
                UnityPlayer.this.nativeLowMemory();
            }
        });
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.f146m != null) {
            this.f146m.mo12348c();
        } else if (this.f147n != null) {
            this.f147n.pauseARCore();
        }
        if (this.f152t != null) {
            this.f152t.mo12554a();
        }
        GoogleVrProxy b = GoogleVrApi.m43b();
        if (b != null) {
            b.pauseGvrLayout();
        }
        m89d();
    }

    public void quit() {
        if (GoogleVrApi.m43b() != null) {
            GoogleVrApi.m41a();
        }
        this.f151s = true;
        if (!this.f139f.mo12521e()) {
            pause();
        }
        this.f134a.mo12446a();
        try {
            this.f134a.join(4000);
        } catch (InterruptedException e) {
            this.f134a.interrupt();
        }
        if (this.f141h != null) {
            this.f149p.unregisterReceiver(this.f141h);
        }
        this.f141h = null;
        if (C0783l.m147c()) {
            removeAllViews();
        }
        kill();
        m96g();
    }

    public void removeViewFromPlayer(View view) {
        boolean z = true;
        m75a((View) this.f150q, view);
        boolean z2 = view.getParent() == null;
        boolean z3 = this.f150q.getParent() == this;
        if (!z2 || !z3) {
            z = false;
        }
        if (!z) {
            if (!z2) {
                C0771e.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (!z3) {
                C0771e.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
            }
        }
    }

    public void reportError(String str, String str2) {
        C0771e.Log(6, str + ": " + str2);
    }

    /* access modifiers changed from: protected */
    public void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m76a((C0764f) new C0764f() {
            /* renamed from: a */
            public final void mo12441a() {
                if (z) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                } else if (str != null) {
                    UnityPlayer.this.nativeSetInputString(str);
                }
                if (i == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }

    public void resume() {
        if (this.f146m != null) {
            this.f146m.mo12349d();
        } else if (this.f147n != null) {
            this.f147n.resumeARCore();
        }
        this.f139f.mo12518b(false);
        if (this.f152t != null) {
            this.f152t.mo12556b();
        }
        m93f();
        nativeRestartActivityIndicator();
        GoogleVrProxy b = GoogleVrApi.m43b();
        if (b != null) {
            b.mo12358b();
        }
    }

    /* access modifiers changed from: protected */
    public void setSoftInputStr(final String str) {
        mo12383a((Runnable) new Runnable() {
            public final void run() {
                if (UnityPlayer.this.f135b != null && str != null) {
                    UnityPlayer.this.f135b.mo12502a(str);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        final String str3 = str;
        final int i2 = i;
        final boolean z5 = z;
        final boolean z6 = z2;
        final boolean z7 = z3;
        final boolean z8 = z4;
        final String str4 = str2;
        mo12383a((Runnable) new Runnable() {
            public final void run() {
                UnityPlayer.this.f135b = new C0775i(UnityPlayer.this.f149p, this, str3, i2, z5, z6, z7, str4);
                UnityPlayer.this.f135b.show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.f152t == null) {
            this.f152t = new C0789o(this);
        }
        boolean a = this.f152t.mo12555a(this.f149p, str, i, i2, i3, z, (long) i4, (long) i5, new C0789o.C0796a() {
            /* renamed from: a */
            public final void mo12425a() {
                C0789o unused = UnityPlayer.this.f152t = null;
            }
        });
        if (a) {
            mo12383a((Runnable) new Runnable() {
                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && (UnityPlayer.this.f149p instanceof Activity)) {
                        ((Activity) UnityPlayer.this.f149p).setRequestedOrientation(UnityPlayer.this.f136c);
                    }
                }
            });
        }
        return a;
    }

    public void start() {
    }

    public void stop() {
    }

    /* access modifiers changed from: protected */
    public void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.f149p.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.f148o, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.f148o);
        }
    }

    public void windowFocusChanged(boolean z) {
        this.f139f.mo12517a(z);
        if (z && this.f135b != null) {
            nativeSoftInputLostFocus();
            reportSoftInputStr((String) null, 1, false);
        }
        if (z) {
            this.f134a.mo12450c();
        } else {
            this.f134a.mo12452d();
        }
        m93f();
    }
}
