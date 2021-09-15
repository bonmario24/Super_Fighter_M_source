package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.JSONLexer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

public final class SerializeWriter extends Writer {
    public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final char[] DigitOnes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final char[] ascii_chars = {'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static final char[] replaceChars = new char[93];
    static final int[] sizeTable = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
    static final byte[] specicalFlags_doubleQuotes = new byte[161];
    static final byte[] specicalFlags_singleQuotes = new byte[161];
    protected char[] buf;
    protected int count;
    protected int features;
    protected final Writer writer;

    static {
        specicalFlags_doubleQuotes[0] = 4;
        specicalFlags_doubleQuotes[1] = 4;
        specicalFlags_doubleQuotes[2] = 4;
        specicalFlags_doubleQuotes[3] = 4;
        specicalFlags_doubleQuotes[4] = 4;
        specicalFlags_doubleQuotes[5] = 4;
        specicalFlags_doubleQuotes[6] = 4;
        specicalFlags_doubleQuotes[7] = 4;
        specicalFlags_doubleQuotes[8] = 1;
        specicalFlags_doubleQuotes[9] = 1;
        specicalFlags_doubleQuotes[10] = 1;
        specicalFlags_doubleQuotes[11] = 4;
        specicalFlags_doubleQuotes[12] = 1;
        specicalFlags_doubleQuotes[13] = 1;
        specicalFlags_doubleQuotes[34] = 1;
        specicalFlags_doubleQuotes[92] = 1;
        specicalFlags_singleQuotes[0] = 4;
        specicalFlags_singleQuotes[1] = 4;
        specicalFlags_singleQuotes[2] = 4;
        specicalFlags_singleQuotes[3] = 4;
        specicalFlags_singleQuotes[4] = 4;
        specicalFlags_singleQuotes[5] = 4;
        specicalFlags_singleQuotes[6] = 4;
        specicalFlags_singleQuotes[7] = 4;
        specicalFlags_singleQuotes[8] = 1;
        specicalFlags_singleQuotes[9] = 1;
        specicalFlags_singleQuotes[10] = 1;
        specicalFlags_singleQuotes[11] = 4;
        specicalFlags_singleQuotes[12] = 1;
        specicalFlags_singleQuotes[13] = 1;
        specicalFlags_singleQuotes[92] = 1;
        specicalFlags_singleQuotes[39] = 1;
        for (int i = 14; i <= 31; i++) {
            specicalFlags_doubleQuotes[i] = 4;
            specicalFlags_singleQuotes[i] = 4;
        }
        for (int i2 = 127; i2 <= 160; i2++) {
            specicalFlags_doubleQuotes[i2] = 4;
            specicalFlags_singleQuotes[i2] = 4;
        }
        replaceChars[0] = '0';
        replaceChars[1] = '1';
        replaceChars[2] = '2';
        replaceChars[3] = '3';
        replaceChars[4] = '4';
        replaceChars[5] = '5';
        replaceChars[6] = '6';
        replaceChars[7] = '7';
        replaceChars[8] = 'b';
        replaceChars[9] = 't';
        replaceChars[10] = 'n';
        replaceChars[11] = 'v';
        replaceChars[12] = 'f';
        replaceChars[13] = 'r';
        replaceChars[34] = '\"';
        replaceChars[39] = '\'';
        replaceChars[47] = '/';
        replaceChars[92] = '\\';
    }

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer2) {
        this.writer = writer2;
        this.features = JSON.DEFAULT_GENERATE_FEATURE;
        this.buf = bufLocal.get();
        if (bufLocal != null) {
            bufLocal.set((Object) null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
    }

    public SerializeWriter(SerializerFeature... features2) {
        this((Writer) null, 0, features2);
    }

    public SerializeWriter(Writer writer2, int featuresValue, SerializerFeature[] features2) {
        this.writer = writer2;
        this.buf = bufLocal.get();
        if (this.buf != null) {
            bufLocal.set((Object) null);
        }
        if (this.buf == null) {
            this.buf = new char[1024];
        }
        for (SerializerFeature feature : features2) {
            featuresValue |= feature.mask;
        }
        this.features = featuresValue;
    }

    public SerializeWriter(int initialSize) {
        this((Writer) null, initialSize);
    }

    public SerializeWriter(Writer writer2, int initialSize) {
        this.writer = writer2;
        if (initialSize <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + initialSize);
        }
        this.buf = new char[initialSize];
    }

    public void config(SerializerFeature feature, boolean state) {
        if (state) {
            this.features |= feature.mask;
        } else {
            this.features &= feature.mask ^ -1;
        }
    }

    public boolean isEnabled(SerializerFeature feature) {
        return (this.features & feature.mask) != 0;
    }

    public void write(int c) {
        int newcount = this.count + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                flush();
                newcount = 1;
            }
        }
        this.buf[this.count] = (char) c;
        this.count = newcount;
    }

    public void write(char[] c, int off, int len) {
        if (off < 0 || off > c.length || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            int newcount = this.count + len;
            if (newcount > this.buf.length) {
                if (this.writer == null) {
                    expandCapacity(newcount);
                } else {
                    do {
                        int rest = this.buf.length - this.count;
                        System.arraycopy(c, off, this.buf, this.count, rest);
                        this.count = this.buf.length;
                        flush();
                        len -= rest;
                        off += rest;
                    } while (len > this.buf.length);
                    newcount = len;
                }
            }
            System.arraycopy(c, off, this.buf, this.count, len);
            this.count = newcount;
        }
    }

    /* access modifiers changed from: protected */
    public void expandCapacity(int minimumCapacity) {
        int newCapacity = ((this.buf.length * 3) / 2) + 1;
        if (newCapacity < minimumCapacity) {
            newCapacity = minimumCapacity;
        }
        char[] newValue = new char[newCapacity];
        System.arraycopy(this.buf, 0, newValue, 0, this.count);
        this.buf = newValue;
    }

    public void write(String str, int off, int len) {
        int newcount = this.count + len;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                do {
                    int rest = this.buf.length - this.count;
                    str.getChars(off, off + rest, this.buf, this.count);
                    this.count = this.buf.length;
                    flush();
                    len -= rest;
                    off += rest;
                } while (len > this.buf.length);
                newcount = len;
            }
        }
        str.getChars(off, off + len, this.buf, this.count);
        this.count = newcount;
    }

    public void writeTo(Writer out) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        out.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream out, String charsetName) throws IOException {
        writeTo(out, Charset.forName(charsetName));
    }

    public void writeTo(OutputStream out, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        out.write(new String(this.buf, 0, this.count).getBytes(charset.name()));
    }

    public SerializeWriter append(CharSequence csq) {
        String s = csq == null ? "null" : csq.toString();
        write(s, 0, s.length());
        return this;
    }

    public SerializeWriter append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        String s = csq.subSequence(start, end).toString();
        write(s, 0, s.length());
        return this;
    }

    public SerializeWriter append(char c) {
        write((int) c);
        return this;
    }

    public byte[] toBytes(String charsetName) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charsetName == null) {
            charsetName = "UTF-8";
        }
        try {
            return new String(this.buf, 0, this.count).getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("toBytes error", e);
        }
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= 8192) {
            bufLocal.set(this.buf);
        }
        this.buf = null;
    }

    public void write(String text) {
        if (text == null) {
            writeNull();
        } else {
            write(text, 0, text.length());
        }
    }

    public void writeInt(int i) {
        int x;
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        if (i < 0) {
            x = -i;
        } else {
            x = i;
        }
        int j = 0;
        while (x > sizeTable[j]) {
            j++;
        }
        int size = j + 1;
        if (i < 0) {
            size++;
        }
        int newcount = this.count + size;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                getChars((long) i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        getChars((long) i, newcount, this.buf);
        this.count = newcount;
    }

    public void writeByteArray(byte[] bytes) {
        int bytesLen = bytes.length;
        boolean singleQuote = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        char quote = singleQuote ? '\'' : '\"';
        if (bytesLen == 0) {
            write(singleQuote ? "''" : "\"\"");
            return;
        }
        char[] CA = JSONLexer.f335CA;
        int eLen = (bytesLen / 3) * 3;
        int offset = this.count;
        int newcount = this.count + ((((bytesLen - 1) / 3) + 1) << 2) + 2;
        if (newcount > this.buf.length) {
            if (this.writer != null) {
                write((int) quote);
                int s = 0;
                while (true) {
                    int s2 = s;
                    if (s2 >= eLen) {
                        break;
                    }
                    int s3 = s2 + 1;
                    int s4 = s3 + 1;
                    s = s4 + 1;
                    int i = ((bytes[s2] & 255) << 16) | ((bytes[s3] & 255) << 8) | (bytes[s4] & 255);
                    write((int) CA[(i >>> 18) & 63]);
                    write((int) CA[(i >>> 12) & 63]);
                    write((int) CA[(i >>> 6) & 63]);
                    write((int) CA[i & 63]);
                }
                int left = bytesLen - eLen;
                if (left > 0) {
                    int i2 = ((bytes[eLen] & 255) << 10) | (left == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
                    write((int) CA[i2 >> 12]);
                    write((int) CA[(i2 >>> 6) & 63]);
                    write((int) left == 2 ? CA[i2 & 63] : '=');
                    write(61);
                }
                write((int) quote);
                return;
            }
            expandCapacity(newcount);
        }
        this.count = newcount;
        this.buf[offset] = quote;
        int s5 = 0;
        int d = offset + 1;
        while (true) {
            int s6 = s5;
            if (s6 >= eLen) {
                break;
            }
            int s7 = s6 + 1;
            int s8 = s7 + 1;
            s5 = s8 + 1;
            int i3 = ((bytes[s6] & 255) << 16) | ((bytes[s7] & 255) << 8) | (bytes[s8] & 255);
            int d2 = d + 1;
            this.buf[d] = CA[(i3 >>> 18) & 63];
            int d3 = d2 + 1;
            this.buf[d2] = CA[(i3 >>> 12) & 63];
            int d4 = d3 + 1;
            this.buf[d3] = CA[(i3 >>> 6) & 63];
            d = d4 + 1;
            this.buf[d4] = CA[i3 & 63];
        }
        int left2 = bytesLen - eLen;
        if (left2 > 0) {
            int i4 = ((bytes[eLen] & 255) << 10) | (left2 == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
            this.buf[newcount - 5] = CA[i4 >> 12];
            this.buf[newcount - 4] = CA[(i4 >>> 6) & 63];
            this.buf[newcount - 3] = left2 == 2 ? CA[i4 & 63] : '=';
            this.buf[newcount - 2] = '=';
        }
        this.buf[newcount - 1] = quote;
    }

    public void writeLong(long i) {
        long val;
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        if (i < 0) {
            val = -i;
        } else {
            val = i;
        }
        int size = 0;
        long p = 10;
        int j = 1;
        while (true) {
            if (j >= 19) {
                break;
            } else if (val < p) {
                size = j;
                break;
            } else {
                p *= 10;
                j++;
            }
        }
        if (size == 0) {
            size = 19;
        }
        if (i < 0) {
            size++;
        }
        int newcount = this.count + size;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else {
                char[] chars = new char[size];
                getChars(i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        getChars(i, newcount, this.buf);
        this.count = newcount;
    }

    public void writeNull() {
        write("null");
    }

    /* JADX WARNING: type inference failed for: r0v44, types: [int] */
    /* JADX WARNING: type inference failed for: r0v146, types: [int] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeStringWithDoubleQuote(java.lang.String r27, char r28, boolean r29) {
        /*
            r26 = this;
            if (r27 != 0) goto L_0x000f
            r26.writeNull()
            if (r28 == 0) goto L_0x000e
            r0 = r26
            r1 = r28
            r0.write((int) r1)
        L_0x000e:
            return
        L_0x000f:
            int r15 = r27.length()
            r0 = r26
            int r0 = r0.count
            r21 = r0
            int r21 = r21 + r15
            int r16 = r21 + 2
            if (r28 == 0) goto L_0x0021
            int r16 = r16 + 1
        L_0x0021:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r16
            r1 = r21
            if (r0 <= r1) goto L_0x01a7
            r0 = r26
            java.io.Writer r0 = r0.writer
            r21 = r0
            if (r21 == 0) goto L_0x01a0
            r21 = 34
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            r11 = 0
        L_0x0044:
            int r21 = r27.length()
            r0 = r21
            if (r11 >= r0) goto L_0x018c
            r0 = r27
            char r7 = r0.charAt(r11)
            r0 = r26
            int r0 = r0.features
            r21 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r22 = com.alibaba.fastjson.serializer.SerializerFeature.BrowserCompatible
            r0 = r22
            int r0 = r0.mask
            r22 = r0
            r21 = r21 & r22
            if (r21 == 0) goto L_0x0146
            r21 = 8
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 12
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 10
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 13
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 9
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 34
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 47
            r0 = r21
            if (r7 == r0) goto L_0x0094
            r21 = 92
            r0 = r21
            if (r7 != r0) goto L_0x00ab
        L_0x0094:
            r21 = 92
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = replaceChars
            char r21 = r21[r7]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
        L_0x00a8:
            int r11 = r11 + 1
            goto L_0x0044
        L_0x00ab:
            r21 = 32
            r0 = r21
            if (r7 >= r0) goto L_0x00f2
            r21 = 92
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            r21 = 117(0x75, float:1.64E-43)
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            r21 = 48
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            r21 = 48
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = ascii_chars
            int r22 = r7 * 2
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = ascii_chars
            int r22 = r7 * 2
            int r22 = r22 + 1
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            goto L_0x00a8
        L_0x00f2:
            r21 = 127(0x7f, float:1.78E-43)
            r0 = r21
            if (r7 < r0) goto L_0x0185
            r21 = 92
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            r21 = 117(0x75, float:1.64E-43)
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = DIGITS
            int r22 = r7 >>> 12
            r22 = r22 & 15
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = DIGITS
            int r22 = r7 >>> 8
            r22 = r22 & 15
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = DIGITS
            int r22 = r7 >>> 4
            r22 = r22 & 15
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = DIGITS
            r22 = r7 & 15
            char r21 = r21[r22]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            goto L_0x00a8
        L_0x0146:
            byte[] r21 = specicalFlags_doubleQuotes
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r21
            if (r7 >= r0) goto L_0x0157
            byte[] r21 = specicalFlags_doubleQuotes
            byte r21 = r21[r7]
            if (r21 != 0) goto L_0x016f
        L_0x0157:
            r21 = 47
            r0 = r21
            if (r7 != r0) goto L_0x0185
            r0 = r26
            int r0 = r0.features
            r21 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r22 = com.alibaba.fastjson.serializer.SerializerFeature.WriteSlashAsSpecial
            r0 = r22
            int r0 = r0.mask
            r22 = r0
            r21 = r21 & r22
            if (r21 == 0) goto L_0x0185
        L_0x016f:
            r21 = 92
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            char[] r21 = replaceChars
            char r21 = r21[r7]
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            goto L_0x00a8
        L_0x0185:
            r0 = r26
            r0.write((int) r7)
            goto L_0x00a8
        L_0x018c:
            r21 = 34
            r0 = r26
            r1 = r21
            r0.write((int) r1)
            if (r28 == 0) goto L_0x000e
            r0 = r26
            r1 = r28
            r0.write((int) r1)
            goto L_0x000e
        L_0x01a0:
            r0 = r26
            r1 = r16
            r0.expandCapacity(r1)
        L_0x01a7:
            r0 = r26
            int r0 = r0.count
            r21 = r0
            int r19 = r21 + 1
            int r9 = r19 + r15
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            r23 = 34
            r21[r22] = r23
            r21 = 0
            r0 = r26
            char[] r0 = r0.buf
            r22 = r0
            r0 = r27
            r1 = r21
            r2 = r22
            r3 = r19
            r0.getChars(r1, r15, r2, r3)
            r0 = r16
            r1 = r26
            r1.count = r0
            r0 = r26
            int r0 = r0.features
            r21 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r22 = com.alibaba.fastjson.serializer.SerializerFeature.BrowserCompatible
            r0 = r22
            int r0 = r0.mask
            r22 = r0
            r21 = r21 & r22
            if (r21 == 0) goto L_0x042c
            r14 = -1
            r11 = r19
        L_0x01ef:
            if (r11 >= r9) goto L_0x0247
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            char r7 = r21[r11]
            r21 = 34
            r0 = r21
            if (r7 == r0) goto L_0x020b
            r21 = 47
            r0 = r21
            if (r7 == r0) goto L_0x020b
            r21 = 92
            r0 = r21
            if (r7 != r0) goto L_0x0211
        L_0x020b:
            r14 = r11
            int r16 = r16 + 1
        L_0x020e:
            int r11 = r11 + 1
            goto L_0x01ef
        L_0x0211:
            r21 = 8
            r0 = r21
            if (r7 == r0) goto L_0x022f
            r21 = 12
            r0 = r21
            if (r7 == r0) goto L_0x022f
            r21 = 10
            r0 = r21
            if (r7 == r0) goto L_0x022f
            r21 = 13
            r0 = r21
            if (r7 == r0) goto L_0x022f
            r21 = 9
            r0 = r21
            if (r7 != r0) goto L_0x0233
        L_0x022f:
            r14 = r11
            int r16 = r16 + 1
            goto L_0x020e
        L_0x0233:
            r21 = 32
            r0 = r21
            if (r7 >= r0) goto L_0x023d
            r14 = r11
            int r16 = r16 + 5
            goto L_0x020e
        L_0x023d:
            r21 = 127(0x7f, float:1.78E-43)
            r0 = r21
            if (r7 < r0) goto L_0x020e
            r14 = r11
            int r16 = r16 + 5
            goto L_0x020e
        L_0x0247:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r16
            r1 = r21
            if (r0 <= r1) goto L_0x025f
            r0 = r26
            r1 = r16
            r0.expandCapacity(r1)
        L_0x025f:
            r0 = r16
            r1 = r26
            r1.count = r0
            r11 = r14
        L_0x0266:
            r0 = r19
            if (r11 < r0) goto L_0x03f2
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            char r7 = r21[r11]
            r21 = 8
            r0 = r21
            if (r7 == r0) goto L_0x0290
            r21 = 12
            r0 = r21
            if (r7 == r0) goto L_0x0290
            r21 = 10
            r0 = r21
            if (r7 == r0) goto L_0x0290
            r21 = 13
            r0 = r21
            if (r7 == r0) goto L_0x0290
            r21 = 9
            r0 = r21
            if (r7 != r0) goto L_0x02c4
        L_0x0290:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r0 = r26
            char[] r0 = r0.buf
            r23 = r0
            int r24 = r11 + 2
            int r25 = r9 - r11
            int r25 = r25 + -1
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r11] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            char[] r23 = replaceChars
            char r23 = r23[r7]
            r21[r22] = r23
            int r9 = r9 + 1
        L_0x02c1:
            int r11 = r11 + -1
            goto L_0x0266
        L_0x02c4:
            r21 = 34
            r0 = r21
            if (r7 == r0) goto L_0x02d6
            r21 = 47
            r0 = r21
            if (r7 == r0) goto L_0x02d6
            r21 = 92
            r0 = r21
            if (r7 != r0) goto L_0x0304
        L_0x02d6:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r0 = r26
            char[] r0 = r0.buf
            r23 = r0
            int r24 = r11 + 2
            int r25 = r9 - r11
            int r25 = r25 + -1
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r11] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r21[r22] = r7
            int r9 = r9 + 1
            goto L_0x02c1
        L_0x0304:
            r21 = 32
            r0 = r21
            if (r7 >= r0) goto L_0x0375
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r0 = r26
            char[] r0 = r0.buf
            r23 = r0
            int r24 = r11 + 6
            int r25 = r9 - r11
            int r25 = r25 + -1
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r11] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r23 = 117(0x75, float:1.64E-43)
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 2
            r23 = 48
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 3
            r23 = 48
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 4
            char[] r23 = ascii_chars
            int r24 = r7 * 2
            char r23 = r23[r24]
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 5
            char[] r23 = ascii_chars
            int r24 = r7 * 2
            int r24 = r24 + 1
            char r23 = r23[r24]
            r21[r22] = r23
            int r9 = r9 + 5
            goto L_0x02c1
        L_0x0375:
            r21 = 127(0x7f, float:1.78E-43)
            r0 = r21
            if (r7 < r0) goto L_0x02c1
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r0 = r26
            char[] r0 = r0.buf
            r23 = r0
            int r24 = r11 + 6
            int r25 = r9 - r11
            int r25 = r25 + -1
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r11] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 1
            r23 = 117(0x75, float:1.64E-43)
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 2
            char[] r23 = DIGITS
            int r24 = r7 >>> 12
            r24 = r24 & 15
            char r23 = r23[r24]
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 3
            char[] r23 = DIGITS
            int r24 = r7 >>> 8
            r24 = r24 & 15
            char r23 = r23[r24]
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 4
            char[] r23 = DIGITS
            int r24 = r7 >>> 4
            r24 = r24 & 15
            char r23 = r23[r24]
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r22 = r11 + 5
            char[] r23 = DIGITS
            r24 = r7 & 15
            char r23 = r23[r24]
            r21[r22] = r23
            int r9 = r9 + 5
            goto L_0x02c1
        L_0x03f2:
            if (r28 == 0) goto L_0x0418
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -2
            r23 = 34
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -1
            r21[r22] = r28
            goto L_0x000e
        L_0x0418:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -1
            r23 = 34
            r21[r22] = r23
            goto L_0x000e
        L_0x042c:
            r17 = 0
            r14 = -1
            r10 = -1
            r13 = 0
            if (r29 == 0) goto L_0x0574
            r11 = r19
        L_0x0435:
            if (r11 >= r9) goto L_0x04e1
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            char r7 = r21[r11]
            r21 = 8232(0x2028, float:1.1535E-41)
            r0 = r21
            if (r7 != r0) goto L_0x0455
            int r17 = r17 + 1
            r14 = r11
            r13 = r7
            int r16 = r16 + 4
            r21 = -1
            r0 = r21
            if (r10 != r0) goto L_0x0452
            r10 = r11
        L_0x0452:
            int r11 = r11 + 1
            goto L_0x0435
        L_0x0455:
            r21 = 93
            r0 = r21
            if (r7 < r0) goto L_0x0475
            r21 = 127(0x7f, float:1.78E-43)
            r0 = r21
            if (r7 < r0) goto L_0x0452
            r21 = 160(0xa0, float:2.24E-43)
            r0 = r21
            if (r7 > r0) goto L_0x0452
            r21 = -1
            r0 = r21
            if (r10 != r0) goto L_0x046e
            r10 = r11
        L_0x046e:
            int r17 = r17 + 1
            r14 = r11
            r13 = r7
            int r16 = r16 + 4
            goto L_0x0452
        L_0x0475:
            r21 = 32
            r0 = r21
            if (r7 != r0) goto L_0x04a3
            r12 = 0
        L_0x047c:
            if (r12 == 0) goto L_0x0452
            int r17 = r17 + 1
            r14 = r11
            r13 = r7
            byte[] r21 = specicalFlags_doubleQuotes
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r21
            if (r7 >= r0) goto L_0x049b
            byte[] r21 = specicalFlags_doubleQuotes
            byte r21 = r21[r7]
            r22 = 4
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x049b
            int r16 = r16 + 4
        L_0x049b:
            r21 = -1
            r0 = r21
            if (r10 != r0) goto L_0x0452
            r10 = r11
            goto L_0x0452
        L_0x04a3:
            r21 = 47
            r0 = r21
            if (r7 != r0) goto L_0x04bd
            r0 = r26
            int r0 = r0.features
            r21 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r22 = com.alibaba.fastjson.serializer.SerializerFeature.WriteSlashAsSpecial
            r0 = r22
            int r0 = r0.mask
            r22 = r0
            r21 = r21 & r22
            if (r21 == 0) goto L_0x04bd
            r12 = 1
            goto L_0x047c
        L_0x04bd:
            r21 = 35
            r0 = r21
            if (r7 <= r0) goto L_0x04cb
            r21 = 92
            r0 = r21
            if (r7 == r0) goto L_0x04cb
            r12 = 0
            goto L_0x047c
        L_0x04cb:
            r21 = 31
            r0 = r21
            if (r7 <= r0) goto L_0x04dd
            r21 = 92
            r0 = r21
            if (r7 == r0) goto L_0x04dd
            r21 = 34
            r0 = r21
            if (r7 != r0) goto L_0x04df
        L_0x04dd:
            r12 = 1
            goto L_0x047c
        L_0x04df:
            r12 = 0
            goto L_0x047c
        L_0x04e1:
            if (r17 <= 0) goto L_0x0574
            int r16 = r16 + r17
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r16
            r1 = r21
            if (r0 <= r1) goto L_0x04fd
            r0 = r26
            r1 = r16
            r0.expandCapacity(r1)
        L_0x04fd:
            r0 = r16
            r1 = r26
            r1.count = r0
            r21 = 1
            r0 = r17
            r1 = r21
            if (r0 != r1) goto L_0x0667
            r21 = 8232(0x2028, float:1.1535E-41)
            r0 = r21
            if (r13 != r0) goto L_0x059a
            int r18 = r14 + 1
            int r8 = r14 + 6
            int r21 = r9 - r14
            int r4 = r21 + -1
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            char[] r0 = r0.buf
            r22 = r0
            r0 = r21
            r1 = r18
            r2 = r22
            java.lang.System.arraycopy(r0, r1, r2, r8, r4)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            r22 = 117(0x75, float:1.64E-43)
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            r22 = 50
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            r22 = 48
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            r22 = 50
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            r22 = 56
            r21[r14] = r22
        L_0x0574:
            if (r28 == 0) goto L_0x07a0
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -2
            r23 = 34
            r21[r22] = r23
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -1
            r21[r22] = r28
            goto L_0x000e
        L_0x059a:
            r7 = r13
            byte[] r21 = specicalFlags_doubleQuotes
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r21
            if (r7 >= r0) goto L_0x0630
            byte[] r21 = specicalFlags_doubleQuotes
            byte r21 = r21[r7]
            r22 = 4
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x0630
            int r18 = r14 + 1
            int r8 = r14 + 6
            int r21 = r9 - r14
            int r4 = r21 + -1
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            char[] r0 = r0.buf
            r22 = r0
            r0 = r21
            r1 = r18
            r2 = r22
            java.lang.System.arraycopy(r0, r1, r2, r8, r4)
            r5 = r14
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            r22 = 92
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            r22 = 117(0x75, float:1.64E-43)
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 12
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 8
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 4
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            r23 = r7 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            goto L_0x0574
        L_0x0630:
            int r18 = r14 + 1
            int r8 = r14 + 2
            int r21 = r9 - r14
            int r4 = r21 + -1
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            char[] r0 = r0.buf
            r22 = r0
            r0 = r21
            r1 = r18
            r2 = r22
            java.lang.System.arraycopy(r0, r1, r2, r8, r4)
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r22 = 92
            r21[r14] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r14 = r14 + 1
            char[] r22 = replaceChars
            char r22 = r22[r7]
            r21[r14] = r22
            goto L_0x0574
        L_0x0667:
            r21 = 1
            r0 = r17
            r1 = r21
            if (r0 <= r1) goto L_0x0574
            int r20 = r10 - r19
            r5 = r10
            r11 = r20
        L_0x0674:
            int r21 = r27.length()
            r0 = r21
            if (r11 >= r0) goto L_0x0574
            r0 = r27
            char r7 = r0.charAt(r11)
            byte[] r21 = specicalFlags_doubleQuotes
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r0 = r21
            if (r7 >= r0) goto L_0x0693
            byte[] r21 = specicalFlags_doubleQuotes
            byte r21 = r21[r7]
            if (r21 != 0) goto L_0x06ab
        L_0x0693:
            r21 = 47
            r0 = r21
            if (r7 != r0) goto L_0x072c
            r0 = r26
            int r0 = r0.features
            r21 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r22 = com.alibaba.fastjson.serializer.SerializerFeature.WriteSlashAsSpecial
            r0 = r22
            int r0 = r0.mask
            r22 = r0
            r21 = r21 & r22
            if (r21 == 0) goto L_0x072c
        L_0x06ab:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            r22 = 92
            r21[r5] = r22
            byte[] r21 = specicalFlags_doubleQuotes
            byte r21 = r21[r7]
            r22 = 4
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x071b
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            r22 = 117(0x75, float:1.64E-43)
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 12
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 8
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 4
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            r23 = r7 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            int r9 = r9 + 5
        L_0x0717:
            int r11 = r11 + 1
            goto L_0x0674
        L_0x071b:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = replaceChars
            char r22 = r22[r7]
            r21[r6] = r22
            int r9 = r9 + 1
            goto L_0x0717
        L_0x072c:
            r21 = 8232(0x2028, float:1.1535E-41)
            r0 = r21
            if (r7 != r0) goto L_0x0793
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            r22 = 92
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            r22 = 117(0x75, float:1.64E-43)
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 12
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 8
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            char[] r22 = DIGITS
            int r23 = r7 >>> 4
            r23 = r23 & 15
            char r22 = r22[r23]
            r21[r5] = r22
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r5 = r6 + 1
            char[] r22 = DIGITS
            r23 = r7 & 15
            char r22 = r22[r23]
            r21[r6] = r22
            int r9 = r9 + 5
            goto L_0x0717
        L_0x0793:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            int r6 = r5 + 1
            r21[r5] = r7
            r5 = r6
            goto L_0x0717
        L_0x07a0:
            r0 = r26
            char[] r0 = r0.buf
            r21 = r0
            r0 = r26
            int r0 = r0.count
            r22 = r0
            int r22 = r22 + -1
            r23 = 34
            r21[r22] = r23
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.SerializeWriter.writeStringWithDoubleQuote(java.lang.String, char, boolean):void");
    }

    public void write(boolean value) {
        write(value ? "true" : "false");
    }

    public void writeString(String text) {
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            writeStringWithSingleQuote(text);
        } else {
            writeStringWithDoubleQuote(text, 0, true);
        }
    }

    /* access modifiers changed from: protected */
    public void writeStringWithSingleQuote(String text) {
        if (text == null) {
            int newcount = this.count + 4;
            if (newcount > this.buf.length) {
                expandCapacity(newcount);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = newcount;
            return;
        }
        int len = text.length();
        int newcount2 = this.count + len + 2;
        if (newcount2 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    if (ch <= 13 || ch == '\\' || ch == '\'' || (ch == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                        write(92);
                        write((int) replaceChars[ch]);
                    } else {
                        write((int) ch);
                    }
                }
                write(39);
                return;
            }
            expandCapacity(newcount2);
        }
        int start = this.count + 1;
        int end = start + len;
        this.buf[this.count] = '\'';
        text.getChars(0, len, this.buf, start);
        this.count = newcount2;
        int specialCount = 0;
        int lastSpecialIndex = -1;
        char lastSpecial = 0;
        for (int i2 = start; i2 < end; i2++) {
            char ch2 = this.buf[i2];
            if (ch2 <= 13 || ch2 == '\\' || ch2 == '\'' || (ch2 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                specialCount++;
                lastSpecialIndex = i2;
                lastSpecial = ch2;
            }
        }
        int newcount3 = newcount2 + specialCount;
        if (newcount3 > this.buf.length) {
            expandCapacity(newcount3);
        }
        this.count = newcount3;
        if (specialCount == 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.buf[lastSpecialIndex] = '\\';
            this.buf[lastSpecialIndex + 1] = replaceChars[lastSpecial];
        } else if (specialCount > 1) {
            System.arraycopy(this.buf, lastSpecialIndex + 1, this.buf, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.buf[lastSpecialIndex] = '\\';
            int lastSpecialIndex2 = lastSpecialIndex + 1;
            this.buf[lastSpecialIndex2] = replaceChars[lastSpecial];
            int end2 = end + 1;
            for (int i3 = lastSpecialIndex2 - 2; i3 >= start; i3--) {
                char ch3 = this.buf[i3];
                if (ch3 <= 13 || ch3 == '\\' || ch3 == '\'' || (ch3 == '/' && (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0)) {
                    System.arraycopy(this.buf, i3 + 1, this.buf, i3 + 2, (end2 - i3) - 1);
                    this.buf[i3] = '\\';
                    this.buf[i3 + 1] = replaceChars[ch3];
                    end2++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String key, boolean checkSpecial) {
        if ((this.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
            if ((this.features & SerializerFeature.QuoteFieldNames.mask) != 0) {
                writeStringWithSingleQuote(key);
                write(58);
                return;
            }
            writeKeyWithSingleQuoteIfHasSpecial(key);
        } else if ((this.features & SerializerFeature.QuoteFieldNames.mask) != 0) {
            writeStringWithDoubleQuote(key, ':', checkSpecial);
        } else {
            writeKeyWithDoubleQuoteIfHasSpecial(key);
        }
    }

    private void writeKeyWithDoubleQuoteIfHasSpecial(String text) {
        int len = text.length();
        int newcount = this.count + len + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else if (len == 0) {
                write(34);
                write(34);
                write(58);
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i < len) {
                        char ch = text.charAt(i);
                        if (ch < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch] != 0) {
                            hasSpecial = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (hasSpecial) {
                    write(34);
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_doubleQuotes.length || specicalFlags_doubleQuotes[ch2] == 0) {
                        write((int) ch2);
                    } else {
                        write(92);
                        write((int) replaceChars[ch2]);
                    }
                }
                if (hasSpecial) {
                    write(34);
                }
                write(58);
                return;
            }
        }
        if (len == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i3 = this.count;
            this.count = i3 + 1;
            cArr[i3] = '\"';
            char[] cArr2 = this.buf;
            int i4 = this.count;
            this.count = i4 + 1;
            cArr2[i4] = '\"';
            char[] cArr3 = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.count;
        int end = start + len;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.buf[i6];
            if (ch3 < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch3] != 0) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i6);
                    this.buf[start] = '\"';
                    int i7 = i6 + 1;
                    this.buf[i7] = '\\';
                    i6 = i7 + 1;
                    this.buf[i6] = replaceChars[ch3];
                    end += 2;
                    this.buf[this.count - 2] = '\"';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, end - i6);
                    this.buf[i6] = '\\';
                    i6++;
                    this.buf[i6] = replaceChars[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.buf[this.count - 1] = ':';
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String text) {
        int len = text.length();
        int newcount = this.count + len + 1;
        if (newcount > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(newcount);
            } else if (len == 0) {
                write(39);
                write(39);
                write(58);
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i < len) {
                        char ch = text.charAt(i);
                        if (ch < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch] != 0) {
                            hasSpecial = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (hasSpecial) {
                    write(39);
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_singleQuotes.length || specicalFlags_singleQuotes[ch2] == 0) {
                        write((int) ch2);
                    } else {
                        write(92);
                        write((int) replaceChars[ch2]);
                    }
                }
                if (hasSpecial) {
                    write(39);
                }
                write(58);
                return;
            }
        }
        if (len == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i3 = this.count;
            this.count = i3 + 1;
            cArr[i3] = '\'';
            char[] cArr2 = this.buf;
            int i4 = this.count;
            this.count = i4 + 1;
            cArr2[i4] = '\'';
            char[] cArr3 = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.count;
        int end = start + len;
        text.getChars(0, len, this.buf, start);
        this.count = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.buf[i6];
            if (ch3 < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch3] != 0) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.buf, 0, this.buf, 1, i6);
                    this.buf[start] = '\'';
                    int i7 = i6 + 1;
                    this.buf[i7] = '\\';
                    i6 = i7 + 1;
                    this.buf[i6] = replaceChars[ch3];
                    end += 2;
                    this.buf[this.count - 2] = '\'';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.buf.length) {
                        expandCapacity(newcount);
                    }
                    this.count = newcount;
                    System.arraycopy(this.buf, i6 + 1, this.buf, i6 + 2, end - i6);
                    this.buf[i6] = '\\';
                    i6++;
                    this.buf[i6] = replaceChars[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.buf[newcount - 1] = ':';
    }

    public void flush() {
        if (this.writer != null) {
            try {
                this.writer.write(this.buf, 0, this.count);
                this.writer.flush();
                this.count = 0;
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
    }

    protected static void getChars(long i, int index, char[] buf2) {
        int charPos = index;
        char sign = 0;
        if (i < 0) {
            sign = '-';
            i = -i;
        }
        while (i > 2147483647L) {
            long q = i / 100;
            int r = (int) (i - (((q << 6) + (q << 5)) + (q << 2)));
            i = q;
            int charPos2 = charPos - 1;
            buf2[charPos2] = DigitOnes[r];
            charPos = charPos2 - 1;
            buf2[charPos] = DigitTens[r];
        }
        int i2 = (int) i;
        while (i2 >= 65536) {
            int q2 = i2 / 100;
            int r2 = i2 - (((q2 << 6) + (q2 << 5)) + (q2 << 2));
            i2 = q2;
            int charPos3 = charPos - 1;
            buf2[charPos3] = DigitOnes[r2];
            charPos = charPos3 - 1;
            buf2[charPos] = DigitTens[r2];
        }
        do {
            int q22 = (52429 * i2) >>> 19;
            charPos--;
            buf2[charPos] = digits[i2 - ((q22 << 3) + (q22 << 1))];
            i2 = q22;
        } while (i2 != 0);
        if (sign != 0) {
            buf2[charPos - 1] = sign;
        }
    }
}
