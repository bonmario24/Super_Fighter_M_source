package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.TypeUtils;
import com.facebook.share.internal.ShareConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig mapping, Class<?> clazz) {
        super(mapping, clazz, clazz);
    }

    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Throwable ex;
        Throwable ex2;
        JSONLexer lexer = parser.lexer;
        if (lexer.token == 8) {
            lexer.nextToken();
            return null;
        }
        if (parser.resolveStatus == 2) {
            parser.resolveStatus = 0;
        } else if (lexer.token != 12) {
            throw new JSONException("syntax error");
        }
        Throwable cause = null;
        Class<?> exClass = null;
        if (type != null && (type instanceof Class)) {
            Class<?> clazz = (Class) type;
            if (Throwable.class.isAssignableFrom(clazz)) {
                exClass = clazz;
            }
        }
        String message = null;
        StackTraceElement[] stackTrace = null;
        HashMap hashMap = new HashMap();
        while (true) {
            String key = lexer.scanSymbol(parser.symbolTable);
            if (key == null) {
                if (lexer.token == 13) {
                    lexer.nextToken(16);
                    break;
                } else if (lexer.token == 16 && (lexer.features & Feature.AllowArbitraryCommas.mask) != 0) {
                }
            }
            lexer.nextTokenWithChar(':');
            if (JSON.DEFAULT_TYPE_KEY.equals(key)) {
                if (lexer.token == 4) {
                    exClass = TypeUtils.loadClass(lexer.stringVal(), parser.config.defaultClassLoader);
                    lexer.nextToken(16);
                } else {
                    throw new JSONException("syntax error");
                }
            } else if (ShareConstants.WEB_DIALOG_PARAM_MESSAGE.equals(key)) {
                if (lexer.token == 8) {
                    message = null;
                } else if (lexer.token == 4) {
                    message = lexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
                lexer.nextToken();
            } else if ("cause".equals(key)) {
                cause = (Throwable) deserialze(parser, (Type) null, "cause");
            } else if ("stackTrace".equals(key)) {
                stackTrace = (StackTraceElement[]) parser.parseObject(StackTraceElement[].class);
            } else {
                hashMap.put(key, parser.parse());
            }
            if (lexer.token == 13) {
                lexer.nextToken(16);
                break;
            }
        }
        if (exClass == null) {
            ex2 = new Exception(message, cause);
        } else {
            Constructor<?> defaultConstructor = null;
            Constructor<?> messageConstructor = null;
            Constructor<?> causeConstructor = null;
            try {
                Constructor<?>[] constructors = exClass.getConstructors();
                int length = constructors.length;
                for (int i = 0; i < length; i++) {
                    Constructor<?> constructor = constructors[i];
                    if (constructor.getParameterTypes().length == 0) {
                        defaultConstructor = constructor;
                    } else if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0] == String.class) {
                        messageConstructor = constructor;
                    } else if (constructor.getParameterTypes().length == 2 && constructor.getParameterTypes()[0] == String.class && constructor.getParameterTypes()[1] == Throwable.class) {
                        causeConstructor = constructor;
                    }
                }
                if (causeConstructor != null) {
                    ex = (Throwable) causeConstructor.newInstance(new Object[]{message, cause});
                } else if (messageConstructor != null) {
                    ex = (Throwable) messageConstructor.newInstance(new Object[]{message});
                } else if (defaultConstructor != null) {
                    ex = (Throwable) defaultConstructor.newInstance(new Object[0]);
                } else {
                    ex = null;
                }
                if (ex == null) {
                    try {
                        ex2 = new Exception(message, cause);
                    } catch (Exception e) {
                        e = e;
                        Throwable th = ex;
                    }
                } else {
                    ex2 = ex;
                }
            } catch (Exception e2) {
                e = e2;
                throw new JSONException("create instance error", e);
            }
        }
        if (stackTrace == null) {
            return ex2;
        }
        ex2.setStackTrace(stackTrace);
        return ex2;
    }
}
