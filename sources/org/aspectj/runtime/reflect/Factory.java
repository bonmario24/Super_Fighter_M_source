package org.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.StringTokenizer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.AdviceSignature;
import org.aspectj.lang.reflect.CatchClauseSignature;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.InitializerSignature;
import org.aspectj.lang.reflect.LockSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.lang.reflect.UnlockSignature;
import org.aspectj.runtime.reflect.JoinPointImpl;

public final class Factory {
    private static Object[] NO_ARGS = new Object[0];
    private static final String[] NO_STRINGS = new String[0];
    private static final Class[] NO_TYPES = new Class[0];
    static Class class$java$lang$ClassNotFoundException;
    static Hashtable prims = new Hashtable();
    int count = 0;
    String filename;
    Class lexicalClass;
    ClassLoader lookupClassLoader;

    static {
        prims.put("void", Void.TYPE);
        prims.put("boolean", Boolean.TYPE);
        prims.put("byte", Byte.TYPE);
        prims.put("char", Character.TYPE);
        prims.put("short", Short.TYPE);
        prims.put("int", Integer.TYPE);
        prims.put("long", Long.TYPE);
        prims.put("float", Float.TYPE);
        prims.put("double", Double.TYPE);
    }

    static Class makeClass(String s, ClassLoader loader) {
        Class ret;
        if (s.equals("*")) {
            return null;
        }
        Class ret2 = (Class) prims.get(s);
        if (ret2 != null) {
            return ret2;
        }
        if (loader != null) {
            return Class.forName(s, false, loader);
        }
        try {
            return Class.forName(s);
        } catch (ClassNotFoundException e) {
            if (class$java$lang$ClassNotFoundException == null) {
                ret = class$("java.lang.ClassNotFoundException");
                class$java$lang$ClassNotFoundException = ret;
            } else {
                ret = class$java$lang$ClassNotFoundException;
            }
            return ret;
        }
    }

    static Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Factory(String filename2, Class lexicalClass2) {
        this.filename = filename2;
        this.lexicalClass = lexicalClass2;
        this.lookupClassLoader = lexicalClass2.getClassLoader();
    }

    public JoinPoint.StaticPart makeSJP(String kind, String modifiers, String methodName, String declaringType, String paramTypes, String paramNames, String exceptionTypes, String returnType, int l) {
        Signature sig = makeMethodSig(modifiers, methodName, declaringType, paramTypes, paramNames, exceptionTypes, returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(l, -1));
    }

    public JoinPoint.StaticPart makeSJP(String kind, String modifiers, String methodName, String declaringType, String paramTypes, String paramNames, String returnType, int l) {
        Signature sig = makeMethodSig(modifiers, methodName, declaringType, paramTypes, paramNames, "", returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(l, -1));
    }

    public JoinPoint.StaticPart makeMethodSJP(String kind, int modifiers, String methodName, Class declaringType, Class[] paramTypes, String[] paramNames, Class[] exceptionTypes, Class returnType, int line) {
        Class[] clsArr;
        if (paramTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = paramTypes;
        }
        Signature sig = makeMethodSig(modifiers, methodName, declaringType, clsArr, paramNames == null ? NO_STRINGS : paramNames, exceptionTypes == null ? NO_TYPES : exceptionTypes, returnType == null ? Void.TYPE : returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeMethodESJP(String kind, int modifiers, String methodName, Class declaringType, Class[] paramTypes, String[] paramNames, Class[] exceptionTypes, Class returnType, int line) {
        Class[] clsArr;
        if (paramTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = paramTypes;
        }
        Signature sig = makeMethodSig(modifiers, methodName, declaringType, clsArr, paramNames == null ? NO_STRINGS : paramNames, exceptionTypes == null ? NO_TYPES : exceptionTypes, returnType == null ? Void.TYPE : returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeConstructorSJP(String kind, int modifiers, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, int line) {
        Class[] clsArr;
        if (parameterTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = parameterTypes;
        }
        ConstructorSignatureImpl sig = new ConstructorSignatureImpl(modifiers, declaringType, clsArr, parameterNames == null ? NO_STRINGS : parameterNames, exceptionTypes == null ? NO_TYPES : exceptionTypes);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeConstructorESJP(String kind, int modifiers, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, int line) {
        Class[] clsArr;
        if (parameterTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = parameterTypes;
        }
        ConstructorSignatureImpl sig = new ConstructorSignatureImpl(modifiers, declaringType, clsArr, parameterNames == null ? NO_STRINGS : parameterNames, exceptionTypes == null ? NO_TYPES : exceptionTypes);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeCatchClauseSJP(String kind, Class declaringType, Class parameterType, String parameterName, int line) {
        if (parameterName == null) {
            parameterName = "";
        }
        CatchClauseSignatureImpl sig = new CatchClauseSignatureImpl(declaringType, parameterType, parameterName);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeCatchClauseESJP(String kind, Class declaringType, Class parameterType, String parameterName, int line) {
        if (parameterName == null) {
            parameterName = "";
        }
        CatchClauseSignatureImpl sig = new CatchClauseSignatureImpl(declaringType, parameterType, parameterName);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeFieldSJP(String kind, int modifiers, String name, Class declaringType, Class fieldType, int line) {
        FieldSignatureImpl sig = new FieldSignatureImpl(modifiers, name, declaringType, fieldType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeFieldESJP(String kind, int modifiers, String name, Class declaringType, Class fieldType, int line) {
        FieldSignatureImpl sig = new FieldSignatureImpl(modifiers, name, declaringType, fieldType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeInitializerSJP(String kind, int modifiers, Class declaringType, int line) {
        InitializerSignatureImpl sig = new InitializerSignatureImpl(modifiers, declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeInitializerESJP(String kind, int modifiers, Class declaringType, int line) {
        InitializerSignatureImpl sig = new InitializerSignatureImpl(modifiers, declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeLockSJP(String kind, Class declaringType, int line) {
        LockSignatureImpl sig = new LockSignatureImpl(declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeLockESJP(String kind, Class declaringType, int line) {
        LockSignatureImpl sig = new LockSignatureImpl(declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeUnlockSJP(String kind, Class declaringType, int line) {
        UnlockSignatureImpl sig = new UnlockSignatureImpl(declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeUnlockESJP(String kind, Class declaringType, int line) {
        UnlockSignatureImpl sig = new UnlockSignatureImpl(declaringType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeAdviceSJP(String kind, int modifiers, String name, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, Class returnType, int line) {
        Class[] clsArr;
        if (parameterTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = parameterTypes;
        }
        AdviceSignatureImpl sig = new AdviceSignatureImpl(modifiers, name, declaringType, clsArr, parameterNames == null ? NO_STRINGS : parameterNames, exceptionTypes == null ? NO_TYPES : exceptionTypes, returnType == null ? Void.TYPE : returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.EnclosingStaticPart makeAdviceESJP(String kind, int modifiers, String name, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, Class returnType, int line) {
        Class[] clsArr;
        if (parameterTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = parameterTypes;
        }
        AdviceSignatureImpl sig = new AdviceSignatureImpl(modifiers, name, declaringType, clsArr, parameterNames == null ? NO_STRINGS : parameterNames, exceptionTypes == null ? NO_TYPES : exceptionTypes, returnType == null ? Void.TYPE : returnType);
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(line, -1));
    }

    public JoinPoint.StaticPart makeSJP(String kind, Signature sig, SourceLocation loc) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, loc);
    }

    public JoinPoint.StaticPart makeSJP(String kind, Signature sig, int l, int c) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(l, c));
    }

    public JoinPoint.StaticPart makeSJP(String kind, Signature sig, int l) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.StaticPartImpl(i, kind, sig, makeSourceLoc(l, -1));
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String kind, Signature sig, SourceLocation loc) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, loc);
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String kind, Signature sig, int l, int c) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(l, c));
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String kind, Signature sig, int l) {
        int i = this.count;
        this.count = i + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i, kind, sig, makeSourceLoc(l, -1));
    }

    public static JoinPoint.StaticPart makeEncSJP(Member member) {
        Signature sig;
        String kind;
        if (member instanceof Method) {
            Method method = (Method) member;
            sig = new MethodSignatureImpl(method.getModifiers(), method.getName(), method.getDeclaringClass(), method.getParameterTypes(), new String[method.getParameterTypes().length], method.getExceptionTypes(), method.getReturnType());
            kind = JoinPoint.METHOD_EXECUTION;
        } else if (member instanceof Constructor) {
            Constructor cons = (Constructor) member;
            sig = new ConstructorSignatureImpl(cons.getModifiers(), cons.getDeclaringClass(), cons.getParameterTypes(), new String[cons.getParameterTypes().length], cons.getExceptionTypes());
            kind = JoinPoint.CONSTRUCTOR_EXECUTION;
        } else {
            throw new IllegalArgumentException("member must be either a method or constructor");
        }
        return new JoinPointImpl.EnclosingStaticPartImpl(-1, kind, sig, (SourceLocation) null);
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object _this, Object target) {
        return new JoinPointImpl(staticPart, _this, target, NO_ARGS);
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object _this, Object target, Object arg0) {
        return new JoinPointImpl(staticPart, _this, target, new Object[]{arg0});
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object _this, Object target, Object arg0, Object arg1) {
        return new JoinPointImpl(staticPart, _this, target, new Object[]{arg0, arg1});
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object _this, Object target, Object[] args) {
        return new JoinPointImpl(staticPart, _this, target, args);
    }

    public MethodSignature makeMethodSig(String stringRep) {
        MethodSignatureImpl ret = new MethodSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public MethodSignature makeMethodSig(String modifiers, String methodName, String declaringType, String paramTypes, String paramNames, String exceptionTypes, String returnType) {
        return makeMethodSig(modifiers, methodName, makeClass(declaringType, this.lookupClassLoader), paramTypes, paramNames, exceptionTypes, returnType);
    }

    public MethodSignature makeMethodSig(String modifiers, String methodName, Class declaringTypeClass, String paramTypes, String paramNames, String exceptionTypes, String returnType) {
        int modifiersAsInt = Integer.parseInt(modifiers, 16);
        StringTokenizer st = new StringTokenizer(paramTypes, ":");
        int numParams = st.countTokens();
        Class[] paramTypeClasses = new Class[numParams];
        for (int i = 0; i < numParams; i++) {
            paramTypeClasses[i] = makeClass(st.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer st2 = new StringTokenizer(paramNames, ":");
        int numParams2 = st2.countTokens();
        String[] paramNamesArray = new String[numParams2];
        for (int i2 = 0; i2 < numParams2; i2++) {
            paramNamesArray[i2] = st2.nextToken();
        }
        StringTokenizer st3 = new StringTokenizer(exceptionTypes, ":");
        int numParams3 = st3.countTokens();
        Class[] exceptionTypeClasses = new Class[numParams3];
        for (int i3 = 0; i3 < numParams3; i3++) {
            exceptionTypeClasses[i3] = makeClass(st3.nextToken(), this.lookupClassLoader);
        }
        return new MethodSignatureImpl(modifiersAsInt, methodName, declaringTypeClass, paramTypeClasses, paramNamesArray, exceptionTypeClasses, makeClass(returnType, this.lookupClassLoader));
    }

    public MethodSignature makeMethodSig(int modifiers, String name, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, Class returnType) {
        Class[] clsArr;
        if (parameterTypes == null) {
            clsArr = NO_TYPES;
        } else {
            clsArr = parameterTypes;
        }
        MethodSignatureImpl ret = new MethodSignatureImpl(modifiers, name, declaringType, clsArr, parameterNames, exceptionTypes == null ? NO_TYPES : exceptionTypes, returnType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public ConstructorSignature makeConstructorSig(String stringRep) {
        ConstructorSignatureImpl ret = new ConstructorSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public ConstructorSignature makeConstructorSig(String modifiers, String declaringType, String paramTypes, String paramNames, String exceptionTypes) {
        int modifiersAsInt = Integer.parseInt(modifiers, 16);
        Class declaringTypeClass = makeClass(declaringType, this.lookupClassLoader);
        StringTokenizer st = new StringTokenizer(paramTypes, ":");
        int numParams = st.countTokens();
        Class[] paramTypeClasses = new Class[numParams];
        for (int i = 0; i < numParams; i++) {
            paramTypeClasses[i] = makeClass(st.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer st2 = new StringTokenizer(paramNames, ":");
        int numParams2 = st2.countTokens();
        String[] paramNamesArray = new String[numParams2];
        for (int i2 = 0; i2 < numParams2; i2++) {
            paramNamesArray[i2] = st2.nextToken();
        }
        StringTokenizer st3 = new StringTokenizer(exceptionTypes, ":");
        int numParams3 = st3.countTokens();
        Class[] exceptionTypeClasses = new Class[numParams3];
        for (int i3 = 0; i3 < numParams3; i3++) {
            exceptionTypeClasses[i3] = makeClass(st3.nextToken(), this.lookupClassLoader);
        }
        ConstructorSignatureImpl ret = new ConstructorSignatureImpl(modifiersAsInt, declaringTypeClass, paramTypeClasses, paramNamesArray, exceptionTypeClasses);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public ConstructorSignature makeConstructorSig(int modifiers, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes) {
        ConstructorSignatureImpl ret = new ConstructorSignatureImpl(modifiers, declaringType, parameterTypes, parameterNames, exceptionTypes);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public FieldSignature makeFieldSig(String stringRep) {
        FieldSignatureImpl ret = new FieldSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public FieldSignature makeFieldSig(String modifiers, String name, String declaringType, String fieldType) {
        FieldSignatureImpl ret = new FieldSignatureImpl(Integer.parseInt(modifiers, 16), name, makeClass(declaringType, this.lookupClassLoader), makeClass(fieldType, this.lookupClassLoader));
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public FieldSignature makeFieldSig(int modifiers, String name, Class declaringType, Class fieldType) {
        FieldSignatureImpl ret = new FieldSignatureImpl(modifiers, name, declaringType, fieldType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public AdviceSignature makeAdviceSig(String stringRep) {
        AdviceSignatureImpl ret = new AdviceSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public AdviceSignature makeAdviceSig(String modifiers, String name, String declaringType, String paramTypes, String paramNames, String exceptionTypes, String returnType) {
        int modifiersAsInt = Integer.parseInt(modifiers, 16);
        Class declaringTypeClass = makeClass(declaringType, this.lookupClassLoader);
        StringTokenizer st = new StringTokenizer(paramTypes, ":");
        int numParams = st.countTokens();
        Class[] paramTypeClasses = new Class[numParams];
        for (int i = 0; i < numParams; i++) {
            paramTypeClasses[i] = makeClass(st.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer st2 = new StringTokenizer(paramNames, ":");
        int numParams2 = st2.countTokens();
        String[] paramNamesArray = new String[numParams2];
        for (int i2 = 0; i2 < numParams2; i2++) {
            paramNamesArray[i2] = st2.nextToken();
        }
        StringTokenizer st3 = new StringTokenizer(exceptionTypes, ":");
        int numParams3 = st3.countTokens();
        Class[] exceptionTypeClasses = new Class[numParams3];
        for (int i3 = 0; i3 < numParams3; i3++) {
            exceptionTypeClasses[i3] = makeClass(st3.nextToken(), this.lookupClassLoader);
        }
        AdviceSignatureImpl ret = new AdviceSignatureImpl(modifiersAsInt, name, declaringTypeClass, paramTypeClasses, paramNamesArray, exceptionTypeClasses, makeClass(returnType, this.lookupClassLoader));
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public AdviceSignature makeAdviceSig(int modifiers, String name, Class declaringType, Class[] parameterTypes, String[] parameterNames, Class[] exceptionTypes, Class returnType) {
        AdviceSignatureImpl ret = new AdviceSignatureImpl(modifiers, name, declaringType, parameterTypes, parameterNames, exceptionTypes, returnType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public InitializerSignature makeInitializerSig(String stringRep) {
        InitializerSignatureImpl ret = new InitializerSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public InitializerSignature makeInitializerSig(String modifiers, String declaringType) {
        InitializerSignatureImpl ret = new InitializerSignatureImpl(Integer.parseInt(modifiers, 16), makeClass(declaringType, this.lookupClassLoader));
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public InitializerSignature makeInitializerSig(int modifiers, Class declaringType) {
        InitializerSignatureImpl ret = new InitializerSignatureImpl(modifiers, declaringType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public CatchClauseSignature makeCatchClauseSig(String stringRep) {
        CatchClauseSignatureImpl ret = new CatchClauseSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public CatchClauseSignature makeCatchClauseSig(String declaringType, String parameterType, String parameterName) {
        CatchClauseSignatureImpl ret = new CatchClauseSignatureImpl(makeClass(declaringType, this.lookupClassLoader), makeClass(new StringTokenizer(parameterType, ":").nextToken(), this.lookupClassLoader), new StringTokenizer(parameterName, ":").nextToken());
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public CatchClauseSignature makeCatchClauseSig(Class declaringType, Class parameterType, String parameterName) {
        CatchClauseSignatureImpl ret = new CatchClauseSignatureImpl(declaringType, parameterType, parameterName);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public LockSignature makeLockSig(String stringRep) {
        LockSignatureImpl ret = new LockSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public LockSignature makeLockSig() {
        LockSignatureImpl ret = new LockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public LockSignature makeLockSig(Class declaringType) {
        LockSignatureImpl ret = new LockSignatureImpl(declaringType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public UnlockSignature makeUnlockSig(String stringRep) {
        UnlockSignatureImpl ret = new UnlockSignatureImpl(stringRep);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public UnlockSignature makeUnlockSig() {
        UnlockSignatureImpl ret = new UnlockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public UnlockSignature makeUnlockSig(Class declaringType) {
        UnlockSignatureImpl ret = new UnlockSignatureImpl(declaringType);
        ret.setLookupClassLoader(this.lookupClassLoader);
        return ret;
    }

    public SourceLocation makeSourceLoc(int line, int col) {
        return new SourceLocationImpl(this.lexicalClass, this.filename, line);
    }
}
