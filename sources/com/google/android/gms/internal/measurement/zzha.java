package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzha<E> extends zzdq<E> implements RandomAccess {
    private static final zzha<Object> zza;
    private E[] zzb;
    private int zzc;

    public static <E> zzha<E> zzd() {
        return zza;
    }

    zzha() {
        this(new Object[10], 0);
    }

    private zzha(E[] eArr, int i) {
        this.zzb = eArr;
        this.zzc = i;
    }

    public final boolean add(E e) {
        zzc();
        if (this.zzc == this.zzb.length) {
            this.zzb = Arrays.copyOf(this.zzb, ((this.zzc * 3) / 2) + 1);
        }
        E[] eArr = this.zzb;
        int i = this.zzc;
        this.zzc = i + 1;
        eArr[i] = e;
        this.modCount++;
        return true;
    }

    public final void add(int i, E e) {
        zzc();
        if (i < 0 || i > this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
        if (this.zzc < this.zzb.length) {
            System.arraycopy(this.zzb, i, this.zzb, i + 1, this.zzc - i);
        } else {
            E[] eArr = new Object[(((this.zzc * 3) / 2) + 1)];
            System.arraycopy(this.zzb, 0, eArr, 0, i);
            System.arraycopy(this.zzb, i, eArr, i + 1, this.zzc - i);
            this.zzb = eArr;
        }
        this.zzb[i] = e;
        this.zzc++;
        this.modCount++;
    }

    public final E get(int i) {
        zzb(i);
        return this.zzb[i];
    }

    public final E remove(int i) {
        zzc();
        zzb(i);
        E e = this.zzb[i];
        if (i < this.zzc - 1) {
            System.arraycopy(this.zzb, i + 1, this.zzb, i, (this.zzc - i) - 1);
        }
        this.zzc--;
        this.modCount++;
        return e;
    }

    public final E set(int i, E e) {
        zzc();
        zzb(i);
        E e2 = this.zzb[i];
        this.zzb[i] = e;
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzc;
    }

    private final void zzb(int i) {
        if (i < 0 || i >= this.zzc) {
            throw new IndexOutOfBoundsException(zzc(i));
        }
    }

    private final String zzc(int i) {
        return new StringBuilder(35).append("Index:").append(i).append(", Size:").append(this.zzc).toString();
    }

    public final /* synthetic */ zzfn zza(int i) {
        if (i >= this.zzc) {
            return new zzha(Arrays.copyOf(this.zzb, i), this.zzc);
        }
        throw new IllegalArgumentException();
    }

    static {
        zzha<Object> zzha = new zzha<>(new Object[0], 0);
        zza = zzha;
        zzha.zzb();
    }
}
