package com.appsflyer.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.appsflyer.internal.an */
public class C0933an extends FilterInputStream {

    /* renamed from: ı */
    private byte[] f552;

    /* renamed from: Ɩ */
    private int f553;

    /* renamed from: ǃ */
    private byte[] f554;

    /* renamed from: ɩ */
    private C0930ak f555;

    /* renamed from: ɹ */
    private int[] f556;

    /* renamed from: Ι */
    private byte[] f557;

    /* renamed from: ι */
    private final int f558;

    /* renamed from: І */
    private int f559;

    /* renamed from: і */
    private int f560 = Integer.MAX_VALUE;

    /* renamed from: Ӏ */
    private int f561;

    public C0933an(InputStream inputStream, int[] iArr, byte[] bArr, int i, boolean z, int i2) throws IOException {
        super(inputStream);
        this.f558 = Math.min(Math.max(i, 3), 16);
        this.f554 = new byte[8];
        this.f557 = new byte[8];
        this.f552 = new byte[8];
        this.f556 = new int[2];
        this.f553 = 8;
        this.f561 = 8;
        this.f559 = i2;
        if (i2 == 2) {
            System.arraycopy(bArr, 0, this.f557, 0, 8);
        }
        this.f555 = new C0930ak(iArr, this.f558, true, z);
    }

    public int read() throws IOException {
        m356();
        if (this.f553 >= this.f561) {
            return -1;
        }
        byte[] bArr = this.f554;
        int i = this.f553;
        this.f553 = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i + i2;
        int i4 = i;
        while (i4 < i3) {
            m356();
            if (this.f553 < this.f561) {
                byte[] bArr2 = this.f554;
                int i5 = this.f553;
                this.f553 = i5 + 1;
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
        m356();
        return this.f561 - this.f553;
    }

    public boolean markSupported() {
        return false;
    }

    /* renamed from: Ι */
    private void m357() {
        if (this.f559 == 2) {
            System.arraycopy(this.f554, 0, this.f552, 0, this.f554.length);
        }
        C0929aj.m351(((this.f554[0] << 24) & -16777216) + ((this.f554[1] << 16) & 16711680) + ((this.f554[2] << 8) & 65280) + (this.f554[3] & 255), ((this.f554[4] << 24) & -16777216) + ((this.f554[5] << 16) & 16711680) + ((this.f554[6] << 8) & 65280) + (this.f554[7] & 255), false, this.f558, this.f555.f536, this.f555.f537, this.f556);
        int i = this.f556[0];
        int i2 = this.f556[1];
        this.f554[0] = (byte) (i >> 24);
        this.f554[1] = (byte) (i >> 16);
        this.f554[2] = (byte) (i >> 8);
        this.f554[3] = (byte) i;
        this.f554[4] = (byte) (i2 >> 24);
        this.f554[5] = (byte) (i2 >> 16);
        this.f554[6] = (byte) (i2 >> 8);
        this.f554[7] = (byte) i2;
        if (this.f559 == 2) {
            for (int i3 = 0; i3 < 8; i3++) {
                byte[] bArr = this.f554;
                bArr[i3] = (byte) (bArr[i3] ^ this.f557[i3]);
            }
            System.arraycopy(this.f552, 0, this.f557, 0, this.f552.length);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* renamed from: ǃ */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m356() throws java.io.IOException {
        /*
            r6 = this;
            r5 = 0
            r1 = 8
            int r0 = r6.f560
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r0 != r2) goto L_0x0012
            java.io.InputStream r0 = r6.in
            int r0 = r0.read()
            r6.f560 = r0
        L_0x0012:
            int r0 = r6.f553
            if (r0 != r1) goto L_0x005f
            byte[] r0 = r6.f554
            int r2 = r6.f560
            byte r2 = (byte) r2
            r0[r5] = r2
            int r0 = r6.f560
            if (r0 >= 0) goto L_0x0029
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "unexpected block size"
            r0.<init>(r1)
            throw r0
        L_0x0029:
            r0 = 1
        L_0x002a:
            java.io.InputStream r2 = r6.in
            byte[] r3 = r6.f554
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
            r6.m357()
            java.io.InputStream r0 = r6.in
            int r0 = r0.read()
            r6.f560 = r0
            r6.f553 = r5
            int r0 = r6.f560
            if (r0 >= 0) goto L_0x0062
            byte[] r0 = r6.f554
            r1 = 7
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = 8 - r0
        L_0x005d:
            r6.f561 = r0
        L_0x005f:
            int r0 = r6.f561
            return r0
        L_0x0062:
            r0 = r1
            goto L_0x005d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.C0933an.m356():int");
    }
}
