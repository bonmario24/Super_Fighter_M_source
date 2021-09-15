package com.google.android.gms.internal.firebase_auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzij extends zzgr<Integer> implements zzim, zzka, RandomAccess {
    private static final zzij zza;
    private int[] zzb;
    private int zzc;

    public static zzij zzd() {
        return zza;
    }

    zzij() {
        this(new int[10], 0);
    }

    private zzij(int[] iArr, int i) {
        this.zzb = iArr;
        this.zzc = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzc();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzb, i2, this.zzb, i, this.zzc - i2);
        this.zzc -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzij)) {
            return super.equals(obj);
        }
        zzij zzij = (zzij) obj;
        if (this.zzc != zzij.zzc) {
            return false;
        }
        int[] iArr = zzij.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (this.zzb[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + this.zzb[i2];
        }
        return i;
    }

    /* renamed from: zzb */
    public final zzim zza(int i) {
        if (i >= this.zzc) {
            return new zzij(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public final int zzc(int i) {
        zze(i);
        return this.zzb[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == intValue) {
                return i;
            }
        }
        return -1;
    }

    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int size() {
        return this.zzc;
    }

    public final void zzd(int i) {
        zzc();
        if (this.zzc == this.zzb.length) {
            int[] iArr = new int[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, iArr, 0, this.zzc);
            this.zzb = iArr;
        }
        int[] iArr2 = this.zzb;
        int i2 = this.zzc;
        this.zzc = i2 + 1;
        iArr2[i2] = i;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzc();
        zzii.zza(collection);
        if (!(collection instanceof zzij)) {
            return super.addAll(collection);
        }
        zzij zzij = (zzij) collection;
        if (zzij.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzij.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzij.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzij.zzb, 0, this.zzb, this.zzc, zzij.zzc);
        this.zzc = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzc();
        for (int i = 0; i < this.zzc; i++) {
            if (obj.equals(Integer.valueOf(this.zzb[i]))) {
                System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
                this.zzc--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zze(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
    }

    private final String zzf(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.zzc).toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzc();
        zze(i);
        int i2 = this.zzb[i];
        this.zzb[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzc();
        zze(i);
        int i2 = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzc();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            int[] iArr = new int[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, iArr, 0, i);
            System.arraycopy(this.zzb, i, iArr, i + 1, this.zzc - i);
            this.zzb = iArr;
        }
        this.zzb[i] = intValue;
        this.zzc++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zzd(((Integer) obj).intValue());
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(zzc(i));
    }

    static {
        zzij zzij = new zzij(new int[0], 0);
        zza = zzij;
        zzij.mo22488c_();
    }
}
