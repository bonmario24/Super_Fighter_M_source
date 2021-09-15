package com.appsflyer.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.appsflyer.internal.al */
public class C0931al extends FilterInputStream {

    /* renamed from: Ι */
    private static final short f538 = ((short) ((int) ((Math.sqrt(5.0d) - 1.0d) * Math.pow(2.0d, 15.0d))));

    /* renamed from: ı */
    private byte[] f539 = new byte[8];

    /* renamed from: Ɩ */
    private int f540;

    /* renamed from: ǃ */
    private int f541 = 8;

    /* renamed from: ȷ */
    private int f542;

    /* renamed from: ɩ */
    private byte[] f543 = new byte[8];

    /* renamed from: ɹ */
    private int f544;

    /* renamed from: ɾ */
    private int f545;

    /* renamed from: ι */
    private byte[] f546 = new byte[8];

    /* renamed from: І */
    private int f547;

    /* renamed from: і */
    private int f548 = 8;

    /* renamed from: Ӏ */
    private int f549 = Integer.MAX_VALUE;

    /* renamed from: ӏ */
    private int f550;

    public C0931al(InputStream inputStream, int[] iArr, int i, byte[] bArr, int i2, int i3) throws IOException {
        super(inputStream);
        this.f547 = Math.min(Math.max(i2, 5), 16);
        this.f544 = i3;
        if (i3 == 3) {
            System.arraycopy(bArr, 0, this.f546, 0, 8);
        }
        long j = ((((long) iArr[0]) & 4294967295L) << 32) | (((long) iArr[1]) & 4294967295L);
        if (i == 0) {
            this.f540 = (int) j;
            this.f545 = (int) (((j >> 3) * ((long) f538)) >> 32);
            this.f542 = (int) (j >> 32);
            this.f550 = (int) ((j >> 3) + ((long) f538));
            return;
        }
        this.f540 = (int) j;
        this.f545 = ((int) j) * i;
        this.f542 = ((int) j) ^ i;
        this.f550 = (int) (j >> 32);
    }

    public int read() throws IOException {
        m355();
        if (this.f541 >= this.f548) {
            return -1;
        }
        byte[] bArr = this.f539;
        int i = this.f541;
        this.f541 = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = i;
        while (i4 < i3) {
            m355();
            if (this.f541 < this.f548) {
                byte[] bArr2 = this.f539;
                int i5 = this.f541;
                this.f541 = i5 + 1;
                bArr[i4] = bArr2[i5];
                i4++;
            } else if (i4 == i) {
                return -1;
            } else {
                return i2 - (i3 - i4);
            }
        }
        return i2;
    }

    public long skip(long j) throws IOException {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }

    public int available() throws IOException {
        m355();
        return this.f548 - this.f541;
    }

    public boolean markSupported() {
        return false;
    }

    /* renamed from: ɩ */
    private void m354() {
        if (this.f544 == 3) {
            System.arraycopy(this.f539, 0, this.f543, 0, this.f539.length);
        }
        int i = ((this.f539[0] << 24) & -16777216) + ((this.f539[1] << 16) & 16711680) + ((this.f539[2] << 8) & 65280) + (this.f539[3] & 255);
        int i2 = (this.f539[7] & 255) + ((this.f539[4] << 24) & -16777216) + ((this.f539[5] << 16) & 16711680) + ((this.f539[6] << 8) & 65280);
        for (int i3 = 0; i3 < this.f547; i3++) {
            i2 -= (((f538 * (this.f547 - i3)) + i) ^ ((i << 4) + this.f542)) ^ ((i >>> 5) + this.f550);
            i -= (((f538 * (this.f547 - i3)) + i2) ^ ((i2 << 4) + this.f540)) ^ ((i2 >>> 5) + this.f545);
        }
        this.f539[0] = (byte) (i >> 24);
        this.f539[1] = (byte) (i >> 16);
        this.f539[2] = (byte) (i >> 8);
        this.f539[3] = (byte) i;
        this.f539[4] = (byte) (i2 >> 24);
        this.f539[5] = (byte) (i2 >> 16);
        this.f539[6] = (byte) (i2 >> 8);
        this.f539[7] = (byte) i2;
        if (this.f544 == 3) {
            for (int i4 = 0; i4 < 8; i4++) {
                byte[] bArr = this.f539;
                bArr[i4] = (byte) (bArr[i4] ^ this.f546[i4]);
            }
            System.arraycopy(this.f543, 0, this.f546, 0, this.f543.length);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* renamed from: ι */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m355() throws java.io.IOException {
        /*
            r6 = this;
            r5 = 0
            r1 = 8
            int r0 = r6.f549
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r0 != r2) goto L_0x0012
            java.io.InputStream r0 = r6.in
            int r0 = r0.read()
            r6.f549 = r0
        L_0x0012:
            int r0 = r6.f541
            if (r0 != r1) goto L_0x005f
            byte[] r0 = r6.f539
            int r2 = r6.f549
            byte r2 = (byte) r2
            r0[r5] = r2
            int r0 = r6.f549
            if (r0 >= 0) goto L_0x0029
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unexpected block size"
            r0.<init>(r1)
            throw r0
        L_0x0029:
            r0 = 1
        L_0x002a:
            java.io.InputStream r2 = r6.in
            byte[] r3 = r6.f539
            int r4 = 8 - r0
            int r2 = r2.read(r3, r0, r4)
            if (r2 <= 0) goto L_0x0039
            int r0 = r0 + r2
            if (r0 < r1) goto L_0x002a
        L_0x0039:
            if (r0 >= r1) goto L_0x0043
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unexpected block size"
            r0.<init>(r1)
            throw r0
        L_0x0043:
            r6.m354()
            java.io.InputStream r0 = r6.in
            int r0 = r0.read()
            r6.f549 = r0
            r6.f541 = r5
            int r0 = r6.f549
            if (r0 >= 0) goto L_0x0062
            byte[] r0 = r6.f539
            r1 = 7
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = 8 - r0
        L_0x005d:
            r6.f548 = r0
        L_0x005f:
            int r0 = r6.f548
            return r0
        L_0x0062:
            r0 = r1
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0931al.m355():int");
    }
}
