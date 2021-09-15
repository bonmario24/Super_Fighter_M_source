package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class JavaBeanInfo {
    final Constructor<?> creatorConstructor;
    final Constructor<?> defaultConstructor;
    final int defaultConstructorParameterSize;
    final Method factoryMethod;
    final FieldInfo[] fields;
    final JSONType jsonType;
    boolean ordered = false;
    final FieldInfo[] sortedFields;
    final boolean supportBeanToArray;
    public final String typeName;

    JavaBeanInfo(Class<?> clazz, Constructor<?> defaultConstructor2, Constructor<?> creatorConstructor2, Method factoryMethod2, FieldInfo[] fields2, FieldInfo[] sortedFields2, JSONType jsonType2) {
        int i = 0;
        this.defaultConstructor = defaultConstructor2;
        this.creatorConstructor = creatorConstructor2;
        this.factoryMethod = factoryMethod2;
        this.fields = fields2;
        this.jsonType = jsonType2;
        if (jsonType2 != null) {
            String typeName2 = jsonType2.typeName();
            if (typeName2.length() != 0) {
                this.typeName = typeName2;
            } else {
                this.typeName = clazz.getName();
            }
        } else {
            this.typeName = clazz.getName();
        }
        boolean supportBeanToArray2 = false;
        if (jsonType2 != null) {
            for (Feature feature : jsonType2.parseFeatures()) {
                if (feature == Feature.SupportArrayToBean) {
                    supportBeanToArray2 = true;
                }
            }
        }
        this.supportBeanToArray = supportBeanToArray2;
        FieldInfo[] sortedFields3 = computeSortedFields(fields2, sortedFields2);
        this.sortedFields = !Arrays.equals(fields2, sortedFields3) ? sortedFields3 : fields2;
        this.defaultConstructorParameterSize = defaultConstructor2 != null ? defaultConstructor2.getParameterTypes().length : i;
    }

    private FieldInfo[] computeSortedFields(FieldInfo[] fields2, FieldInfo[] sortedFields2) {
        if (this.jsonType == null) {
            FieldInfo[] fieldInfoArr = sortedFields2;
            return sortedFields2;
        }
        String[] orders = this.jsonType.orders();
        if (!(orders == null || orders.length == 0)) {
            boolean containsAll = true;
            int i = 0;
            while (true) {
                if (i >= orders.length) {
                    break;
                }
                boolean got = false;
                int j = 0;
                while (true) {
                    if (j >= sortedFields2.length) {
                        break;
                    } else if (sortedFields2[j].name.equals(orders[i])) {
                        got = true;
                        break;
                    } else {
                        j++;
                    }
                }
                if (!got) {
                    containsAll = false;
                    break;
                }
                i++;
            }
            if (!containsAll) {
                FieldInfo[] fieldInfoArr2 = sortedFields2;
                return sortedFields2;
            } else if (orders.length == fields2.length) {
                boolean orderMatch = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= orders.length) {
                        break;
                    } else if (!sortedFields2[i2].name.equals(orders[i2])) {
                        orderMatch = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (orderMatch) {
                    FieldInfo[] fieldInfoArr3 = sortedFields2;
                    return sortedFields2;
                }
                FieldInfo[] newSortedFields = new FieldInfo[sortedFields2.length];
                for (int i3 = 0; i3 < orders.length; i3++) {
                    int j2 = 0;
                    while (true) {
                        if (j2 >= sortedFields2.length) {
                            break;
                        } else if (sortedFields2[j2].name.equals(orders[i3])) {
                            newSortedFields[i3] = sortedFields2[j2];
                            break;
                        } else {
                            j2++;
                        }
                    }
                }
                this.ordered = true;
                FieldInfo[] fieldInfoArr4 = newSortedFields;
                return newSortedFields;
            } else {
                FieldInfo[] newSortedFields2 = new FieldInfo[sortedFields2.length];
                for (int i4 = 0; i4 < orders.length; i4++) {
                    int j3 = 0;
                    while (true) {
                        if (j3 >= sortedFields2.length) {
                            break;
                        } else if (sortedFields2[j3].name.equals(orders[i4])) {
                            newSortedFields2[i4] = sortedFields2[j3];
                            break;
                        } else {
                            j3++;
                        }
                    }
                }
                int fieldIndex = orders.length;
                for (int i5 = 0; i5 < sortedFields2.length; i5++) {
                    boolean contains = false;
                    int j4 = 0;
                    while (true) {
                        if (j4 >= newSortedFields2.length || j4 >= fieldIndex) {
                            break;
                        } else if (newSortedFields2[i5].equals(sortedFields2[j4])) {
                            contains = true;
                            break;
                        } else {
                            j4++;
                        }
                    }
                    if (!contains) {
                        newSortedFields2[fieldIndex] = sortedFields2[i5];
                        fieldIndex++;
                    }
                }
                this.ordered = true;
            }
        }
        FieldInfo[] fieldInfoArr5 = sortedFields2;
        return sortedFields2;
    }

    static boolean addField(List<FieldInfo> fields2, FieldInfo field, boolean fieldOnly) {
        if (!fieldOnly) {
            int size = fields2.size();
            for (int i = 0; i < size; i++) {
                FieldInfo item = fields2.get(i);
                if (item.name.equals(field.name) && (!item.getOnly || field.getOnly)) {
                    return false;
                }
            }
        }
        fields2.add(field);
        return true;
    }

    /* JADX WARNING: type inference failed for: r69v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alibaba.fastjson.parser.JavaBeanInfo build(java.lang.Class<?> r69, int r70, java.lang.reflect.Type r71, boolean r72, boolean r73, boolean r74, boolean r75) {
        /*
            java.util.ArrayList r54 = new java.util.ArrayList
            r54.<init>()
            r52 = 0
            r0 = r70
            r4 = r0 & 1024(0x400, float:1.435E-42)
            if (r4 != 0) goto L_0x0045
            r4 = 0
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x068d }
            r0 = r69
            java.lang.reflect.Constructor r52 = r0.getDeclaredConstructor(r4)     // Catch:{ Exception -> 0x068d }
        L_0x0016:
            if (r52 != 0) goto L_0x0045
            boolean r4 = r69.isMemberClass()
            if (r4 == 0) goto L_0x0045
            r4 = r70 & 8
            if (r4 != 0) goto L_0x0045
            java.lang.reflect.Constructor[] r5 = r69.getDeclaredConstructors()
            int r11 = r5.length
            r4 = 0
        L_0x0028:
            if (r4 >= r11) goto L_0x0045
            r49 = r5[r4]
            java.lang.Class[] r66 = r49.getParameterTypes()
            r0 = r66
            int r12 = r0.length
            r13 = 1
            if (r12 != r13) goto L_0x007d
            r12 = 0
            r12 = r66[r12]
            java.lang.Class r13 = r69.getDeclaringClass()
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x007d
            r52 = r49
        L_0x0045:
            r14 = 0
            if (r72 == 0) goto L_0x0080
            r63 = 0
        L_0x004a:
            java.lang.reflect.Field[] r51 = r69.getDeclaredFields()
            if (r52 != 0) goto L_0x026c
            boolean r4 = r69.isInterface()
            if (r4 != 0) goto L_0x026c
            r0 = r70
            r4 = r0 & 1024(0x400, float:1.435E-42)
            if (r4 != 0) goto L_0x026c
            r14 = 0
            java.lang.reflect.Constructor[] r5 = r69.getDeclaredConstructors()
            int r11 = r5.length
            r4 = 0
        L_0x0063:
            if (r4 >= r11) goto L_0x0087
            r49 = r5[r4]
            java.lang.Class<com.alibaba.fastjson.annotation.JSONCreator> r12 = com.alibaba.fastjson.annotation.JSONCreator.class
            r0 = r49
            java.lang.annotation.Annotation r32 = r0.getAnnotation(r12)
            com.alibaba.fastjson.annotation.JSONCreator r32 = (com.alibaba.fastjson.annotation.JSONCreator) r32
            if (r32 == 0) goto L_0x00c7
            if (r14 == 0) goto L_0x0085
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException
            java.lang.String r5 = "multi-json creator"
            r4.<init>(r5)
            throw r4
        L_0x007d:
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0080:
            java.lang.reflect.Method[] r63 = r69.getMethods()
            goto L_0x004a
        L_0x0085:
            r14 = r49
        L_0x0087:
            if (r14 == 0) goto L_0x014a
            r0 = r69
            r1 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r14, r1)
            java.lang.Class[] r66 = r14.getParameterTypes()
            if (r75 == 0) goto L_0x00ca
            java.lang.reflect.Type[] r57 = r14.getGenericParameterTypes()
        L_0x009a:
            r58 = 0
        L_0x009c:
            r0 = r66
            int r4 = r0.length
            r0 = r58
            if (r0 >= r4) goto L_0x010a
            java.lang.annotation.Annotation[][] r4 = r14.getParameterAnnotations()
            r65 = r4[r58]
            r33 = 0
            r0 = r65
            int r5 = r0.length
            r4 = 0
        L_0x00af:
            if (r4 >= r5) goto L_0x00bd
            r64 = r65[r4]
            r0 = r64
            boolean r11 = r0 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r11 == 0) goto L_0x00cd
            r33 = r64
            com.alibaba.fastjson.annotation.JSONField r33 = (com.alibaba.fastjson.annotation.JSONField) r33
        L_0x00bd:
            if (r33 != 0) goto L_0x00d0
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException
            java.lang.String r5 = "illegal json creator"
            r4.<init>(r5)
            throw r4
        L_0x00c7:
            int r4 = r4 + 1
            goto L_0x0063
        L_0x00ca:
            r57 = r66
            goto L_0x009a
        L_0x00cd:
            int r4 = r4 + 1
            goto L_0x00af
        L_0x00d0:
            r6 = r66[r58]
            r7 = r57[r58]
            java.lang.String r4 = r33.name()
            r0 = r69
            r1 = r51
            java.lang.reflect.Field r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r4, r1)
            if (r8 == 0) goto L_0x00e9
            r0 = r69
            r1 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r8, r1)
        L_0x00e9:
            int r9 = r33.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r33.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r4)
            com.alibaba.fastjson.util.FieldInfo r3 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.String r4 = r33.name()
            r5 = r69
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r0 = r54
            r1 = r72
            addField(r0, r3, r1)
            int r58 = r58 + 1
            goto L_0x009c
        L_0x010a:
            int r4 = r54.size()
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r16 = r0
            r0 = r54
            r1 = r16
            r0.toArray(r1)
            r0 = r16
            int r4 = r0.length
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r17 = r0
            r4 = 0
            r5 = 0
            r0 = r16
            int r11 = r0.length
            r0 = r16
            r1 = r17
            java.lang.System.arraycopy(r0, r4, r1, r5, r11)
            java.util.Arrays.sort(r17)
            if (r73 == 0) goto L_0x0147
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r4 = com.alibaba.fastjson.annotation.JSONType.class
            r0 = r69
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONType r4 = (com.alibaba.fastjson.annotation.JSONType) r4
            r18 = r4
        L_0x013d:
            com.alibaba.fastjson.parser.JavaBeanInfo r11 = new com.alibaba.fastjson.parser.JavaBeanInfo
            r13 = 0
            r15 = 0
            r12 = r69
            r11.<init>(r12, r13, r14, r15, r16, r17, r18)
        L_0x0146:
            return r11
        L_0x0147:
            r18 = 0
            goto L_0x013d
        L_0x014a:
            r23 = 0
            r0 = r63
            int r5 = r0.length
            r4 = 0
        L_0x0150:
            if (r4 >= r5) goto L_0x0185
            r26 = r63[r4]
            int r11 = r26.getModifiers()
            boolean r11 = java.lang.reflect.Modifier.isStatic(r11)
            if (r11 == 0) goto L_0x016a
            java.lang.Class r11 = r26.getReturnType()
            r0 = r69
            boolean r11 = r0.isAssignableFrom(r11)
            if (r11 != 0) goto L_0x016d
        L_0x016a:
            int r4 = r4 + 1
            goto L_0x0150
        L_0x016d:
            java.lang.Class<com.alibaba.fastjson.annotation.JSONCreator> r11 = com.alibaba.fastjson.annotation.JSONCreator.class
            r0 = r26
            java.lang.annotation.Annotation r32 = r0.getAnnotation(r11)
            com.alibaba.fastjson.annotation.JSONCreator r32 = (com.alibaba.fastjson.annotation.JSONCreator) r32
            if (r32 == 0) goto L_0x016a
            if (r23 == 0) goto L_0x0183
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException
            java.lang.String r5 = "multi-json creator"
            r4.<init>(r5)
            throw r4
        L_0x0183:
            r23 = r26
        L_0x0185:
            if (r23 == 0) goto L_0x0251
            r0 = r69
            r1 = r23
            r2 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2)
            java.lang.Class[] r66 = r23.getParameterTypes()
            if (r75 == 0) goto L_0x01c7
            java.lang.reflect.Type[] r56 = r23.getGenericParameterTypes()
        L_0x019a:
            r58 = 0
        L_0x019c:
            r0 = r66
            int r4 = r0.length
            r0 = r58
            if (r0 >= r4) goto L_0x01fe
            java.lang.annotation.Annotation[][] r4 = r23.getParameterAnnotations()
            r65 = r4[r58]
            r33 = 0
            r0 = r65
            int r5 = r0.length
            r4 = 0
        L_0x01af:
            if (r4 >= r5) goto L_0x01bd
            r64 = r65[r4]
            r0 = r64
            boolean r11 = r0 instanceof com.alibaba.fastjson.annotation.JSONField
            if (r11 == 0) goto L_0x01ca
            r33 = r64
            com.alibaba.fastjson.annotation.JSONField r33 = (com.alibaba.fastjson.annotation.JSONField) r33
        L_0x01bd:
            if (r33 != 0) goto L_0x01cd
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException
            java.lang.String r5 = "illegal json creator"
            r4.<init>(r5)
            throw r4
        L_0x01c7:
            r56 = r66
            goto L_0x019a
        L_0x01ca:
            int r4 = r4 + 1
            goto L_0x01af
        L_0x01cd:
            r6 = r66[r58]
            r7 = r56[r58]
            java.lang.String r4 = r33.name()
            r0 = r69
            r1 = r51
            java.lang.reflect.Field r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r4, r1)
            int r9 = r33.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r33.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r4)
            com.alibaba.fastjson.util.FieldInfo r3 = new com.alibaba.fastjson.util.FieldInfo
            java.lang.String r4 = r33.name()
            r5 = r69
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r0 = r54
            r1 = r72
            addField(r0, r3, r1)
            int r58 = r58 + 1
            goto L_0x019c
        L_0x01fe:
            int r4 = r54.size()
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r16 = r0
            r0 = r54
            r1 = r16
            r0.toArray(r1)
            r0 = r16
            int r4 = r0.length
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r17 = r0
            r4 = 0
            r5 = 0
            r0 = r16
            int r11 = r0.length
            r0 = r16
            r1 = r17
            java.lang.System.arraycopy(r0, r4, r1, r5, r11)
            java.util.Arrays.sort(r17)
            boolean r4 = java.util.Arrays.equals(r16, r17)
            if (r4 == 0) goto L_0x022b
            r17 = r16
        L_0x022b:
            if (r73 == 0) goto L_0x024e
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r4 = com.alibaba.fastjson.annotation.JSONType.class
            r0 = r69
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONType r4 = (com.alibaba.fastjson.annotation.JSONType) r4
            r18 = r4
        L_0x0239:
            com.alibaba.fastjson.parser.JavaBeanInfo r19 = new com.alibaba.fastjson.parser.JavaBeanInfo
            r21 = 0
            r22 = 0
            r20 = r69
            r24 = r16
            r25 = r17
            r26 = r18
            r19.<init>(r20, r21, r22, r23, r24, r25, r26)
            r11 = r19
            goto L_0x0146
        L_0x024e:
            r18 = 0
            goto L_0x0239
        L_0x0251:
            com.alibaba.fastjson.JSONException r4 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r11 = "default constructor not found. "
            java.lang.StringBuilder r5 = r5.append(r11)
            r0 = r69
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x026c:
            if (r52 == 0) goto L_0x0277
            r0 = r69
            r1 = r52
            r2 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2)
        L_0x0277:
            if (r72 != 0) goto L_0x0478
            r0 = r63
            int r11 = r0.length
            r4 = 0
            r5 = r4
        L_0x027e:
            if (r5 >= r11) goto L_0x0478
            r26 = r63[r5]
            r9 = 0
            r10 = 0
            java.lang.String r61 = r26.getName()
            int r4 = r61.length()
            r12 = 4
            if (r4 < r12) goto L_0x0299
            int r4 = r26.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x029d
        L_0x0299:
            int r4 = r5 + 1
            r5 = r4
            goto L_0x027e
        L_0x029d:
            java.lang.Class r67 = r26.getReturnType()
            java.lang.Class r4 = java.lang.Void.TYPE
            r0 = r67
            if (r0 == r4) goto L_0x02af
            java.lang.Class r4 = r26.getDeclaringClass()
            r0 = r67
            if (r0 != r4) goto L_0x0299
        L_0x02af:
            java.lang.Class[] r4 = r26.getParameterTypes()
            int r4 = r4.length
            r12 = 1
            if (r4 != r12) goto L_0x0299
            java.lang.Class r4 = r26.getDeclaringClass()
            java.lang.Class<java.lang.Object> r12 = java.lang.Object.class
            if (r4 == r12) goto L_0x0299
            if (r74 == 0) goto L_0x0322
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            r0 = r26
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            r32 = r4
        L_0x02cd:
            if (r32 != 0) goto L_0x02d9
            if (r74 == 0) goto L_0x02d9
            r0 = r69
            r1 = r26
            com.alibaba.fastjson.annotation.JSONField r32 = com.alibaba.fastjson.util.TypeUtils.getSupperMethodAnnotation(r0, r1)
        L_0x02d9:
            if (r32 == 0) goto L_0x0325
            boolean r4 = r32.deserialize()
            if (r4 == 0) goto L_0x0299
            int r9 = r32.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r32.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r4)
            java.lang.String r4 = r32.name()
            int r4 = r4.length()
            if (r4 == 0) goto L_0x0325
            java.lang.String r25 = r32.name()
            com.alibaba.fastjson.util.FieldInfo r24 = new com.alibaba.fastjson.util.FieldInfo
            r27 = 0
            r33 = 0
            r28 = r69
            r29 = r71
            r30 = r9
            r31 = r10
            r34 = r75
            r24.<init>(r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
            r0 = r54
            r1 = r24
            r2 = r72
            addField(r0, r1, r2)
            r0 = r69
            r1 = r26
            r2 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2)
            goto L_0x0299
        L_0x0322:
            r32 = 0
            goto L_0x02cd
        L_0x0325:
            java.lang.String r4 = "set"
            r0 = r61
            boolean r4 = r0.startsWith(r4)
            if (r4 == 0) goto L_0x0299
            r4 = 3
            r0 = r61
            char r47 = r0.charAt(r4)
            boolean r4 = java.lang.Character.isUpperCase(r47)
            if (r4 == 0) goto L_0x0404
            boolean r4 = com.alibaba.fastjson.util.TypeUtils.compatibleWithJavaBean
            if (r4 == 0) goto L_0x03dd
            r4 = 3
            r0 = r61
            java.lang.String r4 = r0.substring(r4)
            java.lang.String r25 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r4)
            r35 = r25
        L_0x034d:
            r0 = r69
            r1 = r35
            r2 = r51
            java.lang.reflect.Field r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r1, r2)
            if (r8 != 0) goto L_0x0397
            java.lang.Class[] r4 = r26.getParameterTypes()
            r12 = 0
            r4 = r4[r12]
            java.lang.Class r12 = java.lang.Boolean.TYPE
            if (r4 != r12) goto L_0x0397
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r12 = "is"
            java.lang.StringBuilder r4 = r4.append(r12)
            r12 = 0
            r0 = r35
            char r12 = r0.charAt(r12)
            char r12 = java.lang.Character.toUpperCase(r12)
            java.lang.StringBuilder r4 = r4.append(r12)
            r12 = 1
            r0 = r35
            java.lang.String r12 = r0.substring(r12)
            java.lang.StringBuilder r4 = r4.append(r12)
            java.lang.String r59 = r4.toString()
            r0 = r69
            r1 = r59
            r2 = r51
            java.lang.reflect.Field r8 = com.alibaba.fastjson.util.TypeUtils.getField(r0, r1, r2)
        L_0x0397:
            if (r8 == 0) goto L_0x044d
            if (r74 == 0) goto L_0x0449
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r4 = r8.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            r33 = r4
        L_0x03a5:
            if (r33 == 0) goto L_0x044d
            int r9 = r33.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r33.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r4)
            java.lang.String r4 = r33.name()
            int r4 = r4.length()
            if (r4 == 0) goto L_0x044d
            java.lang.String r25 = r33.name()
            com.alibaba.fastjson.util.FieldInfo r24 = new com.alibaba.fastjson.util.FieldInfo
            r27 = r8
            r28 = r69
            r29 = r71
            r30 = r9
            r31 = r10
            r34 = r75
            r24.<init>(r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
            r0 = r54
            r1 = r24
            r2 = r72
            addField(r0, r1, r2)
            goto L_0x0299
        L_0x03dd:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r12 = 3
            r0 = r61
            char r12 = r0.charAt(r12)
            char r12 = java.lang.Character.toLowerCase(r12)
            java.lang.StringBuilder r4 = r4.append(r12)
            r12 = 4
            r0 = r61
            java.lang.String r12 = r0.substring(r12)
            java.lang.StringBuilder r4 = r4.append(r12)
            java.lang.String r25 = r4.toString()
            r35 = r25
            goto L_0x034d
        L_0x0404:
            r4 = 95
            r0 = r47
            if (r0 != r4) goto L_0x0415
            r4 = 4
            r0 = r61
            java.lang.String r25 = r0.substring(r4)
            r35 = r25
            goto L_0x034d
        L_0x0415:
            r4 = 102(0x66, float:1.43E-43)
            r0 = r47
            if (r0 != r4) goto L_0x0426
            r4 = 3
            r0 = r61
            java.lang.String r25 = r0.substring(r4)
            r35 = r25
            goto L_0x034d
        L_0x0426:
            int r4 = r61.length()
            r12 = 5
            if (r4 < r12) goto L_0x0299
            r4 = 4
            r0 = r61
            char r4 = r0.charAt(r4)
            boolean r4 = java.lang.Character.isUpperCase(r4)
            if (r4 == 0) goto L_0x0299
            r4 = 3
            r0 = r61
            java.lang.String r4 = r0.substring(r4)
            java.lang.String r25 = com.alibaba.fastjson.util.TypeUtils.decapitalize(r4)
            r35 = r25
            goto L_0x034d
        L_0x0449:
            r33 = 0
            goto L_0x03a5
        L_0x044d:
            com.alibaba.fastjson.util.FieldInfo r34 = new com.alibaba.fastjson.util.FieldInfo
            r37 = 0
            r43 = 0
            r36 = r26
            r38 = r69
            r39 = r71
            r40 = r9
            r41 = r10
            r42 = r32
            r44 = r75
            r34.<init>(r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)
            r0 = r54
            r1 = r34
            r2 = r72
            addField(r0, r1, r2)
            r0 = r69
            r1 = r26
            r2 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2)
            goto L_0x0299
        L_0x0478:
            java.util.ArrayList r48 = new java.util.ArrayList
            r0 = r51
            int r4 = r0.length
            r0 = r48
            r0.<init>(r4)
            r0 = r51
            int r5 = r0.length
            r4 = 0
        L_0x0486:
            if (r4 >= r5) goto L_0x04a5
            r53 = r51[r4]
            int r11 = r53.getModifiers()
            r11 = r11 & 8
            if (r11 == 0) goto L_0x0495
        L_0x0492:
            int r4 = r4 + 1
            goto L_0x0486
        L_0x0495:
            int r11 = r53.getModifiers()
            r11 = r11 & 1
            if (r11 == 0) goto L_0x0492
            r0 = r48
            r1 = r53
            r0.add(r1)
            goto L_0x0492
        L_0x04a5:
            java.lang.Class r46 = r69.getSuperclass()
        L_0x04a9:
            if (r46 == 0) goto L_0x04db
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            r0 = r46
            if (r0 == r4) goto L_0x04db
            java.lang.reflect.Field[] r5 = r46.getDeclaredFields()
            int r11 = r5.length
            r4 = 0
        L_0x04b7:
            if (r4 >= r11) goto L_0x04d6
            r53 = r5[r4]
            int r12 = r53.getModifiers()
            r12 = r12 & 8
            if (r12 == 0) goto L_0x04c6
        L_0x04c3:
            int r4 = r4 + 1
            goto L_0x04b7
        L_0x04c6:
            int r12 = r53.getModifiers()
            r12 = r12 & 1
            if (r12 == 0) goto L_0x04c3
            r0 = r48
            r1 = r53
            r0.add(r1)
            goto L_0x04c3
        L_0x04d6:
            java.lang.Class r46 = r46.getSuperclass()
            goto L_0x04a9
        L_0x04db:
            java.util.Iterator r5 = r48.iterator()
        L_0x04df:
            boolean r4 = r5.hasNext()
            if (r4 == 0) goto L_0x0574
            java.lang.Object r8 = r5.next()
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            java.lang.String r55 = r8.getName()
            r50 = 0
            r58 = 0
            int r68 = r54.size()
        L_0x04f7:
            r0 = r58
            r1 = r68
            if (r0 >= r1) goto L_0x0518
            r0 = r54
            r1 = r58
            java.lang.Object r60 = r0.get(r1)
            com.alibaba.fastjson.util.FieldInfo r60 = (com.alibaba.fastjson.util.FieldInfo) r60
            r0 = r60
            java.lang.String r4 = r0.name
            r0 = r55
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0515
            r50 = 1
        L_0x0515:
            int r58 = r58 + 1
            goto L_0x04f7
        L_0x0518:
            if (r50 != 0) goto L_0x04df
            r9 = 0
            r10 = 0
            r25 = r55
            if (r74 == 0) goto L_0x0571
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            java.lang.annotation.Annotation r4 = r8.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            r33 = r4
        L_0x052a:
            if (r33 == 0) goto L_0x0546
            int r9 = r33.ordinal()
            com.alibaba.fastjson.serializer.SerializerFeature[] r4 = r33.serialzeFeatures()
            int r10 = com.alibaba.fastjson.serializer.SerializerFeature.m199of(r4)
            java.lang.String r4 = r33.name()
            int r4 = r4.length()
            if (r4 == 0) goto L_0x0546
            java.lang.String r25 = r33.name()
        L_0x0546:
            r0 = r69
            r1 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r8, r1)
            com.alibaba.fastjson.util.FieldInfo r34 = new com.alibaba.fastjson.util.FieldInfo
            r36 = 0
            r42 = 0
            r35 = r25
            r37 = r8
            r38 = r69
            r39 = r71
            r40 = r9
            r41 = r10
            r43 = r33
            r44 = r75
            r34.<init>(r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)
            r0 = r54
            r1 = r34
            r2 = r72
            addField(r0, r1, r2)
            goto L_0x04df
        L_0x0571:
            r33 = 0
            goto L_0x052a
        L_0x0574:
            if (r72 != 0) goto L_0x0640
            java.lang.reflect.Method[] r11 = r69.getMethods()
            int r12 = r11.length
            r4 = 0
            r5 = r4
        L_0x057d:
            if (r5 >= r12) goto L_0x0640
            r26 = r11[r5]
            java.lang.String r61 = r26.getName()
            int r4 = r61.length()
            r13 = 4
            if (r4 < r13) goto L_0x0596
            int r4 = r26.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x059a
        L_0x0596:
            int r4 = r5 + 1
            r5 = r4
            goto L_0x057d
        L_0x059a:
            java.lang.String r4 = "get"
            r0 = r61
            boolean r4 = r0.startsWith(r4)
            if (r4 == 0) goto L_0x0596
            r4 = 3
            r0 = r61
            char r4 = r0.charAt(r4)
            boolean r4 = java.lang.Character.isUpperCase(r4)
            if (r4 == 0) goto L_0x0596
            java.lang.Class[] r4 = r26.getParameterTypes()
            int r4 = r4.length
            if (r4 != 0) goto L_0x0596
            java.lang.Class r62 = r26.getReturnType()
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            r0 = r62
            boolean r4 = r4.isAssignableFrom(r0)
            if (r4 != 0) goto L_0x05d0
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            r0 = r62
            boolean r4 = r4.isAssignableFrom(r0)
            if (r4 == 0) goto L_0x0596
        L_0x05d0:
            if (r74 == 0) goto L_0x0619
            java.lang.Class<com.alibaba.fastjson.annotation.JSONField> r4 = com.alibaba.fastjson.annotation.JSONField.class
            r0 = r26
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONField r4 = (com.alibaba.fastjson.annotation.JSONField) r4
            r32 = r4
        L_0x05de:
            if (r32 == 0) goto L_0x061c
            java.lang.String r45 = r32.name()
            int r4 = r45.length()
            if (r4 <= 0) goto L_0x061c
            r25 = r45
        L_0x05ec:
            com.alibaba.fastjson.util.FieldInfo r34 = new com.alibaba.fastjson.util.FieldInfo
            r37 = 0
            r40 = 0
            r41 = 0
            r43 = 0
            r35 = r25
            r36 = r26
            r38 = r69
            r39 = r71
            r42 = r32
            r44 = r75
            r34.<init>(r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)
            r0 = r54
            r1 = r34
            r2 = r72
            addField(r0, r1, r2)
            r0 = r69
            r1 = r26
            r2 = r70
            com.alibaba.fastjson.util.TypeUtils.setAccessible(r0, r1, r2)
            goto L_0x0596
        L_0x0619:
            r32 = 0
            goto L_0x05de
        L_0x061c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r13 = 3
            r0 = r61
            char r13 = r0.charAt(r13)
            char r13 = java.lang.Character.toLowerCase(r13)
            java.lang.StringBuilder r4 = r4.append(r13)
            r13 = 4
            r0 = r61
            java.lang.String r13 = r0.substring(r13)
            java.lang.StringBuilder r4 = r4.append(r13)
            java.lang.String r25 = r4.toString()
            goto L_0x05ec
        L_0x0640:
            int r4 = r54.size()
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r16 = r0
            r0 = r54
            r1 = r16
            r0.toArray(r1)
            r0 = r16
            int r4 = r0.length
            com.alibaba.fastjson.util.FieldInfo[] r0 = new com.alibaba.fastjson.util.FieldInfo[r4]
            r17 = r0
            r4 = 0
            r5 = 0
            r0 = r16
            int r11 = r0.length
            r0 = r16
            r1 = r17
            java.lang.System.arraycopy(r0, r4, r1, r5, r11)
            java.util.Arrays.sort(r17)
            if (r73 == 0) goto L_0x068a
            java.lang.Class<com.alibaba.fastjson.annotation.JSONType> r4 = com.alibaba.fastjson.annotation.JSONType.class
            r0 = r69
            java.lang.annotation.Annotation r4 = r0.getAnnotation(r4)
            com.alibaba.fastjson.annotation.JSONType r4 = (com.alibaba.fastjson.annotation.JSONType) r4
            r18 = r4
        L_0x0673:
            com.alibaba.fastjson.parser.JavaBeanInfo r34 = new com.alibaba.fastjson.parser.JavaBeanInfo
            r37 = 0
            r38 = 0
            r35 = r69
            r36 = r52
            r39 = r16
            r40 = r17
            r41 = r18
            r34.<init>(r35, r36, r37, r38, r39, r40, r41)
            r11 = r34
            goto L_0x0146
        L_0x068a:
            r18 = 0
            goto L_0x0673
        L_0x068d:
            r4 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanInfo.build(java.lang.Class, int, java.lang.reflect.Type, boolean, boolean, boolean, boolean):com.alibaba.fastjson.parser.JavaBeanInfo");
    }
}
