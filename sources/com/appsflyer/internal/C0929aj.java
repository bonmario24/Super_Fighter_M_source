package com.appsflyer.internal;

/* renamed from: com.appsflyer.internal.aj */
public class C0929aj {
    /* renamed from: ı */
    public static void m351(int i, int i2, boolean z, int i3, int[] iArr, int[][] iArr2, int[] iArr3) {
        if (!z) {
            m352(iArr);
        }
        int i4 = 0;
        while (i4 < i3) {
            int i5 = iArr[i4] ^ i;
            i = (((iArr2[0][i5 >>> 24] + iArr2[1][(i5 >>> 16) & 255]) ^ iArr2[2][(i5 >>> 8) & 255]) + iArr2[3][i5 & 255]) ^ i2;
            i4++;
            i2 = i5;
        }
        int i6 = iArr[iArr.length - 2] ^ i;
        int i7 = iArr[iArr.length - 1] ^ i2;
        if (!z) {
            m352(iArr);
        }
        iArr3[0] = i7;
        iArr3[1] = i6;
    }

    /* renamed from: ı */
    private static void m352(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }
}
