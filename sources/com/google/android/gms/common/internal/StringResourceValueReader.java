package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.C1775R;
import com.google.android.gms.common.annotation.KeepForSdk;
import javax.annotation.Nullable;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class StringResourceValueReader {
    private final Resources zzfi;
    private final String zzfj = this.zzfi.getResourcePackageName(C1775R.string.common_google_play_services_unknown_issue);

    public StringResourceValueReader(Context context) {
        Preconditions.checkNotNull(context);
        this.zzfi = context.getResources();
    }

    @KeepForSdk
    @Nullable
    public String getString(String str) {
        int identifier = this.zzfi.getIdentifier(str, "string", this.zzfj);
        if (identifier == 0) {
            return null;
        }
        return this.zzfi.getString(identifier);
    }
}
