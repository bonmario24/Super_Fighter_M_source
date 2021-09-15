package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JavaBeanDeserializer implements ObjectDeserializer {
    public final JavaBeanInfo beanInfo;
    private final Class<?> clazz;
    private final FieldDeserializer[] fieldDeserializers;
    private final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz2, Type type) {
        this(config, clazz2, type, JavaBeanInfo.build(clazz2, clazz2.getModifiers(), type, false, true, true, true));
    }

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz2, Type type, JavaBeanInfo beanInfo2) {
        this.clazz = clazz2;
        this.beanInfo = beanInfo2;
        this.sortedFieldDeserializers = new FieldDeserializer[beanInfo2.sortedFields.length];
        int size = beanInfo2.sortedFields.length;
        for (int i = 0; i < size; i++) {
            this.sortedFieldDeserializers[i] = config.createFieldDeserializer(config, clazz2, beanInfo2.sortedFields[i]);
        }
        this.fieldDeserializers = new FieldDeserializer[beanInfo2.fields.length];
        int size2 = beanInfo2.fields.length;
        for (int i2 = 0; i2 < size2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(beanInfo2.fields[i2].name);
        }
    }

    /* access modifiers changed from: protected */
    public Object createInstance(DefaultJSONParser parser, Type type) {
        Object object;
        boolean ordred;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            Class<?> clazz2 = (Class) type;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if ((parser.lexer.features & Feature.OrderedField.mask) != 0) {
                ordred = true;
            } else {
                ordred = false;
            }
            return Proxy.newProxyInstance(loader, new Class[]{clazz2}, new JSONObject(ordred));
        } else if (this.beanInfo.defaultConstructor == null) {
            return null;
        } else {
            try {
                Constructor<?> constructor = this.beanInfo.defaultConstructor;
                if (this.beanInfo.defaultConstructorParameterSize == 0) {
                    object = constructor.newInstance(new Object[0]);
                } else {
                    object = constructor.newInstance(new Object[]{parser.contex.object});
                }
                if (!(parser == null || (parser.lexer.features & Feature.InitStringFieldAsEmpty.mask) == 0)) {
                    for (FieldInfo fieldInfo : this.beanInfo.fields) {
                        if (fieldInfo.fieldClass == String.class) {
                            fieldInfo.set(object, "");
                        }
                    }
                }
                return object;
            } catch (Exception e) {
                throw new JSONException("create instance error, class " + this.clazz.getName(), e);
            }
        }
    }

    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return deserialze(parser, type, fieldName, (Object) null);
    }

    private <T> T deserialzeArrayMapping(DefaultJSONParser parser, Type type, Object fieldName, Object object) {
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        Enum value;
        char charAt6;
        char charAt7;
        char charAt8;
        char charAt9;
        char charAt10;
        char charAt11;
        char charAt12;
        String strVal;
        char charAt13;
        char charAt14;
        char charAt15;
        char charAt16;
        JSONLexer lexer = parser.lexer;
        T createInstance = createInstance(parser, type);
        int size = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < size) {
            char seperator = i == size + -1 ? ']' : ',';
            FieldDeserializer fieldDeser = this.sortedFieldDeserializers[i];
            FieldInfo fieldInfo = fieldDeser.fieldInfo;
            Class<?> fieldClass = fieldInfo.fieldClass;
            try {
                if (fieldClass == Integer.TYPE) {
                    int intValue = (int) lexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setInt(createInstance, intValue);
                    } else {
                        fieldDeser.setValue((Object) createInstance, (Object) new Integer(intValue));
                    }
                    if (lexer.f339ch == ',') {
                        int index = lexer.f338bp + 1;
                        lexer.f338bp = index;
                        if (index >= lexer.len) {
                            charAt16 = JSONLexer.EOI;
                        } else {
                            charAt16 = lexer.text.charAt(index);
                        }
                        lexer.f339ch = charAt16;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index2 = lexer.f338bp + 1;
                        lexer.f338bp = index2;
                        if (index2 >= lexer.len) {
                            charAt15 = JSONLexer.EOI;
                        } else {
                            charAt15 = lexer.text.charAt(index2);
                        }
                        lexer.f339ch = charAt15;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else if (fieldClass == String.class) {
                    if (lexer.f339ch == '\"') {
                        strVal = lexer.scanStringValue('\"');
                    } else if (lexer.f339ch != 'n' || !lexer.text.startsWith("null", lexer.f338bp)) {
                        throw new JSONException("not match string. feild : " + fieldName);
                    } else {
                        lexer.f338bp += 4;
                        int index3 = lexer.f338bp;
                        if (lexer.f338bp >= lexer.len) {
                            charAt12 = JSONLexer.EOI;
                        } else {
                            charAt12 = lexer.text.charAt(index3);
                        }
                        lexer.f339ch = charAt12;
                        strVal = null;
                    }
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.set(createInstance, strVal);
                    } else {
                        fieldDeser.setValue((Object) createInstance, (Object) strVal);
                    }
                    if (lexer.f339ch == ',') {
                        int index4 = lexer.f338bp + 1;
                        lexer.f338bp = index4;
                        if (index4 >= lexer.len) {
                            charAt14 = JSONLexer.EOI;
                        } else {
                            charAt14 = lexer.text.charAt(index4);
                        }
                        lexer.f339ch = charAt14;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index5 = lexer.f338bp + 1;
                        lexer.f338bp = index5;
                        if (index5 >= lexer.len) {
                            charAt13 = JSONLexer.EOI;
                        } else {
                            charAt13 = lexer.text.charAt(index5);
                        }
                        lexer.f339ch = charAt13;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else if (fieldClass == Long.TYPE) {
                    long longValue = lexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setLong(createInstance, longValue);
                    } else {
                        fieldDeser.setValue((Object) createInstance, (Object) new Long(longValue));
                    }
                    if (lexer.f339ch == ',') {
                        int index6 = lexer.f338bp + 1;
                        lexer.f338bp = index6;
                        if (index6 >= lexer.len) {
                            charAt11 = JSONLexer.EOI;
                        } else {
                            charAt11 = lexer.text.charAt(index6);
                        }
                        lexer.f339ch = charAt11;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index7 = lexer.f338bp + 1;
                        lexer.f338bp = index7;
                        if (index7 >= lexer.len) {
                            charAt10 = JSONLexer.EOI;
                        } else {
                            charAt10 = lexer.text.charAt(index7);
                        }
                        lexer.f339ch = charAt10;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else if (fieldClass == Boolean.TYPE) {
                    boolean booleanValue = lexer.scanBoolean();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setBoolean(createInstance, booleanValue);
                    } else {
                        fieldDeser.setValue((Object) createInstance, (Object) Boolean.valueOf(booleanValue));
                    }
                    if (lexer.f339ch == ',') {
                        int index8 = lexer.f338bp + 1;
                        lexer.f338bp = index8;
                        if (index8 >= lexer.len) {
                            charAt9 = JSONLexer.EOI;
                        } else {
                            charAt9 = lexer.text.charAt(index8);
                        }
                        lexer.f339ch = charAt9;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index9 = lexer.f338bp + 1;
                        lexer.f338bp = index9;
                        if (index9 >= lexer.len) {
                            charAt8 = JSONLexer.EOI;
                        } else {
                            charAt8 = lexer.text.charAt(index9);
                        }
                        lexer.f339ch = charAt8;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else if (fieldClass.isEnum()) {
                    char ch = lexer.f339ch;
                    if (ch == '\"') {
                        String enumName = lexer.scanSymbol(parser.symbolTable);
                        if (enumName == null) {
                            value = null;
                        } else {
                            value = Enum.valueOf(fieldClass, enumName);
                        }
                    } else if (ch < '0' || ch > '9') {
                        throw new JSONException("illegal enum." + lexer.info());
                    } else {
                        value = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeser).getFieldValueDeserilizer(parser.config)).values[(int) lexer.scanLongValue()];
                    }
                    fieldDeser.setValue((Object) createInstance, (Object) value);
                    if (lexer.f339ch == ',') {
                        int index10 = lexer.f338bp + 1;
                        lexer.f338bp = index10;
                        if (index10 >= lexer.len) {
                            charAt7 = JSONLexer.EOI;
                        } else {
                            charAt7 = lexer.text.charAt(index10);
                        }
                        lexer.f339ch = charAt7;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index11 = lexer.f338bp + 1;
                        lexer.f338bp = index11;
                        if (index11 >= lexer.len) {
                            charAt6 = JSONLexer.EOI;
                        } else {
                            charAt6 = lexer.text.charAt(index11);
                        }
                        lexer.f339ch = charAt6;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else if (fieldClass == Date.class && lexer.f339ch == '1') {
                    fieldDeser.setValue((Object) createInstance, (Object) new Date(lexer.scanLongValue()));
                    if (lexer.f339ch == ',') {
                        int index12 = lexer.f338bp + 1;
                        lexer.f338bp = index12;
                        if (index12 >= lexer.len) {
                            charAt5 = JSONLexer.EOI;
                        } else {
                            charAt5 = lexer.text.charAt(index12);
                        }
                        lexer.f339ch = charAt5;
                        lexer.token = 16;
                    } else if (lexer.f339ch == ']') {
                        int index13 = lexer.f338bp + 1;
                        lexer.f338bp = index13;
                        if (index13 >= lexer.len) {
                            charAt4 = JSONLexer.EOI;
                        } else {
                            charAt4 = lexer.text.charAt(index13);
                        }
                        lexer.f339ch = charAt4;
                        lexer.token = 15;
                    } else {
                        lexer.nextToken();
                    }
                } else {
                    if (lexer.f339ch == '[') {
                        int index14 = lexer.f338bp + 1;
                        lexer.f338bp = index14;
                        if (index14 >= lexer.len) {
                            charAt3 = JSONLexer.EOI;
                        } else {
                            charAt3 = lexer.text.charAt(index14);
                        }
                        lexer.f339ch = charAt3;
                        lexer.token = 14;
                    } else if (lexer.f339ch == '{') {
                        int index15 = lexer.f338bp + 1;
                        lexer.f338bp = index15;
                        if (index15 >= lexer.len) {
                            charAt2 = JSONLexer.EOI;
                        } else {
                            charAt2 = lexer.text.charAt(index15);
                        }
                        lexer.f339ch = charAt2;
                        lexer.token = 12;
                    } else {
                        lexer.nextToken();
                    }
                    fieldDeser.parseField(parser, createInstance, fieldInfo.fieldType, (Map<String, Object>) null);
                    if (seperator == ']') {
                        if (lexer.token != 15) {
                            throw new JSONException("syntax error");
                        }
                    } else if (seperator == ',' && lexer.token != 16) {
                        throw new JSONException("syntax error");
                    }
                }
                i++;
            } catch (IllegalAccessException e) {
                throw new JSONException("set " + fieldInfo.name + "error", e);
            }
        }
        if (lexer.f339ch == ',') {
            int index16 = lexer.f338bp + 1;
            lexer.f338bp = index16;
            if (index16 >= lexer.len) {
                charAt = JSONLexer.EOI;
            } else {
                charAt = lexer.text.charAt(index16);
            }
            lexer.f339ch = charAt;
            lexer.token = 16;
        } else {
            lexer.nextToken();
        }
        return createInstance;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v0, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v2, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v4, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v6, resolved type: java.lang.Double} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v10, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v11, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v12, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v213, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v13, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v14, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v15, resolved type: java.lang.Boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v16, resolved type: java.lang.Enum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v18, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x044a, code lost:
        r13 = getSeeAlso(r49.config, r48.beanInfo, r45);
        r46 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x045c, code lost:
        if (r13 != null) goto L_0x0486;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x045e, code lost:
        r46 = com.alibaba.fastjson.util.TypeUtils.loadClass(r45, r49.config.defaultClassLoader);
        r18 = com.alibaba.fastjson.util.TypeUtils.getClass(r50);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x046e, code lost:
        if (r18 == null) goto L_0x047c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x0470, code lost:
        if (r46 == null) goto L_0x049f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x047a, code lost:
        if (r18.isAssignableFrom(r46) == false) goto L_0x049f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x047c, code lost:
        r13 = r49.config.getDeserializer(r46);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0486, code lost:
        r4 = r13.deserialze(r49, r46, r51);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0490, code lost:
        if (r11 == null) goto L_0x0496;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0492, code lost:
        r11.object = r52;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0496, code lost:
        r49.setContext(r12);
        r37 = r52;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x04a6, code lost:
        throw new com.alibaba.fastjson.JSONException("type not match");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x054c, code lost:
        if (r19 == java.lang.Double.class) goto L_0x054e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0578, code lost:
        r17 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0597, code lost:
        throw new com.alibaba.fastjson.JSONException("set property error, " + r22.name, r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x069b, code lost:
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r33.token));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x06ed, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0710, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error, " + r48.beanInfo.creatorConstructor.toGenericString(), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0727, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x074a, code lost:
        throw new com.alibaba.fastjson.JSONException("create factory method error, " + r48.beanInfo.factoryMethod.toString(), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0107, code lost:
        r4 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:15:0x004a, B:287:0x0556, B:355:0x06d2, B:367:0x071b] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r49, java.lang.reflect.Type r50, java.lang.Object r51, T r52) {
        /*
            r48 = this;
            java.lang.Class<com.alibaba.fastjson.JSON> r4 = com.alibaba.fastjson.JSON.class
            r0 = r50
            if (r0 == r4) goto L_0x000c
            java.lang.Class<com.alibaba.fastjson.JSONObject> r4 = com.alibaba.fastjson.JSONObject.class
            r0 = r50
            if (r0 != r4) goto L_0x0013
        L_0x000c:
            java.lang.Object r4 = r49.parse()
            r37 = r52
        L_0x0012:
            return r4
        L_0x0013:
            r0 = r49
            com.alibaba.fastjson.parser.JSONLexer r0 = r0.lexer
            r33 = r0
            r0 = r33
            int r0 = r0.token
            r44 = r0
            r4 = 8
            r0 = r44
            if (r0 != r4) goto L_0x0030
            r4 = 16
            r0 = r33
            r0.nextToken(r4)
            r4 = 0
            r37 = r52
            goto L_0x0012
        L_0x0030:
            r0 = r33
            boolean r14 = r0.disableCircularReferenceDetect
            r0 = r49
            com.alibaba.fastjson.parser.ParseContext r12 = r0.contex
            if (r52 == 0) goto L_0x003e
            if (r12 == 0) goto L_0x003e
            com.alibaba.fastjson.parser.ParseContext r12 = r12.parent
        L_0x003e:
            r11 = 0
            r9 = 0
            r4 = 13
            r0 = r44
            if (r0 != r4) goto L_0x0063
            r4 = 16
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0107 }
            if (r52 != 0) goto L_0x0053
            java.lang.Object r52 = r48.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r49, (java.lang.reflect.Type) r50)     // Catch:{ all -> 0x0107 }
        L_0x0053:
            if (r11 == 0) goto L_0x0059
            r0 = r52
            r11.object = r0
        L_0x0059:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            r4 = r52
            goto L_0x0012
        L_0x0063:
            r4 = 14
            r0 = r44
            if (r0 != r4) goto L_0x0095
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r4 = r0.beanInfo     // Catch:{ all -> 0x0107 }
            boolean r4 = r4.supportBeanToArray     // Catch:{ all -> 0x0107 }
            if (r4 != 0) goto L_0x007c
            r0 = r33
            int r4 = r0.features     // Catch:{ all -> 0x0107 }
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.SupportArrayToBean     // Catch:{ all -> 0x0107 }
            int r5 = r5.mask     // Catch:{ all -> 0x0107 }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0092
        L_0x007c:
            r32 = 1
        L_0x007e:
            if (r32 == 0) goto L_0x0095
            java.lang.Object r4 = r48.deserialzeArrayMapping(r49, r50, r51, r52)     // Catch:{ all -> 0x0107 }
            if (r11 == 0) goto L_0x008a
            r0 = r52
            r11.object = r0
        L_0x008a:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            goto L_0x0012
        L_0x0092:
            r32 = 0
            goto L_0x007e
        L_0x0095:
            r4 = 12
            r0 = r44
            if (r0 == r4) goto L_0x0114
            r4 = 16
            r0 = r44
            if (r0 == r4) goto L_0x0114
            boolean r4 = r33.isBlankInput()     // Catch:{ all -> 0x0107 }
            if (r4 == 0) goto L_0x00b7
            r4 = 0
            if (r11 == 0) goto L_0x00ae
            r0 = r52
            r11.object = r0
        L_0x00ae:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            goto L_0x0012
        L_0x00b7:
            r4 = 4
            r0 = r44
            if (r0 != r4) goto L_0x00d9
            java.lang.String r43 = r33.stringVal()     // Catch:{ all -> 0x0107 }
            int r4 = r43.length()     // Catch:{ all -> 0x0107 }
            if (r4 != 0) goto L_0x00d9
            r33.nextToken()     // Catch:{ all -> 0x0107 }
            r4 = 0
            if (r11 == 0) goto L_0x00d0
            r0 = r52
            r11.object = r0
        L_0x00d0:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            goto L_0x0012
        L_0x00d9:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0107 }
            r4.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = "syntax error, expect {, actual "
            java.lang.StringBuffer r4 = r4.append(r5)     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r33.info()     // Catch:{ all -> 0x0107 }
            java.lang.StringBuffer r10 = r4.append(r5)     // Catch:{ all -> 0x0107 }
            r0 = r51
            boolean r4 = r0 instanceof java.lang.String     // Catch:{ all -> 0x0107 }
            if (r4 == 0) goto L_0x00fd
            java.lang.String r4 = ", fieldName "
            java.lang.StringBuffer r4 = r10.append(r4)     // Catch:{ all -> 0x0107 }
            r0 = r51
            r4.append(r0)     // Catch:{ all -> 0x0107 }
        L_0x00fd:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r10.toString()     // Catch:{ all -> 0x0107 }
            r4.<init>(r5)     // Catch:{ all -> 0x0107 }
            throw r4     // Catch:{ all -> 0x0107 }
        L_0x0107:
            r4 = move-exception
        L_0x0108:
            if (r11 == 0) goto L_0x010e
            r0 = r52
            r11.object = r0
        L_0x010e:
            r0 = r49
            r0.setContext(r12)
            throw r4
        L_0x0114:
            r0 = r49
            int r4 = r0.resolveStatus     // Catch:{ all -> 0x0107 }
            r5 = 2
            if (r4 != r5) goto L_0x0120
            r4 = 0
            r0 = r49
            r0.resolveStatus = r4     // Catch:{ all -> 0x0107 }
        L_0x0120:
            r21 = 0
            r0 = r48
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r0.sortedFieldDeserializers     // Catch:{ all -> 0x0107 }
            int r0 = r4.length     // Catch:{ all -> 0x0107 }
            r42 = r0
            r30 = r9
        L_0x012b:
            r6 = 0
            r20 = 0
            r22 = 0
            r19 = 0
            r0 = r21
            r1 = r42
            if (r0 >= r1) goto L_0x014a
            r0 = r48
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r0.sortedFieldDeserializers     // Catch:{ all -> 0x0347 }
            r20 = r4[r21]     // Catch:{ all -> 0x0347 }
            r0 = r20
            com.alibaba.fastjson.util.FieldInfo r0 = r0.fieldInfo     // Catch:{ all -> 0x0347 }
            r22 = r0
            r0 = r22
            java.lang.Class<?> r0 = r0.fieldClass     // Catch:{ all -> 0x0347 }
            r19 = r0
        L_0x014a:
            r35 = 0
            r47 = 0
            r23 = 0
            r27 = 0
            r28 = 0
            r26 = 0
            r24 = 0
            if (r20 == 0) goto L_0x017e
            r0 = r22
            char[] r0 = r0.name_chars     // Catch:{ all -> 0x0347 }
            r36 = r0
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0347 }
            r0 = r19
            if (r0 == r4) goto L_0x016c
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            r0 = r19
            if (r0 != r4) goto L_0x01d5
        L_0x016c:
            r0 = r33
            r1 = r36
            int r27 = r0.scanFieldInt(r1)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x01c6
            r35 = 1
            r47 = 1
        L_0x017e:
            if (r35 != 0) goto L_0x04af
            r0 = r49
            com.alibaba.fastjson.parser.SymbolTable r4 = r0.symbolTable     // Catch:{ all -> 0x0347 }
            r0 = r33
            java.lang.String r6 = r0.scanSymbol(r4)     // Catch:{ all -> 0x0347 }
            if (r6 != 0) goto L_0x0308
            r0 = r33
            int r0 = r0.token     // Catch:{ all -> 0x0347 }
            r44 = r0
            r4 = 13
            r0 = r44
            if (r0 != r4) goto L_0x02f3
            r4 = 16
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0347 }
            r9 = r30
        L_0x01a1:
            if (r52 != 0) goto L_0x06dc
            if (r9 != 0) goto L_0x069c
            java.lang.Object r52 = r48.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r49, (java.lang.reflect.Type) r50)     // Catch:{ all -> 0x0107 }
            if (r11 != 0) goto L_0x01b5
            r0 = r49
            r1 = r52
            r2 = r51
            com.alibaba.fastjson.parser.ParseContext r11 = r0.setContext(r12, r1, r2)     // Catch:{ all -> 0x0107 }
        L_0x01b5:
            if (r11 == 0) goto L_0x01bb
            r0 = r52
            r11.object = r0
        L_0x01bb:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            r4 = r52
            goto L_0x0012
        L_0x01c6:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
        L_0x01cf:
            int r21 = r21 + 1
            r30 = r9
            goto L_0x012b
        L_0x01d5:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x0347 }
            r0 = r19
            if (r0 == r4) goto L_0x01e1
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            r0 = r19
            if (r0 != r4) goto L_0x01fe
        L_0x01e1:
            r0 = r33
            r1 = r36
            long r28 = r0.scanFieldLong(r1)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x01f4
            r35 = 1
            r47 = 1
            goto L_0x017e
        L_0x01f4:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x01fe:
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r0 = r19
            if (r0 != r4) goto L_0x0222
            r0 = r33
            r1 = r36
            java.lang.String r23 = r0.scanFieldString(r1)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x0218
            r35 = 1
            r47 = 1
            goto L_0x017e
        L_0x0218:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x0222:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0347 }
            r0 = r19
            if (r0 == r4) goto L_0x022e
            java.lang.Class<java.lang.Boolean> r4 = java.lang.Boolean.class
            r0 = r19
            if (r0 != r4) goto L_0x0250
        L_0x022e:
            r0 = r33
            r1 = r36
            boolean r4 = r0.scanFieldBoolean(r1)     // Catch:{ all -> 0x0347 }
            java.lang.Boolean r23 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x0246
            r35 = 1
            r47 = 1
            goto L_0x017e
        L_0x0246:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x0250:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x0347 }
            r0 = r19
            if (r0 == r4) goto L_0x025c
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r19
            if (r0 != r4) goto L_0x027b
        L_0x025c:
            r0 = r33
            r1 = r36
            float r26 = r0.scanFieldFloat(r1)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x0270
            r35 = 1
            r47 = 1
            goto L_0x017e
        L_0x0270:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x027b:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x0347 }
            r0 = r19
            if (r0 == r4) goto L_0x0287
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r19
            if (r0 != r4) goto L_0x02a6
        L_0x0287:
            r0 = r33
            r1 = r36
            double r24 = r0.scanFieldDouble(r1)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x029b
            r35 = 1
            r47 = 1
            goto L_0x017e
        L_0x029b:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x02a6:
            r0 = r22
            boolean r4 = r0.isEnum     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x02e5
            r0 = r49
            com.alibaba.fastjson.parser.ParserConfig r4 = r0.config     // Catch:{ all -> 0x0347 }
            r0 = r19
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r4 = r4.getDeserializer(r0)     // Catch:{ all -> 0x0347 }
            boolean r4 = r4 instanceof com.alibaba.fastjson.parser.EnumDeserializer     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x02e5
            r0 = r49
            com.alibaba.fastjson.parser.SymbolTable r4 = r0.symbolTable     // Catch:{ all -> 0x0347 }
            r0 = r33
            r1 = r36
            java.lang.String r16 = r0.scanFieldSymbol(r1, r4)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            if (r4 <= 0) goto L_0x02da
            r35 = 1
            r47 = 1
            r0 = r19
            r1 = r16
            java.lang.Enum r23 = java.lang.Enum.valueOf(r0, r1)     // Catch:{ all -> 0x0347 }
            goto L_0x017e
        L_0x02da:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0347 }
            r5 = -2
            if (r4 != r5) goto L_0x017e
            r9 = r30
            goto L_0x01cf
        L_0x02e5:
            r0 = r33
            r1 = r36
            boolean r4 = r0.matchField(r1)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x0753
            r35 = 1
            goto L_0x017e
        L_0x02f3:
            r4 = 16
            r0 = r44
            if (r0 != r4) goto L_0x0308
            r0 = r33
            int r4 = r0.features     // Catch:{ all -> 0x0347 }
            com.alibaba.fastjson.parser.Feature r5 = com.alibaba.fastjson.parser.Feature.AllowArbitraryCommas     // Catch:{ all -> 0x0347 }
            int r5 = r5.mask     // Catch:{ all -> 0x0347 }
            r4 = r4 & r5
            if (r4 == 0) goto L_0x0308
            r9 = r30
            goto L_0x01cf
        L_0x0308:
            java.lang.String r4 = "$ref"
            if (r4 != r6) goto L_0x0407
            r4 = 58
            r0 = r33
            r0.nextTokenWithChar(r4)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r0 = r0.token     // Catch:{ all -> 0x0347 }
            r44 = r0
            r4 = 4
            r0 = r44
            if (r0 != r4) goto L_0x03c9
            java.lang.String r40 = r33.stringVal()     // Catch:{ all -> 0x0347 }
            java.lang.String r4 = "@"
            r0 = r40
            boolean r4 = r4.equals(r0)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x034c
            java.lang.Object r0 = r12.object     // Catch:{ all -> 0x0347 }
            r52 = r0
        L_0x0330:
            r4 = 13
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0347 }
            r5 = 13
            if (r4 == r5) goto L_0x03e6
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0347 }
            java.lang.String r5 = "illegal ref"
            r4.<init>(r5)     // Catch:{ all -> 0x0347 }
            throw r4     // Catch:{ all -> 0x0347 }
        L_0x0347:
            r4 = move-exception
            r9 = r30
            goto L_0x0108
        L_0x034c:
            java.lang.String r4 = ".."
            r0 = r40
            boolean r4 = r4.equals(r0)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x037b
            com.alibaba.fastjson.parser.ParseContext r0 = r12.parent     // Catch:{ all -> 0x0347 }
            r39 = r0
            r0 = r39
            java.lang.Object r4 = r0.object     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x0367
            r0 = r39
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0347 }
            r52 = r0
            goto L_0x0330
        L_0x0367:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0347 }
            r0 = r39
            r1 = r40
            r4.<init>(r0, r1)     // Catch:{ all -> 0x0347 }
            r0 = r49
            r0.addResolveTask(r4)     // Catch:{ all -> 0x0347 }
            r4 = 1
            r0 = r49
            r0.resolveStatus = r4     // Catch:{ all -> 0x0347 }
            goto L_0x0330
        L_0x037b:
            java.lang.String r4 = "$"
            r0 = r40
            boolean r4 = r4.equals(r0)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x03b6
            r41 = r12
        L_0x0387:
            r0 = r41
            com.alibaba.fastjson.parser.ParseContext r4 = r0.parent     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x0394
            r0 = r41
            com.alibaba.fastjson.parser.ParseContext r0 = r0.parent     // Catch:{ all -> 0x0347 }
            r41 = r0
            goto L_0x0387
        L_0x0394:
            r0 = r41
            java.lang.Object r4 = r0.object     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x03a1
            r0 = r41
            java.lang.Object r0 = r0.object     // Catch:{ all -> 0x0347 }
            r52 = r0
            goto L_0x0330
        L_0x03a1:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0347 }
            r0 = r41
            r1 = r40
            r4.<init>(r0, r1)     // Catch:{ all -> 0x0347 }
            r0 = r49
            r0.addResolveTask(r4)     // Catch:{ all -> 0x0347 }
            r4 = 1
            r0 = r49
            r0.resolveStatus = r4     // Catch:{ all -> 0x0347 }
            goto L_0x0330
        L_0x03b6:
            com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask r4 = new com.alibaba.fastjson.parser.DefaultJSONParser$ResolveTask     // Catch:{ all -> 0x0347 }
            r0 = r40
            r4.<init>(r12, r0)     // Catch:{ all -> 0x0347 }
            r0 = r49
            r0.addResolveTask(r4)     // Catch:{ all -> 0x0347 }
            r4 = 1
            r0 = r49
            r0.resolveStatus = r4     // Catch:{ all -> 0x0347 }
            goto L_0x0330
        L_0x03c9:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0347 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0347 }
            r5.<init>()     // Catch:{ all -> 0x0347 }
            java.lang.String r7 = "illegal ref, "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0347 }
            java.lang.String r7 = com.alibaba.fastjson.parser.JSONToken.name(r44)     // Catch:{ all -> 0x0347 }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0347 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0347 }
            r4.<init>(r5)     // Catch:{ all -> 0x0347 }
            throw r4     // Catch:{ all -> 0x0347 }
        L_0x03e6:
            r4 = 16
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0347 }
            r0 = r49
            r1 = r52
            r2 = r51
            r0.setContext(r12, r1, r2)     // Catch:{ all -> 0x0347 }
            if (r11 == 0) goto L_0x03fc
            r0 = r52
            r11.object = r0
        L_0x03fc:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            r4 = r52
            goto L_0x0012
        L_0x0407:
            java.lang.String r4 = "@type"
            if (r4 != r6) goto L_0x04af
            r4 = 58
            r0 = r33
            r0.nextTokenWithChar(r4)     // Catch:{ all -> 0x0347 }
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0347 }
            r5 = 4
            if (r4 != r5) goto L_0x04a7
            java.lang.String r45 = r33.stringVal()     // Catch:{ all -> 0x0347 }
            r4 = 16
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0347 }
            r0 = r50
            boolean r4 = r0 instanceof java.lang.Class     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x044a
            r0 = r50
            java.lang.Class r0 = (java.lang.Class) r0     // Catch:{ all -> 0x0347 }
            r4 = r0
            java.lang.String r4 = r4.getName()     // Catch:{ all -> 0x0347 }
            r0 = r45
            boolean r4 = r0.equals(r4)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x044a
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0347 }
            r5 = 13
            if (r4 != r5) goto L_0x0753
            r33.nextToken()     // Catch:{ all -> 0x0347 }
            r9 = r30
            goto L_0x01a1
        L_0x044a:
            r0 = r49
            com.alibaba.fastjson.parser.ParserConfig r4 = r0.config     // Catch:{ all -> 0x0347 }
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r5 = r0.beanInfo     // Catch:{ all -> 0x0347 }
            r0 = r48
            r1 = r45
            com.alibaba.fastjson.parser.JavaBeanDeserializer r13 = r0.getSeeAlso(r4, r5, r1)     // Catch:{ all -> 0x0347 }
            r46 = 0
            if (r13 != 0) goto L_0x0486
            r0 = r49
            com.alibaba.fastjson.parser.ParserConfig r4 = r0.config     // Catch:{ all -> 0x0347 }
            java.lang.ClassLoader r4 = r4.defaultClassLoader     // Catch:{ all -> 0x0347 }
            r0 = r45
            java.lang.Class r46 = com.alibaba.fastjson.util.TypeUtils.loadClass(r0, r4)     // Catch:{ all -> 0x0347 }
            java.lang.Class r18 = com.alibaba.fastjson.util.TypeUtils.getClass(r50)     // Catch:{ all -> 0x0347 }
            if (r18 == 0) goto L_0x047c
            if (r46 == 0) goto L_0x049f
            r0 = r18
            r1 = r46
            boolean r4 = r0.isAssignableFrom(r1)     // Catch:{ all -> 0x0347 }
            if (r4 == 0) goto L_0x049f
        L_0x047c:
            r0 = r49
            com.alibaba.fastjson.parser.ParserConfig r4 = r0.config     // Catch:{ all -> 0x0347 }
            r0 = r46
            com.alibaba.fastjson.parser.deserializer.ObjectDeserializer r13 = r4.getDeserializer(r0)     // Catch:{ all -> 0x0347 }
        L_0x0486:
            r0 = r49
            r1 = r46
            r2 = r51
            java.lang.Object r4 = r13.deserialze(r0, r1, r2)     // Catch:{ all -> 0x0347 }
            if (r11 == 0) goto L_0x0496
            r0 = r52
            r11.object = r0
        L_0x0496:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            goto L_0x0012
        L_0x049f:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0347 }
            java.lang.String r5 = "type not match"
            r4.<init>(r5)     // Catch:{ all -> 0x0347 }
            throw r4     // Catch:{ all -> 0x0347 }
        L_0x04a7:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0347 }
            java.lang.String r5 = "syntax error"
            r4.<init>(r5)     // Catch:{ all -> 0x0347 }
            throw r4     // Catch:{ all -> 0x0347 }
        L_0x04af:
            if (r52 != 0) goto L_0x074f
            if (r30 != 0) goto L_0x074f
            java.lang.Object r52 = r48.createInstance((com.alibaba.fastjson.parser.DefaultJSONParser) r49, (java.lang.reflect.Type) r50)     // Catch:{ all -> 0x0347 }
            if (r52 != 0) goto L_0x074b
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ all -> 0x0347 }
            r0 = r48
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r0.fieldDeserializers     // Catch:{ all -> 0x0347 }
            int r4 = r4.length     // Catch:{ all -> 0x0347 }
            r9.<init>(r4)     // Catch:{ all -> 0x0347 }
        L_0x04c3:
            if (r14 != 0) goto L_0x04cf
            r0 = r49
            r1 = r52
            r2 = r51
            com.alibaba.fastjson.parser.ParseContext r11 = r0.setContext(r12, r1, r2)     // Catch:{ all -> 0x0107 }
        L_0x04cf:
            if (r35 == 0) goto L_0x0651
            if (r47 != 0) goto L_0x04f7
            r0 = r20
            r1 = r49
            r2 = r52
            r3 = r50
            r0.parseField(r1, r2, r3, r9)     // Catch:{ all -> 0x0107 }
        L_0x04de:
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0107 }
            r5 = 16
            if (r4 == r5) goto L_0x01cf
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0107 }
            r5 = 13
            if (r4 != r5) goto L_0x066c
            r4 = 16
            r0 = r33
            r0.nextToken(r4)     // Catch:{ all -> 0x0107 }
            goto L_0x01a1
        L_0x04f7:
            if (r52 != 0) goto L_0x0554
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x0107 }
            r0 = r19
            if (r0 == r4) goto L_0x0505
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            r0 = r19
            if (r0 != r4) goto L_0x051b
        L_0x0505:
            java.lang.Integer r23 = java.lang.Integer.valueOf(r27)     // Catch:{ all -> 0x0107 }
        L_0x0509:
            r0 = r22
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x0107 }
            r0 = r23
            r9.put(r4, r0)     // Catch:{ all -> 0x0107 }
        L_0x0512:
            r0 = r33
            int r4 = r0.matchStat     // Catch:{ all -> 0x0107 }
            r5 = 4
            if (r4 != r5) goto L_0x04de
            goto L_0x01a1
        L_0x051b:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ all -> 0x0107 }
            r0 = r19
            if (r0 == r4) goto L_0x0527
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            r0 = r19
            if (r0 != r4) goto L_0x052c
        L_0x0527:
            java.lang.Long r23 = java.lang.Long.valueOf(r28)     // Catch:{ all -> 0x0107 }
            goto L_0x0509
        L_0x052c:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ all -> 0x0107 }
            r0 = r19
            if (r0 == r4) goto L_0x0538
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r19
            if (r0 != r4) goto L_0x0542
        L_0x0538:
            java.lang.Float r23 = new java.lang.Float     // Catch:{ all -> 0x0107 }
            r0 = r23
            r1 = r26
            r0.<init>(r1)     // Catch:{ all -> 0x0107 }
            goto L_0x0509
        L_0x0542:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ all -> 0x0107 }
            r0 = r19
            if (r0 == r4) goto L_0x054e
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r19
            if (r0 != r4) goto L_0x0509
        L_0x054e:
            java.lang.Double r23 = new java.lang.Double     // Catch:{ all -> 0x0107 }
            r23.<init>(r24)     // Catch:{ all -> 0x0107 }
            goto L_0x0509
        L_0x0554:
            if (r23 != 0) goto L_0x0646
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 == r4) goto L_0x0562
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            r0 = r19
            if (r0 != r4) goto L_0x05a5
        L_0x0562:
            r0 = r22
            boolean r4 = r0.fieldAccess     // Catch:{ IllegalAccessException -> 0x0578 }
            if (r4 == 0) goto L_0x0598
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 != r4) goto L_0x0598
            r0 = r20
            r1 = r52
            r2 = r27
            r0.setValue((java.lang.Object) r1, (int) r2)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x0578:
            r17 = move-exception
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0107 }
            r5.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = "set property error, "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            r0 = r22
            java.lang.String r7 = r0.name     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0107 }
            r0 = r17
            r4.<init>(r5, r0)     // Catch:{ all -> 0x0107 }
            throw r4     // Catch:{ all -> 0x0107 }
        L_0x0598:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r27)     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r20
            r1 = r52
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x05a5:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 == r4) goto L_0x05b1
            java.lang.Class<java.lang.Long> r4 = java.lang.Long.class
            r0 = r19
            if (r0 != r4) goto L_0x05d5
        L_0x05b1:
            r0 = r22
            boolean r4 = r0.fieldAccess     // Catch:{ IllegalAccessException -> 0x0578 }
            if (r4 == 0) goto L_0x05c8
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 != r4) goto L_0x05c8
            r0 = r20
            r1 = r52
            r2 = r28
            r0.setValue((java.lang.Object) r1, (long) r2)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x05c8:
            java.lang.Long r4 = java.lang.Long.valueOf(r28)     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r20
            r1 = r52
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x05d5:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 == r4) goto L_0x05e1
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r0 = r19
            if (r0 != r4) goto L_0x0608
        L_0x05e1:
            r0 = r22
            boolean r4 = r0.fieldAccess     // Catch:{ IllegalAccessException -> 0x0578 }
            if (r4 == 0) goto L_0x05f8
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 != r4) goto L_0x05f8
            r0 = r20
            r1 = r52
            r2 = r26
            r0.setValue((java.lang.Object) r1, (float) r2)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x05f8:
            java.lang.Float r4 = new java.lang.Float     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r26
            r4.<init>(r0)     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r20
            r1 = r52
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x0608:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 == r4) goto L_0x0614
            java.lang.Class<java.lang.Double> r4 = java.lang.Double.class
            r0 = r19
            if (r0 != r4) goto L_0x063b
        L_0x0614:
            r0 = r22
            boolean r4 = r0.fieldAccess     // Catch:{ IllegalAccessException -> 0x0578 }
            if (r4 == 0) goto L_0x062b
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r19
            if (r0 != r4) goto L_0x062b
            r0 = r20
            r1 = r52
            r2 = r24
            r0.setValue((java.lang.Object) r1, (double) r2)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x062b:
            java.lang.Double r4 = new java.lang.Double     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r24
            r4.<init>(r0)     // Catch:{ IllegalAccessException -> 0x0578 }
            r0 = r20
            r1 = r52
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x063b:
            r0 = r20
            r1 = r52
            r2 = r23
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ IllegalAccessException -> 0x0578 }
            goto L_0x0512
        L_0x0646:
            r0 = r20
            r1 = r52
            r2 = r23
            r0.setValue((java.lang.Object) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x0107 }
            goto L_0x0512
        L_0x0651:
            r4 = r48
            r5 = r49
            r7 = r52
            r8 = r50
            boolean r34 = r4.parseField(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0107 }
            if (r34 != 0) goto L_0x04de
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0107 }
            r5 = 13
            if (r4 != r5) goto L_0x01cf
            r33.nextToken()     // Catch:{ all -> 0x0107 }
            goto L_0x01a1
        L_0x066c:
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0107 }
            r5 = 18
            if (r4 == r5) goto L_0x067b
            r0 = r33
            int r4 = r0.token     // Catch:{ all -> 0x0107 }
            r5 = 1
            if (r4 != r5) goto L_0x01cf
        L_0x067b:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0107 }
            r5.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = "syntax error, unexpect token "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            r0 = r33
            int r7 = r0.token     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = com.alibaba.fastjson.parser.JSONToken.name(r7)     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0107 }
            r4.<init>(r5)     // Catch:{ all -> 0x0107 }
            throw r4     // Catch:{ all -> 0x0107 }
        L_0x069c:
            r0 = r48
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r0.fieldDeserializers     // Catch:{ all -> 0x0107 }
            int r0 = r4.length     // Catch:{ all -> 0x0107 }
            r42 = r0
            r0 = r42
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0107 }
            r38 = r0
            r31 = 0
        L_0x06ab:
            r0 = r31
            r1 = r42
            if (r0 >= r1) goto L_0x06c8
            r0 = r48
            com.alibaba.fastjson.parser.deserializer.FieldDeserializer[] r4 = r0.fieldDeserializers     // Catch:{ all -> 0x0107 }
            r4 = r4[r31]     // Catch:{ all -> 0x0107 }
            com.alibaba.fastjson.util.FieldInfo r0 = r4.fieldInfo     // Catch:{ all -> 0x0107 }
            r22 = r0
            r0 = r22
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x0107 }
            java.lang.Object r4 = r9.get(r4)     // Catch:{ all -> 0x0107 }
            r38[r31] = r4     // Catch:{ all -> 0x0107 }
            int r31 = r31 + 1
            goto L_0x06ab
        L_0x06c8:
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r4 = r0.beanInfo     // Catch:{ all -> 0x0107 }
            java.lang.reflect.Constructor<?> r4 = r4.creatorConstructor     // Catch:{ all -> 0x0107 }
            if (r4 == 0) goto L_0x0711
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r4 = r0.beanInfo     // Catch:{ Exception -> 0x06ed }
            java.lang.reflect.Constructor<?> r4 = r4.creatorConstructor     // Catch:{ Exception -> 0x06ed }
            r0 = r38
            java.lang.Object r52 = r4.newInstance(r0)     // Catch:{ Exception -> 0x06ed }
        L_0x06dc:
            if (r11 == 0) goto L_0x06e2
            r0 = r52
            r11.object = r0
        L_0x06e2:
            r0 = r49
            r0.setContext(r12)
            r37 = r52
            r4 = r52
            goto L_0x0012
        L_0x06ed:
            r15 = move-exception
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0107 }
            r5.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = "create instance error, "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r7 = r0.beanInfo     // Catch:{ all -> 0x0107 }
            java.lang.reflect.Constructor<?> r7 = r7.creatorConstructor     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = r7.toGenericString()     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0107 }
            r4.<init>(r5, r15)     // Catch:{ all -> 0x0107 }
            throw r4     // Catch:{ all -> 0x0107 }
        L_0x0711:
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r4 = r0.beanInfo     // Catch:{ all -> 0x0107 }
            java.lang.reflect.Method r4 = r4.factoryMethod     // Catch:{ all -> 0x0107 }
            if (r4 == 0) goto L_0x06dc
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r4 = r0.beanInfo     // Catch:{ Exception -> 0x0727 }
            java.lang.reflect.Method r4 = r4.factoryMethod     // Catch:{ Exception -> 0x0727 }
            r5 = 0
            r0 = r38
            java.lang.Object r52 = r4.invoke(r5, r0)     // Catch:{ Exception -> 0x0727 }
            goto L_0x06dc
        L_0x0727:
            r15 = move-exception
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0107 }
            r5.<init>()     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = "create factory method error, "
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            r0 = r48
            com.alibaba.fastjson.parser.JavaBeanInfo r7 = r0.beanInfo     // Catch:{ all -> 0x0107 }
            java.lang.reflect.Method r7 = r7.factoryMethod     // Catch:{ all -> 0x0107 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r5 = r5.append(r7)     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0107 }
            r4.<init>(r5, r15)     // Catch:{ all -> 0x0107 }
            throw r4     // Catch:{ all -> 0x0107 }
        L_0x074b:
            r9 = r30
            goto L_0x04c3
        L_0x074f:
            r9 = r30
            goto L_0x04cf
        L_0x0753:
            r9 = r30
            goto L_0x01cf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public FieldDeserializer getFieldDeserializer(String key) {
        if (key == null) {
            return null;
        }
        if (this.beanInfo.ordered) {
            for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
                if (this.sortedFieldDeserializers[i].fieldInfo.name.equalsIgnoreCase(key)) {
                    return this.sortedFieldDeserializers[i];
                }
            }
            return null;
        }
        int low = 0;
        int high = this.sortedFieldDeserializers.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = this.sortedFieldDeserializers[mid].fieldInfo.name.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp <= 0) {
                return this.sortedFieldDeserializers[mid];
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    private boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        JSONLexer lexer = parser.lexer;
        FieldDeserializer fieldDeserializer = getFieldDeserializer(key);
        if (fieldDeserializer == null) {
            boolean startsWithIs = key.startsWith("is");
            FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
            int length = fieldDeserializerArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                FieldDeserializer fieldDeser = fieldDeserializerArr[i];
                FieldInfo fieldInfo = fieldDeser.fieldInfo;
                Class<?> fieldClass = fieldInfo.fieldClass;
                String fieldName = fieldInfo.name;
                if (!fieldName.equalsIgnoreCase(key)) {
                    if (startsWithIs && ((fieldClass == Boolean.TYPE || fieldClass == Boolean.class) && fieldName.equalsIgnoreCase(key.substring(2)))) {
                        fieldDeserializer = fieldDeser;
                        break;
                    }
                    i++;
                } else {
                    fieldDeserializer = fieldDeser;
                    break;
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(parser, object, key);
            return false;
        }
        lexer.nextTokenWithChar(':');
        fieldDeserializer.parseField(parser, object, objectType, fieldValues);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void parseExtra(DefaultJSONParser parser, Object object, String key) {
        Object value;
        JSONLexer lexer = parser.lexer;
        if ((parser.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + key);
        }
        lexer.nextTokenWithChar(':');
        Type type = null;
        List<ExtraTypeProvider> extraTypeProviders = parser.extraTypeProviders;
        if (extraTypeProviders != null) {
            for (ExtraTypeProvider extraProvider : extraTypeProviders) {
                type = extraProvider.getExtraType(object, key);
            }
        }
        if (type == null) {
            value = parser.parse();
        } else {
            value = parser.parseObject(type);
        }
        if (object instanceof ExtraProcessable) {
            ((ExtraProcessable) object).processExtra(key, value);
            return;
        }
        List<ExtraProcessor> extraProcessors = parser.extraProcessors;
        if (extraProcessors != null) {
            for (ExtraProcessor process : extraProcessors) {
                process.processExtra(object, key, value);
            }
        }
    }

    public Object createInstance(Map<String, Object> map, ParserConfig config) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.beanInfo.creatorConstructor == null) {
            Object object = createInstance((DefaultJSONParser) null, (Type) this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                FieldDeserializer fieldDeser = getFieldDeserializer(entry.getKey());
                if (fieldDeser != null) {
                    Object value = entry.getValue();
                    Method method = fieldDeser.fieldInfo.method;
                    if (method != null) {
                        method.invoke(object, new Object[]{TypeUtils.cast(value, method.getGenericParameterTypes()[0], config)});
                    } else {
                        fieldDeser.fieldInfo.field.set(object, TypeUtils.cast(value, fieldDeser.fieldInfo.fieldType, config));
                    }
                }
            }
            return object;
        }
        FieldInfo[] fieldInfoList = this.beanInfo.fields;
        int size = fieldInfoList.length;
        Object[] params = new Object[size];
        for (int i = 0; i < size; i++) {
            params[i] = map.get(fieldInfoList[i].name);
        }
        if (this.beanInfo.creatorConstructor == null) {
            return null;
        }
        try {
            return this.beanInfo.creatorConstructor.newInstance(params);
        } catch (Exception e) {
            throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e);
        }
    }

    /* access modifiers changed from: protected */
    public JavaBeanDeserializer getSeeAlso(ParserConfig config, JavaBeanInfo beanInfo2, String typeName) {
        if (beanInfo2.jsonType == null) {
            return null;
        }
        for (Class<?> seeAlsoClass : beanInfo2.jsonType.seeAlso()) {
            ObjectDeserializer seeAlsoDeser = config.getDeserializer(seeAlsoClass);
            if (seeAlsoDeser instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer seeAlsoJavaBeanDeser = (JavaBeanDeserializer) seeAlsoDeser;
                JavaBeanInfo subBeanInfo = seeAlsoJavaBeanDeser.beanInfo;
                if (subBeanInfo.typeName.equals(typeName)) {
                    return seeAlsoJavaBeanDeser;
                }
                JavaBeanDeserializer subSeeAlso = getSeeAlso(config, subBeanInfo, typeName);
                if (subSeeAlso != null) {
                    return subSeeAlso;
                }
            }
        }
        return null;
    }
}
