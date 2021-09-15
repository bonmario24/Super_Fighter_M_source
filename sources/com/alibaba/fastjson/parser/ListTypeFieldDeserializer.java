package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class ListTypeFieldDeserializer extends FieldDeserializer {
    private final boolean array;
    private ObjectDeserializer deserializer;
    private final Type itemType;

    public ListTypeFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo, 14);
        Type fieldType = fieldInfo.fieldType;
        Class<?> fieldClass = fieldInfo.fieldClass;
        if (fieldType instanceof ParameterizedType) {
            this.itemType = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
            this.array = false;
        } else if (fieldClass.isArray()) {
            this.itemType = fieldClass.getComponentType();
            this.array = true;
        } else {
            this.itemType = Object.class;
            this.array = false;
        }
    }

    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        List list;
        Object obj;
        if (parser.lexer.token == 8) {
            setValue(object, (Object) null);
            parser.lexer.nextToken();
            return;
        }
        JSONArray jsonArray = null;
        if (this.array) {
            jsonArray = new JSONArray();
            list = jsonArray;
            jsonArray.setComponentType(this.itemType);
        } else {
            list = new ArrayList();
        }
        ParseContext context = parser.contex;
        parser.setContext(context, object, this.fieldInfo.name);
        parseArray(parser, objectType, list);
        parser.setContext(context);
        if (this.array) {
            obj = list.toArray((Object[]) Array.newInstance((Class) this.itemType, list.size()));
            jsonArray.setRelatedArray(obj);
        } else {
            obj = list;
        }
        if (object == null) {
            fieldValues.put(this.fieldInfo.name, obj);
        } else {
            setValue(object, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void parseArray(DefaultJSONParser parser, Type objectType, Collection array2) {
        char charAt;
        char charAt2;
        char charAt3;
        char charAt4;
        char charAt5;
        char charAt6;
        Type itemType2 = this.itemType;
        ObjectDeserializer itemTypeDeser = this.deserializer;
        if ((itemType2 instanceof TypeVariable) && (objectType instanceof ParameterizedType)) {
            TypeVariable typeVar = (TypeVariable) itemType2;
            ParameterizedType paramType = (ParameterizedType) objectType;
            Class<?> objectClass = null;
            if (paramType.getRawType() instanceof Class) {
                objectClass = (Class) paramType.getRawType();
            }
            int paramIndex = -1;
            if (objectClass != null) {
                int i = 0;
                int size = objectClass.getTypeParameters().length;
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (objectClass.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                        paramIndex = i;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (paramIndex != -1) {
                itemType2 = paramType.getActualTypeArguments()[paramIndex];
                if (!itemType2.equals(this.itemType)) {
                    itemTypeDeser = parser.config.getDeserializer(itemType2);
                }
            }
        }
        JSONLexer lexer = parser.lexer;
        if (lexer.token != 14) {
            String errorMessage = "exepct '[', but " + JSONToken.name(lexer.token);
            if (objectType != null) {
                errorMessage = errorMessage + ", type : " + objectType;
            }
            throw new JSONException(errorMessage);
        }
        if (itemTypeDeser == null) {
            itemTypeDeser = parser.config.getDeserializer(itemType2);
            this.deserializer = itemTypeDeser;
        }
        int ch = lexer.f339ch;
        if (ch == 91) {
            int index = lexer.f338bp + 1;
            lexer.f338bp = index;
            if (index >= lexer.len) {
                charAt6 = JSONLexer.EOI;
            } else {
                charAt6 = lexer.text.charAt(index);
            }
            lexer.f339ch = charAt6;
            lexer.token = 14;
        } else if (ch == 123) {
            int index2 = lexer.f338bp + 1;
            lexer.f338bp = index2;
            if (index2 >= lexer.len) {
                charAt2 = JSONLexer.EOI;
            } else {
                charAt2 = lexer.text.charAt(index2);
            }
            lexer.f339ch = charAt2;
            lexer.token = 12;
        } else if (ch == 34) {
            lexer.scanString();
        } else if (ch == 93) {
            int index3 = lexer.f338bp + 1;
            lexer.f338bp = index3;
            if (index3 >= lexer.len) {
                charAt = JSONLexer.EOI;
            } else {
                charAt = lexer.text.charAt(index3);
            }
            lexer.f339ch = charAt;
            lexer.token = 15;
        } else {
            lexer.nextToken();
        }
        int i2 = 0;
        while (true) {
            if (lexer.token == 16 && (lexer.features & Feature.AllowArbitraryCommas.mask) != 0) {
                lexer.nextToken();
            } else if (lexer.token == 15) {
                break;
            } else {
                array2.add(itemTypeDeser.deserialze(parser, itemType2, Integer.valueOf(i2)));
                if (parser.resolveStatus == 1) {
                    parser.checkListResolve(array2);
                }
                if (lexer.token == 16) {
                    int ch2 = lexer.f339ch;
                    if (ch2 == 91) {
                        int index4 = lexer.f338bp + 1;
                        lexer.f338bp = index4;
                        if (index4 >= lexer.len) {
                            charAt5 = JSONLexer.EOI;
                        } else {
                            charAt5 = lexer.text.charAt(index4);
                        }
                        lexer.f339ch = charAt5;
                        lexer.token = 14;
                    } else if (ch2 == 123) {
                        int index5 = lexer.f338bp + 1;
                        lexer.f338bp = index5;
                        if (index5 >= lexer.len) {
                            charAt4 = JSONLexer.EOI;
                        } else {
                            charAt4 = lexer.text.charAt(index5);
                        }
                        lexer.f339ch = charAt4;
                        lexer.token = 12;
                    } else if (ch2 == 34) {
                        lexer.scanString();
                    } else {
                        lexer.nextToken();
                    }
                }
                i2++;
            }
        }
        if (lexer.f339ch == ',') {
            int index6 = lexer.f338bp + 1;
            lexer.f338bp = index6;
            if (index6 >= lexer.len) {
                charAt3 = JSONLexer.EOI;
            } else {
                charAt3 = lexer.text.charAt(index6);
            }
            lexer.f339ch = charAt3;
            lexer.token = 16;
            return;
        }
        lexer.nextToken();
    }
}
