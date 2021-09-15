package com.bun.miitmdid.supplier.msa;

import android.os.AsyncTask;
import android.support.annotation.Keep;
import com.bun.lib.C0973c;
import com.bun.miitmdid.p014c.p018e.C0994a;

@Keep
/* renamed from: com.bun.miitmdid.supplier.msa.a */
public class C1017a extends AsyncTask<Void, Void, Boolean> {
    @Keep

    /* renamed from: a */
    public C0973c f796a;
    @Keep

    /* renamed from: b */
    public C0994a f797b;

    @Keep
    public C1017a(C0973c cVar, C0994a aVar) {
        this.f796a = cVar;
        this.f797b = aVar;
    }

    /* access modifiers changed from: protected */
    @Keep
    /* renamed from: a */
    public native Boolean mo14845a(Void... voidArr);

    /* access modifiers changed from: protected */
    @Keep
    /* renamed from: a */
    public native void mo14846a(Boolean bool);

    /* access modifiers changed from: protected */
    @Keep
    public native /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr);

    /* access modifiers changed from: protected */
    @Keep
    public native /* bridge */ /* synthetic */ void onPostExecute(Object obj);
}
