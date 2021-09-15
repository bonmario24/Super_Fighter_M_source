package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer implements ObjectSerializer {
    private static final char[] false_chars = {'f', 'a', 'l', 's', 'e'};
    private static final char[] true_chars = {'t', 'r', 'u', 'e'};
    protected int features;
    private final FieldSerializer[] getters;
    private final FieldSerializer[] sortedGetters;
    protected String typeName;

    public JavaBeanSerializer(Class<?> clazz) {
        this(clazz, clazz.getModifiers(), (Map<String, String>) null, false, true, true, true);
    }

    public JavaBeanSerializer(Class<?> clazz, String... aliasList) {
        this(clazz, clazz.getModifiers(), map(aliasList), false, true, true, true);
    }

    private static Map<String, String> map(String... aliasList) {
        Map<String, String> aliasMap = new HashMap<>();
        for (String alias : aliasList) {
            aliasMap.put(alias, alias);
        }
        return aliasMap;
    }

    public JavaBeanSerializer(Class<?> clazz, int classModifiers, Map<String, String> aliasMap, boolean fieldOnly, boolean jsonTypeSupport, boolean jsonFieldSupport, boolean fieldGenericSupport) {
        this.features = 0;
        JSONType jsonType = jsonTypeSupport ? (JSONType) clazz.getAnnotation(JSONType.class) : null;
        if (jsonType != null) {
            this.features = SerializerFeature.m199of(jsonType.serialzeFeatures());
            this.typeName = jsonType.typeName();
            if (this.typeName.length() == 0) {
                this.typeName = null;
            }
        }
        List<FieldInfo> fieldInfoList = TypeUtils.computeGetters(clazz, classModifiers, fieldOnly, jsonType, aliasMap, false, jsonFieldSupport, fieldGenericSupport);
        List<FieldSerializer> getterList = new ArrayList<>();
        for (FieldInfo fieldInfo : fieldInfoList) {
            getterList.add(new FieldSerializer(fieldInfo));
        }
        this.getters = (FieldSerializer[]) getterList.toArray(new FieldSerializer[getterList.size()]);
        String[] orders = jsonType != null ? jsonType.orders() : null;
        if (orders == null || orders.length == 0) {
            FieldSerializer[] sortedGetters2 = new FieldSerializer[this.getters.length];
            System.arraycopy(this.getters, 0, sortedGetters2, 0, this.getters.length);
            Arrays.sort(sortedGetters2);
            if (Arrays.equals(sortedGetters2, this.getters)) {
                this.sortedGetters = this.getters;
            } else {
                this.sortedGetters = sortedGetters2;
            }
        } else {
            List<FieldInfo> fieldInfoList2 = TypeUtils.computeGetters(clazz, classModifiers, fieldOnly, jsonType, aliasMap, true, jsonFieldSupport, fieldGenericSupport);
            List<FieldSerializer> getterList2 = new ArrayList<>();
            for (FieldInfo fieldInfo2 : fieldInfoList2) {
                getterList2.add(new FieldSerializer(fieldInfo2));
            }
            this.sortedGetters = (FieldSerializer[]) getterList2.toArray(new FieldSerializer[getterList2.size()]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:126:0x0348 A[Catch:{ Exception -> 0x05aa, all -> 0x05d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0452 A[Catch:{ Exception -> 0x05aa, all -> 0x05d8 }, LOOP:5: B:168:0x044c->B:170:0x0452, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x035d A[EDGE_INSN: B:356:0x035d->B:129:0x035d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r71, java.lang.Object r72, java.lang.Object r73, java.lang.reflect.Type r74) throws java.io.IOException {
        /*
            r70 = this;
            r0 = r71
            com.alibaba.fastjson.serializer.SerializeWriter r0 = r0.out
            r43 = r0
            if (r72 != 0) goto L_0x000c
            r43.writeNull()
        L_0x000b:
            return
        L_0x000c:
            r0 = r71
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.context
            r66 = r0
            if (r66 == 0) goto L_0x002c
            r0 = r71
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.context
            r66 = r0
            r0 = r66
            int r0 = r0.features
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            r0 = r67
            int r0 = r0.mask
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x0048
        L_0x002c:
            r0 = r71
            java.util.IdentityHashMap<java.lang.Object, com.alibaba.fastjson.serializer.SerialContext> r0 = r0.references
            r66 = r0
            if (r66 == 0) goto L_0x0048
            r0 = r71
            java.util.IdentityHashMap<java.lang.Object, com.alibaba.fastjson.serializer.SerialContext> r0 = r0.references
            r66 = r0
            r0 = r66
            r1 = r72
            boolean r66 = r0.containsKey(r1)
            if (r66 == 0) goto L_0x0048
            r71.writeReference(r72)
            goto L_0x000b
        L_0x0048:
            r0 = r43
            int r0 = r0.features
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.SortField
            r0 = r67
            int r0 = r0.mask
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x01f7
            r0 = r70
            com.alibaba.fastjson.serializer.FieldSerializer[] r0 = r0.sortedGetters
            r27 = r0
        L_0x0060:
            r0 = r71
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.context
            r44 = r0
            r0 = r43
            int r0 = r0.features
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect
            r0 = r67
            int r0 = r0.mask
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x00bb
            com.alibaba.fastjson.serializer.SerialContext r66 = new com.alibaba.fastjson.serializer.SerialContext
            r0 = r70
            int r0 = r0.features
            r67 = r0
            r0 = r66
            r1 = r44
            r2 = r72
            r3 = r73
            r4 = r67
            r0.<init>(r1, r2, r3, r4)
            r0 = r66
            r1 = r71
            r1.context = r0
            r0 = r71
            java.util.IdentityHashMap<java.lang.Object, com.alibaba.fastjson.serializer.SerialContext> r0 = r0.references
            r66 = r0
            if (r66 != 0) goto L_0x00a6
            java.util.IdentityHashMap r66 = new java.util.IdentityHashMap
            r66.<init>()
            r0 = r66
            r1 = r71
            r1.references = r0
        L_0x00a6:
            r0 = r71
            java.util.IdentityHashMap<java.lang.Object, com.alibaba.fastjson.serializer.SerialContext> r0 = r0.references
            r66 = r0
            r0 = r71
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.context
            r67 = r0
            r0 = r66
            r1 = r72
            r2 = r67
            r0.put(r1, r2)
        L_0x00bb:
            r0 = r70
            int r0 = r0.features
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.BeanToArray
            r0 = r67
            int r0 = r0.mask
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x00df
            r0 = r43
            int r0 = r0.features
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.BeanToArray
            r0 = r67
            int r0 = r0.mask
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x01ff
        L_0x00df:
            r64 = 1
        L_0x00e1:
            if (r64 == 0) goto L_0x0203
            r57 = 91
        L_0x00e5:
            if (r64 == 0) goto L_0x0207
            r17 = 93
        L_0x00e9:
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            int r37 = r66 + 1
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r37
            r1 = r66
            if (r0 <= r1) goto L_0x0111
            r0 = r43
            java.io.Writer r0 = r0.writer     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x020b
            r0 = r43
            r1 = r37
            r0.expandCapacity(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x0111:
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66[r67] = r57     // Catch:{ Exception -> 0x05aa }
            r0 = r37
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
            r0 = r27
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 <= 0) goto L_0x0144
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x0144
            r71.incrementIndent()     // Catch:{ Exception -> 0x05aa }
            r71.println()     // Catch:{ Exception -> 0x05aa }
        L_0x0144:
            r13 = 0
            r0 = r70
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x018b
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x0212
            if (r74 != 0) goto L_0x018b
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteRootClassName     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x018b
            r0 = r71
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.context     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            com.alibaba.fastjson.serializer.SerialContext r0 = r0.parent     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x0212
        L_0x018b:
            r29 = 1
        L_0x018d:
            if (r29 == 0) goto L_0x01c8
            java.lang.Class r39 = r72.getClass()     // Catch:{ Exception -> 0x05aa }
            r0 = r39
            r1 = r74
            if (r0 == r1) goto L_0x01c8
            r0 = r71
            com.alibaba.fastjson.serializer.SerializeConfig r0 = r0.config     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            java.lang.String r0 = r0.typeKey     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r67 = 0
            r0 = r43
            r1 = r66
            r2 = r67
            r0.writeFieldName(r1, r2)     // Catch:{ Exception -> 0x05aa }
            r0 = r70
            java.lang.String r0 = r0.typeName     // Catch:{ Exception -> 0x05aa }
            r58 = r0
            if (r58 != 0) goto L_0x01c0
            java.lang.Class r66 = r72.getClass()     // Catch:{ Exception -> 0x05aa }
            java.lang.String r58 = r66.getName()     // Catch:{ Exception -> 0x05aa }
        L_0x01c0:
            r0 = r71
            r1 = r58
            r0.write((java.lang.String) r1)     // Catch:{ Exception -> 0x05aa }
            r13 = 1
        L_0x01c8:
            if (r13 == 0) goto L_0x0216
            r55 = 44
        L_0x01cc:
            r36 = r55
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.BeforeFilter> r0 = r0.beforeFilters     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x0219
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.BeforeFilter> r0 = r0.beforeFilters     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            java.util.Iterator r66 = r66.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x01e0:
            boolean r67 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r67 == 0) goto L_0x0219
            java.lang.Object r10 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.BeforeFilter r10 = (com.alibaba.fastjson.serializer.BeforeFilter) r10     // Catch:{ Exception -> 0x05aa }
            r0 = r71
            r1 = r72
            r2 = r36
            char r36 = r10.writeBefore(r0, r1, r2)     // Catch:{ Exception -> 0x05aa }
            goto L_0x01e0
        L_0x01f7:
            r0 = r70
            com.alibaba.fastjson.serializer.FieldSerializer[] r0 = r0.getters
            r27 = r0
            goto L_0x0060
        L_0x01ff:
            r64 = 0
            goto L_0x00e1
        L_0x0203:
            r57 = 123(0x7b, float:1.72E-43)
            goto L_0x00e5
        L_0x0207:
            r17 = 125(0x7d, float:1.75E-43)
            goto L_0x00e9
        L_0x020b:
            r43.flush()     // Catch:{ Exception -> 0x05aa }
            r37 = 1
            goto L_0x0111
        L_0x0212:
            r29 = 0
            goto L_0x018d
        L_0x0216:
            r55 = 0
            goto L_0x01cc
        L_0x0219:
            r66 = 44
            r0 = r36
            r1 = r66
            if (r0 != r1) goto L_0x02cd
            r13 = 1
        L_0x0222:
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x02d0
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x02d0
            r14 = 1
        L_0x0247:
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x02d3
            r59 = 1
        L_0x025b:
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.NotWriteDefaultValue     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x02d6
            r38 = 1
        L_0x026f:
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.PropertyFilter> r0 = r0.propertyFilters     // Catch:{ Exception -> 0x05aa }
            r46 = r0
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.NameFilter> r0 = r0.nameFilters     // Catch:{ Exception -> 0x05aa }
            r35 = r0
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.ValueFilter> r0 = r0.valueFilters     // Catch:{ Exception -> 0x05aa }
            r62 = r0
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.PropertyPreFilter> r0 = r0.propertyPreFilters     // Catch:{ Exception -> 0x05aa }
            r25 = r0
            r28 = 0
        L_0x0289:
            r0 = r27
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r28
            r1 = r66
            if (r0 >= r1) goto L_0x086e
            r23 = r27[r28]     // Catch:{ Exception -> 0x05aa }
            r0 = r23
            com.alibaba.fastjson.util.FieldInfo r0 = r0.fieldInfo     // Catch:{ Exception -> 0x05aa }
            r21 = r0
            r0 = r21
            java.lang.Class<?> r0 = r0.fieldClass     // Catch:{ Exception -> 0x05aa }
            r20 = r0
            r0 = r21
            java.lang.String r0 = r0.name     // Catch:{ Exception -> 0x05aa }
            r22 = r0
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.SkipTransientField     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x02d9
            r0 = r21
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x05aa }
            r19 = r0
            if (r19 == 0) goto L_0x02d9
            r0 = r21
            boolean r0 = r0.fieldTransient     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x02d9
        L_0x02ca:
            int r28 = r28 + 1
            goto L_0x0289
        L_0x02cd:
            r13 = 0
            goto L_0x0222
        L_0x02d0:
            r14 = 0
            goto L_0x0247
        L_0x02d3:
            r59 = 0
            goto L_0x025b
        L_0x02d6:
            r38 = 0
            goto L_0x026f
        L_0x02d9:
            r9 = 1
            if (r25 == 0) goto L_0x02fb
            java.util.Iterator r66 = r25.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x02e0:
            boolean r67 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r67 == 0) goto L_0x02fb
            java.lang.Object r24 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.PropertyPreFilter r24 = (com.alibaba.fastjson.serializer.PropertyPreFilter) r24     // Catch:{ Exception -> 0x05aa }
            r0 = r24
            r1 = r71
            r2 = r72
            r3 = r22
            boolean r67 = r0.apply(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            if (r67 != 0) goto L_0x02e0
            r9 = 0
        L_0x02fb:
            if (r9 == 0) goto L_0x02ca
            r47 = 0
            r50 = 0
            r52 = 0
            r48 = 0
            r49 = 0
            r63 = 0
            r0 = r21
            boolean r0 = r0.fieldAccess     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x03da
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x0394
            r0 = r21
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r72
            int r50 = r0.getInt(r1)     // Catch:{ Exception -> 0x05aa }
            r63 = 1
        L_0x0329:
            r8 = 1
            if (r46 == 0) goto L_0x090e
            if (r63 == 0) goto L_0x090a
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x03e6
            java.lang.Integer r47 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
        L_0x033e:
            java.util.Iterator r66 = r46.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x0342:
            boolean r67 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r67 == 0) goto L_0x035d
            java.lang.Object r45 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.PropertyFilter r45 = (com.alibaba.fastjson.serializer.PropertyFilter) r45     // Catch:{ Exception -> 0x05aa }
            r0 = r45
            r1 = r72
            r2 = r22
            r3 = r41
            boolean r67 = r0.apply(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            if (r67 != 0) goto L_0x0342
            r8 = 0
        L_0x035d:
            if (r8 == 0) goto L_0x02ca
            r31 = r22
            if (r35 == 0) goto L_0x042e
            if (r63 == 0) goto L_0x0377
            if (r49 != 0) goto L_0x0377
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x040a
            java.lang.Integer r47 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
        L_0x0377:
            java.util.Iterator r66 = r35.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x037b:
            boolean r67 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r67 == 0) goto L_0x042e
            java.lang.Object r34 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.NameFilter r34 = (com.alibaba.fastjson.serializer.NameFilter) r34     // Catch:{ Exception -> 0x05aa }
            r0 = r34
            r1 = r72
            r2 = r31
            r3 = r41
            java.lang.String r31 = r0.process(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            goto L_0x037b
        L_0x0394:
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x03ae
            r0 = r21
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r72
            long r52 = r0.getLong(r1)     // Catch:{ Exception -> 0x05aa }
            r63 = 1
            goto L_0x0329
        L_0x03ae:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x03c8
            r0 = r21
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r72
            boolean r48 = r0.getBoolean(r1)     // Catch:{ Exception -> 0x05aa }
            r63 = 1
            goto L_0x0329
        L_0x03c8:
            r0 = r21
            java.lang.reflect.Field r0 = r0.field     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r72
            java.lang.Object r47 = r0.get(r1)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            goto L_0x0329
        L_0x03da:
            r0 = r23
            r1 = r72
            java.lang.Object r47 = r0.getPropertyValue(r1)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            goto L_0x0329
        L_0x03e6:
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x03f8
            java.lang.Long r47 = java.lang.Long.valueOf(r52)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
            goto L_0x033e
        L_0x03f8:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x090a
            java.lang.Boolean r47 = java.lang.Boolean.valueOf(r48)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
            goto L_0x033e
        L_0x040a:
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x041c
            java.lang.Long r47 = java.lang.Long.valueOf(r52)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
            goto L_0x0377
        L_0x041c:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x0377
            java.lang.Boolean r47 = java.lang.Boolean.valueOf(r48)     // Catch:{ Exception -> 0x05aa }
            r49 = 1
            r41 = r47
            goto L_0x0377
        L_0x042e:
            if (r62 == 0) goto L_0x0491
            if (r63 == 0) goto L_0x0904
            if (r49 != 0) goto L_0x0904
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x0467
            java.lang.Integer r47 = java.lang.Integer.valueOf(r50)     // Catch:{ Exception -> 0x05aa }
            r41 = r47
            r49 = 1
            r42 = r41
            r67 = r47
        L_0x0448:
            java.util.Iterator r66 = r62.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x044c:
            boolean r68 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r68 == 0) goto L_0x0495
            java.lang.Object r61 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.ValueFilter r61 = (com.alibaba.fastjson.serializer.ValueFilter) r61     // Catch:{ Exception -> 0x05aa }
            r0 = r61
            r1 = r72
            r2 = r22
            r3 = r67
            java.lang.Object r47 = r0.process(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            r67 = r47
            goto L_0x044c
        L_0x0467:
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x047c
            java.lang.Long r47 = java.lang.Long.valueOf(r52)     // Catch:{ Exception -> 0x05aa }
            r41 = r47
            r49 = 1
            r42 = r41
            r67 = r47
            goto L_0x0448
        L_0x047c:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x0904
            java.lang.Boolean r47 = java.lang.Boolean.valueOf(r48)     // Catch:{ Exception -> 0x05aa }
            r41 = r47
            r49 = 1
            r42 = r41
            r67 = r47
            goto L_0x0448
        L_0x0491:
            r42 = r41
            r67 = r41
        L_0x0495:
            if (r49 == 0) goto L_0x04b5
            if (r67 != 0) goto L_0x04b5
            if (r64 != 0) goto L_0x04b5
            r0 = r23
            boolean r0 = r0.writeNull     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x04b5
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r68 = com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ Exception -> 0x05aa }
            r0 = r68
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r66 = r66 & r68
            if (r66 == 0) goto L_0x02ca
        L_0x04b5:
            if (r49 == 0) goto L_0x051b
            if (r67 == 0) goto L_0x051b
            if (r38 == 0) goto L_0x051b
            java.lang.Class r66 = java.lang.Byte.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 == r1) goto L_0x04eb
            java.lang.Class r66 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 == r1) goto L_0x04eb
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 == r1) goto L_0x04eb
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 == r1) goto L_0x04eb
            java.lang.Class r66 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 == r1) goto L_0x04eb
            java.lang.Class r66 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x04ff
        L_0x04eb:
            r0 = r67
            boolean r0 = r0 instanceof java.lang.Number     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x04ff
            r0 = r67
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            byte r66 = r66.byteValue()     // Catch:{ Exception -> 0x05aa }
            if (r66 == 0) goto L_0x02ca
        L_0x04ff:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x051b
            r0 = r67
            boolean r0 = r0 instanceof java.lang.Boolean     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x051b
            r0 = r67
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            boolean r66 = r66.booleanValue()     // Catch:{ Exception -> 0x05aa }
            if (r66 == 0) goto L_0x02ca
        L_0x051b:
            if (r13 == 0) goto L_0x0570
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            int r37 = r66 + 1
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r37
            r1 = r66
            if (r0 <= r1) goto L_0x0545
            r0 = r43
            java.io.Writer r0 = r0.writer     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x058d
            r0 = r43
            r1 = r37
            r0.expandCapacity(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x0545:
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r69 = 44
            r66[r68] = r69     // Catch:{ Exception -> 0x05aa }
            r0 = r37
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r68 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x05aa }
            r0 = r68
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r66 = r66 & r68
            if (r66 == 0) goto L_0x0570
            r71.println()     // Catch:{ Exception -> 0x05aa }
        L_0x0570:
            r0 = r31
            r1 = r22
            if (r0 == r1) goto L_0x0593
            if (r64 != 0) goto L_0x0583
            r66 = 1
            r0 = r43
            r1 = r31
            r2 = r66
            r0.writeFieldName(r1, r2)     // Catch:{ Exception -> 0x05aa }
        L_0x0583:
            r0 = r71
            r1 = r67
            r0.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x05aa }
        L_0x058a:
            r13 = 1
            goto L_0x02ca
        L_0x058d:
            r43.flush()     // Catch:{ Exception -> 0x05aa }
            r37 = 1
            goto L_0x0545
        L_0x0593:
            r0 = r42
            r1 = r67
            if (r0 == r1) goto L_0x05e0
            if (r64 != 0) goto L_0x05a2
            r0 = r23
            r1 = r71
            r0.writePrefix(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x05a2:
            r0 = r71
            r1 = r67
            r0.write((java.lang.Object) r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x05aa:
            r15 = move-exception
            java.lang.String r18 = "write javaBean error"
            if (r73 == 0) goto L_0x05ce
            java.lang.StringBuilder r66 = new java.lang.StringBuilder     // Catch:{ all -> 0x05d8 }
            r66.<init>()     // Catch:{ all -> 0x05d8 }
            r0 = r66
            r1 = r18
            java.lang.StringBuilder r66 = r0.append(r1)     // Catch:{ all -> 0x05d8 }
            java.lang.String r67 = ", fieldName : "
            java.lang.StringBuilder r66 = r66.append(r67)     // Catch:{ all -> 0x05d8 }
            r0 = r66
            r1 = r73
            java.lang.StringBuilder r66 = r0.append(r1)     // Catch:{ all -> 0x05d8 }
            java.lang.String r18 = r66.toString()     // Catch:{ all -> 0x05d8 }
        L_0x05ce:
            com.alibaba.fastjson.JSONException r66 = new com.alibaba.fastjson.JSONException     // Catch:{ all -> 0x05d8 }
            r0 = r66
            r1 = r18
            r0.<init>(r1, r15)     // Catch:{ all -> 0x05d8 }
            throw r66     // Catch:{ all -> 0x05d8 }
        L_0x05d8:
            r66 = move-exception
            r0 = r44
            r1 = r71
            r1.context = r0
            throw r66
        L_0x05e0:
            if (r64 != 0) goto L_0x0632
            if (r14 == 0) goto L_0x06a8
            r0 = r21
            char[] r11 = r0.name_chars     // Catch:{ Exception -> 0x05aa }
            r40 = 0
            int r0 = r11.length     // Catch:{ Exception -> 0x05aa }
            r32 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            int r37 = r66 + r32
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r37
            r1 = r66
            if (r0 <= r1) goto L_0x0615
            r0 = r43
            java.io.Writer r0 = r0.writer     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x0651
            r0 = r43
            r1 = r37
            r0.expandCapacity(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x0615:
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r0 = r40
            r1 = r66
            r2 = r68
            r3 = r32
            java.lang.System.arraycopy(r11, r0, r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            r0 = r37
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
        L_0x0632:
            if (r63 == 0) goto L_0x0785
            if (r49 != 0) goto L_0x0785
            java.lang.Class r66 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x0738
            r66 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r50
            r1 = r66
            if (r0 != r1) goto L_0x06b0
            java.lang.String r66 = "-2147483648"
            r0 = r43
            r1 = r66
            r0.write((java.lang.String) r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0651:
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            int r54 = r66 - r68
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r0 = r40
            r1 = r66
            r2 = r68
            r3 = r54
            java.lang.System.arraycopy(r11, r0, r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
            r43.flush()     // Catch:{ Exception -> 0x05aa }
            int r32 = r32 - r54
            int r40 = r40 + r54
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r32
            r1 = r66
            if (r0 > r1) goto L_0x0651
            r37 = r32
            goto L_0x0615
        L_0x06a8:
            r0 = r23
            r1 = r71
            r0.writePrefix(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x0632
        L_0x06b0:
            if (r50 >= 0) goto L_0x0711
            r0 = r50
            int r0 = -r0
            r65 = r0
        L_0x06b7:
            r30 = 0
        L_0x06b9:
            int[] r66 = com.alibaba.fastjson.serializer.SerializeWriter.sizeTable     // Catch:{ Exception -> 0x05aa }
            r66 = r66[r30]     // Catch:{ Exception -> 0x05aa }
            r0 = r65
            r1 = r66
            if (r0 > r1) goto L_0x0714
            int r56 = r30 + 1
            if (r50 >= 0) goto L_0x06c9
            int r56 = r56 + 1
        L_0x06c9:
            r26 = 0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            int r37 = r66 + r56
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r37
            r1 = r66
            if (r0 <= r1) goto L_0x06f3
            r0 = r43
            java.io.Writer r0 = r0.writer     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x0717
            r0 = r43
            r1 = r37
            r0.expandCapacity(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x06f3:
            if (r26 != 0) goto L_0x058a
            r0 = r50
            long r0 = (long) r0     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r0 = r66
            r2 = r37
            r3 = r68
            com.alibaba.fastjson.serializer.SerializeWriter.getChars(r0, r2, r3)     // Catch:{ Exception -> 0x05aa }
            r0 = r37
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0711:
            r65 = r50
            goto L_0x06b7
        L_0x0714:
            int r30 = r30 + 1
            goto L_0x06b9
        L_0x0717:
            r0 = r56
            char[] r12 = new char[r0]     // Catch:{ Exception -> 0x05aa }
            r0 = r50
            long r0 = (long) r0     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r2 = r56
            com.alibaba.fastjson.serializer.SerializeWriter.getChars(r0, r2, r12)     // Catch:{ Exception -> 0x05aa }
            r66 = 0
            int r0 = r12.length     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r0 = r43
            r1 = r66
            r2 = r67
            r0.write((char[]) r12, (int) r1, (int) r2)     // Catch:{ Exception -> 0x05aa }
            r26 = 1
            goto L_0x06f3
        L_0x0738:
            java.lang.Class r66 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x074f
            r0 = r71
            com.alibaba.fastjson.serializer.SerializeWriter r0 = r0.out     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            r1 = r52
            r0.writeLong(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x074f:
            java.lang.Class r66 = java.lang.Boolean.TYPE     // Catch:{ Exception -> 0x05aa }
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x058a
            if (r48 == 0) goto L_0x076f
            r0 = r71
            com.alibaba.fastjson.serializer.SerializeWriter r0 = r0.out     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            char[] r67 = true_chars     // Catch:{ Exception -> 0x05aa }
            r68 = 0
            char[] r69 = true_chars     // Catch:{ Exception -> 0x05aa }
            r0 = r69
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r69 = r0
            r66.write((char[]) r67, (int) r68, (int) r69)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x076f:
            r0 = r71
            com.alibaba.fastjson.serializer.SerializeWriter r0 = r0.out     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            char[] r67 = false_chars     // Catch:{ Exception -> 0x05aa }
            r68 = 0
            char[] r69 = false_chars     // Catch:{ Exception -> 0x05aa }
            r0 = r69
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r69 = r0
            r66.write((char[]) r67, (int) r68, (int) r69)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0785:
            if (r64 != 0) goto L_0x0863
            java.lang.Class<java.lang.String> r66 = java.lang.String.class
            r0 = r20
            r1 = r66
            if (r0 != r1) goto L_0x07e7
            if (r67 != 0) goto L_0x07c5
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 != 0) goto L_0x07b5
            r0 = r23
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x07c0
        L_0x07b5:
            java.lang.String r66 = ""
            r0 = r43
            r1 = r66
            r0.writeString(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x07c0:
            r43.writeNull()     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x07c5:
            r0 = r67
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x05aa }
            r51 = r0
            if (r59 == 0) goto L_0x07d6
            r0 = r43
            r1 = r51
            r0.writeStringWithSingleQuote(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x07d6:
            r66 = 0
            r67 = 1
            r0 = r43
            r1 = r51
            r2 = r66
            r3 = r67
            r0.writeStringWithDoubleQuote(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x07e7:
            r0 = r21
            boolean r0 = r0.isEnum     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x0858
            if (r67 == 0) goto L_0x0853
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r68 = com.alibaba.fastjson.serializer.SerializerFeature.WriteEnumUsingToString     // Catch:{ Exception -> 0x05aa }
            r0 = r68
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r68 = r0
            r66 = r66 & r68
            if (r66 == 0) goto L_0x0840
            r0 = r67
            java.lang.Enum r0 = (java.lang.Enum) r0     // Catch:{ Exception -> 0x05aa }
            r16 = r0
            java.lang.String r33 = r16.toString()     // Catch:{ Exception -> 0x05aa }
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x082c
            r60 = 1
        L_0x0821:
            if (r60 == 0) goto L_0x082f
            r0 = r43
            r1 = r33
            r0.writeStringWithSingleQuote(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x082c:
            r60 = 0
            goto L_0x0821
        L_0x082f:
            r66 = 0
            r67 = 0
            r0 = r43
            r1 = r33
            r2 = r66
            r3 = r67
            r0.writeStringWithDoubleQuote(r1, r2, r3)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0840:
            r0 = r67
            java.lang.Enum r0 = (java.lang.Enum) r0     // Catch:{ Exception -> 0x05aa }
            r16 = r0
            int r66 = r16.ordinal()     // Catch:{ Exception -> 0x05aa }
            r0 = r43
            r1 = r66
            r0.writeInt(r1)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0853:
            r43.writeNull()     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0858:
            r0 = r23
            r1 = r71
            r2 = r67
            r0.writeValue(r1, r2)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x0863:
            r0 = r23
            r1 = r71
            r2 = r67
            r0.writeValue(r1, r2)     // Catch:{ Exception -> 0x05aa }
            goto L_0x058a
        L_0x086e:
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.AfterFilter> r0 = r0.afterFilters     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 == 0) goto L_0x089b
            if (r13 == 0) goto L_0x0899
            r7 = 44
        L_0x087a:
            r0 = r71
            java.util.List<com.alibaba.fastjson.serializer.AfterFilter> r0 = r0.afterFilters     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            java.util.Iterator r66 = r66.iterator()     // Catch:{ Exception -> 0x05aa }
        L_0x0884:
            boolean r67 = r66.hasNext()     // Catch:{ Exception -> 0x05aa }
            if (r67 == 0) goto L_0x089b
            java.lang.Object r6 = r66.next()     // Catch:{ Exception -> 0x05aa }
            com.alibaba.fastjson.serializer.AfterFilter r6 = (com.alibaba.fastjson.serializer.AfterFilter) r6     // Catch:{ Exception -> 0x05aa }
            r0 = r71
            r1 = r72
            char r7 = r6.writeAfter(r0, r1, r7)     // Catch:{ Exception -> 0x05aa }
            goto L_0x0884
        L_0x0899:
            r7 = 0
            goto L_0x087a
        L_0x089b:
            r0 = r27
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 <= 0) goto L_0x08ba
            r0 = r43
            int r0 = r0.features     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            com.alibaba.fastjson.serializer.SerializerFeature r67 = com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ Exception -> 0x05aa }
            r0 = r67
            int r0 = r0.mask     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66 = r66 & r67
            if (r66 == 0) goto L_0x08ba
            r71.decrementIdent()     // Catch:{ Exception -> 0x05aa }
            r71.println()     // Catch:{ Exception -> 0x05aa }
        L_0x08ba:
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            int r37 = r66 + 1
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r66
            int r0 = r0.length     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r37
            r1 = r66
            if (r0 <= r1) goto L_0x08e2
            r0 = r43
            java.io.Writer r0 = r0.writer     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            if (r66 != 0) goto L_0x08fe
            r0 = r43
            r1 = r37
            r0.expandCapacity(r1)     // Catch:{ Exception -> 0x05aa }
        L_0x08e2:
            r0 = r43
            char[] r0 = r0.buf     // Catch:{ Exception -> 0x05aa }
            r66 = r0
            r0 = r43
            int r0 = r0.count     // Catch:{ Exception -> 0x05aa }
            r67 = r0
            r66[r67] = r17     // Catch:{ Exception -> 0x05aa }
            r0 = r37
            r1 = r43
            r1.count = r0     // Catch:{ Exception -> 0x05aa }
            r0 = r44
            r1 = r71
            r1.context = r0
            goto L_0x000b
        L_0x08fe:
            r43.flush()     // Catch:{ Exception -> 0x05aa }
            r37 = 1
            goto L_0x08e2
        L_0x0904:
            r42 = r41
            r67 = r41
            goto L_0x0448
        L_0x090a:
            r41 = r47
            goto L_0x033e
        L_0x090e:
            r41 = r47
            goto L_0x035d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }

    public Map<String, Object> getFieldValuesMap(Object object) throws Exception {
        Map<String, Object> map = new LinkedHashMap<>(this.sortedGetters.length);
        for (FieldSerializer getter : this.sortedGetters) {
            map.put(getter.fieldInfo.name, getter.getPropertyValue(object));
        }
        return map;
    }
}
