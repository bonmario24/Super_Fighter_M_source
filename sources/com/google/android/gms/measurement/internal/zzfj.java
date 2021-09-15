package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzfj {
    private final String zza;
    private final Bundle zzb = new Bundle();
    private boolean zzc;
    private Bundle zzd;
    private final /* synthetic */ zzfe zze;

    public zzfj(zzfe zzfe, String str, Bundle bundle) {
        this.zze = zzfe;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }

    @WorkerThread
    public final Bundle zza() {
        if (!this.zzc) {
            this.zzc = true;
            String string = this.zze.zzg().getString(this.zza, (String) null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            String string2 = jSONObject.getString("n");
                            String string3 = jSONObject.getString("t");
                            char c = 65535;
                            switch (string3.hashCode()) {
                                case 100:
                                    if (string3.equals("d")) {
                                        c = 1;
                                        break;
                                    }
                                    break;
                                case 108:
                                    if (string3.equals("l")) {
                                        c = 2;
                                        break;
                                    }
                                    break;
                                case 115:
                                    if (string3.equals("s")) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                            }
                            switch (c) {
                                case 0:
                                    bundle.putString(string2, jSONObject.getString("v"));
                                    break;
                                case 1:
                                    bundle.putDouble(string2, Double.parseDouble(jSONObject.getString("v")));
                                    break;
                                case 2:
                                    bundle.putLong(string2, Long.parseLong(jSONObject.getString("v")));
                                    break;
                                default:
                                    this.zze.zzr().zzf().zza("Unrecognized persisted bundle type. Type", string3);
                                    break;
                            }
                        } catch (JSONException e) {
                            this.zze.zzr().zzf().zza("Error reading value from SharedPreferences. Value dropped");
                        } catch (NumberFormatException e2) {
                            this.zze.zzr().zzf().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.zzd = bundle;
                } catch (JSONException e3) {
                    this.zze.zzr().zzf().zza("Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.zzd == null) {
                this.zzd = this.zzb;
            }
        }
        return this.zzd;
    }

    @WorkerThread
    public final void zza(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor edit = this.zze.zzg().edit();
        if (bundle.size() == 0) {
            edit.remove(this.zza);
        } else {
            edit.putString(this.zza, zzb(bundle));
        }
        edit.apply();
        this.zzd = bundle;
    }

    private final String zzb(Bundle bundle) {
        JSONArray jSONArray = new JSONArray();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("n", str);
                    jSONObject.put("v", String.valueOf(obj));
                    if (obj instanceof String) {
                        jSONObject.put("t", "s");
                    } else if (obj instanceof Long) {
                        jSONObject.put("t", "l");
                    } else if (obj instanceof Double) {
                        jSONObject.put("t", "d");
                    } else {
                        this.zze.zzr().zzf().zza("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    this.zze.zzr().zzf().zza("Cannot serialize bundle value to SharedPreferences", e);
                }
            }
        }
        return jSONArray.toString();
    }
}
