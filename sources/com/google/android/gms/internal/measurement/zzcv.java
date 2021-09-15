package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
final /* synthetic */ class zzcv implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final zzcs zza;

    zzcv(zzcs zzcs) {
        this.zza = zzcs;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
