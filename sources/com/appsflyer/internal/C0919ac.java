package com.appsflyer.internal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/* renamed from: com.appsflyer.internal.ac */
public final class C0919ac extends ContentFetcher<String> {
    public C0919ac(Context context) {
        super(context, "com.facebook.katana.provider.AttributionIdProvider", "e3f9e1e0cf99d0e56a055ba65e241b3399f7cea524326b0cdd6ec1327ed0fdc1", 500);
    }

    @Nullable
    /* renamed from: ɩ */
    public final String mo14684() {
        start();
        return (String) super.get();
    }

    /* access modifiers changed from: private */
    /* renamed from: ǃ */
    public String query() {
        Cursor cursor = null;
        try {
            Cursor query = this.context.getContentResolver().query(Uri.parse(new StringBuilder("content://").append(this.authority).toString()), new String[]{"aid"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("aid"));
                        if (query == null) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Nullable
    public final /* synthetic */ Object get() {
        start();
        return (String) super.get();
    }
}
