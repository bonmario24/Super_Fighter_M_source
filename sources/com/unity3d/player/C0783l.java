package com.unity3d.player;

/* renamed from: com.unity3d.player.l */
final class C0783l {

    /* renamed from: a */
    private static boolean f264a = false;

    /* renamed from: b */
    private boolean f265b;

    /* renamed from: c */
    private boolean f266c;

    /* renamed from: d */
    private boolean f267d;

    /* renamed from: e */
    private boolean f268e;

    C0783l() {
        this.f265b = !C0774h.f243c;
        this.f266c = false;
        this.f267d = false;
        this.f268e = true;
    }

    /* renamed from: a */
    static void m145a() {
        f264a = true;
    }

    /* renamed from: b */
    static void m146b() {
        f264a = false;
    }

    /* renamed from: c */
    static boolean m147c() {
        return f264a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo12517a(boolean z) {
        this.f266c = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo12518b(boolean z) {
        this.f268e = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo12519c(boolean z) {
        this.f267d = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo12520d() {
        this.f265b = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean mo12521e() {
        return this.f268e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final boolean mo12522f() {
        return f264a && this.f266c && this.f265b && !this.f268e && !this.f267d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo12523g() {
        return this.f267d;
    }

    public final String toString() {
        return super.toString();
    }
}
