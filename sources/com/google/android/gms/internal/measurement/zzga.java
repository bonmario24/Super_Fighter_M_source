package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzga extends zzdq<Long> implements zzfk, zzgy, RandomAccess {
    private static final zzga zza;
    private long[] zzb;
    private int zzc;

    public static zzga zzd() {
        return zza;
    }

    zzga() {
        this(new long[10], 0);
    }

    private zzga(long[] jArr, int i) {
        this.zzb = jArr;
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
        if (!(obj instanceof zzga)) {
            return super.equals(obj);
        }
        zzga zzga = (zzga) obj;
        if (this.zzc != zzga.zzc) {
            return false;
        }
        long[] jArr = zzga.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (this.zzb[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + zzfh.zza(this.zzb[i2]);
        }
        return i;
    }

    /* renamed from: zzc */
    public final zzfk zza(int i) {
        if (i >= this.zzc) {
            return new zzga(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public final long zzb(int i) {
        zzd(i);
        return this.zzb[i];
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == longValue) {
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

    public final void zza(long j) {
        zzc();
        if (this.zzc == this.zzb.length) {
            long[] jArr = new long[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, jArr, 0, this.zzc);
            this.zzb = jArr;
        }
        long[] jArr2 = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        jArr2[i] = j;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzc();
        zzfh.zza(collection);
        if (!(collection instanceof zzga)) {
            return super.addAll(collection);
        }
        zzga zzga = (zzga) collection;
        if (zzga.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzga.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzga.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzga.zzb, 0, this.zzb, this.zzc, zzga.zzc);
        this.zzc = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzc();
        for (int i = 0; i < this.zzc; i++) {
            if (obj.equals(Long.valueOf(this.zzb[i]))) {
                System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
                this.zzc--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzd(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
        }
    }

    private final String zze(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.zzc).toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzc();
        zzd(i);
        long j = this.zzb[i];
        this.zzb[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzc();
        zzd(i);
        long j = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzc();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zze(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            long[] jArr = new long[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, jArr, 0, i);
            System.arraycopy(this.zzb, i, jArr, i + 1, this.zzc - i);
            this.zzb = jArr;
        }
        this.zzb[i] = longValue;
        this.zzc++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Long) obj).longValue());
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(zzb(i));
    }

    static {
        zzga zzga = new zzga(new long[0], 0);
        zza = zzga;
        zzga.zzb();
    }
}
