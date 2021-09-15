package com.google.android.gms.internal.firebase_auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzhr extends zzgr<Double> implements zzio<Double>, zzka, RandomAccess {
    private static final zzhr zza;
    private double[] zzb;
    private int zzc;

    zzhr() {
        this(new double[10], 0);
    }

    private zzhr(double[] dArr, int i) {
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
        if (!(obj instanceof zzhr)) {
            return super.equals(obj);
        }
        zzhr zzhr = (zzhr) obj;
        if (this.zzc != zzhr.zzc) {
            return false;
        }
        double[] dArr = zzhr.zzb;
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
            i = (i * 31) + zzii.zza(Double.doubleToLongBits(this.zzb[i2]));
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
        zzii.zza(collection);
        if (!(collection instanceof zzhr)) {
            return super.addAll(collection);
        }
        zzhr zzhr = (zzhr) collection;
        if (zzhr.zzc == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.zzc < zzhr.zzc) {
            throw new OutOfMemoryError();
        }
        int i = this.zzc + zzhr.zzc;
        if (i > this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, i);
        }
        System.arraycopy(zzhr.zzb, 0, this.zzb, this.zzc, zzhr.zzc);
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

    public final /* synthetic */ zzio zza(int i) {
        if (i >= this.zzc) {
            return new zzhr(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzb(i);
        return Double.valueOf(this.zzb[i]);
    }

    static {
        zzhr zzhr = new zzhr(new double[0], 0);
        zza = zzhr;
        zzhr.mo22488c_();
    }
}
