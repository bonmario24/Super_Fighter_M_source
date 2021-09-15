package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> targetType) {
        JsonAdapter annotation = (JsonAdapter) targetType.getRawType().getAnnotation(JsonAdapter.class);
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, targetType, annotation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.google.gson.JsonSerializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.gson.TypeAdapter<?>} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r8, com.google.gson.Gson r9, com.google.gson.reflect.TypeToken<?> r10, com.google.gson.annotations.JsonAdapter r11) {
        /*
            r7 = this;
            r5 = 0
            java.lang.Class r3 = r11.value()
            com.google.gson.reflect.TypeToken r3 = com.google.gson.reflect.TypeToken.get(r3)
            com.google.gson.internal.ObjectConstructor r3 = r8.get(r3)
            java.lang.Object r6 = r3.construct()
            boolean r3 = r6 instanceof com.google.gson.TypeAdapter
            if (r3 == 0) goto L_0x0025
            r0 = r6
            com.google.gson.TypeAdapter r0 = (com.google.gson.TypeAdapter) r0
        L_0x0018:
            if (r0 == 0) goto L_0x0024
            boolean r3 = r11.nullSafe()
            if (r3 == 0) goto L_0x0024
            com.google.gson.TypeAdapter r0 = r0.nullSafe()
        L_0x0024:
            return r0
        L_0x0025:
            boolean r3 = r6 instanceof com.google.gson.TypeAdapterFactory
            if (r3 == 0) goto L_0x0030
            com.google.gson.TypeAdapterFactory r6 = (com.google.gson.TypeAdapterFactory) r6
            com.google.gson.TypeAdapter r0 = r6.create(r9, r10)
            goto L_0x0018
        L_0x0030:
            boolean r3 = r6 instanceof com.google.gson.JsonSerializer
            if (r3 != 0) goto L_0x0038
            boolean r3 = r6 instanceof com.google.gson.JsonDeserializer
            if (r3 == 0) goto L_0x0054
        L_0x0038:
            boolean r3 = r6 instanceof com.google.gson.JsonSerializer
            if (r3 == 0) goto L_0x0050
            r3 = r6
            com.google.gson.JsonSerializer r3 = (com.google.gson.JsonSerializer) r3
            r1 = r3
        L_0x0040:
            boolean r3 = r6 instanceof com.google.gson.JsonDeserializer
            if (r3 == 0) goto L_0x0052
            com.google.gson.JsonDeserializer r6 = (com.google.gson.JsonDeserializer) r6
            r2 = r6
        L_0x0048:
            com.google.gson.internal.bind.TreeTypeAdapter r0 = new com.google.gson.internal.bind.TreeTypeAdapter
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5)
            goto L_0x0018
        L_0x0050:
            r1 = r5
            goto L_0x0040
        L_0x0052:
            r2 = r5
            goto L_0x0048
        L_0x0054:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Invalid attempt to bind an instance of "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Class r5 = r6.getClass()
            java.lang.String r5 = r5.getName()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " as a @JsonAdapter for "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = r10.toString()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter):com.google.gson.TypeAdapter");
    }
}
