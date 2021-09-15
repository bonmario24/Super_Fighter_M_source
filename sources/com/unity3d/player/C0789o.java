package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.C0786n;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.o */
final class C0789o {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UnityPlayer f304a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f305b = null;

    /* renamed from: c */
    private C0796a f306c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Semaphore f307d = new Semaphore(0);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Lock f308e = new ReentrantLock();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0786n f309f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f310g = 2;

    /* renamed from: h */
    private boolean f311h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f312i = false;

    /* renamed from: com.unity3d.player.o$a */
    public interface C0796a {
        /* renamed from: a */
        void mo12425a();
    }

    C0789o(UnityPlayer unityPlayer) {
        this.f304a = unityPlayer;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m173d() {
        if (this.f309f != null) {
            this.f304a.removeViewFromPlayer(this.f309f);
            this.f312i = false;
            this.f309f.destroyPlayer();
            this.f309f = null;
            if (this.f306c != null) {
                this.f306c.mo12425a();
            }
        }
    }

    /* renamed from: a */
    public final void mo12554a() {
        this.f308e.lock();
        if (this.f309f != null) {
            if (this.f310g == 0) {
                this.f309f.CancelOnPrepare();
            } else if (this.f312i) {
                this.f311h = this.f309f.mo12528a();
                if (!this.f311h) {
                    this.f309f.pause();
                }
            }
        }
        this.f308e.unlock();
    }

    /* renamed from: a */
    public final boolean mo12555a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0796a aVar) {
        this.f308e.lock();
        this.f306c = aVar;
        this.f305b = context;
        this.f307d.drainPermits();
        this.f310g = 2;
        final String str2 = str;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final boolean z2 = z;
        final long j3 = j;
        final long j4 = j2;
        runOnUiThread(new Runnable() {
            public final void run() {
                if (C0789o.this.f309f != null) {
                    C0771e.Log(5, "Video already playing");
                    int unused = C0789o.this.f310g = 2;
                    C0789o.this.f307d.release();
                    return;
                }
                C0786n unused2 = C0789o.this.f309f = new C0786n(C0789o.this.f305b, str2, i4, i5, i6, z2, j3, j4, new C0786n.C0787a() {
                    /* renamed from: a */
                    public final void mo12551a(int i) {
                        C0789o.this.f308e.lock();
                        int unused = C0789o.this.f310g = i;
                        if (i == 3 && C0789o.this.f312i) {
                            C0789o.this.runOnUiThread(new Runnable() {
                                public final void run() {
                                    C0789o.this.m173d();
                                    C0789o.this.f304a.resume();
                                }
                            });
                        }
                        if (i != 0) {
                            C0789o.this.f307d.release();
                        }
                        C0789o.this.f308e.unlock();
                    }
                });
                if (C0789o.this.f309f != null) {
                    C0789o.this.f304a.addView(C0789o.this.f309f);
                }
            }
        });
        boolean z3 = false;
        try {
            this.f308e.unlock();
            this.f307d.acquire();
            this.f308e.lock();
            z3 = this.f310g != 2;
        } catch (InterruptedException e) {
        }
        runOnUiThread(new Runnable() {
            public final void run() {
                C0789o.this.f304a.pause();
            }
        });
        if (!z3 || this.f310g == 3) {
            runOnUiThread(new Runnable() {
                public final void run() {
                    C0789o.this.m173d();
                    C0789o.this.f304a.resume();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                public final void run() {
                    if (C0789o.this.f309f != null) {
                        C0789o.this.f304a.addViewToPlayer(C0789o.this.f309f, true);
                        boolean unused = C0789o.this.f312i = true;
                        C0789o.this.f309f.requestFocus();
                    }
                }
            });
        }
        this.f308e.unlock();
        return z3;
    }

    /* renamed from: b */
    public final void mo12556b() {
        this.f308e.lock();
        if (this.f309f != null && this.f312i && !this.f311h) {
            this.f309f.start();
        }
        this.f308e.unlock();
    }

    /* renamed from: c */
    public final void mo12557c() {
        this.f308e.lock();
        if (this.f309f != null) {
            this.f309f.updateVideoLayout();
        }
        this.f308e.unlock();
    }

    /* access modifiers changed from: protected */
    public final void runOnUiThread(Runnable runnable) {
        if (this.f305b instanceof Activity) {
            ((Activity) this.f305b).runOnUiThread(runnable);
        } else {
            C0771e.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
