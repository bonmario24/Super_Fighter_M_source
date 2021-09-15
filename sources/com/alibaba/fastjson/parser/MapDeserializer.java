package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapDeserializer implements ObjectDeserializer {
    public static MapDeserializer instance = new MapDeserializer();

    MapDeserializer() {
    }

    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type == JSONObject.class && parser.fieldTypeResolver == null) {
            return parser.parseObject();
        }
        JSONLexer lexer = parser.lexer;
        if (lexer.token == 8) {
            lexer.nextToken(16);
            return null;
        }
        Map<?, ?> map = createMap(type);
        ParseContext context = parser.contex;
        try {
            parser.setContext(context, map, fieldName);
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type keyType = parameterizedType.getActualTypeArguments()[0];
                Type valueType = parameterizedType.getActualTypeArguments()[1];
                if (String.class == keyType) {
                    return parseMap(parser, map, valueType, fieldName);
                }
                T parseMap = parseMap(parser, map, keyType, valueType, fieldName);
                parser.setContext(context);
                return parseMap;
            }
            T parseObject = parser.parseObject((Map) map, fieldName);
            parser.setContext(context);
            return parseObject;
        } finally {
            parser.setContext(context);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r3 = r12.config.getDeserializer(r1);
        r5.nextToken(16);
        r12.resolveStatus = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0177, code lost:
        if (r2 == null) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x017b, code lost:
        if ((r15 instanceof java.lang.Integer) != false) goto L_0x0180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x017d, code lost:
        r12.popContext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0180, code lost:
        r9 = (java.util.Map) r3.deserialze(r12, r1, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0186, code lost:
        r12.setContext(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r12, java.util.Map<java.lang.String, java.lang.Object> r13, java.lang.reflect.Type r14, java.lang.Object r15) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r5 = r12.lexer
            int r9 = r5.token
            r10 = 12
            if (r9 == r10) goto L_0x0023
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "syntax error, expect {, actual "
            java.lang.StringBuilder r10 = r10.append(r11)
            int r11 = r5.token
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0023:
            com.alibaba.fastjson.parser.ParseContext r2 = r12.contex
        L_0x0025:
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            int r9 = r5.features     // Catch:{ all -> 0x0072 }
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0072 }
            int r10 = r10.mask     // Catch:{ all -> 0x0072 }
            r9 = r9 & r10
            if (r9 == 0) goto L_0x0040
        L_0x0033:
            r9 = 44
            if (r0 != r9) goto L_0x0040
            r5.next()     // Catch:{ all -> 0x0072 }
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            goto L_0x0033
        L_0x0040:
            r9 = 34
            if (r0 != r9) goto L_0x0077
            com.alibaba.fastjson.parser.SymbolTable r9 = r12.symbolTable     // Catch:{ all -> 0x0072 }
            r10 = 34
            java.lang.String r4 = r5.scanSymbol(r9, r10)     // Catch:{ all -> 0x0072 }
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            r9 = 58
            if (r0 == r9) goto L_0x0127
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r10.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = "syntax error, "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = r5.info()     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0072 }
            r9.<init>(r10)     // Catch:{ all -> 0x0072 }
            throw r9     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r9 = move-exception
            r12.setContext(r2)
            throw r9
        L_0x0077:
            r9 = 125(0x7d, float:1.75E-43)
            if (r0 != r9) goto L_0x008a
            r5.next()     // Catch:{ all -> 0x0072 }
            r9 = 0
            r5.f341sp = r9     // Catch:{ all -> 0x0072 }
            r9 = 16
            r5.nextToken(r9)     // Catch:{ all -> 0x0072 }
            r12.setContext(r2)
        L_0x0089:
            return r13
        L_0x008a:
            r9 = 39
            if (r0 != r9) goto L_0x00e2
            int r9 = r5.features     // Catch:{ all -> 0x0072 }
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.AllowSingleQuotes     // Catch:{ all -> 0x0072 }
            int r10 = r10.mask     // Catch:{ all -> 0x0072 }
            r9 = r9 & r10
            if (r9 != 0) goto L_0x00b4
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r10.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = "syntax error, "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = r5.info()     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0072 }
            r9.<init>(r10)     // Catch:{ all -> 0x0072 }
            throw r9     // Catch:{ all -> 0x0072 }
        L_0x00b4:
            com.alibaba.fastjson.parser.SymbolTable r9 = r12.symbolTable     // Catch:{ all -> 0x0072 }
            r10 = 39
            java.lang.String r4 = r5.scanSymbol(r9, r10)     // Catch:{ all -> 0x0072 }
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            r9 = 58
            if (r0 == r9) goto L_0x0127
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r10.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = "syntax error, "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = r5.info()     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0072 }
            r9.<init>(r10)     // Catch:{ all -> 0x0072 }
            throw r9     // Catch:{ all -> 0x0072 }
        L_0x00e2:
            int r9 = r5.features     // Catch:{ all -> 0x0072 }
            com.alibaba.fastjson.parser.Feature r10 = com.alibaba.fastjson.parser.Feature.AllowUnQuotedFieldNames     // Catch:{ all -> 0x0072 }
            int r10 = r10.mask     // Catch:{ all -> 0x0072 }
            r9 = r9 & r10
            if (r9 != 0) goto L_0x00f3
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = "syntax error"
            r9.<init>(r10)     // Catch:{ all -> 0x0072 }
            throw r9     // Catch:{ all -> 0x0072 }
        L_0x00f3:
            com.alibaba.fastjson.parser.SymbolTable r9 = r12.symbolTable     // Catch:{ all -> 0x0072 }
            java.lang.String r4 = r5.scanSymbolUnQuoted(r9)     // Catch:{ all -> 0x0072 }
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            r9 = 58
            if (r0 == r9) goto L_0x0127
            com.alibaba.fastjson.JSONException r9 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r10.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = "expect ':' at "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            int r11 = r5.pos     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.String r11 = ", actual "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0072 }
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x0072 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0072 }
            r9.<init>(r10)     // Catch:{ all -> 0x0072 }
            throw r9     // Catch:{ all -> 0x0072 }
        L_0x0127:
            r5.next()     // Catch:{ all -> 0x0072 }
            r5.skipWhitespace()     // Catch:{ all -> 0x0072 }
            char r0 = r5.f339ch     // Catch:{ all -> 0x0072 }
            r9 = 0
            r5.f341sp = r9     // Catch:{ all -> 0x0072 }
            java.lang.String r9 = "@type"
            if (r4 != r9) goto L_0x018c
            com.alibaba.fastjson.parser.Feature r9 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x0072 }
            boolean r9 = r5.isEnabled(r9)     // Catch:{ all -> 0x0072 }
            if (r9 != 0) goto L_0x018c
            com.alibaba.fastjson.parser.SymbolTable r9 = r12.symbolTable     // Catch:{ all -> 0x0072 }
            r10 = 34
            java.lang.String r7 = r5.scanSymbol(r9, r10)     // Catch:{ all -> 0x0072 }
            com.alibaba.fastjson.parser.ParserConfig r9 = r12.config     // Catch:{ all -> 0x0072 }
            java.lang.ClassLoader r9 = r9.defaultClassLoader     // Catch:{ all -> 0x0072 }
            java.lang.Class r1 = com.alibaba.fastjson.util.TypeUtils.loadClass(r7, r9)     // Catch:{ all -> 0x0072 }
            java.lang.Class r9 = r13.getClass()     // Catch:{ all -> 0x0072 }
            if (r1 != r9) goto L_0x0169
            r9 = 16
            r5.nextToken(r9)     // Catch:{ all -> 0x0072 }
            int r9 = r5.token     // Catch:{ all -> 0x0072 }
            r10 = 13
            if (r9 != r10) goto L_0x0025
            r9 = 16
            r5.nextToken(r9)     // Catch:{ all -> 0x0072 }
            r12.setContext(r2)
            goto L_0x0089
        L_0x0169:
            com.alibaba.fastjson.parser.ParserConfig r9 = r12.config     // Catch:{ all -> 0x0072 }
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = r9.getDeserializer(r1)     // Catch:{ all -> 0x0072 }
            r9 = 16
            r5.nextToken(r9)     // Catch:{ all -> 0x0072 }
            r9 = 2
            r12.resolveStatus = r9     // Catch:{ all -> 0x0072 }
            if (r2 == 0) goto L_0x0180
            boolean r9 = r15 instanceof java.lang.Integer     // Catch:{ all -> 0x0072 }
            if (r9 != 0) goto L_0x0180
            r12.popContext()     // Catch:{ all -> 0x0072 }
        L_0x0180:
            java.lang.Object r9 = r3.deserialze(r12, r1, r15)     // Catch:{ all -> 0x0072 }
            java.util.Map r9 = (java.util.Map) r9     // Catch:{ all -> 0x0072 }
            r12.setContext(r2)
            r13 = r9
            goto L_0x0089
        L_0x018c:
            r5.nextToken()     // Catch:{ all -> 0x0072 }
            r12.setContext(r2)     // Catch:{ all -> 0x0072 }
            int r9 = r5.token     // Catch:{ all -> 0x0072 }
            r10 = 8
            if (r9 != r10) goto L_0x01b9
            r8 = 0
            r5.nextToken()     // Catch:{ all -> 0x0072 }
        L_0x019c:
            r13.put(r4, r8)     // Catch:{ all -> 0x0072 }
            int r9 = r12.resolveStatus     // Catch:{ all -> 0x0072 }
            r10 = 1
            if (r9 != r10) goto L_0x01a7
            r12.checkMapResolve(r13, r4)     // Catch:{ all -> 0x0072 }
        L_0x01a7:
            r12.setContext(r2, r8, r4)     // Catch:{ all -> 0x0072 }
            int r6 = r5.token     // Catch:{ all -> 0x0072 }
            r9 = 20
            if (r6 == r9) goto L_0x01b4
            r9 = 15
            if (r6 != r9) goto L_0x01be
        L_0x01b4:
            r12.setContext(r2)
            goto L_0x0089
        L_0x01b9:
            java.lang.Object r8 = r12.parseObject((java.lang.reflect.Type) r14, (java.lang.Object) r4)     // Catch:{ all -> 0x0072 }
            goto L_0x019c
        L_0x01be:
            r9 = 13
            if (r6 != r9) goto L_0x0025
            r5.nextToken()     // Catch:{ all -> 0x0072 }
            r12.setContext(r2)
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.Object):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        r5 = null;
        r4.nextTokenWithChar(':');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        if (r4.token != 4) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        r7 = r4.stringVal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        if ("..".equals(r7) == false) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r5 = r1.parent.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0079, code lost:
        r4.nextToken(13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0082, code lost:
        if (r4.token == 13) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008b, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0097, code lost:
        if ("$".equals(r7) == false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0099, code lost:
        r8 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009c, code lost:
        if (r8.parent == null) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009e, code lost:
        r8 = r8.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a1, code lost:
        r5 = r8.object;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a4, code lost:
        r15.addResolveTask(new com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask(r1, r7));
        r15.resolveStatus = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cc, code lost:
        throw new com.alibaba.fastjson.JSONException("illegal ref, " + com.alibaba.fastjson.parser.JSONToken.name(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cd, code lost:
        r4.nextToken(16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d2, code lost:
        r15.setContext(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseMap(com.alibaba.fastjson.parser.DefaultJSONParser r15, java.util.Map<java.lang.Object, java.lang.Object> r16, java.lang.reflect.Type r17, java.lang.reflect.Type r18, java.lang.Object r19) {
        /*
            com.alibaba.fastjson.parser.JSONLexer r4 = r15.lexer
            int r9 = r4.token
            r12 = 12
            if (r9 == r12) goto L_0x0029
            r12 = 16
            if (r9 == r12) goto L_0x0029
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "syntax error, expect {, actual "
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = com.alibaba.fastjson.parser.JSONToken.name(r9)
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x0029:
            com.alibaba.fastjson.parser.ParserConfig r12 = r15.config
            r0 = r17
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r3 = r12.getDeserializer(r0)
            com.alibaba.fastjson.parser.ParserConfig r12 = r15.config
            r0 = r18
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r11 = r12.getDeserializer(r0)
            r4.nextToken()
            com.alibaba.fastjson.parser.ParseContext r1 = r15.contex
        L_0x003e:
            int r9 = r4.token     // Catch:{ all -> 0x008c }
            r12 = 13
            if (r9 != r12) goto L_0x004d
            r12 = 16
            r4.nextToken(r12)     // Catch:{ all -> 0x008c }
            r15.setContext(r1)
        L_0x004c:
            return r16
        L_0x004d:
            r12 = 4
            if (r9 != r12) goto L_0x00d9
            boolean r12 = r4.isRef()     // Catch:{ all -> 0x008c }
            if (r12 == 0) goto L_0x00d9
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x008c }
            boolean r12 = r4.isEnabled(r12)     // Catch:{ all -> 0x008c }
            if (r12 != 0) goto L_0x00d9
            r5 = 0
            r12 = 58
            r4.nextTokenWithChar(r12)     // Catch:{ all -> 0x008c }
            int r12 = r4.token     // Catch:{ all -> 0x008c }
            r13 = 4
            if (r12 != r13) goto L_0x00b0
            java.lang.String r7 = r4.stringVal()     // Catch:{ all -> 0x008c }
            java.lang.String r12 = ".."
            boolean r12 = r12.equals(r7)     // Catch:{ all -> 0x008c }
            if (r12 == 0) goto L_0x0091
            com.alibaba.fastjson.parser.ParseContext r6 = r1.parent     // Catch:{ all -> 0x008c }
            java.lang.Object r5 = r6.object     // Catch:{ all -> 0x008c }
        L_0x0079:
            r12 = 13
            r4.nextToken(r12)     // Catch:{ all -> 0x008c }
            int r12 = r4.token     // Catch:{ all -> 0x008c }
            r13 = 13
            if (r12 == r13) goto L_0x00cd
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x008c }
            java.lang.String r13 = "illegal ref"
            r12.<init>(r13)     // Catch:{ all -> 0x008c }
            throw r12     // Catch:{ all -> 0x008c }
        L_0x008c:
            r12 = move-exception
            r15.setContext(r1)
            throw r12
        L_0x0091:
            java.lang.String r12 = "$"
            boolean r12 = r12.equals(r7)     // Catch:{ all -> 0x008c }
            if (r12 == 0) goto L_0x00a4
            r8 = r1
        L_0x009a:
            com.alibaba.fastjson.parser.ParseContext r12 = r8.parent     // Catch:{ all -> 0x008c }
            if (r12 == 0) goto L_0x00a1
            com.alibaba.fastjson.parser.ParseContext r8 = r8.parent     // Catch:{ all -> 0x008c }
            goto L_0x009a
        L_0x00a1:
            java.lang.Object r5 = r8.object     // Catch:{ all -> 0x008c }
            goto L_0x0079
        L_0x00a4:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r12 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x008c }
            r12.<init>(r1, r7)     // Catch:{ all -> 0x008c }
            r15.addResolveTask(r12)     // Catch:{ all -> 0x008c }
            r12 = 1
            r15.resolveStatus = r12     // Catch:{ all -> 0x008c }
            goto L_0x0079
        L_0x00b0:
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r13.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r14 = "illegal ref, "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x008c }
            java.lang.String r14 = com.alibaba.fastjson.parser.JSONToken.name(r9)     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x008c }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x008c }
            r12.<init>(r13)     // Catch:{ all -> 0x008c }
            throw r12     // Catch:{ all -> 0x008c }
        L_0x00cd:
            r12 = 16
            r4.nextToken(r12)     // Catch:{ all -> 0x008c }
            r15.setContext(r1)
            r16 = r5
            goto L_0x004c
        L_0x00d9:
            int r12 = r16.size()     // Catch:{ all -> 0x008c }
            if (r12 != 0) goto L_0x0111
            r12 = 4
            if (r9 != r12) goto L_0x0111
            java.lang.String r12 = "@type"
            java.lang.String r13 = r4.stringVal()     // Catch:{ all -> 0x008c }
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x008c }
            if (r12 == 0) goto L_0x0111
            com.alibaba.fastjson.parser.Feature r12 = com.alibaba.fastjson.parser.Feature.DisableSpecialKeyDetect     // Catch:{ all -> 0x008c }
            boolean r12 = r4.isEnabled(r12)     // Catch:{ all -> 0x008c }
            if (r12 != 0) goto L_0x0111
            r12 = 58
            r4.nextTokenWithChar(r12)     // Catch:{ all -> 0x008c }
            r12 = 16
            r4.nextToken(r12)     // Catch:{ all -> 0x008c }
            int r12 = r4.token     // Catch:{ all -> 0x008c }
            r13 = 13
            if (r12 != r13) goto L_0x010e
            r4.nextToken()     // Catch:{ all -> 0x008c }
            r15.setContext(r1)
            goto L_0x004c
        L_0x010e:
            r4.nextToken()     // Catch:{ all -> 0x008c }
        L_0x0111:
            r12 = 0
            r0 = r17
            java.lang.Object r2 = r3.deserialze(r15, r0, r12)     // Catch:{ all -> 0x008c }
            int r12 = r4.token     // Catch:{ all -> 0x008c }
            r13 = 17
            if (r12 == r13) goto L_0x0139
            com.alibaba.fastjson.JSONException r12 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r13.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r14 = "syntax error, expect :, actual "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x008c }
            int r14 = r4.token     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x008c }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x008c }
            r12.<init>(r13)     // Catch:{ all -> 0x008c }
            throw r12     // Catch:{ all -> 0x008c }
        L_0x0139:
            r4.nextToken()     // Catch:{ all -> 0x008c }
            r0 = r18
            java.lang.Object r10 = r11.deserialze(r15, r0, r2)     // Catch:{ all -> 0x008c }
            int r12 = r15.resolveStatus     // Catch:{ all -> 0x008c }
            r13 = 1
            if (r12 != r13) goto L_0x014c
            r0 = r16
            r15.checkMapResolve(r0, r2)     // Catch:{ all -> 0x008c }
        L_0x014c:
            r0 = r16
            r0.put(r2, r10)     // Catch:{ all -> 0x008c }
            int r12 = r4.token     // Catch:{ all -> 0x008c }
            r13 = 16
            if (r12 != r13) goto L_0x003e
            r4.nextToken()     // Catch:{ all -> 0x008c }
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.MapDeserializer.parseMap(com.alibaba.fastjson.parser.DefaultJSONParser, java.util.Map, java.lang.reflect.Type, java.lang.reflect.Type, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Map<?, ?> createMap(Type type) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class || type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type == JSONObject.class) {
            return new JSONObject();
        }
        if (type instanceof ParameterizedType) {
            return createMap(((ParameterizedType) type).getRawType());
        }
        Class<?> clazz = (Class) type;
        if (clazz.isInterface()) {
            throw new JSONException("unsupport type " + type);
        }
        try {
            return (Map) clazz.newInstance();
        } catch (Exception e) {
            throw new JSONException("unsupport type " + type, e);
        }
    }
}
