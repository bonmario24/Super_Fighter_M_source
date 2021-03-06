package org.aspectj.internal.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.InterTypeFieldDeclaration;

public class InterTypeFieldDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeFieldDeclaration {
    private Type genericType;
    private String name;
    private AjType<?> type;

    public InterTypeFieldDeclarationImpl(AjType<?> decType, String target, int mods, String name2, AjType<?> type2, Type genericType2) {
        super(decType, target, mods);
        this.name = name2;
        this.type = type2;
        this.genericType = genericType2;
    }

    public InterTypeFieldDeclarationImpl(AjType<?> decType, AjType<?> targetType, Field base) {
        super(decType, targetType, base.getModifiers());
        this.name = base.getName();
        this.type = AjTypeSystem.getAjType(base.getType());
        Type gt = base.getGenericType();
        if (gt instanceof Class) {
            this.genericType = AjTypeSystem.getAjType((Class) gt);
        } else {
            this.genericType = gt;
        }
    }

    public String getName() {
        return this.name;
    }

    public AjType<?> getType() {
        return this.type;
    }

    public Type getGenericType() {
        return this.genericType;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(Modifier.toString(getModifiers()));
        sb.append(" ");
        sb.append(getType().toString());
        sb.append(" ");
        sb.append(this.targetTypeName);
        sb.append(".");
        sb.append(getName());
        return sb.toString();
    }
}
