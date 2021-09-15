package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public final class JSONLexer {

    /* renamed from: CA */
    public static final char[] f335CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int END = 4;
    public static final char EOI = '\u001a';

    /* renamed from: IA */
    static final int[] f336IA = new int[256];
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;

    /* renamed from: V6 */
    private static boolean f337V6 = false;
    public static final int VALUE = 3;
    protected static final int[] digits = new int[103];
    public static final boolean[] firstIdentifierFlags = new boolean[256];
    public static final boolean[] identifierFlags = new boolean[256];
    private static final ThreadLocal<char[]> sbufLocal = new ThreadLocal<>();

    /* renamed from: bp */
    protected int f338bp;
    protected Calendar calendar;

    /* renamed from: ch */
    protected char f339ch;
    public boolean disableCircularReferenceDetect;
    protected int eofPos;
    public int features;
    protected boolean hasSpecial;
    protected final int len;
    public Locale locale;
    public int matchStat;

    /* renamed from: np */
    protected int f340np;
    protected int pos;
    protected char[] sbuf;

    /* renamed from: sp */
    protected int f341sp;
    protected String stringDefaultValue;
    protected final String text;
    public TimeZone timeZone;
    protected int token;

    static {
        boolean z;
        int version = -1;
        try {
            version = Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt((Object) null);
        } catch (Exception e) {
        }
        if (version >= 23) {
            z = true;
        } else {
            z = false;
        }
        f337V6 = z;
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = (i3 - 65) + 10;
        }
        Arrays.fill(f336IA, -1);
        int iS = f335CA.length;
        for (int i4 = 0; i4 < iS; i4++) {
            f336IA[f335CA[i4]] = i4;
        }
        f336IA[61] = 0;
        for (char c = 0; c < firstIdentifierFlags.length; c = (char) (c + 1)) {
            if (c >= 'A' && c <= 'Z') {
                firstIdentifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                firstIdentifierFlags[c] = true;
            } else if (c == '_') {
                firstIdentifierFlags[c] = true;
            }
        }
        for (char c2 = 0; c2 < identifierFlags.length; c2 = (char) (c2 + 1)) {
            if (c2 >= 'A' && c2 <= 'Z') {
                identifierFlags[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                identifierFlags[c2] = true;
            } else if (c2 == '_') {
                identifierFlags[c2] = true;
            } else if (c2 >= '0' && c2 <= '9') {
                identifierFlags[c2] = true;
            }
        }
    }

    public JSONLexer(String input) {
        this(input, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] input, int inputLength) {
        this(input, inputLength, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONLexer(char[] input, int inputLength, int features2) {
        this(new String(input, 0, inputLength), features2);
    }

    public JSONLexer(String input, int features2) {
        char charAt;
        String str;
        boolean z;
        this.features = JSON.DEFAULT_PARSER_FEATURE;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.calendar = null;
        this.matchStat = 0;
        this.sbuf = sbufLocal.get();
        if (this.sbuf == null) {
            this.sbuf = new char[512];
        }
        this.features = features2;
        this.text = input;
        this.len = this.text.length();
        this.f338bp = -1;
        int index = this.f338bp + 1;
        this.f338bp = index;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        if (this.f339ch == 65279) {
            next();
        }
        if ((Feature.InitStringFieldAsEmpty.mask & features2) != 0) {
            str = "";
        } else {
            str = null;
        }
        this.stringDefaultValue = str;
        if ((Feature.DisableCircularReferenceDetect.mask & features2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.disableCircularReferenceDetect = z;
    }

    public final int token() {
        return this.token;
    }

    public void close() {
        if (this.sbuf.length <= 8196) {
            sbufLocal.set(this.sbuf);
        }
        this.sbuf = null;
    }

    public char next() {
        char charAt;
        int index = this.f338bp + 1;
        this.f338bp = index;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        return charAt;
    }

    public final void config(Feature feature, boolean state) {
        if (state) {
            this.features |= feature.mask;
        } else {
            this.features &= feature.mask ^ -1;
        }
        if (feature == Feature.InitStringFieldAsEmpty) {
            this.stringDefaultValue = state ? "" : null;
        }
        this.disableCircularReferenceDetect = (this.features & Feature.DisableCircularReferenceDetect.mask) != 0;
    }

    public final boolean isEnabled(Feature feature) {
        return (this.features & feature.mask) != 0;
    }

    public final void nextTokenWithChar(char expect) {
        char charAt;
        this.f341sp = 0;
        while (this.f339ch != expect) {
            if (this.f339ch == ' ' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 12 || this.f339ch == 8) {
                next();
            } else {
                throw new JSONException("not match " + expect + " - " + this.f339ch);
            }
        }
        int index = this.f338bp + 1;
        this.f338bp = index;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        nextToken();
    }

    public final boolean isRef() {
        return this.f341sp == 4 && this.text.startsWith("$ref", this.f340np + 1);
    }

    public final String numberString() {
        char chLocal = this.text.charAt((this.f340np + this.f341sp) - 1);
        int sp = this.f341sp;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            sp--;
        }
        return subString(this.f340np, sp);
    }

    /* access modifiers changed from: protected */
    public char charAt(int index) {
        if (index >= this.len) {
            return EOI;
        }
        return this.text.charAt(index);
    }

    public final void nextToken() {
        boolean eof;
        char c = EOI;
        this.f341sp = 0;
        while (true) {
            this.pos = this.f338bp;
            if (this.f339ch == '/') {
                skipComment();
            } else if (this.f339ch == '\"') {
                scanString();
                return;
            } else if ((this.f339ch < '0' || this.f339ch > '9') && this.f339ch != '-') {
                if (this.f339ch == ',') {
                    next();
                    this.token = 16;
                    return;
                }
                switch (this.f339ch) {
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if ((this.features & Feature.AllowSingleQuotes.mask) == 0) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        scanString();
                        return;
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case ':':
                        next();
                        this.token = 17;
                        return;
                    case 'S':
                    case 'T':
                    case 'u':
                        scanIdent();
                        return;
                    case '[':
                        int index = this.f338bp + 1;
                        this.f338bp = index;
                        if (index < this.len) {
                            c = this.text.charAt(index);
                        }
                        this.f339ch = c;
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case 't':
                        scanTrue();
                        return;
                    case '{':
                        int index2 = this.f338bp + 1;
                        this.f338bp = index2;
                        if (index2 < this.len) {
                            c = this.text.charAt(index2);
                        }
                        this.f339ch = c;
                        this.token = 12;
                        return;
                    case '}':
                        int index3 = this.f338bp + 1;
                        this.f338bp = index3;
                        if (index3 < this.len) {
                            c = this.text.charAt(index3);
                        }
                        this.f339ch = c;
                        this.token = 13;
                        return;
                    default:
                        if (this.f338bp == this.len || (this.f339ch == 26 && this.f338bp + 1 == this.len)) {
                            eof = true;
                        } else {
                            eof = false;
                        }
                        if (eof) {
                            if (this.token == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.token = 20;
                            int i = this.eofPos;
                            this.f338bp = i;
                            this.pos = i;
                            return;
                        } else if (this.f339ch <= 31 || this.f339ch == 127) {
                            next();
                            break;
                        } else {
                            this.token = 1;
                            next();
                            return;
                        }
                }
            }
        }
        scanNumber();
    }

    public final void nextToken(int expect) {
        char c = EOI;
        this.f341sp = 0;
        while (true) {
            switch (expect) {
                case 2:
                    if (this.f339ch >= '0' && this.f339ch <= '9') {
                        this.pos = this.f338bp;
                        scanNumber();
                        return;
                    } else if (this.f339ch == '\"') {
                        this.pos = this.f338bp;
                        scanString();
                        return;
                    } else if (this.f339ch == '[') {
                        this.token = 14;
                        next();
                        return;
                    } else if (this.f339ch == '{') {
                        this.token = 12;
                        next();
                        return;
                    }
                    break;
                case 4:
                    if (this.f339ch == '\"') {
                        this.pos = this.f338bp;
                        scanString();
                        return;
                    } else if (this.f339ch >= '0' && this.f339ch <= '9') {
                        this.pos = this.f338bp;
                        scanNumber();
                        return;
                    } else if (this.f339ch == '{') {
                        this.token = 12;
                        int index = this.f338bp + 1;
                        this.f338bp = index;
                        if (index < this.len) {
                            c = this.text.charAt(index);
                        }
                        this.f339ch = c;
                        return;
                    }
                    break;
                case 12:
                    if (this.f339ch == '{') {
                        this.token = 12;
                        int index2 = this.f338bp + 1;
                        this.f338bp = index2;
                        if (index2 < this.len) {
                            c = this.text.charAt(index2);
                        }
                        this.f339ch = c;
                        return;
                    } else if (this.f339ch == '[') {
                        this.token = 14;
                        int index3 = this.f338bp + 1;
                        this.f338bp = index3;
                        if (index3 < this.len) {
                            c = this.text.charAt(index3);
                        }
                        this.f339ch = c;
                        return;
                    }
                    break;
                case 14:
                    if (this.f339ch == '[') {
                        this.token = 14;
                        next();
                        return;
                    } else if (this.f339ch == '{') {
                        this.token = 12;
                        next();
                        return;
                    }
                    break;
                case 15:
                    if (this.f339ch == ']') {
                        this.token = 15;
                        next();
                        return;
                    }
                    break;
                case 16:
                    if (this.f339ch == ',') {
                        this.token = 16;
                        int index4 = this.f338bp + 1;
                        this.f338bp = index4;
                        if (index4 < this.len) {
                            c = this.text.charAt(index4);
                        }
                        this.f339ch = c;
                        return;
                    } else if (this.f339ch == '}') {
                        this.token = 13;
                        int index5 = this.f338bp + 1;
                        this.f338bp = index5;
                        if (index5 < this.len) {
                            c = this.text.charAt(index5);
                        }
                        this.f339ch = c;
                        return;
                    } else if (this.f339ch == ']') {
                        this.token = 15;
                        int index6 = this.f338bp + 1;
                        this.f338bp = index6;
                        if (index6 < this.len) {
                            c = this.text.charAt(index6);
                        }
                        this.f339ch = c;
                        return;
                    } else if (this.f339ch == 26) {
                        this.token = 20;
                        return;
                    }
                    break;
                case 18:
                    nextIdent();
                    return;
                case 20:
                    break;
            }
            if (this.f339ch == 26) {
                this.token = 20;
                return;
            }
            if (this.f339ch == ' ' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 12 || this.f339ch == 8) {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (true) {
            if (!(this.f339ch <= ' ' && (this.f339ch == ' ' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 12 || this.f339ch == 8))) {
                break;
            }
            next();
        }
        if (this.f339ch == '_' || Character.isLetter(this.f339ch)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    public final Number integerValue() throws NumberFormatException {
        long limit;
        int i;
        long result = 0;
        boolean negative = false;
        int i2 = this.f340np;
        int max = this.f340np + this.f341sp;
        char type = ' ';
        switch (charAt(max - 1)) {
            case 'B':
                max--;
                type = 'B';
                break;
            case 'L':
                max--;
                type = 'L';
                break;
            case 'S':
                max--;
                type = 'S';
                break;
        }
        if (charAt(this.f340np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i2 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i2;
        }
        if (i < max) {
            result = (long) (-(charAt(i) - '0'));
            i++;
        }
        while (i < max) {
            int i3 = i + 1;
            int digit = charAt(i) - '0';
            if (result < -922337203685477580L) {
                return new BigInteger(numberString());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                return new BigInteger(numberString());
            }
            result = result2 - ((long) digit);
            i = i3;
        }
        if (!negative) {
            long result3 = -result;
            if (result3 > 2147483647L || type == 'L') {
                int i4 = i;
                return Long.valueOf(result3);
            } else if (type == 'S') {
                int i5 = i;
                return Short.valueOf((short) ((int) result3));
            } else if (type == 'B') {
                int i6 = i;
                return Byte.valueOf((byte) ((int) result3));
            } else {
                int i7 = i;
                return Integer.valueOf((int) result3);
            }
        } else if (i <= this.f340np + 1) {
            throw new NumberFormatException(numberString());
        } else if (result < -2147483648L || type == 'L') {
            int i8 = i;
            return Long.valueOf(result);
        } else if (type == 'S') {
            int i9 = i;
            return Short.valueOf((short) ((int) result));
        } else if (type == 'B') {
            int i10 = i;
            return Byte.valueOf((byte) ((int) result));
        } else {
            int i11 = i;
            return Integer.valueOf((int) result);
        }
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        while (true) {
            if (this.f339ch != ' ' && this.f339ch != 10 && this.f339ch != 13 && this.f339ch != 9 && this.f339ch != 12 && this.f339ch != 8) {
                break;
            }
            next();
        }
        if (this.f339ch == '\"') {
            return scanSymbol(symbolTable, '\"');
        }
        if (this.f339ch == '\'') {
            if ((this.features & Feature.AllowSingleQuotes.mask) != 0) {
                return scanSymbol(symbolTable, '\'');
            }
            throw new JSONException("syntax error");
        } else if (this.f339ch == '}') {
            next();
            this.token = 13;
            return null;
        } else if (this.f339ch == ',') {
            next();
            this.token = 16;
            return null;
        } else if (this.f339ch == 26) {
            this.token = 20;
            return null;
        } else if ((this.features & Feature.AllowUnQuotedFieldNames.mask) != 0) {
            return scanSymbolUnQuoted(symbolTable);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public String scanSymbol(SymbolTable symbolTable, char quoteChar) {
        String strVal;
        char charAt;
        int hash = 0;
        boolean hasSpecial2 = false;
        int startIndex = this.f338bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        int chars_len = endIndex - startIndex;
        char[] chars = sub_chars(this.f338bp + 1, chars_len);
        while (chars_len > 0 && chars[chars_len - 1] == '\\') {
            int slashCount = 1;
            int i = chars_len - 2;
            while (i >= 0 && chars[i] == '\\') {
                slashCount++;
                i--;
            }
            if (slashCount % 2 == 0) {
                break;
            }
            int nextIndex = this.text.indexOf(quoteChar, endIndex + 1);
            int next_chars_len = chars_len + (nextIndex - endIndex);
            if (next_chars_len >= chars.length) {
                int newLen = (chars.length * 3) / 2;
                if (newLen < next_chars_len) {
                    newLen = next_chars_len;
                }
                char[] newChars = new char[newLen];
                System.arraycopy(chars, 0, newChars, 0, chars.length);
                chars = newChars;
            }
            this.text.getChars(endIndex, nextIndex, chars, chars_len);
            chars_len = next_chars_len;
            endIndex = nextIndex;
            hasSpecial2 = true;
        }
        if (!hasSpecial2) {
            for (int i2 = 0; i2 < chars_len; i2++) {
                char ch = chars[i2];
                hash = (hash * 31) + ch;
                if (ch == '\\') {
                    hasSpecial2 = true;
                }
            }
            if (hasSpecial2) {
                strVal = readString(chars, chars_len);
            } else {
                strVal = chars_len < 20 ? symbolTable.addSymbol(chars, 0, chars_len, hash) : new String(chars, 0, chars_len);
            }
        } else {
            strVal = readString(chars, chars_len);
        }
        this.f338bp = endIndex + 1;
        int index = this.f338bp;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        return strVal;
    }

    private static String readString(char[] chars, int chars_len) {
        int len2;
        char[] sbuf2 = new char[chars_len];
        int i = 0;
        int len3 = 0;
        while (i < chars_len) {
            char ch = chars[i];
            if (ch != '\\') {
                len2 = len3 + 1;
                sbuf2[len3] = ch;
            } else {
                i++;
                switch (chars[i]) {
                    case '\"':
                        len2 = len3 + 1;
                        sbuf2[len3] = '\"';
                        break;
                    case '\'':
                        len2 = len3 + 1;
                        sbuf2[len3] = '\'';
                        break;
                    case '/':
                        len2 = len3 + 1;
                        sbuf2[len3] = '/';
                        break;
                    case '0':
                        len2 = len3 + 1;
                        sbuf2[len3] = 0;
                        break;
                    case '1':
                        len2 = len3 + 1;
                        sbuf2[len3] = 1;
                        break;
                    case '2':
                        len2 = len3 + 1;
                        sbuf2[len3] = 2;
                        break;
                    case '3':
                        len2 = len3 + 1;
                        sbuf2[len3] = 3;
                        break;
                    case '4':
                        len2 = len3 + 1;
                        sbuf2[len3] = 4;
                        break;
                    case '5':
                        len2 = len3 + 1;
                        sbuf2[len3] = 5;
                        break;
                    case '6':
                        len2 = len3 + 1;
                        sbuf2[len3] = 6;
                        break;
                    case '7':
                        len2 = len3 + 1;
                        sbuf2[len3] = 7;
                        break;
                    case 'F':
                    case 'f':
                        len2 = len3 + 1;
                        sbuf2[len3] = 12;
                        break;
                    case '\\':
                        len2 = len3 + 1;
                        sbuf2[len3] = '\\';
                        break;
                    case 'b':
                        len2 = len3 + 1;
                        sbuf2[len3] = 8;
                        break;
                    case 'n':
                        len2 = len3 + 1;
                        sbuf2[len3] = 10;
                        break;
                    case 'r':
                        len2 = len3 + 1;
                        sbuf2[len3] = 13;
                        break;
                    case 't':
                        len2 = len3 + 1;
                        sbuf2[len3] = 9;
                        break;
                    case 'u':
                        len2 = len3 + 1;
                        int i2 = i + 1;
                        int i3 = i2 + 1;
                        int i4 = i3 + 1;
                        i = i4 + 1;
                        sbuf2[len3] = (char) Integer.parseInt(new String(new char[]{chars[i2], chars[i3], chars[i4], chars[i]}), 16);
                        break;
                    case 'v':
                        len2 = len3 + 1;
                        sbuf2[len3] = 11;
                        break;
                    case 'x':
                        len2 = len3 + 1;
                        int i5 = i + 1;
                        i = i5 + 1;
                        sbuf2[len3] = (char) ((digits[chars[i5]] * 16) + digits[chars[i]]);
                        break;
                    default:
                        throw new JSONException("unclosed.str.lit");
                }
            }
            i++;
            len3 = len2;
        }
        return new String(sbuf2, 0, len3);
    }

    public String info() {
        String substring;
        StringBuilder append = new StringBuilder().append("pos ").append(this.f338bp).append(", json : ");
        if (this.text.length() < 65536) {
            substring = this.text;
        } else {
            substring = this.text.substring(0, 65536);
        }
        return append.append(substring).toString();
    }

    /* access modifiers changed from: protected */
    public void skipComment() {
        next();
        if (this.f339ch == '/') {
            do {
                next();
            } while (this.f339ch != 10);
            next();
        } else if (this.f339ch == '*') {
            next();
            while (this.f339ch != 26) {
                if (this.f339ch == '*') {
                    next();
                    if (this.f339ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        char first = this.f339ch;
        if (!(this.f339ch >= firstIdentifierFlags.length || firstIdentifierFlags[first])) {
            throw new JSONException("illegal identifier : " + this.f339ch + ", " + info());
        }
        int hash = first;
        this.f340np = this.f338bp;
        this.f341sp = 1;
        while (true) {
            char ch = next();
            if (ch < identifierFlags.length && !identifierFlags[ch]) {
                break;
            }
            hash = (hash * 31) + ch;
            this.f341sp++;
        }
        this.f339ch = charAt(this.f338bp);
        this.token = 18;
        if (this.f341sp != 4 || !this.text.startsWith("null", this.f340np)) {
            return symbolTable.addSymbol(this.text, this.f340np, this.f341sp, hash);
        }
        return null;
    }

    public final void scanString() {
        char charAt;
        char quoteChar = this.f339ch;
        boolean hasSpecial2 = false;
        int startIndex = this.f338bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        int chars_len = endIndex - startIndex;
        char[] chars = sub_chars(this.f338bp + 1, chars_len);
        while (chars_len > 0 && chars[chars_len - 1] == '\\') {
            int slashCount = 1;
            int i = chars_len - 2;
            while (i >= 0 && chars[i] == '\\') {
                slashCount++;
                i--;
            }
            if (slashCount % 2 == 0) {
                break;
            }
            int nextIndex = this.text.indexOf(quoteChar, endIndex + 1);
            int next_chars_len = chars_len + (nextIndex - endIndex);
            if (next_chars_len >= chars.length) {
                int newLen = (chars.length * 3) / 2;
                if (newLen < next_chars_len) {
                    newLen = next_chars_len;
                }
                char[] newChars = new char[newLen];
                System.arraycopy(chars, 0, newChars, 0, chars.length);
                chars = newChars;
            }
            this.text.getChars(endIndex, nextIndex, chars, chars_len);
            chars_len = next_chars_len;
            endIndex = nextIndex;
            hasSpecial2 = true;
        }
        if (!hasSpecial2) {
            for (int i2 = 0; i2 < chars_len; i2++) {
                if (chars[i2] == '\\') {
                    hasSpecial2 = true;
                }
            }
        }
        this.sbuf = chars;
        this.f341sp = chars_len;
        this.f340np = this.f338bp;
        this.hasSpecial = hasSpecial2;
        this.f338bp = endIndex + 1;
        int index = this.f338bp;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        this.token = 4;
    }

    public String scanStringValue(char quoteChar) {
        String strVal;
        char charAt;
        int startIndex = this.f338bp + 1;
        int endIndex = this.text.indexOf(quoteChar, startIndex);
        if (endIndex == -1) {
            throw new JSONException("unclosed str, " + info());
        }
        if (f337V6) {
            strVal = this.text.substring(startIndex, endIndex);
        } else {
            int chars_len = endIndex - startIndex;
            strVal = new String(sub_chars(this.f338bp + 1, chars_len), 0, chars_len);
        }
        if (strVal.indexOf(92) != -1) {
            while (true) {
                int slashCount = 0;
                int i = endIndex - 1;
                while (i >= 0 && this.text.charAt(i) == '\\') {
                    slashCount++;
                    i--;
                }
                if (slashCount % 2 == 0) {
                    break;
                }
                endIndex = this.text.indexOf(quoteChar, endIndex + 1);
            }
            int chars_len2 = endIndex - startIndex;
            strVal = readString(sub_chars(this.f338bp + 1, chars_len2), chars_len2);
        }
        this.f338bp = endIndex + 1;
        int index = this.f338bp;
        if (index >= this.len) {
            charAt = EOI;
        } else {
            charAt = this.text.charAt(index);
        }
        this.f339ch = charAt;
        return strVal;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public final int intValue() {
        int limit;
        int i;
        int i2;
        int result = 0;
        boolean negative = false;
        int i3 = this.f340np;
        int max = this.f340np + this.f341sp;
        if (charAt(this.f340np) == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            i = i3 + 1;
        } else {
            limit = -2147483647;
            i = i3;
        }
        if (i < max) {
            result = -(charAt(i) - '0');
            i++;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            char ch = charAt(i);
            if (ch == 'L' || ch == 'S' || ch == 'B') {
                break;
            }
            int digit = ch - '0';
            if (result < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int result2 = result * 10;
            if (result2 < limit + digit) {
                throw new NumberFormatException(numberString());
            }
            result = result2 - digit;
            i = i2;
        }
        if (!negative) {
            return -result;
        }
        if (i2 > this.f340np + 1) {
            return result;
        }
        throw new NumberFormatException(numberString());
    }

    public byte[] bytesValue() {
        return decodeFast(this.text, this.f340np + 1, this.f341sp);
    }

    private void scanTrue() {
        if (this.text.startsWith("true", this.f338bp)) {
            this.f338bp += 4;
            this.f339ch = charAt(this.f338bp);
            if (this.f339ch == ' ' || this.f339ch == ',' || this.f339ch == '}' || this.f339ch == ']' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 26 || this.f339ch == 12 || this.f339ch == 8 || this.f339ch == ':') {
                this.token = 6;
                return;
            }
        }
        throw new JSONException("scan true error");
    }

    private void scanNullOrNew() {
        int token2 = 0;
        if (this.text.startsWith("null", this.f338bp)) {
            this.f338bp += 4;
            token2 = 8;
        } else if (this.text.startsWith("new", this.f338bp)) {
            this.f338bp += 3;
            token2 = 9;
        }
        if (token2 != 0) {
            this.f339ch = charAt(this.f338bp);
            if (this.f339ch == ' ' || this.f339ch == ',' || this.f339ch == '}' || this.f339ch == ']' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 26 || this.f339ch == 12 || this.f339ch == 8) {
                this.token = token2;
                return;
            }
        }
        throw new JSONException("scan null/new error");
    }

    private void scanFalse() {
        if (this.text.startsWith("false", this.f338bp)) {
            this.f338bp += 5;
            this.f339ch = charAt(this.f338bp);
            if (this.f339ch == ' ' || this.f339ch == ',' || this.f339ch == '}' || this.f339ch == ']' || this.f339ch == 10 || this.f339ch == 13 || this.f339ch == 9 || this.f339ch == 26 || this.f339ch == 12 || this.f339ch == 8 || this.f339ch == ':') {
                this.token = 7;
                return;
            }
        }
        throw new JSONException("scan false error");
    }

    private void scanIdent() {
        this.f340np = this.f338bp - 1;
        this.hasSpecial = false;
        do {
            this.f341sp++;
            next();
        } while (Character.isLetterOrDigit(this.f339ch));
        String ident = stringVal();
        if (ident.equals("null")) {
            this.token = 8;
        } else if (ident.equals("true")) {
            this.token = 6;
        } else if (ident.equals("false")) {
            this.token = 7;
        } else if (ident.equals("new")) {
            this.token = 9;
        } else if (ident.equals("undefined")) {
            this.token = 23;
        } else if (ident.equals("Set")) {
            this.token = 21;
        } else if (ident.equals("TreeSet")) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return readString(this.sbuf, this.f341sp);
        }
        return subString(this.f340np + 1, this.f341sp);
    }

    private final String subString(int offset, int count) {
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return new String(this.sbuf, 0, count);
        }
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return new String(chars);
    }

    /* access modifiers changed from: package-private */
    public final char[] sub_chars(int offset, int count) {
        if (count < this.sbuf.length) {
            this.text.getChars(offset, offset + count, this.sbuf, 0);
            return this.sbuf;
        }
        char[] chars = new char[count];
        this.sbuf = chars;
        this.text.getChars(offset, offset + count, chars, 0);
        return chars;
    }

    public final boolean isBlankInput() {
        boolean whitespace;
        int i = 0;
        while (true) {
            char ch = charAt(i);
            if (ch == 26) {
                return true;
            }
            if (ch > ' ' || !(ch == ' ' || ch == 10 || ch == 13 || ch == 9 || ch == 12 || ch == 8)) {
                whitespace = false;
            } else {
                whitespace = true;
            }
            if (!whitespace) {
                return false;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public final void skipWhitespace() {
        while (this.f339ch <= '/') {
            if (this.f339ch == ' ' || this.f339ch == 13 || this.f339ch == 10 || this.f339ch == 9 || this.f339ch == 12 || this.f339ch == 8) {
                next();
            } else if (this.f339ch == '/') {
                skipComment();
            } else {
                return;
            }
        }
    }

    public final void scanNumber() {
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        char charAt6;
        char charAt7;
        this.f340np = this.f338bp;
        if (this.f339ch == '-') {
            this.f341sp++;
            int index = this.f338bp + 1;
            this.f338bp = index;
            if (index >= this.len) {
                charAt7 = 26;
            } else {
                charAt7 = this.text.charAt(index);
            }
            this.f339ch = charAt7;
        }
        while (this.f339ch >= '0' && this.f339ch <= '9') {
            this.f341sp++;
            int index2 = this.f338bp + 1;
            this.f338bp = index2;
            if (index2 >= this.len) {
                charAt6 = 26;
            } else {
                charAt6 = this.text.charAt(index2);
            }
            this.f339ch = charAt6;
        }
        boolean isDouble = false;
        if (this.f339ch == '.') {
            this.f341sp++;
            int index3 = this.f338bp + 1;
            this.f338bp = index3;
            if (index3 >= this.len) {
                charAt4 = 26;
            } else {
                charAt4 = this.text.charAt(index3);
            }
            this.f339ch = charAt4;
            isDouble = true;
            while (this.f339ch >= '0' && this.f339ch <= '9') {
                this.f341sp++;
                int index4 = this.f338bp + 1;
                this.f338bp = index4;
                if (index4 >= this.len) {
                    charAt5 = 26;
                } else {
                    charAt5 = this.text.charAt(index4);
                }
                this.f339ch = charAt5;
            }
        }
        if (this.f339ch == 'L') {
            this.f341sp++;
            next();
        } else if (this.f339ch == 'S') {
            this.f341sp++;
            next();
        } else if (this.f339ch == 'B') {
            this.f341sp++;
            next();
        } else if (this.f339ch == 'F') {
            this.f341sp++;
            next();
            isDouble = true;
        } else if (this.f339ch == 'D') {
            this.f341sp++;
            next();
            isDouble = true;
        } else if (this.f339ch == 'e' || this.f339ch == 'E') {
            this.f341sp++;
            int index5 = this.f338bp + 1;
            this.f338bp = index5;
            if (index5 >= this.len) {
                charAt = 26;
            } else {
                charAt = this.text.charAt(index5);
            }
            this.f339ch = charAt;
            if (this.f339ch == '+' || this.f339ch == '-') {
                this.f341sp++;
                int index6 = this.f338bp + 1;
                this.f338bp = index6;
                if (index6 >= this.len) {
                    charAt3 = 26;
                } else {
                    charAt3 = this.text.charAt(index6);
                }
                this.f339ch = charAt3;
            }
            while (this.f339ch >= '0' && this.f339ch <= '9') {
                this.f341sp++;
                int index7 = this.f338bp + 1;
                this.f338bp = index7;
                if (index7 >= this.len) {
                    charAt2 = 26;
                } else {
                    charAt2 = this.text.charAt(index7);
                }
                this.f339ch = charAt2;
            }
            if (this.f339ch == 'D' || this.f339ch == 'F') {
                this.f341sp++;
                next();
            }
            isDouble = true;
        }
        if (isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    public boolean scanBoolean() {
        int offset;
        boolean value;
        if (this.text.startsWith("false", this.f338bp)) {
            offset = 5;
            value = false;
        } else if (this.text.startsWith("true", this.f338bp)) {
            offset = 4;
            value = true;
        } else if (this.f339ch == '1') {
            offset = 1;
            value = true;
        } else if (this.f339ch == '0') {
            offset = 1;
            value = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        this.f338bp += offset;
        this.f339ch = charAt(this.f338bp);
        return value;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: java.math.BigInteger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: java.math.BigInteger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v10, resolved type: java.math.BigInteger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v16, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v17, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v18, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v19, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v20, resolved type: java.math.BigInteger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v21, resolved type: java.math.BigDecimal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v22, resolved type: java.math.BigDecimal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v206, resolved type: java.math.BigDecimal} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v23, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v24, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v207, resolved type: java.math.BigInteger} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v25, resolved type: java.lang.Double} */
    /* JADX WARNING: type inference failed for: r16v2, types: [java.lang.Float] */
    /* JADX WARNING: type inference failed for: r16v3, types: [java.lang.Byte] */
    /* JADX WARNING: type inference failed for: r16v4, types: [java.lang.Short] */
    /* JADX WARNING: type inference failed for: r16v8 */
    /* JADX WARNING: type inference failed for: r16v11 */
    /* JADX WARNING: type inference failed for: r16v15, types: [java.lang.Long] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Number scanNumberValue() {
        /*
            r26 = this;
            r0 = r26
            int r0 = r0.f338bp
            r20 = r0
            r19 = 0
            r16 = 0
            r23 = 0
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 45
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x00cf
            r11 = 1
            r12 = -9223372036854775808
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x00c1
            r23 = 26
        L_0x0049:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
        L_0x004f:
            r14 = 0
        L_0x0051:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 48
            r0 = r23
            r1 = r24
            if (r0 < r1) goto L_0x00e4
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 57
            r0 = r23
            r1 = r24
            if (r0 > r1) goto L_0x00e4
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            int r5 = r23 + -48
            r24 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r23 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r23 >= 0) goto L_0x0080
            r19 = 1
        L_0x0080:
            r24 = 10
            long r14 = r14 * r24
            long r0 = (long) r5
            r24 = r0
            long r24 = r24 + r12
            int r23 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r23 >= 0) goto L_0x008f
            r19 = 1
        L_0x008f:
            long r0 = (long) r5
            r24 = r0
            long r14 = r14 - r24
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x00d7
            r23 = 26
        L_0x00ba:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
            goto L_0x0051
        L_0x00c1:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x0049
        L_0x00cf:
            r11 = 0
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x004f
        L_0x00d7:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x00ba
        L_0x00e4:
            if (r11 != 0) goto L_0x00e7
            long r14 = -r14
        L_0x00e7:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 76
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0190
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r26.next()
            java.lang.Long r16 = java.lang.Long.valueOf(r14)
        L_0x010a:
            r9 = 0
            r7 = 0
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 46
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0256
            r9 = 1
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x023a
            r23 = 26
        L_0x0141:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
        L_0x0147:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 48
            r0 = r23
            r1 = r24
            if (r0 < r1) goto L_0x0256
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 57
            r0 = r23
            r1 = r24
            if (r0 > r1) goto L_0x0256
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x0248
            r23 = 26
        L_0x0189:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
            goto L_0x0147
        L_0x0190:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 83
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x01bd
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r26.next()
            int r0 = (int) r14
            r23 = r0
            r0 = r23
            short r0 = (short) r0
            r23 = r0
            java.lang.Short r16 = java.lang.Short.valueOf(r23)
            goto L_0x010a
        L_0x01bd:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 66
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x01ea
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r26.next()
            int r0 = (int) r14
            r23 = r0
            r0 = r23
            byte r0 = (byte) r0
            r23 = r0
            java.lang.Byte r16 = java.lang.Byte.valueOf(r23)
            goto L_0x010a
        L_0x01ea:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 70
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0212
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r26.next()
            float r0 = (float) r14
            r23 = r0
            java.lang.Float r16 = java.lang.Float.valueOf(r23)
            goto L_0x010a
        L_0x0212:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 68
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x010a
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r26.next()
            double r0 = (double) r14
            r24 = r0
            java.lang.Double r16 = java.lang.Double.valueOf(r24)
            goto L_0x010a
        L_0x023a:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x0141
        L_0x0248:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x0189
        L_0x0256:
            r22 = 0
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 101(0x65, float:1.42E-43)
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x0274
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 69
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x038d
        L_0x0274:
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x0331
            r23 = 26
        L_0x029a:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 43
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x02bc
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 45
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x02e8
        L_0x02bc:
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x033f
            r23 = 26
        L_0x02e2:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
        L_0x02e8:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 48
            r0 = r23
            r1 = r24
            if (r0 < r1) goto L_0x0359
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 57
            r0 = r23
            r1 = r24
            if (r0 > r1) goto L_0x0359
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r8 = r23 + 1
            r0 = r26
            r0.f338bp = r8
            r0 = r26
            int r0 = r0.len
            r23 = r0
            r0 = r23
            if (r8 < r0) goto L_0x034c
            r23 = 26
        L_0x032a:
            r0 = r23
            r1 = r26
            r1.f339ch = r0
            goto L_0x02e8
        L_0x0331:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x029a
        L_0x033f:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x02e2
        L_0x034c:
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r23
            char r23 = r0.charAt(r8)
            goto L_0x032a
        L_0x0359:
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 68
            r0 = r23
            r1 = r24
            if (r0 == r1) goto L_0x0375
            r0 = r26
            char r0 = r0.f339ch
            r23 = r0
            r24 = 70
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x038c
        L_0x0375:
            r0 = r26
            int r0 = r0.f340np
            r23 = r0
            int r23 = r23 + 1
            r0 = r23
            r1 = r26
            r1.f340np = r0
            r0 = r26
            char r0 = r0.f339ch
            r22 = r0
            r26.next()
        L_0x038c:
            r7 = 1
        L_0x038d:
            if (r9 != 0) goto L_0x03e7
            if (r7 != 0) goto L_0x03e7
            if (r19 == 0) goto L_0x03c6
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r10 = r23 - r20
            char[] r4 = new char[r10]
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            r0 = r26
            int r0 = r0.f338bp
            r24 = r0
            r25 = 0
            r0 = r23
            r1 = r20
            r2 = r24
            r3 = r25
            r0.getChars(r1, r2, r4, r3)
            java.lang.String r21 = new java.lang.String
            r0 = r21
            r0.<init>(r4)
            java.math.BigInteger r16 = new java.math.BigInteger
            r0 = r16
            r1 = r21
            r0.<init>(r1)
        L_0x03c6:
            if (r16 != 0) goto L_0x03dd
            r24 = -2147483648(0xffffffff80000000, double:NaN)
            int r23 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r23 <= 0) goto L_0x03e2
            r24 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r23 = (r14 > r24 ? 1 : (r14 == r24 ? 0 : -1))
            if (r23 >= 0) goto L_0x03e2
            int r0 = (int) r14
            r23 = r0
            java.lang.Integer r16 = java.lang.Integer.valueOf(r23)
        L_0x03dd:
            r17 = r16
            r18 = r16
        L_0x03e1:
            return r18
        L_0x03e2:
            java.lang.Long r16 = java.lang.Long.valueOf(r14)
            goto L_0x03dd
        L_0x03e7:
            r0 = r26
            int r0 = r0.f338bp
            r23 = r0
            int r10 = r23 - r20
            if (r22 == 0) goto L_0x03f3
            int r10 = r10 + -1
        L_0x03f3:
            char[] r4 = new char[r10]
            r0 = r26
            java.lang.String r0 = r0.text
            r23 = r0
            int r24 = r20 + r10
            r25 = 0
            r0 = r23
            r1 = r20
            r2 = r24
            r3 = r25
            r0.getChars(r1, r2, r4, r3)
            if (r7 != 0) goto L_0x042a
            r0 = r26
            int r0 = r0.features
            r23 = r0
            com.alibaba.fastjson.parser.Feature r24 = com.alibaba.fastjson.parser.Feature.UseBigDecimal
            r0 = r24
            int r0 = r0.mask
            r24 = r0
            r23 = r23 & r24
            if (r23 == 0) goto L_0x042a
            java.math.BigDecimal r16 = new java.math.BigDecimal
            r0 = r16
            r0.<init>(r4)
        L_0x0425:
            r17 = r16
            r18 = r16
            goto L_0x03e1
        L_0x042a:
            java.lang.String r21 = new java.lang.String
            r0 = r21
            r0.<init>(r4)
            r23 = 70
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x043e
            java.lang.Float r16 = java.lang.Float.valueOf(r21)     // Catch:{ NumberFormatException -> 0x0447 }
            goto L_0x0425
        L_0x043e:
            double r24 = java.lang.Double.parseDouble(r21)     // Catch:{ NumberFormatException -> 0x0447 }
            java.lang.Double r16 = java.lang.Double.valueOf(r24)     // Catch:{ NumberFormatException -> 0x0447 }
            goto L_0x0425
        L_0x0447:
            r6 = move-exception
            com.alibaba.fastjson.JSONException r23 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r24 = new java.lang.StringBuilder
            r24.<init>()
            java.lang.String r25 = r6.getMessage()
            java.lang.StringBuilder r24 = r24.append(r25)
            java.lang.String r25 = ", "
            java.lang.StringBuilder r24 = r24.append(r25)
            java.lang.String r25 = r26.info()
            java.lang.StringBuilder r24 = r24.append(r25)
            java.lang.String r24 = r24.toString()
            r0 = r23
            r1 = r24
            r0.<init>(r1, r6)
            throw r23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanNumberValue():java.lang.Number");
    }

    public final long scanLongValue() {
        boolean negative;
        long limit;
        char charAt;
        this.f340np = 0;
        if (this.f339ch == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            this.f340np++;
            int index = this.f338bp + 1;
            this.f338bp = index;
            if (index >= this.len) {
                throw new JSONException("syntax error, " + info());
            }
            this.f339ch = this.text.charAt(index);
        } else {
            negative = false;
            limit = -9223372036854775807L;
        }
        long longValue = 0;
        while (this.f339ch >= '0' && this.f339ch <= '9') {
            int digit = this.f339ch - '0';
            if (longValue < -922337203685477580L) {
                throw new JSONException("error long value, " + longValue + ", " + info());
            }
            long longValue2 = longValue * 10;
            if (longValue2 < ((long) digit) + limit) {
                throw new JSONException("error long value, " + longValue2 + ", " + info());
            }
            longValue = longValue2 - ((long) digit);
            this.f340np++;
            int index2 = this.f338bp + 1;
            this.f338bp = index2;
            if (index2 >= this.len) {
                charAt = EOI;
            } else {
                charAt = this.text.charAt(index2);
            }
            this.f339ch = charAt;
        }
        if (!negative) {
            return -longValue;
        }
        return longValue;
    }

    public final long longValue() throws NumberFormatException {
        long limit;
        int i;
        int i2;
        char chLocal;
        long result = 0;
        boolean negative = false;
        int i3 = this.f340np;
        int max = this.f340np + this.f341sp;
        if (charAt(this.f340np) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i3 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i3;
        }
        if (i < max) {
            result = (long) (-(charAt(i) - '0'));
            i++;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            int index = i;
            if (index >= this.len) {
                chLocal = EOI;
            } else {
                chLocal = this.text.charAt(index);
            }
            if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B') {
                break;
            }
            int digit = chLocal - '0';
            if (result < -922337203685477580L) {
                throw new NumberFormatException(numberString());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                throw new NumberFormatException(numberString());
            }
            result = result2 - ((long) digit);
            i = i2;
        }
        if (!negative) {
            return -result;
        }
        if (i2 > this.f340np + 1) {
            return result;
        }
        throw new NumberFormatException(numberString());
    }

    public final Number decimalValue(boolean decimal) {
        char chLocal = charAt((this.f340np + this.f341sp) - 1);
        if (chLocal == 'F') {
            try {
                return Float.valueOf(Float.parseFloat(numberString()));
            } catch (NumberFormatException ex) {
                throw new JSONException(ex.getMessage() + ", " + info());
            }
        } else if (chLocal == 'D') {
            return Double.valueOf(Double.parseDouble(numberString()));
        } else {
            if (decimal) {
                return decimalValue();
            }
            return Double.valueOf(Double.parseDouble(numberString()));
        }
    }

    public final BigDecimal decimalValue() {
        return new BigDecimal(numberString());
    }

    public boolean matchField(char[] fieldName) {
        char c = EOI;
        if (!charArrayCompare(fieldName)) {
            return false;
        }
        this.f338bp += fieldName.length;
        if (this.f338bp >= this.len) {
            throw new JSONException("unclosed str, " + info());
        }
        this.f339ch = this.text.charAt(this.f338bp);
        if (this.f339ch == '{') {
            int index = this.f338bp + 1;
            this.f338bp = index;
            if (index < this.len) {
                c = this.text.charAt(index);
            }
            this.f339ch = c;
            this.token = 12;
        } else if (this.f339ch == '[') {
            int index2 = this.f338bp + 1;
            this.f338bp = index2;
            if (index2 < this.len) {
                c = this.text.charAt(index2);
            }
            this.f339ch = c;
            this.token = 14;
        } else {
            nextToken();
        }
        return true;
    }

    private boolean charArrayCompare(char[] chars) {
        int destLen = chars.length;
        if (this.f338bp + destLen > this.len) {
            return false;
        }
        for (int i = 0; i < destLen; i++) {
            if (chars[i] != this.text.charAt(this.f338bp + i)) {
                return false;
            }
        }
        return true;
    }

    public int scanFieldInt(char[] fieldName) {
        int offset;
        int offset2;
        char chLocal;
        char c = EOI;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0;
        }
        int offset3 = fieldName.length;
        int offset4 = offset3 + 1;
        char chLocal2 = charAt(this.f338bp + offset3);
        boolean quote = false;
        if (chLocal2 == '\"') {
            quote = true;
            offset = offset4 + 1;
            int index = this.f338bp + offset4;
            if (index >= this.len) {
                chLocal2 = 26;
            } else {
                chLocal2 = this.text.charAt(index);
            }
        } else {
            offset = offset4;
        }
        if (chLocal2 < '0' || chLocal2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int value = chLocal2 - '0';
        while (true) {
            offset2 = offset + 1;
            chLocal = charAt(this.f338bp + offset);
            if (chLocal >= '0' && chLocal <= '9') {
                value = (value * 10) + (chLocal - '0');
                offset = offset2;
            }
        }
        if (chLocal == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (chLocal == '\"') {
            if (!quote) {
                this.matchStat = -1;
                return 0;
            }
            int offset5 = offset2 + 1;
            int index2 = this.f338bp + offset2;
            if (index2 >= this.len) {
                chLocal = 26;
            } else {
                chLocal = this.text.charAt(index2);
            }
            offset2 = offset5;
        }
        if (value < 0) {
            this.matchStat = -1;
            return 0;
        } else if (chLocal == ',') {
            this.f338bp += offset2 - 1;
            int index3 = this.f338bp + 1;
            this.f338bp = index3;
            if (index3 < this.len) {
                c = this.text.charAt(index3);
            }
            this.f339ch = c;
            this.matchStat = 3;
            this.token = 16;
            return value;
        } else if (chLocal == '}') {
            int offset6 = offset2 + 1;
            char chLocal3 = charAt(this.f338bp + offset2);
            if (chLocal3 == ',') {
                this.token = 16;
                this.f338bp += offset6 - 1;
                int index4 = this.f338bp + 1;
                this.f338bp = index4;
                if (index4 < this.len) {
                    c = this.text.charAt(index4);
                }
                this.f339ch = c;
            } else if (chLocal3 == ']') {
                this.token = 15;
                this.f338bp += offset6 - 1;
                int index5 = this.f338bp + 1;
                this.f338bp = index5;
                if (index5 < this.len) {
                    c = this.text.charAt(index5);
                }
                this.f339ch = c;
            } else if (chLocal3 == '}') {
                this.token = 13;
                this.f338bp += offset6 - 1;
                int index6 = this.f338bp + 1;
                this.f338bp = index6;
                if (index6 < this.len) {
                    c = this.text.charAt(index6);
                }
                this.f339ch = c;
            } else if (chLocal3 == 26) {
                this.token = 20;
                this.f338bp += offset6 - 1;
                this.f339ch = EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return value;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public long scanFieldLong(char[] fieldName) {
        char chLocal;
        int offset;
        int offset2;
        char chLocal2;
        char chLocal3;
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0;
        }
        int offset3 = fieldName.length;
        int offset4 = offset3 + 1;
        int index = this.f338bp + offset3;
        if (index >= this.len) {
            chLocal = EOI;
        } else {
            chLocal = this.text.charAt(index);
        }
        boolean quote = false;
        if (chLocal == '\"') {
            quote = true;
            offset = offset4 + 1;
            int index2 = this.f338bp + offset4;
            if (index2 >= this.len) {
                chLocal = EOI;
            } else {
                chLocal = this.text.charAt(index2);
            }
        } else {
            offset = offset4;
        }
        if (chLocal < '0' || chLocal > '9') {
            this.matchStat = -1;
            return 0;
        }
        long value = (long) (chLocal - '0');
        while (true) {
            offset2 = offset + 1;
            int index3 = this.f338bp + offset;
            if (index3 >= this.len) {
                chLocal2 = EOI;
            } else {
                chLocal2 = this.text.charAt(index3);
            }
            if (chLocal3 >= '0' && chLocal3 <= '9') {
                value = (10 * value) + ((long) (chLocal3 - '0'));
                offset = offset2;
            }
        }
        if (chLocal3 == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (chLocal3 == '\"') {
            if (!quote) {
                this.matchStat = -1;
                return 0;
            }
            int offset5 = offset2 + 1;
            int index4 = this.f338bp + offset2;
            if (index4 >= this.len) {
                chLocal3 = EOI;
            } else {
                chLocal3 = this.text.charAt(index4);
            }
            offset2 = offset5;
        }
        if (value < 0) {
            this.matchStat = -1;
            return 0;
        } else if (chLocal3 == ',') {
            this.f338bp += offset2 - 1;
            int index5 = this.f338bp + 1;
            this.f338bp = index5;
            if (index5 >= this.len) {
                charAt4 = EOI;
            } else {
                charAt4 = this.text.charAt(index5);
            }
            this.f339ch = charAt4;
            this.matchStat = 3;
            this.token = 16;
            return value;
        } else if (chLocal3 == '}') {
            int offset6 = offset2 + 1;
            char chLocal4 = charAt(this.f338bp + offset2);
            if (chLocal4 == ',') {
                this.token = 16;
                this.f338bp += offset6 - 1;
                int index6 = this.f338bp + 1;
                this.f338bp = index6;
                if (index6 >= this.len) {
                    charAt3 = EOI;
                } else {
                    charAt3 = this.text.charAt(index6);
                }
                this.f339ch = charAt3;
            } else if (chLocal4 == ']') {
                this.token = 15;
                this.f338bp += offset6 - 1;
                int index7 = this.f338bp + 1;
                this.f338bp = index7;
                if (index7 >= this.len) {
                    charAt2 = EOI;
                } else {
                    charAt2 = this.text.charAt(index7);
                }
                this.f339ch = charAt2;
            } else if (chLocal4 == '}') {
                this.token = 13;
                this.f338bp += offset6 - 1;
                int index8 = this.f338bp + 1;
                this.f338bp = index8;
                if (index8 >= this.len) {
                    charAt = EOI;
                } else {
                    charAt = this.text.charAt(index8);
                }
                this.f339ch = charAt;
            } else if (chLocal4 == 26) {
                this.token = 20;
                this.f338bp += offset6 - 1;
                this.f339ch = EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return value;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public String scanFieldString(char[] fieldName) {
        String strVal;
        char chLocal;
        char charAt;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return this.stringDefaultValue;
        }
        int offset = fieldName.length;
        int offset2 = offset + 1;
        int index = this.f338bp + offset;
        if (index >= this.len) {
            throw new JSONException("unclosed str, " + info());
        } else if (this.text.charAt(index) != '\"') {
            this.matchStat = -1;
            return this.stringDefaultValue;
        } else {
            boolean hasSpecial2 = false;
            int startIndex = this.f338bp + offset2;
            int endIndex = this.text.indexOf(34, startIndex);
            if (endIndex == -1) {
                throw new JSONException("unclosed str, " + info());
            }
            if (f337V6) {
                strVal = this.text.substring(startIndex, endIndex);
            } else {
                int chars_len = endIndex - startIndex;
                strVal = new String(sub_chars(this.f338bp + offset2, chars_len), 0, chars_len);
            }
            if (strVal.indexOf(92) != -1) {
                while (true) {
                    int slashCount = 0;
                    int i = endIndex - 1;
                    while (i >= 0 && this.text.charAt(i) == '\\') {
                        hasSpecial2 = true;
                        slashCount++;
                        i--;
                    }
                    if (slashCount % 2 == 0) {
                        break;
                    }
                    endIndex = this.text.indexOf(34, endIndex + 1);
                }
                int chars_len2 = endIndex - startIndex;
                char[] chars = sub_chars(this.f338bp + offset2, chars_len2);
                if (hasSpecial2) {
                    strVal = readString(chars, chars_len2);
                } else {
                    strVal = new String(chars, 0, chars_len2);
                    if (strVal.indexOf(92) != -1) {
                        strVal = readString(chars, chars_len2);
                    }
                }
            }
            int endIndex2 = endIndex + 1;
            int index2 = endIndex2;
            if (index2 >= this.len) {
                chLocal = EOI;
            } else {
                chLocal = this.text.charAt(index2);
            }
            if (chLocal == ',') {
                this.f338bp = endIndex2;
                int index3 = this.f338bp + 1;
                this.f338bp = index3;
                if (index3 >= this.len) {
                    charAt = EOI;
                } else {
                    charAt = this.text.charAt(index3);
                }
                this.f339ch = charAt;
                this.matchStat = 3;
                this.token = 16;
                return strVal;
            } else if (chLocal == '}') {
                int endIndex3 = endIndex2 + 1;
                char chLocal2 = charAt(endIndex3);
                if (chLocal2 == ',') {
                    this.token = 16;
                    this.f338bp = endIndex3;
                    next();
                } else if (chLocal2 == ']') {
                    this.token = 15;
                    this.f338bp = endIndex3;
                    next();
                } else if (chLocal2 == '}') {
                    this.token = 13;
                    this.f338bp = endIndex3;
                    next();
                } else if (chLocal2 == 26) {
                    this.token = 20;
                    this.f338bp = endIndex3;
                    this.f339ch = EOI;
                } else {
                    this.matchStat = -1;
                    return this.stringDefaultValue;
                }
                this.matchStat = 4;
                return strVal;
            } else {
                this.matchStat = -1;
                return this.stringDefaultValue;
            }
        }
    }

    public boolean scanFieldBoolean(char[] fieldName) {
        int offset;
        boolean value;
        char c = EOI;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return false;
        }
        int offset2 = fieldName.length;
        if (this.text.startsWith("false", this.f338bp + offset2)) {
            offset = offset2 + 5;
            value = false;
        } else if (this.text.startsWith("true", this.f338bp + offset2)) {
            offset = offset2 + 4;
            value = true;
        } else if (this.text.startsWith("\"false\"", this.f338bp + offset2)) {
            offset = offset2 + 7;
            value = false;
        } else if (this.text.startsWith("\"true\"", this.f338bp + offset2)) {
            offset = offset2 + 6;
            value = true;
        } else {
            this.matchStat = -1;
            return false;
        }
        int offset3 = offset + 1;
        char chLocal = charAt(this.f338bp + offset);
        if (chLocal == ',') {
            this.f338bp += offset3 - 1;
            int index = this.f338bp + 1;
            this.f338bp = index;
            if (index < this.len) {
                c = this.text.charAt(index);
            }
            this.f339ch = c;
            this.matchStat = 3;
            this.token = 16;
            return value;
        } else if (chLocal == '}') {
            int offset4 = offset3 + 1;
            char chLocal2 = charAt(this.f338bp + offset3);
            if (chLocal2 == ',') {
                this.token = 16;
                this.f338bp += offset4 - 1;
                int index2 = this.f338bp + 1;
                this.f338bp = index2;
                if (index2 < this.len) {
                    c = this.text.charAt(index2);
                }
                this.f339ch = c;
            } else if (chLocal2 == ']') {
                this.token = 15;
                this.f338bp += offset4 - 1;
                int index3 = this.f338bp + 1;
                this.f338bp = index3;
                if (index3 < this.len) {
                    c = this.text.charAt(index3);
                }
                this.f339ch = c;
            } else if (chLocal2 == '}') {
                this.token = 13;
                this.f338bp += offset4 - 1;
                int index4 = this.f338bp + 1;
                this.f338bp = index4;
                if (index4 < this.len) {
                    c = this.text.charAt(index4);
                }
                this.f339ch = c;
            } else if (chLocal2 == 26) {
                this.token = 20;
                this.f338bp += offset4 - 1;
                this.f339ch = EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return value;
        } else {
            this.matchStat = -1;
            return false;
        }
    }

    public final float scanFieldFloat(char[] fieldName) {
        char chLocal;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0.0f;
        }
        int offset = fieldName.length;
        int offset2 = offset + 1;
        char chLocal2 = charAt(this.f338bp + offset);
        if (chLocal2 < '0' || chLocal2 > '9') {
            this.matchStat = -1;
            return 0.0f;
        }
        while (true) {
            int offset3 = offset2;
            offset2 = offset3 + 1;
            chLocal = charAt(this.f338bp + offset3);
            if (chLocal < '0' || chLocal > '9') {
            }
        }
        if (chLocal == '.') {
            int offset4 = offset2 + 1;
            char chLocal3 = charAt(this.f338bp + offset2);
            if (chLocal3 >= '0' && chLocal3 <= '9') {
                while (true) {
                    offset2 = offset4 + 1;
                    chLocal = charAt(this.f338bp + offset4);
                    if (chLocal < '0' || chLocal > '9') {
                        break;
                    }
                    offset4 = offset2;
                }
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
        }
        int offset5 = offset2;
        int start = this.f338bp + fieldName.length;
        float parseFloat = Float.parseFloat(subString(start, ((this.f338bp + offset5) - start) - 1));
        if (chLocal == ',') {
            this.f338bp += offset5 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return parseFloat;
        } else if (chLocal == '}') {
            int offset6 = offset5 + 1;
            char chLocal4 = charAt(this.f338bp + offset5);
            if (chLocal4 == ',') {
                this.token = 16;
                this.f338bp += offset6 - 1;
                next();
            } else if (chLocal4 == ']') {
                this.token = 15;
                this.f338bp += offset6 - 1;
                next();
            } else if (chLocal4 == '}') {
                this.token = 13;
                this.f338bp += offset6 - 1;
                next();
            } else if (chLocal4 == 26) {
                this.f338bp += offset6 - 1;
                this.token = 20;
                this.f339ch = EOI;
            } else {
                this.matchStat = -1;
                return 0.0f;
            }
            this.matchStat = 4;
            return parseFloat;
        } else {
            this.matchStat = -1;
            return 0.0f;
        }
    }

    public final double scanFieldDouble(char[] fieldName) {
        char chLocal;
        int offset;
        char chLocal2;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return 0.0d;
        }
        int offset2 = fieldName.length;
        int offset3 = offset2 + 1;
        char chLocal3 = charAt(this.f338bp + offset2);
        if (chLocal3 < '0' || chLocal3 > '9') {
            this.matchStat = -1;
            return 0.0d;
        }
        while (true) {
            int offset4 = offset3;
            offset3 = offset4 + 1;
            chLocal = charAt(this.f338bp + offset4);
            if (chLocal < '0' || chLocal > '9') {
            }
        }
        if (chLocal == '.') {
            int offset5 = offset3 + 1;
            char chLocal4 = charAt(this.f338bp + offset3);
            if (chLocal4 >= '0' && chLocal4 <= '9') {
                while (true) {
                    offset3 = offset5 + 1;
                    chLocal = charAt(this.f338bp + offset5);
                    if (chLocal < '0' || chLocal > '9') {
                        break;
                    }
                    offset5 = offset3;
                }
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
        }
        if (chLocal2 == 'e' || chLocal2 == 'E') {
            int offset6 = offset + 1;
            chLocal2 = charAt(this.f338bp + offset);
            if (chLocal2 == '+' || chLocal2 == '-') {
                offset = offset6 + 1;
                chLocal2 = charAt(this.f338bp + offset6);
            } else {
                offset = offset6;
            }
            while (chLocal2 >= '0' && chLocal2 <= '9') {
                chLocal2 = charAt(this.f338bp + offset);
                offset++;
            }
        }
        int offset7 = offset;
        int start = this.f338bp + fieldName.length;
        double parseDouble = Double.parseDouble(subString(start, ((this.f338bp + offset7) - start) - 1));
        if (chLocal2 == ',') {
            this.f338bp += offset7 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return parseDouble;
        } else if (chLocal2 == '}') {
            int offset8 = offset7 + 1;
            char chLocal5 = charAt(this.f338bp + offset7);
            if (chLocal5 == ',') {
                this.token = 16;
                this.f338bp += offset8 - 1;
                next();
            } else if (chLocal5 == ']') {
                this.token = 15;
                this.f338bp += offset8 - 1;
                next();
            } else if (chLocal5 == '}') {
                this.token = 13;
                this.f338bp += offset8 - 1;
                next();
            } else if (chLocal5 == 26) {
                this.token = 20;
                this.f338bp += offset8 - 1;
                this.f339ch = EOI;
            } else {
                this.matchStat = -1;
                return 0.0d;
            }
            this.matchStat = 4;
            return parseDouble;
        } else {
            this.matchStat = -1;
            return 0.0d;
        }
    }

    public String scanFieldSymbol(char[] fieldName, SymbolTable symbolTable) {
        char charAt;
        this.matchStat = 0;
        if (!charArrayCompare(fieldName)) {
            this.matchStat = -2;
            return null;
        }
        int offset = fieldName.length;
        int offset2 = offset + 1;
        if (charAt(this.f338bp + offset) != '\"') {
            this.matchStat = -1;
            return null;
        }
        int hash = 0;
        while (true) {
            int offset3 = offset2;
            offset2 = offset3 + 1;
            char chLocal = charAt(this.f338bp + offset3);
            if (chLocal == '\"') {
                int start = this.f338bp + fieldName.length + 1;
                String addSymbol = symbolTable.addSymbol(this.text, start, ((this.f338bp + offset2) - start) - 1, hash);
                int offset4 = offset2 + 1;
                char chLocal2 = charAt(this.f338bp + offset2);
                if (chLocal2 == ',') {
                    this.f338bp += offset4 - 1;
                    int index = this.f338bp + 1;
                    this.f338bp = index;
                    if (index >= this.len) {
                        charAt = EOI;
                    } else {
                        charAt = this.text.charAt(index);
                    }
                    this.f339ch = charAt;
                    this.matchStat = 3;
                    return addSymbol;
                } else if (chLocal2 == '}') {
                    int offset5 = offset4 + 1;
                    char chLocal3 = charAt(this.f338bp + offset4);
                    if (chLocal3 == ',') {
                        this.token = 16;
                        this.f338bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == ']') {
                        this.token = 15;
                        this.f338bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == '}') {
                        this.token = 13;
                        this.f338bp += offset5 - 1;
                        next();
                    } else if (chLocal3 == 26) {
                        this.token = 20;
                        this.f338bp += offset5 - 1;
                        this.f339ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return addSymbol;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            } else {
                hash = (hash * 31) + chLocal;
                if (chLocal == '\\') {
                    this.matchStat = -1;
                    return null;
                }
            }
        }
    }

    public boolean scanISO8601DateIfMatch(boolean strict) {
        int millis;
        int seconds;
        int minute;
        int hour;
        int rest = this.text.length() - this.f338bp;
        if (!strict && rest > 13 && this.text.startsWith("/Date(", this.f338bp) && charAt((this.f338bp + rest) - 1) == '/' && charAt((this.f338bp + rest) - 2) == ')') {
            int plusIndex = -1;
            for (int i = 6; i < rest; i++) {
                char c = charAt(this.f338bp + i);
                if (c != '+') {
                    if (c < '0' || c > '9') {
                        break;
                    }
                } else {
                    plusIndex = i;
                }
            }
            if (plusIndex == -1) {
                return false;
            }
            int offset = this.f338bp + 6;
            long millis2 = Long.parseLong(subString(offset, plusIndex - offset));
            this.calendar = Calendar.getInstance(this.timeZone, this.locale);
            this.calendar.setTimeInMillis(millis2);
            this.token = 5;
            return true;
        } else if (rest == 8 || rest == 14 || rest == 17) {
            if (strict) {
                return false;
            }
            char y0 = charAt(this.f338bp);
            char y1 = charAt(this.f338bp + 1);
            char y2 = charAt(this.f338bp + 2);
            char y3 = charAt(this.f338bp + 3);
            char M0 = charAt(this.f338bp + 4);
            char M1 = charAt(this.f338bp + 5);
            char d0 = charAt(this.f338bp + 6);
            char d1 = charAt(this.f338bp + 7);
            if (!checkDate(y0, y1, y2, y3, M0, M1, d0, d1)) {
                return false;
            }
            setCalendar(y0, y1, y2, y3, M0, M1, d0, d1);
            if (rest != 8) {
                char h0 = charAt(this.f338bp + 8);
                char h1 = charAt(this.f338bp + 9);
                char m0 = charAt(this.f338bp + 10);
                char m1 = charAt(this.f338bp + 11);
                char s0 = charAt(this.f338bp + 12);
                char s1 = charAt(this.f338bp + 13);
                if (!checkTime(h0, h1, m0, m1, s0, s1)) {
                    return false;
                }
                if (rest == 17) {
                    char S0 = charAt(this.f338bp + 14);
                    char S1 = charAt(this.f338bp + 15);
                    char S2 = charAt(this.f338bp + 16);
                    if (S0 < '0' || S0 > '9' || S1 < '0' || S1 > '9' || S2 < '0' || S2 > '9') {
                        return false;
                    }
                    millis = ((S0 - '0') * 100) + ((S1 - '0') * 10) + (S2 - '0');
                } else {
                    millis = 0;
                }
                hour = ((h0 - '0') * 10) + (h1 - '0');
                minute = ((m0 - '0') * 10) + (m1 - '0');
                seconds = ((s0 - '0') * 10) + (s1 - '0');
            } else {
                millis = 0;
                seconds = 0;
                minute = 0;
                hour = 0;
            }
            this.calendar.set(11, hour);
            this.calendar.set(12, minute);
            this.calendar.set(13, seconds);
            this.calendar.set(14, millis);
            this.token = 5;
            return true;
        } else if (rest < 10 || charAt(this.f338bp + 4) != '-' || charAt(this.f338bp + 7) != '-') {
            return false;
        } else {
            char y02 = charAt(this.f338bp);
            char y12 = charAt(this.f338bp + 1);
            char y22 = charAt(this.f338bp + 2);
            char y32 = charAt(this.f338bp + 3);
            char M02 = charAt(this.f338bp + 5);
            char M12 = charAt(this.f338bp + 6);
            char d02 = charAt(this.f338bp + 8);
            char d12 = charAt(this.f338bp + 9);
            if (!checkDate(y02, y12, y22, y32, M02, M12, d02, d12)) {
                return false;
            }
            setCalendar(y02, y12, y22, y32, M02, M12, d02, d12);
            char t = charAt(this.f338bp + 10);
            if (t == 'T' || (t == ' ' && !strict)) {
                if (rest < 19 || charAt(this.f338bp + 13) != ':' || charAt(this.f338bp + 16) != ':') {
                    return false;
                }
                char h02 = charAt(this.f338bp + 11);
                char h12 = charAt(this.f338bp + 12);
                char m02 = charAt(this.f338bp + 14);
                char m12 = charAt(this.f338bp + 15);
                char s02 = charAt(this.f338bp + 17);
                char s12 = charAt(this.f338bp + 18);
                if (!checkTime(h02, h12, m02, m12, s02, s12)) {
                    return false;
                }
                this.calendar.set(11, ((h02 - '0') * 10) + (h12 - '0'));
                this.calendar.set(12, ((m02 - '0') * 10) + (m12 - '0'));
                this.calendar.set(13, ((s02 - '0') * 10) + (s12 - '0'));
                char dot = charAt(this.f338bp + 19);
                if (dot != '.') {
                    this.calendar.set(14, 0);
                    int i2 = this.f338bp + 19;
                    this.f338bp = i2;
                    this.f339ch = charAt(i2);
                    this.token = 5;
                    if (dot == 'Z' && this.calendar.getTimeZone().getRawOffset() != 0) {
                        String[] timeZoneIDs = TimeZone.getAvailableIDs(0);
                        if (timeZoneIDs.length > 0) {
                            this.calendar.setTimeZone(TimeZone.getTimeZone(timeZoneIDs[0]));
                        }
                    }
                    return true;
                } else if (rest < 23) {
                    return false;
                } else {
                    char S02 = charAt(this.f338bp + 20);
                    if (S02 < '0' || S02 > '9') {
                        return false;
                    }
                    int millis3 = digits[S02];
                    int millisLen = 1;
                    char S12 = charAt(this.f338bp + 21);
                    if (S12 >= '0' && S12 <= '9') {
                        millis3 = (millis3 * 10) + digits[S12];
                        millisLen = 2;
                    }
                    if (millisLen == 2) {
                        char S22 = charAt(this.f338bp + 22);
                        if (S22 >= '0' && S22 <= '9') {
                            millis3 = (millis3 * 10) + digits[S22];
                            millisLen = 3;
                        }
                    }
                    this.calendar.set(14, millis3);
                    int timzeZoneLength = 0;
                    char timeZoneFlag = charAt(this.f338bp + 20 + millisLen);
                    if (timeZoneFlag == '+' || timeZoneFlag == '-') {
                        char t0 = charAt(this.f338bp + 20 + millisLen + 1);
                        if (t0 < '0' || t0 > '1') {
                            return false;
                        }
                        char t1 = charAt(this.f338bp + 20 + millisLen + 2);
                        if (t1 < '0' || t1 > '9') {
                            return false;
                        }
                        char t2 = charAt(this.f338bp + 20 + millisLen + 3);
                        if (t2 == ':') {
                            if (charAt(this.f338bp + 20 + millisLen + 4) != '0' || charAt(this.f338bp + 20 + millisLen + 5) != '0') {
                                return false;
                            }
                            timzeZoneLength = 6;
                        } else if (t2 != '0') {
                            timzeZoneLength = 3;
                        } else if (charAt(this.f338bp + 20 + millisLen + 4) != '0') {
                            return false;
                        } else {
                            timzeZoneLength = 5;
                        }
                        int timeZoneOffset = ((digits[t0] * 10) + digits[t1]) * 3600 * 1000;
                        if (timeZoneFlag == '-') {
                            timeZoneOffset = -timeZoneOffset;
                        }
                        if (this.calendar.getTimeZone().getRawOffset() != timeZoneOffset) {
                            String[] timeZoneIDs2 = TimeZone.getAvailableIDs(timeZoneOffset);
                            if (timeZoneIDs2.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(timeZoneIDs2[0]));
                            }
                        }
                    } else if (timeZoneFlag == 'Z') {
                        timzeZoneLength = 1;
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] timeZoneIDs3 = TimeZone.getAvailableIDs(0);
                            if (timeZoneIDs3.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(timeZoneIDs3[0]));
                            }
                        }
                    }
                    char end = charAt(this.f338bp + millisLen + 20 + timzeZoneLength);
                    if (end != 26 && end != '\"') {
                        return false;
                    }
                    int i3 = this.f338bp + millisLen + 20 + timzeZoneLength;
                    this.f338bp = i3;
                    this.f339ch = charAt(i3);
                    this.token = 5;
                    return true;
                }
            } else if (t != '\"' && t != 26) {
                return false;
            } else {
                this.calendar.set(11, 0);
                this.calendar.set(12, 0);
                this.calendar.set(13, 0);
                this.calendar.set(14, 0);
                int i4 = this.f338bp + 10;
                this.f338bp = i4;
                this.f339ch = charAt(i4);
                this.token = 5;
                return true;
            }
        }
    }

    static boolean checkTime(char h0, char h1, char m0, char m1, char s0, char s1) {
        if (h0 == '0') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 == '1') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 != '2' || h1 < '0' || h1 > '4') {
            return false;
        }
        if (m0 < '0' || m0 > '5') {
            if (!(m0 == '6' && m1 == '0')) {
                return false;
            }
        } else if (m1 < '0' || m1 > '9') {
            return false;
        }
        if (s0 < '0' || s0 > '5') {
            if (!(s0 == '6' && s1 == '0')) {
                return false;
            }
        } else if (s1 < '0' || s1 > '9') {
            return false;
        }
        return true;
    }

    private void setCalendar(char y0, char y1, char y2, char y3, char M0, char M1, char d0, char d1) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((y0 - '0') * 1000) + ((y1 - '0') * 100) + ((y2 - '0') * 10) + (y3 - '0'));
        this.calendar.set(2, (((M0 - '0') * 10) + (M1 - '0')) - 1);
        this.calendar.set(5, ((d0 - '0') * 10) + (d1 - '0'));
    }

    static boolean checkDate(char y0, char y1, char y2, char y3, char M0, char M1, int d0, int d1) {
        if ((y0 != '1' && y0 != '2') || y1 < '0' || y1 > '9' || y2 < '0' || y2 > '9' || y3 < '0' || y3 > '9') {
            return false;
        }
        if (M0 == '0') {
            if (M1 < '1' || M1 > '9') {
                return false;
            }
        } else if (M0 != '1') {
            return false;
        } else {
            if (!(M1 == '0' || M1 == '1' || M1 == '2')) {
                return false;
            }
        }
        if (d0 == 48) {
            if (d1 < 49 || d1 > 57) {
                return false;
            }
        } else if (d0 == 49 || d0 == 50) {
            if (d1 < 48 || d1 > 57) {
                return false;
            }
        } else if (d0 != 51) {
            return false;
        } else {
            if (d1 == 48 || d1 == 49) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static final byte[] decodeFast(char[] chars, int offset, int charsLen) {
        int sepCnt;
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int sIx2 = offset;
        int eIx = (offset + charsLen) - 1;
        while (sIx2 < eIx && f336IA[chars[sIx2]] < 0) {
            sIx2++;
        }
        while (eIx > 0 && f336IA[chars[eIx]] < 0) {
            eIx--;
        }
        int pad = chars[eIx] == '=' ? chars[eIx + -1] == '=' ? 2 : 1 : 0;
        int cCnt = (eIx - sIx2) + 1;
        if (charsLen > 76) {
            sepCnt = (chars[76] == 13 ? cCnt / 78 : 0) << 1;
        } else {
            sepCnt = 0;
        }
        int len2 = (((cCnt - sepCnt) * 6) >> 3) - pad;
        byte[] bytes = new byte[len2];
        int cc = 0;
        int eLen = (len2 / 3) * 3;
        int d = 0;
        int sIx3 = sIx2;
        while (d < eLen) {
            int sIx4 = sIx3 + 1;
            int sIx5 = sIx4 + 1;
            int sIx6 = sIx5 + 1;
            int sIx7 = sIx6 + 1;
            int i = (f336IA[chars[sIx3]] << 18) | (f336IA[chars[sIx4]] << 12) | (f336IA[chars[sIx5]] << 6) | f336IA[chars[sIx6]];
            int d2 = d + 1;
            bytes[d] = (byte) (i >> 16);
            int d3 = d2 + 1;
            bytes[d2] = (byte) (i >> 8);
            int d4 = d3 + 1;
            bytes[d3] = (byte) i;
            if (sepCnt <= 0 || (cc = cc + 1) != 19) {
                sIx = sIx7;
            } else {
                sIx = sIx7 + 2;
                cc = 0;
            }
            d = d4;
            sIx3 = sIx;
        }
        if (d < len2) {
            int i2 = 0;
            int j = 0;
            while (sIx3 <= eIx - pad) {
                i2 |= f336IA[chars[sIx3]] << (18 - (j * 6));
                j++;
                sIx3++;
            }
            int r = 16;
            while (d < len2) {
                bytes[d] = (byte) (i2 >> r);
                r -= 8;
                d++;
            }
        }
        int i3 = d;
        int i4 = sIx3;
        return bytes;
    }

    public static final byte[] decodeFast(String chars, int offset, int charsLen) {
        int sepCnt;
        int sIx;
        if (charsLen == 0) {
            return new byte[0];
        }
        int sIx2 = offset;
        int eIx = (offset + charsLen) - 1;
        while (sIx2 < eIx && f336IA[chars.charAt(sIx2)] < 0) {
            sIx2++;
        }
        while (eIx > 0 && f336IA[chars.charAt(eIx)] < 0) {
            eIx--;
        }
        int pad = chars.charAt(eIx) == '=' ? chars.charAt(eIx + -1) == '=' ? 2 : 1 : 0;
        int cCnt = (eIx - sIx2) + 1;
        if (charsLen > 76) {
            sepCnt = (chars.charAt(76) == 13 ? cCnt / 78 : 0) << 1;
        } else {
            sepCnt = 0;
        }
        int len2 = (((cCnt - sepCnt) * 6) >> 3) - pad;
        byte[] bytes = new byte[len2];
        int cc = 0;
        int eLen = (len2 / 3) * 3;
        int d = 0;
        int sIx3 = sIx2;
        while (d < eLen) {
            int sIx4 = sIx3 + 1;
            int sIx5 = sIx4 + 1;
            int sIx6 = sIx5 + 1;
            int sIx7 = sIx6 + 1;
            int i = (f336IA[chars.charAt(sIx3)] << 18) | (f336IA[chars.charAt(sIx4)] << 12) | (f336IA[chars.charAt(sIx5)] << 6) | f336IA[chars.charAt(sIx6)];
            int d2 = d + 1;
            bytes[d] = (byte) (i >> 16);
            int d3 = d2 + 1;
            bytes[d2] = (byte) (i >> 8);
            int d4 = d3 + 1;
            bytes[d3] = (byte) i;
            if (sepCnt <= 0 || (cc = cc + 1) != 19) {
                sIx = sIx7;
            } else {
                sIx = sIx7 + 2;
                cc = 0;
            }
            d = d4;
            sIx3 = sIx;
        }
        if (d < len2) {
            int i2 = 0;
            int j = 0;
            while (sIx3 <= eIx - pad) {
                i2 |= f336IA[chars.charAt(sIx3)] << (18 - (j * 6));
                j++;
                sIx3++;
            }
            int r = 16;
            while (d < len2) {
                bytes[d] = (byte) (i2 >> r);
                r -= 8;
                d++;
            }
        }
        int i3 = d;
        int i4 = sIx3;
        return bytes;
    }

    public static final byte[] decodeFast(String s) {
        int sepCnt;
        int sIx;
        int sLen = s.length();
        if (sLen == 0) {
            return new byte[0];
        }
        int sIx2 = 0;
        int eIx = sLen - 1;
        while (sIx2 < eIx && f336IA[s.charAt(sIx2) & 255] < 0) {
            sIx2++;
        }
        while (eIx > 0 && f336IA[s.charAt(eIx) & 255] < 0) {
            eIx--;
        }
        int pad = s.charAt(eIx) == '=' ? s.charAt(eIx + -1) == '=' ? 2 : 1 : 0;
        int cCnt = (eIx - sIx2) + 1;
        if (sLen > 76) {
            sepCnt = (s.charAt(76) == 13 ? cCnt / 78 : 0) << 1;
        } else {
            sepCnt = 0;
        }
        int len2 = (((cCnt - sepCnt) * 6) >> 3) - pad;
        byte[] dArr = new byte[len2];
        int cc = 0;
        int eLen = (len2 / 3) * 3;
        int d = 0;
        int sIx3 = sIx2;
        while (d < eLen) {
            int sIx4 = sIx3 + 1;
            int sIx5 = sIx4 + 1;
            int sIx6 = sIx5 + 1;
            int sIx7 = sIx6 + 1;
            int i = (f336IA[s.charAt(sIx3)] << 18) | (f336IA[s.charAt(sIx4)] << 12) | (f336IA[s.charAt(sIx5)] << 6) | f336IA[s.charAt(sIx6)];
            int d2 = d + 1;
            dArr[d] = (byte) (i >> 16);
            int d3 = d2 + 1;
            dArr[d2] = (byte) (i >> 8);
            int d4 = d3 + 1;
            dArr[d3] = (byte) i;
            if (sepCnt <= 0 || (cc = cc + 1) != 19) {
                sIx = sIx7;
            } else {
                sIx = sIx7 + 2;
                cc = 0;
            }
            d = d4;
            sIx3 = sIx;
        }
        if (d < len2) {
            int i2 = 0;
            int j = 0;
            while (sIx3 <= eIx - pad) {
                i2 |= f336IA[s.charAt(sIx3)] << (18 - (j * 6));
                j++;
                sIx3++;
            }
            int r = 16;
            while (d < len2) {
                dArr[d] = (byte) (i2 >> r);
                r -= 8;
                d++;
            }
        }
        int i3 = d;
        int i4 = sIx3;
        return dArr;
    }
}
