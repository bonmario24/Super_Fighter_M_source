package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class AvailabilityException extends Exception {
    private final ArrayMap<ApiKey<?>, ConnectionResult> zaba;

    public AvailabilityException(ArrayMap<ApiKey<?>, ConnectionResult> arrayMap) {
        this.zaba = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        ApiKey<? extends Api.ApiOptions> apiKey = googleApi.getApiKey();
        Preconditions.checkArgument(this.zaba.get(apiKey) != null, "The given API was not part of the availability request.");
        return this.zaba.get(apiKey);
    }

    public ConnectionResult getConnectionResult(HasApiKey<? extends Api.ApiOptions> hasApiKey) {
        ApiKey<? extends Api.ApiOptions> apiKey = hasApiKey.getApiKey();
        Preconditions.checkArgument(this.zaba.get(apiKey) != null, "The given API was not part of the availability request.");
        return this.zaba.get(apiKey);
    }

    public final ArrayMap<ApiKey<?>, ConnectionResult> zaj() {
        return this.zaba;
    }

    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (ApiKey next : this.zaba.keySet()) {
            ConnectionResult connectionResult = this.zaba.get(next);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String apiName = next.getApiName();
            String valueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(apiName).length() + 2 + String.valueOf(valueOf).length()).append(apiName).append(": ").append(valueOf).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }
}
