package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "WebImageCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();
    @SafeParcelable.VersionField(mo21176id = 1)
    private final int zali;
    @SafeParcelable.Field(getter = "getUrl", mo21170id = 2)
    private final Uri zani;
    @SafeParcelable.Field(getter = "getWidth", mo21170id = 3)
    private final int zanj;
    @SafeParcelable.Field(getter = "getHeight", mo21170id = 4)
    private final int zank;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param(mo21173id = 1) int i, @SafeParcelable.Param(mo21173id = 2) Uri uri, @SafeParcelable.Param(mo21173id = 3) int i2, @SafeParcelable.Param(mo21173id = 4) int i3) {
        this.zali = i;
        this.zani = uri;
        this.zanj = i2;
        this.zank = i3;
    }

    public WebImage(Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    @KeepForSdk
    public WebImage(JSONObject jSONObject) throws IllegalArgumentException {
        this(zaa(jSONObject), jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
    }

    private static Uri zaa(JSONObject jSONObject) {
        if (!jSONObject.has("url")) {
            return null;
        }
        try {
            return Uri.parse(jSONObject.getString("url"));
        } catch (JSONException e) {
            return null;
        }
    }

    public final Uri getUrl() {
        return this.zani;
    }

    public final int getWidth() {
        return this.zanj;
    }

    public final int getHeight() {
        return this.zank;
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zanj), Integer.valueOf(this.zank), this.zani.toString()});
    }

    @KeepForSdk
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zani.toString());
            jSONObject.put("width", this.zanj);
            jSONObject.put("height", this.zank);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        if (Objects.equal(this.zani, webImage.zani) && this.zanj == webImage.zanj && this.zank == webImage.zank) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zani, Integer.valueOf(this.zanj), Integer.valueOf(this.zank));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
