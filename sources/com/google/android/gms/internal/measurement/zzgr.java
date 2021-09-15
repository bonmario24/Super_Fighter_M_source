package com.google.android.gms.internal.measurement;

import com.doraemon.util.ShellAdbUtil;
import com.google.android.gms.internal.measurement.zzfe;
import com.lzy.okgo.cache.CacheEntity;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
final class zzgr {
    static String zza(zzgm zzgm, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzgm, sb, 0);
        return sb.toString();
    }

    private static void zza(zzgm zzgm, StringBuilder sb, int i) {
        boolean booleanValue;
        boolean z;
        String str;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzgm.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str2 : treeSet) {
            String substring = str2.startsWith("get") ? str2.substring(3) : str2;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str2);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i, zza(concat), zzfe.zza(method2, (Object) zzgm, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                if (valueOf4.length() != 0) {
                    str = valueOf3.concat(valueOf4);
                } else {
                    str = new String(valueOf3);
                }
                Method method3 = (Method) hashMap.get(str2);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, i, zza(str), zzfe.zza(method3, (Object) zzgm, new Object[0]));
                }
            }
            String valueOf5 = String.valueOf(substring);
            if (((Method) hashMap2.get(valueOf5.length() != 0 ? "set".concat(valueOf5) : new String("set"))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf6 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (hashMap.containsKey(valueOf6.length() != 0 ? "get".concat(valueOf6) : new String("get"))) {
                    }
                }
                String valueOf7 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf8 = String.valueOf(substring.substring(1));
                String concat2 = valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7);
                String valueOf9 = String.valueOf(substring);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? "get".concat(valueOf9) : new String("get"));
                String valueOf10 = String.valueOf(substring);
                Method method5 = (Method) hashMap.get(valueOf10.length() != 0 ? "has".concat(valueOf10) : new String("has"));
                if (method4 != null) {
                    Object zza = zzfe.zza(method4, (Object) zzgm, new Object[0]);
                    if (method5 == null) {
                        if (zza instanceof Boolean) {
                            z = !((Boolean) zza).booleanValue();
                        } else if (zza instanceof Integer) {
                            z = ((Integer) zza).intValue() == 0;
                        } else if (zza instanceof Float) {
                            z = ((Float) zza).floatValue() == 0.0f;
                        } else if (zza instanceof Double) {
                            z = ((Double) zza).doubleValue() == 0.0d;
                        } else if (zza instanceof String) {
                            z = zza.equals("");
                        } else if (zza instanceof zzdw) {
                            z = zza.equals(zzdw.zza);
                        } else if (zza instanceof zzgm) {
                            z = zza == ((zzgm) zza).mo23726h_();
                        } else {
                            z = zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        }
                        if (!z) {
                            booleanValue = true;
                        } else {
                            booleanValue = false;
                        }
                    } else {
                        booleanValue = ((Boolean) zzfe.zza(method5, (Object) zzgm, new Object[0])).booleanValue();
                    }
                    if (booleanValue) {
                        zza(sb, i, zza(concat2), zza);
                    }
                }
            }
        }
        if (zzgm instanceof zzfe.zzb) {
            Iterator<Map.Entry<zzfe.zze, Object>> zzd = ((zzfe.zzb) zzgm).zzc.zzd();
            if (zzd.hasNext()) {
                zzfe.zze zze = (zzfe.zze) zzd.next().getKey();
                throw new NoSuchMethodError();
            }
        }
        if (((zzfe) zzgm).zzb != null) {
            ((zzfe) zzgm).zzb.zza(sb, i);
        }
    }

    static final void zza(StringBuilder sb, int i, String str, Object obj) {
        int i2 = 0;
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(sb, i, str, zza);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zza2 : ((Map) obj).entrySet()) {
                zza(sb, i, str, zza2);
            }
        } else {
            sb.append(10);
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"").append(zzht.zza(zzdw.zza((String) obj))).append('\"');
            } else if (obj instanceof zzdw) {
                sb.append(": \"").append(zzht.zza((zzdw) obj)).append('\"');
            } else if (obj instanceof zzfe) {
                sb.append(" {");
                zza((zzfe) obj, sb, i + 2);
                sb.append(ShellAdbUtil.COMMAND_LINE_END);
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                zza(sb, i + 2, CacheEntity.KEY, entry.getKey());
                zza(sb, i + 2, "value", entry.getValue());
                sb.append(ShellAdbUtil.COMMAND_LINE_END);
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ").append(obj.toString());
            }
        }
    }

    private static final String zza(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
