package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInButtonConfigCreator")
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInButtonConfig> CREATOR = new zao();
    @SafeParcelable.VersionField(mo21176id = 1)
    private final int zali;
    @SafeParcelable.Field(getter = "getScopes", mo21170id = 4)
    @Deprecated
    private final Scope[] zaoc;
    @SafeParcelable.Field(getter = "getButtonSize", mo21170id = 2)
    private final int zapr;
    @SafeParcelable.Field(getter = "getColorScheme", mo21170id = 3)
    private final int zaps;

    @SafeParcelable.Constructor
    SignInButtonConfig(@SafeParcelable.Param(mo21173id = 1) int i, @SafeParcelable.Param(mo21173id = 2) int i2, @SafeParcelable.Param(mo21173id = 3) int i3, @SafeParcelable.Param(mo21173id = 4) Scope[] scopeArr) {
        this.zali = i;
        this.zapr = i2;
        this.zaps = i3;
        this.zaoc = scopeArr;
    }

    public SignInButtonConfig(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, (Scope[]) null);
    }

    public int getButtonSize() {
        return this.zapr;
    }

    public int getColorScheme() {
        return this.zaps;
    }

    @Deprecated
    public Scope[] getScopes() {
        return this.zaoc;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zali);
        SafeParcelWriter.writeInt(parcel, 2, getButtonSize());
        SafeParcelWriter.writeInt(parcel, 3, getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel, 4, getScopes(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
