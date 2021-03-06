package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzfs {
    private final ByteBuffer zzgd;
    private zzbn zzrh;
    private int zzri;

    private zzfs(ByteBuffer byteBuffer) {
        this.zzgd = byteBuffer;
        this.zzgd.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzfs(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int i;
        int i2 = 0;
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                i = i4;
                break;
            }
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                int length2 = charSequence.length();
                while (i3 < length2) {
                    char charAt2 = charSequence.charAt(i3);
                    if (charAt2 < 2048) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i3) < 65536) {
                                throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3).toString());
                            }
                            i3++;
                        }
                    }
                    i3++;
                }
                i = i4 + i2;
            }
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(((long) i) + 4294967296L).toString());
    }

    private final void zzao(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zzgd.hasRemaining()) {
            throw new zzft(this.zzgd.position(), this.zzgd.limit());
        }
        this.zzgd.put(b);
    }

    private final void zzap(int i) throws IOException {
        while ((i & -128) != 0) {
            zzao((i & 127) | 128);
            i >>>= 7;
        }
        zzao(i);
    }

    public static int zzb(int i, zzfz zzfz) {
        int zzr = zzr(i);
        int zzas = zzfz.zzas();
        return zzr + zzas + zzz(zzas);
    }

    public static int zzb(int i, String str) {
        return zzr(i) + zzh(str);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzr(i) + zzh(bArr);
    }

    public static int zzd(int i, long j) {
        return zzr(i) + zzo(j);
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3 = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                int remaining = byteBuffer.remaining();
                int length = charSequence.length();
                int i4 = arrayOffset + remaining;
                while (i3 < length && i3 + arrayOffset < i4) {
                    char charAt = charSequence.charAt(i3);
                    if (charAt >= 128) {
                        break;
                    }
                    array[arrayOffset + i3] = (byte) charAt;
                    i3++;
                }
                if (i3 == length) {
                    i = arrayOffset + length;
                } else {
                    int i5 = arrayOffset + i3;
                    while (i3 < length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (charAt2 < 128 && i5 < i4) {
                            i2 = i5 + 1;
                            array[i5] = (byte) charAt2;
                        } else if (charAt2 < 2048 && i5 <= i4 - 2) {
                            int i6 = i5 + 1;
                            array[i5] = (byte) ((charAt2 >>> 6) | 960);
                            i2 = i6 + 1;
                            array[i6] = (byte) ((charAt2 & '?') | 128);
                        } else if ((charAt2 < 55296 || 57343 < charAt2) && i5 <= i4 - 3) {
                            int i7 = i5 + 1;
                            array[i5] = (byte) ((charAt2 >>> 12) | 480);
                            int i8 = i7 + 1;
                            array[i7] = (byte) (((charAt2 >>> 6) & 63) | 128);
                            i2 = i8 + 1;
                            array[i8] = (byte) ((charAt2 & '?') | 128);
                        } else if (i5 <= i4 - 4) {
                            if (i3 + 1 != charSequence.length()) {
                                i3++;
                                char charAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt2, charAt3)) {
                                    int codePoint = Character.toCodePoint(charAt2, charAt3);
                                    int i9 = i5 + 1;
                                    array[i5] = (byte) ((codePoint >>> 18) | 240);
                                    int i10 = i9 + 1;
                                    array[i9] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i11 = i10 + 1;
                                    array[i10] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i2 = i11 + 1;
                                    array[i11] = (byte) ((codePoint & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                        } else {
                            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charAt2).append(" at index ").append(i5).toString());
                        }
                        i3++;
                        i5 = i2;
                    }
                    i = i5;
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            int length2 = charSequence.length();
            while (i3 < length2) {
                char charAt4 = charSequence.charAt(i3);
                if (charAt4 < 128) {
                    byteBuffer.put((byte) charAt4);
                } else if (charAt4 < 2048) {
                    byteBuffer.put((byte) ((charAt4 >>> 6) | 960));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else if (charAt4 < 55296 || 57343 < charAt4) {
                    byteBuffer.put((byte) ((charAt4 >>> 12) | 480));
                    byteBuffer.put((byte) (((charAt4 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((charAt4 & '?') | 128));
                } else {
                    if (i3 + 1 != charSequence.length()) {
                        i3++;
                        char charAt5 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt4, charAt5)) {
                            int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                            byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                            byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                        }
                    }
                    throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i3 - 1).toString());
                }
                i3++;
            }
        }
    }

    public static zzfs zzg(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    public static int zzh(String str) {
        int zza = zza(str);
        return zza + zzz(zza);
    }

    public static int zzh(byte[] bArr) {
        return zzz(bArr.length) + bArr.length;
    }

    public static zzfs zzh(byte[] bArr, int i, int i2) {
        return new zzfs(bArr, 0, i2);
    }

    public static long zzj(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzo(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static int zzr(int i) {
        return zzz(i << 3);
    }

    public static int zzs(int i) {
        if (i >= 0) {
            return zzz(i);
        }
        return 10;
    }

    private static int zzz(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public final void zza(int i, zzfz zzfz) throws IOException {
        zzb(i, 2);
        if (zzfz.zzrs < 0) {
            zzfz.zzas();
        }
        zzap(zzfz.zzrs);
        zzfz.zza(this);
    }

    public final void zza(int i, String str) throws IOException {
        zzb(i, 2);
        try {
            int zzz = zzz(str.length());
            if (zzz == zzz(str.length() * 3)) {
                int position = this.zzgd.position();
                if (this.zzgd.remaining() < zzz) {
                    throw new zzft(zzz + position, this.zzgd.limit());
                }
                this.zzgd.position(position + zzz);
                zzd((CharSequence) str, this.zzgd);
                int position2 = this.zzgd.position();
                this.zzgd.position(position);
                zzap((position2 - position) - zzz);
                this.zzgd.position(position2);
                return;
            }
            zzap(zza(str));
            zzd((CharSequence) str, this.zzgd);
        } catch (BufferOverflowException e) {
            zzft zzft = new zzft(this.zzgd.position(), this.zzgd.limit());
            zzft.initCause(e);
            throw zzft;
        }
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzb(i, 2);
        zzap(bArr.length);
        int length = bArr.length;
        if (this.zzgd.remaining() >= length) {
            this.zzgd.put(bArr, 0, length);
            return;
        }
        throw new zzft(this.zzgd.position(), this.zzgd.limit());
    }

    public final void zzb(int i, int i2) throws IOException {
        zzap((i << 3) | i2);
    }

    public final void zzb(int i, boolean z) throws IOException {
        int i2 = 0;
        zzb(25, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (!this.zzgd.hasRemaining()) {
            throw new zzft(this.zzgd.position(), this.zzgd.limit());
        }
        this.zzgd.put(b);
    }

    public final void zzc(int i, int i2) throws IOException {
        zzb(i, 0);
        if (i2 >= 0) {
            zzap(i2);
        } else {
            zzn((long) i2);
        }
    }

    public final void zze(int i, zzdo zzdo) throws IOException {
        if (this.zzrh == null) {
            this.zzrh = zzbn.zza(this.zzgd);
            this.zzri = this.zzgd.position();
        } else if (this.zzri != this.zzgd.position()) {
            this.zzrh.write(this.zzgd.array(), this.zzri, this.zzgd.position() - this.zzri);
            this.zzri = this.zzgd.position();
        }
        zzbn zzbn = this.zzrh;
        zzbn.zza(i, zzdo);
        zzbn.flush();
        this.zzri = this.zzgd.position();
    }

    public final void zzem() {
        if (this.zzgd.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzgd.remaining())}));
        }
    }

    public final void zzi(int i, long j) throws IOException {
        zzb(i, 0);
        zzn(j);
    }

    public final void zzn(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzao((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzao((int) j);
    }
}
