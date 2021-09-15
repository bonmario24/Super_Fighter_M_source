package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzfx implements Cloneable {
    private Object value;
    private zzfv<?, ?> zzrp;
    private List<Object> zzrq = new ArrayList();

    zzfx() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzen()];
        zza(zzfs.zzg(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzeq */
    public final zzfx clone() {
        int i = 0;
        zzfx zzfx = new zzfx();
        try {
            zzfx.zzrp = this.zzrp;
            if (this.zzrq == null) {
                zzfx.zzrq = null;
            } else {
                zzfx.zzrq.addAll(this.zzrq);
            }
            if (this.value != null) {
                if (this.value instanceof zzfz) {
                    zzfx.value = (zzfz) ((zzfz) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzfx.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzfx.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzfx.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzfx.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzfx.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzfx.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzfx.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzfz[]) {
                    zzfz[] zzfzArr = (zzfz[]) this.value;
                    zzfz[] zzfzArr2 = new zzfz[zzfzArr.length];
                    zzfx.value = zzfzArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzfzArr.length) {
                            break;
                        }
                        zzfzArr2[i3] = (zzfz) zzfzArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzfx;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx)) {
            return false;
        }
        zzfx zzfx = (zzfx) obj;
        if (this.value == null || zzfx.value == null) {
            if (this.zzrq != null && zzfx.zzrq != null) {
                return this.zzrq.equals(zzfx.zzrq);
            }
            try {
                return Arrays.equals(toByteArray(), zzfx.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzrp == zzfx.zzrp) {
            return !this.zzrp.zzrk.isArray() ? this.value.equals(zzfx.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzfx.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzfx.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzfx.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzfx.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzfx.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzfx.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzfx.value);
        } else {
            return false;
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzfs zzfs) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.zzrq.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzen() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.zzrq.iterator();
        if (!it.hasNext()) {
            return 0;
        }
        it.next();
        throw new NoSuchMethodError();
    }
}
