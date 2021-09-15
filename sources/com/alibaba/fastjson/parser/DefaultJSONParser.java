package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    protected ParseContext contex;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    protected List<ExtraProcessor> extraProcessors;
    protected List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.locale);
            this.dateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat2) {
        this.dateFormatPattern = dateFormat2;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat2) {
        this.dateFormat = dateFormat2;
    }

    public DefaultJSONParser(String input) {
        this(input, ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String input, ParserConfig config2) {
        this(new JSONLexer(input, JSON.DEFAULT_PARSER_FEATURE), config2);
    }

    public DefaultJSONParser(String input, ParserConfig config2, int features) {
        this(new JSONLexer(input, features), config2);
    }

    public DefaultJSONParser(char[] input, int length, ParserConfig config2, int features) {
        this(new JSONLexer(input, length, features), config2);
    }

    public DefaultJSONParser(JSONLexer lexer2) {
        this(lexer2, ParserConfig.global);
    }

    public DefaultJSONParser(JSONLexer lexer2, ParserConfig config2) {
        char c = JSONLexer.EOI;
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = lexer2;
        this.config = config2;
        this.symbolTable = config2.symbolTable;
        if (lexer2.f339ch == '{') {
            int index = lexer2.f338bp + 1;
            lexer2.f338bp = index;
            lexer2.f339ch = index < lexer2.len ? lexer2.text.charAt(index) : c;
            lexer2.token = 12;
        } else if (lexer2.f339ch == '[') {
            int index2 = lexer2.f338bp + 1;
            lexer2.f338bp = index2;
            lexer2.f339ch = index2 < lexer2.len ? lexer2.text.charAt(index2) : c;
            lexer2.token = 14;
        } else {
            lexer2.nextToken();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:149:0x041c, code lost:
        r25.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0431, code lost:
        if (r25.token != 13) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0433, code lost:
        r25.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x043c, code lost:
        r19 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        r10 = r46.config.getDeserializer(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x044e, code lost:
        if ((r10 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L_0x0458;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0450, code lost:
        r19 = ((com.alibaba.fastjson.parser.JavaBeanDeserializer) r10).createInstance(r46, (java.lang.reflect.Type) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0458, code lost:
        if (r19 != null) goto L_0x0465;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x045e, code lost:
        if (r6 != java.lang.Cloneable.class) goto L_0x046f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0460, code lost:
        r19 = new java.util.HashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0465, code lost:
        if (r11 != false) goto L_0x046b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0467, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0479, code lost:
        if ("java.util.Collections$EmptyMap".equals(r40) == false) goto L_0x0480;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x047b, code lost:
        r19 = java.util.Collections.emptyMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0480, code lost:
        r19 = r6.newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0492, code lost:
        r46.resolveStatus = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x04a0, code lost:
        if (r46.contex == null) goto L_0x04ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x04a8, code lost:
        if ((r48 instanceof java.lang.Integer) != false) goto L_0x04ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x04aa, code lost:
        popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x04b1, code lost:
        if (r47.size() <= 0) goto L_0x04d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x04b3, code lost:
        r27 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r47, r6, r46.config);
        parseObject((java.lang.Object) r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x04c8, code lost:
        if (r11 != false) goto L_0x04ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x04ca, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:?, code lost:
        r47 = r46.config.getDeserializer(r6).deserialze(r46, r6, r48);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x04e6, code lost:
        if (r11 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x04e8, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0502, code lost:
        r25.nextToken(4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0517, code lost:
        if (r25.token != 4) goto L_0x0647;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0519, code lost:
        r31 = r25.stringVal();
        r25.nextToken(13);
        r32 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x0532, code lost:
        if ("@".equals(r31) == false) goto L_0x059c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x053a, code lost:
        if (r46.contex == null) goto L_0x0632;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x053c, code lost:
        r37 = r46.contex;
        r38 = r37.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x054e, code lost:
        if ((r38 instanceof java.lang.Object[]) != false) goto L_0x0558;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0556, code lost:
        if ((r38 instanceof java.util.Collection) == false) goto L_0x0587;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0558, code lost:
        r32 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x055a, code lost:
        r47 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0568, code lost:
        if (r25.token == 13) goto L_0x0636;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0586, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, " + r25.info());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x058d, code lost:
        if (r37.parent == null) goto L_0x055a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x058f, code lost:
        r32 = r37.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x05a6, code lost:
        if ("..".equals(r31) == false) goto L_0x05d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x05ac, code lost:
        if (r7.object == null) goto L_0x05b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x05ae, code lost:
        r47 = r7.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x05b5, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r7, r31));
        r46.resolveStatus = 1;
        r47 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x05da, code lost:
        if ("$".equals(r31) == false) goto L_0x061a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05dc, code lost:
        r34 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x05e4, code lost:
        if (r34.parent == null) goto L_0x05ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x05e6, code lost:
        r34 = r34.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x05f3, code lost:
        if (r34.object == null) goto L_0x05ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x05f5, code lost:
        r32 = r34.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x05fb, code lost:
        r47 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x05ff, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r34, r31));
        r46.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x061a, code lost:
        addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r7, r31));
        r46.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0632, code lost:
        r47 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0636, code lost:
        r25.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x063f, code lost:
        if (r11 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0641, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0669, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r25.token));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0a25, code lost:
        if (r5 != '}') goto L_0x0b3e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0a27, code lost:
        r16 = r25.f338bp + 1;
        r25.f338bp = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x0a3f, code lost:
        if (r16 < r25.len) goto L_0x0a9a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x0a41, code lost:
        r5 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0a43, code lost:
        r25.f339ch = r5;
        r25.f341sp = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0a53, code lost:
        if (r5 != ',') goto L_0x0ab8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0a55, code lost:
        r16 = r25.f338bp + 1;
        r25.f338bp = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x0a6d, code lost:
        if (r16 < r25.len) goto L_0x0aa9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x0a6f, code lost:
        r42 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0a71, code lost:
        r25.f339ch = r42;
        r25.token = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x0a7f, code lost:
        if (r11 != false) goto L_0x0a92;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x0a81, code lost:
        setContext(r46.contex, r47, r48);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x0a92, code lost:
        if (r11 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x0a94, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:?, code lost:
        r5 = r25.text.charAt(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x0aa9, code lost:
        r42 = r25.text.charAt(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x0abc, code lost:
        if (r5 != '}') goto L_0x0af8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0abe, code lost:
        r16 = r25.f338bp + 1;
        r25.f338bp = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x0ad6, code lost:
        if (r16 < r25.len) goto L_0x0ae9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0ad8, code lost:
        r42 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x0ada, code lost:
        r25.f339ch = r42;
        r25.token = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0ae9, code lost:
        r42 = r25.text.charAt(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x0afc, code lost:
        if (r5 != ']') goto L_0x0b39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0afe, code lost:
        r16 = r25.f338bp + 1;
        r25.f338bp = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0b16, code lost:
        if (r16 < r25.len) goto L_0x0b2a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x0b18, code lost:
        r42 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x0b1a, code lost:
        r25.f339ch = r42;
        r25.token = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x0b2a, code lost:
        r42 = r25.text.charAt(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:0x0b39, code lost:
        r25.nextToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x0b5a, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, " + r25.info());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:445:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:446:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:447:?, code lost:
        return r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:?, code lost:
        return r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:449:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:451:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:459:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:460:?, code lost:
        return r47;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r16 = r25.f338bp + 1;
        r25.f338bp = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0149, code lost:
        if (r16 < r25.len) goto L_0x016c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x014b, code lost:
        r42 = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x014d, code lost:
        r25.f339ch = r42;
        r25.f341sp = 0;
        r25.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0164, code lost:
        if (r11 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0166, code lost:
        r46.contex = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r42 = r25.text.charAt(r16);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object parseObject(java.util.Map r47, java.lang.Object r48) {
        /*
            r46 = this;
            r0 = r46
            com.alibaba.fastjson.parser.JSONLexer r0 = r0.lexer
            r25 = r0
            r0 = r25
            int r0 = r0.token
            r39 = r0
            r42 = 8
            r0 = r39
            r1 = r42
            if (r0 != r1) goto L_0x001a
            r25.nextToken()
            r47 = 0
        L_0x0019:
            return r47
        L_0x001a:
            r42 = 12
            r0 = r39
            r1 = r42
            if (r0 == r1) goto L_0x0055
            r42 = 16
            r0 = r39
            r1 = r42
            if (r0 == r1) goto L_0x0055
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r43 = new java.lang.StringBuilder
            r43.<init>()
            java.lang.String r44 = "syntax error, expect {, actual "
            java.lang.StringBuilder r43 = r43.append(r44)
            java.lang.String r44 = r25.tokenName()
            java.lang.StringBuilder r43 = r43.append(r44)
            java.lang.String r44 = ", "
            java.lang.StringBuilder r43 = r43.append(r44)
            java.lang.String r44 = r25.info()
            java.lang.StringBuilder r43 = r43.append(r44)
            java.lang.String r43 = r43.toString()
            r42.<init>(r43)
            throw r42
        L_0x0055:
            r0 = r47
            boolean r0 = r0 instanceof com.alibaba.fastjson.JSONObject
            r42 = r0
            if (r42 == 0) goto L_0x00be
            r23 = r47
            com.alibaba.fastjson.JSONObject r23 = (com.alibaba.fastjson.JSONObject) r23
            java.util.Map r17 = r23.getInnerMap()
            r20 = 1
        L_0x0067:
            r0 = r25
            int r0 = r0.features
            r42 = r0
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.AllowISO8601DateFormat
            r0 = r43
            int r0 = r0.mask
            r43 = r0
            r42 = r42 & r43
            if (r42 == 0) goto L_0x00c3
            r4 = 1
        L_0x007a:
            r0 = r25
            boolean r11 = r0.disableCircularReferenceDetect
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r7 = r0.contex
            r35 = 0
        L_0x0084:
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 34
            r0 = r42
            if (r5 == r0) goto L_0x009b
            r42 = 125(0x7d, float:1.75E-43)
            r0 = r42
            if (r5 == r0) goto L_0x009b
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
        L_0x009b:
            r42 = 44
            r0 = r42
            if (r5 != r0) goto L_0x00c5
            r0 = r25
            int r0 = r0.features     // Catch:{ all -> 0x0123 }
            r42 = r0
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0123 }
            r0 = r43
            int r0 = r0.mask     // Catch:{ all -> 0x0123 }
            r43 = r0
            r42 = r42 & r43
            if (r42 == 0) goto L_0x00c5
            r25.next()     // Catch:{ all -> 0x0123 }
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            goto L_0x009b
        L_0x00be:
            r20 = 0
            r17 = 0
            goto L_0x0067
        L_0x00c3:
            r4 = 0
            goto L_0x007a
        L_0x00c5:
            r21 = 0
            r42 = 34
            r0 = r42
            if (r5 != r0) goto L_0x012b
            r0 = r46
            com.alibaba.fastjson.parser.SymbolTable r0 = r0.symbolTable     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 34
            r0 = r25
            r1 = r42
            r2 = r43
            java.lang.String r24 = r0.scanSymbol(r1, r2)     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 58
            r0 = r42
            if (r5 == r0) goto L_0x0b5b
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 58
            r0 = r42
            if (r5 == r0) goto L_0x0b5b
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "expect ':' at "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.pos     // Catch:{ all -> 0x0123 }
            r44 = r0
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = ", name "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r43
            r1 = r24
            java.lang.StringBuilder r43 = r0.append(r1)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0123:
            r42 = move-exception
            if (r11 != 0) goto L_0x012a
            r0 = r46
            r0.contex = r7
        L_0x012a:
            throw r42
        L_0x012b:
            r42 = 125(0x7d, float:1.75E-43)
            r0 = r42
            if (r5 != r0) goto L_0x017b
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x016c
            r42 = 26
        L_0x014d:
            r0 = r42
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            r42 = 0
            r0 = r42
            r1 = r25
            r1.f341sp = r0     // Catch:{ all -> 0x0123 }
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x016c:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r42 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x014d
        L_0x017b:
            r42 = 39
            r0 = r42
            if (r5 != r0) goto L_0x01fc
            r0 = r25
            int r0 = r0.features     // Catch:{ all -> 0x0123 }
            r42 = r0
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0123 }
            r0 = r43
            int r0 = r0.mask     // Catch:{ all -> 0x0123 }
            r43 = r0
            r42 = r42 & r43
            if (r42 != 0) goto L_0x01b0
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x01b0:
            r0 = r46
            com.alibaba.fastjson.parser.SymbolTable r0 = r0.symbolTable     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 39
            r0 = r25
            r1 = r42
            r2 = r43
            java.lang.String r24 = r0.scanSymbol(r1, r2)     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r0 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 58
            r0 = r42
            r1 = r43
            if (r0 == r1) goto L_0x01d3
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
        L_0x01d3:
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 58
            r0 = r42
            if (r5 == r0) goto L_0x0b5b
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "expect ':' at "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.pos     // Catch:{ all -> 0x0123 }
            r44 = r0
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x01fc:
            r42 = 26
            r0 = r42
            if (r5 != r0) goto L_0x021f
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x021f:
            r42 = 44
            r0 = r42
            if (r5 != r0) goto L_0x0242
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0242:
            r42 = 48
            r0 = r42
            if (r5 < r0) goto L_0x024e
            r42 = 57
            r0 = r42
            if (r5 <= r0) goto L_0x0254
        L_0x024e:
            r42 = 45
            r0 = r42
            if (r5 != r0) goto L_0x02c7
        L_0x0254:
            r42 = 0
            r0 = r42
            r1 = r25
            r1.f341sp = r0     // Catch:{ all -> 0x0123 }
            r25.scanNumber()     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.token     // Catch:{ NumberFormatException -> 0x02a9 }
            r42 = r0
            r43 = 2
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x029e
            java.lang.Number r24 = r25.integerValue()     // Catch:{ NumberFormatException -> 0x02a9 }
        L_0x0271:
            if (r20 == 0) goto L_0x0277
            java.lang.String r24 = r24.toString()     // Catch:{ NumberFormatException -> 0x02a9 }
        L_0x0277:
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 58
            r0 = r42
            if (r5 == r0) goto L_0x0b63
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "parse number key error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x029e:
            r42 = 1
            r0 = r25
            r1 = r42
            java.lang.Number r24 = r0.decimalValue(r1)     // Catch:{ NumberFormatException -> 0x02a9 }
            goto L_0x0271
        L_0x02a9:
            r13 = move-exception
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "parse number key error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x02c7:
            r42 = 123(0x7b, float:1.72E-43)
            r0 = r42
            if (r5 == r0) goto L_0x02d3
            r42 = 91
            r0 = r42
            if (r5 != r0) goto L_0x0338
        L_0x02d3:
            r25.nextToken()     // Catch:{ all -> 0x0123 }
            java.lang.Object r24 = r46.parse()     // Catch:{ all -> 0x0123 }
            r21 = 1
            if (r20 == 0) goto L_0x0b5f
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x0123 }
            r42 = r24
        L_0x02e4:
            if (r21 != 0) goto L_0x03c7
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r43 = r0
            int r16 = r43 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r16
            r1 = r43
            if (r0 < r1) goto L_0x03b7
            r5 = 26
        L_0x0302:
            r0 = r25
            r0.f339ch = r5     // Catch:{ all -> 0x0123 }
        L_0x0306:
            r43 = 32
            r0 = r43
            if (r5 > r0) goto L_0x03cb
            r43 = 32
            r0 = r43
            if (r5 == r0) goto L_0x0330
            r43 = 10
            r0 = r43
            if (r5 == r0) goto L_0x0330
            r43 = 13
            r0 = r43
            if (r5 == r0) goto L_0x0330
            r43 = 9
            r0 = r43
            if (r5 == r0) goto L_0x0330
            r43 = 12
            r0 = r43
            if (r5 == r0) goto L_0x0330
            r43 = 8
            r0 = r43
            if (r5 != r0) goto L_0x03cb
        L_0x0330:
            r25.next()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            goto L_0x0306
        L_0x0338:
            r0 = r25
            int r0 = r0.features     // Catch:{ all -> 0x0123 }
            r42 = r0
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0123 }
            r0 = r43
            int r0 = r0.mask     // Catch:{ all -> 0x0123 }
            r43 = r0
            r42 = r42 & r43
            if (r42 != 0) goto L_0x0367
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0367:
            r0 = r46
            com.alibaba.fastjson.parser.SymbolTable r0 = r0.symbolTable     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r25
            r1 = r42
            java.lang.String r24 = r0.scanSymbolUnQuoted(r1)     // Catch:{ all -> 0x0123 }
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 58
            r0 = r42
            if (r5 == r0) goto L_0x03ad
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "expect ':' at "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r44 = r0
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = ", actual "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r43
            java.lang.StringBuilder r43 = r0.append(r5)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x03ad:
            if (r20 == 0) goto L_0x0b5b
            java.lang.String r24 = r24.toString()     // Catch:{ all -> 0x0123 }
            r42 = r24
            goto L_0x02e4
        L_0x03b7:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r43
            r1 = r16
            char r5 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0302
        L_0x03c7:
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
        L_0x03cb:
            r43 = 0
            r0 = r43
            r1 = r25
            r1.f341sp = r0     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = "@type"
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x04ee
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0123 }
            r0 = r25
            r1 = r43
            boolean r43 = r0.isEnabled(r1)     // Catch:{ all -> 0x0123 }
            if (r43 != 0) goto L_0x04ee
            r0 = r46
            com.alibaba.fastjson.parser.SymbolTable r0 = r0.symbolTable     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 34
            r0 = r25
            r1 = r42
            r2 = r43
            java.lang.String r40 = r0.scanSymbol(r1, r2)     // Catch:{ all -> 0x0123 }
            r0 = r46
            com.alibaba.fastjson.parser.ParserConfig r0 = r0.config     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            java.lang.ClassLoader r0 = r0.defaultClassLoader     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r40
            r1 = r42
            java.lang.Class r6 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r1)     // Catch:{ all -> 0x0123 }
            if (r6 != 0) goto L_0x041c
            java.lang.String r42 = "@type"
            r0 = r47
            r1 = r42
            r2 = r40
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x0084
        L_0x041c:
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 13
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x0492
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            r19 = 0
            r0 = r46
            com.alibaba.fastjson.parser.ParserConfig r0 = r0.config     // Catch:{ Exception -> 0x0485 }
            r42 = r0
            r0 = r42
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r0.getDeserializer(r6)     // Catch:{ Exception -> 0x0485 }
            boolean r0 = r10 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer     // Catch:{ Exception -> 0x0485 }
            r42 = r0
            if (r42 == 0) goto L_0x0458
            com.alibaba.fastjson.parser.JavaBeanDeserializer r10 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r10     // Catch:{ Exception -> 0x0485 }
            r0 = r46
            java.lang.Object r19 = r10.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r0, (java.lang.reflect.Type) r6)     // Catch:{ Exception -> 0x0485 }
        L_0x0458:
            if (r19 != 0) goto L_0x0465
            java.lang.Class<java.lang.Cloneable> r42 = java.lang.Cloneable.class
            r0 = r42
            if (r6 != r0) goto L_0x046f
            java.util.HashMap r19 = new java.util.HashMap     // Catch:{ Exception -> 0x0485 }
            r19.<init>()     // Catch:{ Exception -> 0x0485 }
        L_0x0465:
            if (r11 != 0) goto L_0x046b
            r0 = r46
            r0.contex = r7
        L_0x046b:
            r47 = r19
            goto L_0x0019
        L_0x046f:
            java.lang.String r42 = "java.util.Collections$EmptyMap"
            r0 = r42
            r1 = r40
            boolean r42 = r0.equals(r1)     // Catch:{ Exception -> 0x0485 }
            if (r42 == 0) goto L_0x0480
            java.util.Map r19 = java.util.Collections.emptyMap()     // Catch:{ Exception -> 0x0485 }
            goto L_0x0465
        L_0x0480:
            java.lang.Object r19 = r6.newInstance()     // Catch:{ Exception -> 0x0485 }
            goto L_0x0465
        L_0x0485:
            r12 = move-exception
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = "create instance error"
            r0 = r42
            r1 = r43
            r0.<init>(r1, r12)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0492:
            r42 = 2
            r0 = r42
            r1 = r46
            r1.resolveStatus = r0     // Catch:{ all -> 0x0123 }
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r0 = r0.contex     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x04ad
            r0 = r48
            boolean r0 = r0 instanceof java.lang.Integer     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 != 0) goto L_0x04ad
            r46.popContext()     // Catch:{ all -> 0x0123 }
        L_0x04ad:
            int r42 = r47.size()     // Catch:{ all -> 0x0123 }
            if (r42 <= 0) goto L_0x04d2
            r0 = r46
            com.alibaba.fastjson.parser.ParserConfig r0 = r0.config     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r47
            r1 = r42
            java.lang.Object r27 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r0, r6, (com.alibaba.fastjson.parser.ParserConfig) r1)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r27
            r0.parseObject((java.lang.Object) r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x04ce
            r0 = r46
            r0.contex = r7
        L_0x04ce:
            r47 = r27
            goto L_0x0019
        L_0x04d2:
            r0 = r46
            com.alibaba.fastjson.parser.ParserConfig r0 = r0.config     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r10 = r0.getDeserializer(r6)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r48
            java.lang.Object r47 = r10.deserialze(r0, r6, r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x04ee:
            java.lang.String r43 = "$ref"
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x066a
            com.alibaba.fastjson.parser.Feature r43 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0123 }
            r0 = r25
            r1 = r43
            boolean r43 = r0.isEnabled(r1)     // Catch:{ all -> 0x0123 }
            if (r43 != 0) goto L_0x066a
            r42 = 4
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 4
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x0647
            java.lang.String r31 = r25.stringVal()     // Catch:{ all -> 0x0123 }
            r42 = 13
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            r32 = 0
            java.lang.String r42 = "@"
            r0 = r42
            r1 = r31
            boolean r42 = r0.equals(r1)     // Catch:{ all -> 0x0123 }
            if (r42 == 0) goto L_0x059c
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r0 = r0.contex     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x0632
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r0 = r0.contex     // Catch:{ all -> 0x0123 }
            r37 = r0
            r0 = r37
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0123 }
            r38 = r0
            r0 = r38
            boolean r0 = r0 instanceof java.lang.Object[]     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 != 0) goto L_0x0558
            r0 = r38
            boolean r0 = r0 instanceof java.util.Collection     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x0587
        L_0x0558:
            r32 = r38
        L_0x055a:
            r47 = r32
        L_0x055c:
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 13
            r0 = r42
            r1 = r43
            if (r0 == r1) goto L_0x0636
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0587:
            r0 = r37
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x055a
            r0 = r37
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0123 }
            r32 = r0
            goto L_0x055a
        L_0x059c:
            java.lang.String r42 = ".."
            r0 = r42
            r1 = r31
            boolean r42 = r0.equals(r1)     // Catch:{ all -> 0x0123 }
            if (r42 == 0) goto L_0x05d0
            java.lang.Object r0 = r7.object     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x05b5
            java.lang.Object r0 = r7.object     // Catch:{ all -> 0x0123 }
            r32 = r0
            r47 = r32
            goto L_0x055c
        L_0x05b5:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r42 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0123 }
            r0 = r42
            r1 = r31
            r0.<init>(r7, r1)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r42
            r0.addResolveTask(r1)     // Catch:{ all -> 0x0123 }
            r42 = 1
            r0 = r42
            r1 = r46
            r1.resolveStatus = r0     // Catch:{ all -> 0x0123 }
            r47 = r32
            goto L_0x055c
        L_0x05d0:
            java.lang.String r42 = "$"
            r0 = r42
            r1 = r31
            boolean r42 = r0.equals(r1)     // Catch:{ all -> 0x0123 }
            if (r42 == 0) goto L_0x061a
            r34 = r7
        L_0x05de:
            r0 = r34
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x05ed
            r0 = r34
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0123 }
            r34 = r0
            goto L_0x05de
        L_0x05ed:
            r0 = r34
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0123 }
            r42 = r0
            if (r42 == 0) goto L_0x05ff
            r0 = r34
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0123 }
            r32 = r0
        L_0x05fb:
            r47 = r32
            goto L_0x055c
        L_0x05ff:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r42 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0123 }
            r0 = r42
            r1 = r34
            r2 = r31
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r42
            r0.addResolveTask(r1)     // Catch:{ all -> 0x0123 }
            r42 = 1
            r0 = r42
            r1 = r46
            r1.resolveStatus = r0     // Catch:{ all -> 0x0123 }
            goto L_0x05fb
        L_0x061a:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r42 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0123 }
            r0 = r42
            r1 = r31
            r0.<init>(r7, r1)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r42
            r0.addResolveTask(r1)     // Catch:{ all -> 0x0123 }
            r42 = 1
            r0 = r42
            r1 = r46
            r1.resolveStatus = r0     // Catch:{ all -> 0x0123 }
        L_0x0632:
            r47 = r32
            goto L_0x055c
        L_0x0636:
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x0647:
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "illegal ref, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r44 = r0
            java.lang.String r44 = com.alibaba.fastjson.parser.JSONToken.name(r44)     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x066a:
            if (r11 != 0) goto L_0x0685
            if (r35 != 0) goto L_0x0685
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r0 = r0.contex     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r46
            r1 = r43
            r2 = r47
            r3 = r48
            com.alibaba.fastjson.parser.ParseContext r8 = r0.setContext(r1, r2, r3)     // Catch:{ all -> 0x0123 }
            if (r7 != 0) goto L_0x0683
            r7 = r8
        L_0x0683:
            r35 = 1
        L_0x0685:
            r43 = 34
            r0 = r43
            if (r5 != r0) goto L_0x0711
            r43 = 34
            r0 = r25
            r1 = r43
            java.lang.String r36 = r0.scanStringValue(r1)     // Catch:{ all -> 0x0123 }
            r41 = r36
            if (r4 == 0) goto L_0x06bb
            com.alibaba.fastjson.parser.JSONLexer r22 = new com.alibaba.fastjson.parser.JSONLexer     // Catch:{ all -> 0x0123 }
            r0 = r22
            r1 = r36
            r0.<init>(r1)     // Catch:{ all -> 0x0123 }
            r43 = 1
            r0 = r22
            r1 = r43
            boolean r43 = r0.scanISO8601DateIfMatch(r1)     // Catch:{ all -> 0x0123 }
            if (r43 == 0) goto L_0x06b8
            r0 = r22
            java.util.Calendar r0 = r0.calendar     // Catch:{ all -> 0x0123 }
            r43 = r0
            java.util.Date r41 = r43.getTime()     // Catch:{ all -> 0x0123 }
        L_0x06b8:
            r22.close()     // Catch:{ all -> 0x0123 }
        L_0x06bb:
            if (r17 == 0) goto L_0x0707
            r0 = r17
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x06c6:
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
            r42 = 44
            r0 = r42
            if (r5 == r0) goto L_0x06dd
            r42 = 125(0x7d, float:1.75E-43)
            r0 = r42
            if (r5 == r0) goto L_0x06dd
            r25.skipWhitespace()     // Catch:{ all -> 0x0123 }
            r0 = r25
            char r5 = r0.f339ch     // Catch:{ all -> 0x0123 }
        L_0x06dd:
            r42 = 44
            r0 = r42
            if (r5 != r0) goto L_0x0a21
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x0a11
            r42 = 26
        L_0x06ff:
            r0 = r42
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            goto L_0x0084
        L_0x0707:
            r0 = r47
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x06c6
        L_0x0711:
            r43 = 48
            r0 = r43
            if (r5 < r0) goto L_0x071d
            r43 = 57
            r0 = r43
            if (r5 <= r0) goto L_0x0723
        L_0x071d:
            r43 = 45
            r0 = r43
            if (r5 != r0) goto L_0x0731
        L_0x0723:
            java.lang.Number r41 = r25.scanNumberValue()     // Catch:{ all -> 0x0123 }
            r0 = r47
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x06c6
        L_0x0731:
            r43 = 91
            r0 = r43
            if (r5 != r0) goto L_0x07e0
            r43 = 14
            r0 = r43
            r1 = r25
            r1.token = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r43 = r0
            int r16 = r43 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r16
            r1 = r43
            if (r0 < r1) goto L_0x07a2
            r43 = 26
        L_0x075b:
            r0 = r43
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            java.util.ArrayList r26 = new java.util.ArrayList     // Catch:{ all -> 0x0123 }
            r26.<init>()     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r26
            r2 = r42
            r0.parseArray((java.util.Collection) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0123 }
            com.alibaba.fastjson.JSONArray r41 = new com.alibaba.fastjson.JSONArray     // Catch:{ all -> 0x0123 }
            r0 = r41
            r1 = r26
            r0.<init>((java.util.List<java.lang.Object>) r1)     // Catch:{ all -> 0x0123 }
            if (r17 == 0) goto L_0x07b1
            r0 = r17
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x0783:
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r39 = r0
            r42 = 13
            r0 = r39
            r1 = r42
            if (r0 != r1) goto L_0x07bb
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x07a2:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r43
            r1 = r16
            char r43 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x075b
        L_0x07b1:
            r0 = r47
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x0783
        L_0x07bb:
            r42 = 16
            r0 = r39
            r1 = r42
            if (r0 == r1) goto L_0x0084
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x07e0:
            r43 = 123(0x7b, float:1.72E-43)
            r0 = r43
            if (r5 != r0) goto L_0x0937
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r43 = r0
            int r16 = r43 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r16
            r1 = r43
            if (r0 < r1) goto L_0x08ed
            r43 = 26
        L_0x0802:
            r0 = r43
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            r43 = 12
            r0 = r43
            r1 = r25
            r1.token = r0     // Catch:{ all -> 0x0123 }
            r0 = r48
            boolean r0 = r0 instanceof java.lang.Integer     // Catch:{ all -> 0x0123 }
            r30 = r0
            r0 = r25
            int r0 = r0.features     // Catch:{ all -> 0x0123 }
            r43 = r0
            com.alibaba.fastjson.parser.Feature r44 = com.alibaba.fastjson.parser.Feature.OrderedField     // Catch:{ all -> 0x0123 }
            r0 = r44
            int r0 = r0.mask     // Catch:{ all -> 0x0123 }
            r44 = r0
            r43 = r43 & r44
            if (r43 == 0) goto L_0x08fd
            com.alibaba.fastjson.JSONObject r18 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0123 }
            java.util.LinkedHashMap r43 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            r0 = r18
            r1 = r43
            r0.<init>((java.util.Map<java.lang.String, java.lang.Object>) r1)     // Catch:{ all -> 0x0123 }
        L_0x0836:
            r9 = 0
            if (r11 != 0) goto L_0x0845
            if (r30 != 0) goto L_0x0845
            r0 = r46
            r1 = r18
            r2 = r42
            com.alibaba.fastjson.parser.ParseContext r9 = r0.setContext(r7, r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x0845:
            r28 = 0
            r29 = 0
            r0 = r46
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r0 = r0.fieldTypeResolver     // Catch:{ all -> 0x0123 }
            r43 = r0
            if (r43 == 0) goto L_0x087f
            if (r42 == 0) goto L_0x0904
            java.lang.String r33 = r42.toString()     // Catch:{ all -> 0x0123 }
        L_0x0857:
            r0 = r46
            com.alibaba.fastjson.parser.deserializer.FieldTypeResolver r0 = r0.fieldTypeResolver     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r43
            r1 = r47
            r2 = r33
            java.lang.reflect.Type r15 = r0.resolve(r1, r2)     // Catch:{ all -> 0x0123 }
            if (r15 == 0) goto L_0x087f
            r0 = r46
            com.alibaba.fastjson.parser.ParserConfig r0 = r0.config     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r43
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r14 = r0.getDeserializer(r15)     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r42
            java.lang.Object r28 = r14.deserialze(r0, r15, r1)     // Catch:{ all -> 0x0123 }
            r29 = 1
        L_0x087f:
            if (r29 != 0) goto L_0x088b
            r0 = r46
            r1 = r18
            r2 = r42
            java.lang.Object r28 = r0.parseObject((java.util.Map) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0123 }
        L_0x088b:
            if (r9 == 0) goto L_0x0897
            r0 = r18
            r1 = r28
            if (r0 == r1) goto L_0x0897
            r0 = r47
            r9.object = r0     // Catch:{ all -> 0x0123 }
        L_0x0897:
            r0 = r46
            int r0 = r0.resolveStatus     // Catch:{ all -> 0x0123 }
            r43 = r0
            r44 = 1
            r0 = r43
            r1 = r44
            if (r0 != r1) goto L_0x08b2
            java.lang.String r43 = r42.toString()     // Catch:{ all -> 0x0123 }
            r0 = r46
            r1 = r47
            r2 = r43
            r0.checkMapResolve(r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x08b2:
            if (r17 == 0) goto L_0x0908
            r0 = r17
            r1 = r42
            r2 = r28
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x08bd:
            if (r30 == 0) goto L_0x08c8
            r0 = r46
            r1 = r28
            r2 = r42
            r0.setContext(r7, r1, r2)     // Catch:{ all -> 0x0123 }
        L_0x08c8:
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r39 = r0
            r42 = 13
            r0 = r39
            r1 = r42
            if (r0 != r1) goto L_0x0912
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x08e5
            r0 = r46
            r0.contex = r7     // Catch:{ all -> 0x0123 }
        L_0x08e5:
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x08ed:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r43 = r0
            r0 = r43
            r1 = r16
            char r43 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0802
        L_0x08fd:
            com.alibaba.fastjson.JSONObject r18 = new com.alibaba.fastjson.JSONObject     // Catch:{ all -> 0x0123 }
            r18.<init>()     // Catch:{ all -> 0x0123 }
            goto L_0x0836
        L_0x0904:
            r33 = 0
            goto L_0x0857
        L_0x0908:
            r0 = r47
            r1 = r42
            r2 = r28
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x08bd
        L_0x0912:
            r42 = 16
            r0 = r39
            r1 = r42
            if (r0 == r1) goto L_0x0084
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0937:
            r43 = 116(0x74, float:1.63E-43)
            r0 = r43
            if (r5 != r0) goto L_0x096f
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r43 = r0
            java.lang.String r44 = "true"
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r45 = r0
            boolean r43 = r43.startsWith(r44, r45)     // Catch:{ all -> 0x0123 }
            if (r43 == 0) goto L_0x06c6
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r43 = r0
            int r43 = r43 + 3
            r0 = r43
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r25.next()     // Catch:{ all -> 0x0123 }
            java.lang.Boolean r43 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0123 }
            r0 = r47
            r1 = r42
            r2 = r43
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x06c6
        L_0x096f:
            r43 = 102(0x66, float:1.43E-43)
            r0 = r43
            if (r5 != r0) goto L_0x09a7
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r43 = r0
            java.lang.String r44 = "false"
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r45 = r0
            boolean r43 = r43.startsWith(r44, r45)     // Catch:{ all -> 0x0123 }
            if (r43 == 0) goto L_0x06c6
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r43 = r0
            int r43 = r43 + 4
            r0 = r43
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r25.next()     // Catch:{ all -> 0x0123 }
            java.lang.Boolean r43 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0123 }
            r0 = r47
            r1 = r42
            r2 = r43
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            goto L_0x06c6
        L_0x09a7:
            r25.nextToken()     // Catch:{ all -> 0x0123 }
            java.lang.Object r41 = r46.parse()     // Catch:{ all -> 0x0123 }
            java.lang.Class r43 = r47.getClass()     // Catch:{ all -> 0x0123 }
            java.lang.Class<com.alibaba.fastjson.JSONObject> r44 = com.alibaba.fastjson.JSONObject.class
            r0 = r43
            r1 = r44
            if (r0 != r1) goto L_0x09be
            java.lang.String r42 = r42.toString()     // Catch:{ all -> 0x0123 }
        L_0x09be:
            r0 = r47
            r1 = r42
            r2 = r41
            r0.put(r1, r2)     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 13
            r0 = r42
            r1 = r43
            if (r0 != r1) goto L_0x09e6
            r42 = 16
            r0 = r25
            r1 = r42
            r0.nextToken(r1)     // Catch:{ all -> 0x0123 }
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x09e6:
            r0 = r25
            int r0 = r0.token     // Catch:{ all -> 0x0123 }
            r42 = r0
            r43 = 16
            r0 = r42
            r1 = r43
            if (r0 == r1) goto L_0x0084
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0a11:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r42 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x06ff
        L_0x0a21:
            r42 = 125(0x7d, float:1.75E-43)
            r0 = r42
            if (r5 != r0) goto L_0x0b3e
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x0a9a
            r5 = 26
        L_0x0a43:
            r0 = r25
            r0.f339ch = r5     // Catch:{ all -> 0x0123 }
            r42 = 0
            r0 = r42
            r1 = r25
            r1.f341sp = r0     // Catch:{ all -> 0x0123 }
            r42 = 44
            r0 = r42
            if (r5 != r0) goto L_0x0ab8
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x0aa9
            r42 = 26
        L_0x0a71:
            r0 = r42
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            r42 = 16
            r0 = r42
            r1 = r25
            r1.token = r0     // Catch:{ all -> 0x0123 }
        L_0x0a7f:
            if (r11 != 0) goto L_0x0a92
            r0 = r46
            com.alibaba.fastjson.parser.ParseContext r0 = r0.contex     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r46
            r1 = r42
            r2 = r47
            r3 = r48
            r0.setContext(r1, r2, r3)     // Catch:{ all -> 0x0123 }
        L_0x0a92:
            if (r11 != 0) goto L_0x0019
            r0 = r46
            r0.contex = r7
            goto L_0x0019
        L_0x0a9a:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r5 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0a43
        L_0x0aa9:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r42 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0a71
        L_0x0ab8:
            r42 = 125(0x7d, float:1.75E-43)
            r0 = r42
            if (r5 != r0) goto L_0x0af8
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x0ae9
            r42 = 26
        L_0x0ada:
            r0 = r42
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            r42 = 13
            r0 = r42
            r1 = r25
            r1.token = r0     // Catch:{ all -> 0x0123 }
            goto L_0x0a7f
        L_0x0ae9:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r42 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0ada
        L_0x0af8:
            r42 = 93
            r0 = r42
            if (r5 != r0) goto L_0x0b39
            r0 = r25
            int r0 = r0.f338bp     // Catch:{ all -> 0x0123 }
            r42 = r0
            int r16 = r42 + 1
            r0 = r16
            r1 = r25
            r1.f338bp = r0     // Catch:{ all -> 0x0123 }
            r0 = r25
            int r0 = r0.len     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r16
            r1 = r42
            if (r0 < r1) goto L_0x0b2a
            r42 = 26
        L_0x0b1a:
            r0 = r42
            r1 = r25
            r1.f339ch = r0     // Catch:{ all -> 0x0123 }
            r42 = 15
            r0 = r42
            r1 = r25
            r1.token = r0     // Catch:{ all -> 0x0123 }
            goto L_0x0a7f
        L_0x0b2a:
            r0 = r25
            java.lang.String r0 = r0.text     // Catch:{ all -> 0x0123 }
            r42 = r0
            r0 = r42
            r1 = r16
            char r42 = r0.charAt(r1)     // Catch:{ all -> 0x0123 }
            goto L_0x0b1a
        L_0x0b39:
            r25.nextToken()     // Catch:{ all -> 0x0123 }
            goto L_0x0a7f
        L_0x0b3e:
            com.alibaba.fastjson.JSONException r42 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r43.<init>()     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = "syntax error, "
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r44 = r25.info()     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r43 = r43.append(r44)     // Catch:{ all -> 0x0123 }
            java.lang.String r43 = r43.toString()     // Catch:{ all -> 0x0123 }
            r42.<init>(r43)     // Catch:{ all -> 0x0123 }
            throw r42     // Catch:{ all -> 0x0123 }
        L_0x0b5b:
            r42 = r24
            goto L_0x02e4
        L_0x0b5f:
            r42 = r24
            goto L_0x02e4
        L_0x0b63:
            r42 = r24
            goto L_0x02e4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public <T> T parseObject(Class<T> clazz) {
        return parseObject((Type) clazz, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object fieldName) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (this.lexer.token == 4) {
            if (type == byte[].class) {
                Object bytesValue = this.lexer.bytesValue();
                this.lexer.nextToken();
                return bytesValue;
            } else if (type == char[].class) {
                String strVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return strVal.toCharArray();
            }
        }
        try {
            return this.config.getDeserializer(type).deserialze(this, type, fieldName);
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public <T> List<T> parseArray(Class<T> clazz) {
        List<T> array = new ArrayList<>();
        parseArray((Class<?>) clazz, (Collection) array);
        return array;
    }

    public void parseArray(Class<?> clazz, Collection array) {
        parseArray((Type) clazz, array);
    }

    public void parseArray(Type type, Collection array) {
        parseArray(type, array, (Object) null);
    }

    /* JADX INFO: finally extract failed */
    public void parseArray(Type type, Collection array, Object fieldName) {
        ObjectDeserializer deserializer;
        Object deserialze;
        String value;
        if (this.lexer.token == 21 || this.lexer.token == 22) {
            this.lexer.nextToken();
        }
        if (this.lexer.token != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(12);
        }
        ParseContext context = this.contex;
        if (!this.lexer.disableCircularReferenceDetect) {
            setContext(this.contex, array, fieldName);
        }
        int i = 0;
        while (true) {
            if ((this.lexer.features & Feature.AllowArbitraryCommas.mask) != 0) {
                while (this.lexer.token == 16) {
                    this.lexer.nextToken();
                }
            }
            try {
                if (this.lexer.token == 15) {
                    this.contex = context;
                    this.lexer.nextToken(16);
                    return;
                }
                if (Integer.TYPE == type) {
                    array.add(IntegerCodec.instance.deserialze(this, (Type) null, (Object) null));
                } else if (String.class == type) {
                    if (this.lexer.token == 4) {
                        value = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        Object obj = parse();
                        if (obj == null) {
                            value = null;
                        } else {
                            value = obj.toString();
                        }
                    }
                    array.add(value);
                } else {
                    if (this.lexer.token == 8) {
                        this.lexer.nextToken();
                        deserialze = null;
                    } else {
                        deserialze = deserializer.deserialze(this, type, Integer.valueOf(i));
                    }
                    array.add(deserialze);
                    if (this.resolveStatus == 1) {
                        checkListResolve(array);
                    }
                }
                if (this.lexer.token == 16) {
                    this.lexer.nextToken();
                }
                i++;
            } catch (Throwable th) {
                this.contex = context;
                throw th;
            }
        }
    }

    public Object[] parseArray(Type[] types) {
        Object value;
        if (this.lexer.token == 8) {
            this.lexer.nextToken(16);
            return null;
        } else if (this.lexer.token != 14) {
            throw new JSONException("syntax error, " + this.lexer.info());
        } else {
            Object[] list = new Object[types.length];
            if (types.length == 0) {
                this.lexer.nextToken(15);
                if (this.lexer.token != 15) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
                this.lexer.nextToken(16);
                return new Object[0];
            }
            this.lexer.nextToken(2);
            int i = 0;
            while (i < types.length) {
                if (this.lexer.token == 8) {
                    value = null;
                    this.lexer.nextToken(16);
                } else {
                    Class<?> type = types[i];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (this.lexer.token == 2) {
                            value = Integer.valueOf(this.lexer.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            value = TypeUtils.cast(parse(), (Type) type, this.config);
                        }
                    } else if (type != String.class) {
                        boolean isArray = false;
                        Class<?> componentType = null;
                        if (i == types.length - 1 && (type instanceof Class)) {
                            Class<?> clazz = type;
                            isArray = clazz.isArray();
                            componentType = clazz.getComponentType();
                        }
                        if (!isArray || this.lexer.token == 14) {
                            value = this.config.getDeserializer(type).deserialze(this, type, (Object) null);
                        } else {
                            List<Object> varList = new ArrayList<>();
                            ObjectDeserializer derializer = this.config.getDeserializer(componentType);
                            if (this.lexer.token != 15) {
                                while (true) {
                                    varList.add(derializer.deserialze(this, type, (Object) null));
                                    if (this.lexer.token != 16) {
                                        break;
                                    }
                                    this.lexer.nextToken(12);
                                }
                                if (this.lexer.token != 15) {
                                    throw new JSONException("syntax error, " + this.lexer.info());
                                }
                            }
                            value = TypeUtils.cast((Object) varList, (Type) type, this.config);
                        }
                    } else if (this.lexer.token == 4) {
                        value = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        value = TypeUtils.cast(parse(), (Type) type, this.config);
                    }
                }
                list[i] = value;
                if (this.lexer.token == 15) {
                    break;
                } else if (this.lexer.token != 16) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                } else {
                    if (i == types.length - 1) {
                        this.lexer.nextToken(15);
                    } else {
                        this.lexer.nextToken(2);
                    }
                    i++;
                }
            }
            if (this.lexer.token != 15) {
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            this.lexer.nextToken(16);
            return list;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.alibaba.fastjson.parser.deserializer.ObjectDeserializer] */
    /* JADX WARNING: CFG modification limit reached, blocks count: 150 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseObject(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Class r1 = r13.getClass()
            r0 = 0
            com.alibaba.fastjson.parser.ParserConfig r9 = r12.config
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r2 = r9.getDeserializer(r1)
            boolean r9 = r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer
            if (r9 == 0) goto L_0x0012
            r0 = r2
            com.alibaba.fastjson.parser.JavaBeanDeserializer r0 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r0
        L_0x0012:
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 12
            if (r9 == r10) goto L_0x0054
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 16
            if (r9 == r10) goto L_0x0054
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "syntax error, expect {, actual "
            java.lang.StringBuilder r10 = r10.append(r11)
            com.alibaba.fastjson.parser.JSONLexer r11 = r12.lexer
            java.lang.String r11 = r11.tokenName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0041:
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 16
            if (r9 != r10) goto L_0x006e
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.features
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas
            int r10 = r10.mask
            r9 = r9 & r10
            if (r9 == 0) goto L_0x006e
        L_0x0054:
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            com.alibaba.fastjson.parser.SymbolTable r10 = r12.symbolTable
            java.lang.String r8 = r9.scanSymbol(r10)
            if (r8 != 0) goto L_0x006e
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 13
            if (r9 != r10) goto L_0x0041
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 16
            r9.nextToken(r10)
        L_0x006d:
            return
        L_0x006e:
            r4 = 0
            if (r0 == 0) goto L_0x0075
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer r4 = r0.getFieldDeserializer(r8)
        L_0x0075:
            if (r4 != 0) goto L_0x00c1
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.features
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.IgnoreNotMatch
            int r10 = r10.mask
            r9 = r9 & r10
            if (r9 != 0) goto L_0x00a9
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "setter not found, class "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = r1.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r11 = ", property "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r8)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x00a9:
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 58
            r9.nextTokenWithChar(r10)
            r12.parse()
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 13
            if (r9 != r10) goto L_0x0054
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r9.nextToken()
            goto L_0x006d
        L_0x00c1:
            com.alibaba.fastjson.util.FieldInfo r9 = r4.fieldInfo
            java.lang.Class<?> r3 = r9.fieldClass
            com.alibaba.fastjson.util.FieldInfo r9 = r4.fieldInfo
            java.lang.reflect.Type r5 = r9.fieldType
            java.lang.Class r9 = java.lang.Integer.TYPE
            if (r3 != r9) goto L_0x00f7
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 58
            r9.nextTokenWithChar(r10)
            com.alibaba.fastjson.serializer.IntegerCodec r9 = com.alibaba.fastjson.serializer.IntegerCodec.instance
            r10 = 0
            java.lang.Object r6 = r9.deserialze(r12, r5, r10)
        L_0x00db:
            r4.setValue((java.lang.Object) r13, (java.lang.Object) r6)
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 16
            if (r9 == r10) goto L_0x0054
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            int r9 = r9.token
            r10 = 13
            if (r9 != r10) goto L_0x0054
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 16
            r9.nextToken(r10)
            goto L_0x006d
        L_0x00f7:
            java.lang.Class<java.lang.String> r9 = java.lang.String.class
            if (r3 != r9) goto L_0x0107
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 58
            r9.nextTokenWithChar(r10)
            java.lang.String r6 = r12.parseString()
            goto L_0x00db
        L_0x0107:
            java.lang.Class r9 = java.lang.Long.TYPE
            if (r3 != r9) goto L_0x011a
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 58
            r9.nextTokenWithChar(r10)
            com.alibaba.fastjson.serializer.IntegerCodec r9 = com.alibaba.fastjson.serializer.IntegerCodec.instance
            r10 = 0
            java.lang.Object r6 = r9.deserialze(r12, r5, r10)
            goto L_0x00db
        L_0x011a:
            com.alibaba.fastjson.parser.ParserConfig r9 = r12.config
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r7 = r9.getDeserializer(r3, r5)
            com.alibaba.fastjson.parser.JSONLexer r9 = r12.lexer
            r10 = 58
            r9.nextTokenWithChar(r10)
            r9 = 0
            java.lang.Object r6 = r7.deserialze(r12, r5, r9)
            goto L_0x00db
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.lang.Object):void");
    }

    public Object parseArrayWithType(Type collectionType) {
        if (this.lexer.token == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypes = ((ParameterizedType) collectionType).getActualTypeArguments();
        if (actualTypes.length != 1) {
            throw new JSONException("not support type " + collectionType);
        }
        Type actualTypeArgument = actualTypes[0];
        if (actualTypeArgument instanceof Class) {
            List<Object> array = new ArrayList<>();
            parseArray((Class<?>) (Class) actualTypeArgument, (Collection) array);
            return array;
        } else if (actualTypeArgument instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) actualTypeArgument;
            Type upperBoundType = wildcardType.getUpperBounds()[0];
            if (!Object.class.equals(upperBoundType)) {
                List<Object> array2 = new ArrayList<>();
                parseArray((Class<?>) (Class) upperBoundType, (Collection) array2);
                return array2;
            } else if (wildcardType.getLowerBounds().length == 0) {
                return parse();
            } else {
                throw new JSONException("not support type : " + collectionType);
            }
        } else {
            if (actualTypeArgument instanceof TypeVariable) {
                TypeVariable<?> typeVariable = (TypeVariable) actualTypeArgument;
                Type[] bounds = typeVariable.getBounds();
                if (bounds.length != 1) {
                    throw new JSONException("not support : " + typeVariable);
                }
                Type boundType = bounds[0];
                if (boundType instanceof Class) {
                    List<Object> array3 = new ArrayList<>();
                    parseArray((Class<?>) (Class) boundType, (Collection) array3);
                    return array3;
                }
            }
            if (actualTypeArgument instanceof ParameterizedType) {
                List<Object> array4 = new ArrayList<>();
                parseArray((Type) (ParameterizedType) actualTypeArgument, (Collection) array4);
                return array4;
            }
            throw new JSONException("TODO : " + collectionType);
        }
    }

    /* access modifiers changed from: protected */
    public void checkListResolve(Collection array) {
        if (array instanceof List) {
            ResolveTask task = getLastResolveTask();
            task.fieldDeserializer = new ResolveFieldDeserializer(this, (List) array, array.size() - 1);
            task.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask task2 = getLastResolveTask();
        task2.fieldDeserializer = new ResolveFieldDeserializer(array);
        task2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    /* access modifiers changed from: protected */
    public void checkMapResolve(Map object, Object fieldName) {
        ResolveFieldDeserializer fieldResolver = new ResolveFieldDeserializer(object, fieldName);
        ResolveTask task = getLastResolveTask();
        task.fieldDeserializer = fieldResolver;
        task.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    public Object parseObject(Map object) {
        return parseObject(object, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) (this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject((Map<String, Object>) new LinkedHashMap()) : new JSONObject(), (Object) null);
    }

    public final void parseArray(Collection array) {
        parseArray(array, (Object) null);
    }

    public final void parseArray(Collection array, Object fieldName) {
        boolean first_quote;
        Object value;
        JSONObject object;
        Object value2;
        char charAt;
        char charAt2;
        char ch;
        char charAt3;
        int token = this.lexer.token;
        if (token == 21 || token == 22) {
            this.lexer.nextToken();
            token = this.lexer.token;
        }
        if (token != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(token) + ", pos " + this.lexer.pos);
        }
        boolean disableCircularReferenceDetect = this.lexer.disableCircularReferenceDetect;
        ParseContext context = this.contex;
        if (!disableCircularReferenceDetect) {
            setContext(this.contex, array, fieldName);
        }
        try {
            char ch2 = this.lexer.f339ch;
            if (ch2 != '\"') {
                if (ch2 == ']') {
                    this.lexer.next();
                    this.lexer.nextToken(16);
                    if (disableCircularReferenceDetect) {
                        return;
                    }
                    return;
                }
                if (ch2 == '{') {
                    JSONLexer jSONLexer = this.lexer;
                    int index = jSONLexer.f338bp + 1;
                    jSONLexer.f338bp = index;
                    JSONLexer jSONLexer2 = this.lexer;
                    if (index >= this.lexer.len) {
                        charAt3 = JSONLexer.EOI;
                    } else {
                        charAt3 = this.lexer.text.charAt(index);
                    }
                    jSONLexer2.f339ch = charAt3;
                    this.lexer.token = 12;
                } else {
                    this.lexer.nextToken(12);
                }
                first_quote = false;
            } else if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) == 0) {
                first_quote = true;
            } else {
                this.lexer.nextToken(4);
                first_quote = false;
            }
            int i = 0;
            while (true) {
                if (first_quote && this.lexer.f339ch == '\"') {
                    String value3 = this.lexer.scanStringValue('\"');
                    char ch3 = this.lexer.f339ch;
                    if (ch3 == ',') {
                        JSONLexer jSONLexer3 = this.lexer;
                        int index2 = jSONLexer3.f338bp + 1;
                        jSONLexer3.f338bp = index2;
                        JSONLexer jSONLexer4 = this.lexer;
                        if (index2 >= this.lexer.len) {
                            ch = JSONLexer.EOI;
                        } else {
                            ch = this.lexer.text.charAt(index2);
                        }
                        jSONLexer4.f339ch = ch;
                        array.add(value3);
                        if (this.resolveStatus == 1) {
                            checkListResolve(array);
                        }
                        if (ch == '\"') {
                            continue;
                            i++;
                        } else {
                            this.lexer.nextToken();
                        }
                    } else if (ch3 == ']') {
                        JSONLexer jSONLexer5 = this.lexer;
                        int index3 = jSONLexer5.f338bp + 1;
                        jSONLexer5.f338bp = index3;
                        JSONLexer jSONLexer6 = this.lexer;
                        if (index3 >= this.lexer.len) {
                            charAt2 = JSONLexer.EOI;
                        } else {
                            charAt2 = this.lexer.text.charAt(index3);
                        }
                        jSONLexer6.f339ch = charAt2;
                        array.add(value3);
                        if (this.resolveStatus == 1) {
                            checkListResolve(array);
                        }
                        this.lexer.nextToken(16);
                        if (!disableCircularReferenceDetect) {
                            this.contex = context;
                            return;
                        }
                        return;
                    } else {
                        this.lexer.nextToken();
                    }
                }
                int token2 = this.lexer.token;
                while (token2 == 16 && (this.lexer.features & Feature.AllowArbitraryCommas.mask) != 0) {
                    this.lexer.nextToken();
                    token2 = this.lexer.token;
                }
                switch (token2) {
                    case 2:
                        value = this.lexer.integerValue();
                        this.lexer.nextToken(16);
                        break;
                    case 3:
                        if ((this.lexer.features & Feature.UseBigDecimal.mask) != 0) {
                            value = this.lexer.decimalValue(true);
                        } else {
                            value = this.lexer.decimalValue(false);
                        }
                        this.lexer.nextToken(16);
                        break;
                    case 4:
                        String stringLiteral = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                        if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) == 0) {
                            value = stringLiteral;
                            break;
                        } else {
                            JSONLexer iso8601Lexer = new JSONLexer(stringLiteral);
                            if (iso8601Lexer.scanISO8601DateIfMatch(true)) {
                                value2 = iso8601Lexer.calendar.getTime();
                            } else {
                                value2 = stringLiteral;
                            }
                            iso8601Lexer.close();
                            break;
                        }
                    case 6:
                        value = Boolean.TRUE;
                        this.lexer.nextToken(16);
                        break;
                    case 7:
                        value = Boolean.FALSE;
                        this.lexer.nextToken(16);
                        break;
                    case 8:
                        value = null;
                        this.lexer.nextToken(4);
                        break;
                    case 12:
                        if ((this.lexer.features & Feature.OrderedField.mask) != 0) {
                            object = new JSONObject((Map<String, Object>) new LinkedHashMap());
                        } else {
                            object = new JSONObject();
                        }
                        value = parseObject((Map) object, (Object) Integer.valueOf(i));
                        break;
                    case 14:
                        Collection items = new JSONArray();
                        parseArray(items, (Object) Integer.valueOf(i));
                        value = items;
                        break;
                    case 15:
                        this.lexer.nextToken(16);
                        if (!disableCircularReferenceDetect) {
                            this.contex = context;
                            return;
                        }
                        return;
                    case 20:
                        throw new JSONException("unclosed jsonArray");
                    case 23:
                        value = null;
                        this.lexer.nextToken(4);
                        break;
                    default:
                        value = parse();
                        break;
                }
                array.add(value);
                if (this.resolveStatus == 1) {
                    checkListResolve(array);
                }
                if (this.lexer.token == 16) {
                    char ch4 = this.lexer.f339ch;
                    if (ch4 == '\"') {
                        this.lexer.pos = this.lexer.f338bp;
                        this.lexer.scanString();
                    } else if (ch4 >= '0' && ch4 <= '9') {
                        this.lexer.pos = this.lexer.f338bp;
                        this.lexer.scanNumber();
                    } else if (ch4 == '{') {
                        this.lexer.token = 12;
                        JSONLexer jSONLexer7 = this.lexer;
                        int index4 = jSONLexer7.f338bp + 1;
                        jSONLexer7.f338bp = index4;
                        JSONLexer jSONLexer8 = this.lexer;
                        if (index4 >= this.lexer.len) {
                            charAt = JSONLexer.EOI;
                        } else {
                            charAt = this.lexer.text.charAt(index4);
                        }
                        jSONLexer8.f339ch = charAt;
                    } else {
                        this.lexer.nextToken();
                    }
                }
                i++;
            }
        } finally {
            if (!disableCircularReferenceDetect) {
                this.contex = context;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addResolveTask(ResolveTask task) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(task);
    }

    /* access modifiers changed from: protected */
    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public void setContext(ParseContext context) {
        if (!this.lexer.disableCircularReferenceDetect) {
            this.contex = context;
        }
    }

    /* access modifiers changed from: protected */
    public void popContext() {
        this.contex = this.contex.parent;
        this.contextArray[this.contextArrayIndex - 1] = null;
        this.contextArrayIndex--;
    }

    /* access modifiers changed from: protected */
    public ParseContext setContext(ParseContext parent, Object object, Object fieldName) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parent, object, fieldName);
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            ParseContext[] newArray = new ParseContext[((this.contextArray.length * 3) / 2)];
            System.arraycopy(this.contextArray, 0, newArray, 0, this.contextArray.length);
            this.contextArray = newArray;
        }
        this.contextArray[i] = this.contex;
        return this.contex;
    }

    public Object parse() {
        return parse((Object) null);
    }

    public Object parse(Object fieldName) {
        switch (this.lexer.token) {
            case 2:
                Number intValue = this.lexer.integerValue();
                this.lexer.nextToken();
                return intValue;
            case 3:
                Number value = this.lexer.decimalValue((this.lexer.features & Feature.UseBigDecimal.mask) != 0);
                this.lexer.nextToken();
                return value;
            case 4:
                String stringLiteral = this.lexer.stringVal();
                this.lexer.nextToken(16);
                if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                    JSONLexer iso8601Lexer = new JSONLexer(stringLiteral);
                    try {
                        if (iso8601Lexer.scanISO8601DateIfMatch(true)) {
                            return iso8601Lexer.calendar.getTime();
                        }
                        iso8601Lexer.close();
                    } finally {
                        iso8601Lexer.close();
                    }
                }
                return stringLiteral;
            case 6:
                this.lexer.nextToken(16);
                return Boolean.TRUE;
            case 7:
                this.lexer.nextToken(16);
                return Boolean.FALSE;
            case 8:
            case 23:
                this.lexer.nextToken();
                return null;
            case 9:
                this.lexer.nextToken(18);
                if (this.lexer.token != 18) {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
                this.lexer.nextToken(10);
                accept(10);
                long time = this.lexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(time);
            case 12:
                return parseObject((Map) (this.lexer.features & Feature.OrderedField.mask) != 0 ? new JSONObject((Map<String, Object>) new LinkedHashMap()) : new JSONObject(), fieldName);
            case 14:
                JSONArray array = new JSONArray();
                parseArray((Collection) array, fieldName);
                return array;
            case 20:
                if (this.lexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            case 21:
                this.lexer.nextToken();
                HashSet<Object> set = new HashSet<>();
                parseArray((Collection) set, fieldName);
                return set;
            case 22:
                this.lexer.nextToken();
                TreeSet<Object> treeSet = new TreeSet<>();
                parseArray((Collection) treeSet, fieldName);
                return treeSet;
            default:
                throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public void config(Feature feature, boolean state) {
        this.lexer.config(feature, state);
    }

    public final void accept(int token) {
        if (this.lexer.token == token) {
            this.lexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(token) + ", actual " + JSONToken.name(this.lexer.token));
    }

    public void close() {
        try {
            if ((this.lexer.features & Feature.AutoCloseSource.mask) != 0 && this.lexer.token != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
            }
        } finally {
            this.lexer.close();
        }
    }

    public void handleResovleTask(Object value) {
        if (this.resolveTaskList != null) {
            int size = this.resolveTaskList.size();
            for (int i = 0; i < size; i++) {
                ResolveTask task = this.resolveTaskList.get(i);
                FieldDeserializer fieldDeser = task.fieldDeserializer;
                if (fieldDeser != null) {
                    Object object = null;
                    if (task.ownerContext != null) {
                        object = task.ownerContext.object;
                    }
                    String ref = task.referenceValue;
                    Object refValue = null;
                    if (ref.startsWith("$")) {
                        for (int j = 0; j < this.contextArrayIndex; j++) {
                            if (ref.equals(this.contextArray[j].toString())) {
                                refValue = this.contextArray[j].object;
                            }
                        }
                    } else {
                        refValue = task.context.object;
                    }
                    fieldDeser.setValue(object, refValue);
                }
            }
        }
    }

    public String parseString() {
        char c = JSONLexer.EOI;
        int token = this.lexer.token;
        if (token == 4) {
            String val = this.lexer.stringVal();
            if (this.lexer.f339ch == ',') {
                JSONLexer jSONLexer = this.lexer;
                int index = jSONLexer.f338bp + 1;
                jSONLexer.f338bp = index;
                JSONLexer jSONLexer2 = this.lexer;
                if (index < this.lexer.len) {
                    c = this.lexer.text.charAt(index);
                }
                jSONLexer2.f339ch = c;
                this.lexer.token = 16;
                return val;
            } else if (this.lexer.f339ch == ']') {
                JSONLexer jSONLexer3 = this.lexer;
                int index2 = jSONLexer3.f338bp + 1;
                jSONLexer3.f338bp = index2;
                JSONLexer jSONLexer4 = this.lexer;
                if (index2 < this.lexer.len) {
                    c = this.lexer.text.charAt(index2);
                }
                jSONLexer4.f339ch = c;
                this.lexer.token = 15;
                return val;
            } else if (this.lexer.f339ch == '}') {
                JSONLexer jSONLexer5 = this.lexer;
                int index3 = jSONLexer5.f338bp + 1;
                jSONLexer5.f338bp = index3;
                JSONLexer jSONLexer6 = this.lexer;
                if (index3 < this.lexer.len) {
                    c = this.lexer.text.charAt(index3);
                }
                jSONLexer6.f339ch = c;
                this.lexer.token = 13;
                return val;
            } else {
                this.lexer.nextToken();
                return val;
            }
        } else if (token == 2) {
            String val2 = this.lexer.numberString();
            this.lexer.nextToken(16);
            return val2;
        } else {
            Object value = parse();
            if (value == null) {
                return null;
            }
            return value.toString();
        }
    }

    public static class ResolveTask {
        /* access modifiers changed from: private */
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        /* access modifiers changed from: private */
        public final String referenceValue;

        public ResolveTask(ParseContext context2, String referenceValue2) {
            this.context = context2;
            this.referenceValue = referenceValue2;
        }
    }
}
