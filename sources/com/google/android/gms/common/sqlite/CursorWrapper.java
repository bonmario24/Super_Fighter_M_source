package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzfn;

    @KeepForSdk
    public CursorWrapper(Cursor cursor) {
        super(cursor);
        Cursor cursor2 = cursor;
        for (int i = 0; i < 10 && (cursor2 instanceof android.database.CursorWrapper); i++) {
            cursor2 = ((android.database.CursorWrapper) cursor2).getWrappedCursor();
        }
        if (!(cursor2 instanceof AbstractWindowedCursor)) {
            String valueOf = String.valueOf(cursor2.getClass().getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown type: ".concat(valueOf) : new String("Unknown type: "));
        } else {
            this.zzfn = (AbstractWindowedCursor) cursor2;
        }
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzfn.getWindow();
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zzfn.setWindow(cursorWindow);
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzfn.fillWindow(i, cursorWindow);
    }

    public boolean onMove(int i, int i2) {
        return this.zzfn.onMove(i, i2);
    }

    public /* synthetic */ Cursor getWrappedCursor() {
        return this.zzfn;
    }
}
