package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzeq extends zzdq<Double> implements zzfn<Double>, zzgy, RandomAccess {
    private static final zzeq zza;
    private double[] zzb;
    private int zzc;

    zzeq() {
        this(new double[10], 0);
    }

    private zzeq(double[] dArr, int i) {
        this.zzb = dArr;
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
        if (!(obj instanceof zzeq)) {
            return super.equals(obj);
        }
        zzeq zzeq = (zzeq) obj;
        if (this.zzc != zzeq.zzc) {
            return false;
        }
        double[] dArr = zzeq.zzb;
        for (int i = 0; i < this.zzc; i++) {
            if (Double.doubleToLongBits(this.zzb[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzc; i2++) {
            i = (i * 31) + zzfh.zza(Double.doubleToLongBits(this.zzb[i2]));
        }
        return i;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.zzb[i] == doubleValue) {
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

    public final void zza(double d) {
        zzc();
        if (this.zzc == this.zzb.length) {
            double[] dArr = new double[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, dArr, 0, this.zzc);
            this.zzb = dArr;
        }
        double[] dArr2 = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        dArr2[i] = d;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzc();
        zzfh.zza(collection);
        if (!(collection instanceof zzeq)) {
            return super.addAll(collection);
        }
        zzeq zzeq = (zzeq) collection;
        if (zzeq.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzeq.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzeq.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzeq.zzb, 0, this.zzb, this.zzc, zzeq.zzc);
        this.zzc = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzc();
        for (int i = 0; i < this.zzc; i++) {
            if (obj.equals(Double.valueOf(this.zzb[i]))) {
                System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
                this.zzc--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzb(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
    }

    private final String zzc(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.zzc).toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzc();
        zzb(i);
        double d = this.zzb[i];
        this.zzb[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzc();
        zzb(i);
        double d = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzc();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            double[] dArr = new double[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, dArr, 0, i);
            System.arraycopy(this.zzb, i, dArr, i + 1, this.zzc - i);
            this.zzb = dArr;
        }
        this.zzb[i] = doubleValue;
        this.zzc++;
        this.modCount++;
    }

    public final /* synthetic */ boolean add(Object obj) {
        zza(((Double) obj).doubleValue());
        return true;
    }

    public final /* synthetic */ zzfn zza(int i) {
        if (i >= this.zzc) {
            return new zzeq(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzb(i);
        return Double.valueOf(this.zzb[i]);
    }

    static {
        zzeq zzeq = new zzeq(new double[0], 0);
        zza = zzeq;
        zzeq.zzb();
    }
}
