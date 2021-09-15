package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzg();
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzb();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzc();
    @GuardedBy("DynamiteModule.class")
    private static Boolean zziu;
    @GuardedBy("DynamiteModule.class")
    private static zzj zziv;
    @GuardedBy("DynamiteModule.class")
    private static zzl zziw;
    @GuardedBy("DynamiteModule.class")
    private static String zzix;
    @GuardedBy("DynamiteModule.class")
    private static int zziy = -1;
    private static final ThreadLocal<zza> zziz = new ThreadLocal<>();
    private static final VersionPolicy.zzb zzja = new zza();
    private static final VersionPolicy zzjb = new zzf();
    private final Context zzjc;

    @DynamiteApi
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
        public static class zza {
            public int zzjg = 0;
            public int zzjh = 0;
            public int zzji = 0;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
        public interface zzb {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        zza zza(Context context, String str, zzb zzb2) throws LoadingException;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    private static class zza {
        public Cursor zzjd;

        private zza() {
        }

        /* synthetic */ zza(zza zza) {
            this();
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        VersionPolicy.zza zza2;
        zza zza3 = zziz.get();
        zza zza4 = new zza((zza) null);
        zziz.set(zza4);
        try {
            zza2 = versionPolicy.zza(context, str, zzja);
            Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza2.zzjg).append(" and remote module ").append(str).append(":").append(zza2.zzjh).toString());
            if (zza2.zzji == 0 || ((zza2.zzji == -1 && zza2.zzjg == 0) || (zza2.zzji == 1 && zza2.zzjh == 0))) {
                throw new LoadingException(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza2.zzjg).append(" and remote version is ").append(zza2.zzjh).append(".").toString(), (zza) null);
            } else if (zza2.zzji == -1) {
                DynamiteModule zze = zze(context, str);
                if (zza4.zzjd != null) {
                    zza4.zzjd.close();
                }
                zziz.set(zza3);
                return zze;
            } else if (zza2.zzji == 1) {
                DynamiteModule zza5 = zza(context, str, zza2.zzjh);
                if (zza4.zzjd != null) {
                    zza4.zzjd.close();
                }
                zziz.set(zza3);
                return zza5;
            } else {
                throw new LoadingException(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza2.zzji).toString(), (zza) null);
            }
        } catch (LoadingException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
            if (zza2.zzjg == 0 || versionPolicy.zza(context, str, new zzb(zza2.zzjg, 0)).zzji != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", e, (zza) null);
            }
            DynamiteModule zze2 = zze(context, str);
            if (zza4.zzjd != null) {
                zza4.zzjd.close();
            }
            zziz.set(zza3);
            return zze2;
        } catch (Throwable th) {
            if (zza4.zzjd != null) {
                zza4.zzjd.close();
            }
            zziz.set(zza3);
            throw th;
        }
    }

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, zza zza) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zza) {
            this(str, th);
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
    private static class zzb implements VersionPolicy.zzb {
        private final int zzje;
        private final int zzjf = 0;

        public zzb(int i, int i2) {
            this.zzje = i;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        public final int getLocalVersion(Context context, String str) {
            return this.zzje;
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(str).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".ModuleDescriptor").toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            Log.e("DynamiteModule", new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x0071=Splitter:B:37:0x0071, B:27:0x0043=Splitter:B:27:0x0043} */
    public static int zza(android.content.Context r7, java.lang.String r8, boolean r9) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r2 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r2)     // Catch:{ Throwable -> 0x0077 }
            java.lang.Boolean r0 = zziu     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r1 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r1 = r1.getName()     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            java.lang.Class r3 = r0.loadClass(r1)     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            java.lang.String r0 = "sClassLoader"
            java.lang.reflect.Field r4 = r3.getDeclaredField(r0)     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            monitor-enter(r3)     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
            r0 = 0
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x00b4 }
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x0046
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00b4 }
            if (r0 != r1) goto L_0x0040
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00b4 }
        L_0x0031:
            monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
        L_0x0032:
            zziu = r0     // Catch:{ all -> 0x0074 }
        L_0x0034:
            monitor-exit(r2)     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0077 }
            if (r0 == 0) goto L_0x0105
            int r0 = zzc(r7, r8, r9)     // Catch:{ LoadingException -> 0x00e2 }
        L_0x003f:
            return r0
        L_0x0040:
            zza(r0)     // Catch:{ LoadingException -> 0x010b }
        L_0x0043:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00b4 }
            goto L_0x0031
        L_0x0046:
            java.lang.String r0 = "com.google.android.gms"
            android.content.Context r1 = r7.getApplicationContext()     // Catch:{ all -> 0x00b4 }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x00b4 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x0061
            r0 = 0
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00b4 }
            r4.set(r0, r1)     // Catch:{ all -> 0x00b4 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00b4 }
            goto L_0x0031
        L_0x0061:
            int r0 = zzc(r7, r8, r9)     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.String r1 = zzix     // Catch:{ LoadingException -> 0x00a7 }
            if (r1 == 0) goto L_0x0071
            java.lang.String r1 = zzix     // Catch:{ LoadingException -> 0x00a7 }
            boolean r1 = r1.isEmpty()     // Catch:{ LoadingException -> 0x00a7 }
            if (r1 == 0) goto L_0x007c
        L_0x0071:
            monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r2)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x0074:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0074 }
            throw r0     // Catch:{ Throwable -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r7, r0)
            throw r0
        L_0x007c:
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ LoadingException -> 0x00a7 }
            r5 = 29
            if (r1 < r5) goto L_0x009b
            dalvik.system.DelegateLastClassLoader r1 = new dalvik.system.DelegateLastClassLoader     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.String r5 = zzix     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x00a7 }
            r1.<init>(r5, r6)     // Catch:{ LoadingException -> 0x00a7 }
        L_0x008d:
            zza(r1)     // Catch:{ LoadingException -> 0x00a7 }
            r5 = 0
            r4.set(r5, r1)     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ LoadingException -> 0x00a7 }
            zziu = r1     // Catch:{ LoadingException -> 0x00a7 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r2)     // Catch:{ all -> 0x0074 }
            goto L_0x003f
        L_0x009b:
            com.google.android.gms.dynamite.zzh r1 = new com.google.android.gms.dynamite.zzh     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.String r5 = zzix     // Catch:{ LoadingException -> 0x00a7 }
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x00a7 }
            r1.<init>(r5, r6)     // Catch:{ LoadingException -> 0x00a7 }
            goto L_0x008d
        L_0x00a7:
            r0 = move-exception
            r0 = 0
            java.lang.ClassLoader r1 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x00b4 }
            r4.set(r0, r1)     // Catch:{ all -> 0x00b4 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00b4 }
            goto L_0x0031
        L_0x00b4:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00b4 }
            throw r0     // Catch:{ ClassNotFoundException -> 0x00b7, IllegalAccessException -> 0x0110, NoSuchFieldException -> 0x010e }
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0074 }
            int r3 = r3.length()     // Catch:{ all -> 0x0074 }
            int r3 = r3 + 30
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
            r4.<init>(r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r3 = "Failed to load module via V2: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x0074 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x0074 }
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0074 }
            goto L_0x0032
        L_0x00e2:
            r0 = move-exception
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ Throwable -> 0x0077 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0077 }
            int r3 = r0.length()     // Catch:{ Throwable -> 0x0077 }
            if (r3 == 0) goto L_0x00ff
            java.lang.String r0 = r2.concat(r0)     // Catch:{ Throwable -> 0x0077 }
        L_0x00f9:
            android.util.Log.w(r1, r0)     // Catch:{ Throwable -> 0x0077 }
            r0 = 0
            goto L_0x003f
        L_0x00ff:
            java.lang.String r0 = new java.lang.String     // Catch:{ Throwable -> 0x0077 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x00f9
        L_0x0105:
            int r0 = zzb((android.content.Context) r7, (java.lang.String) r8, (boolean) r9)     // Catch:{ Throwable -> 0x0077 }
            goto L_0x003f
        L_0x010b:
            r0 = move-exception
            goto L_0x0043
        L_0x010e:
            r0 = move-exception
            goto L_0x00b8
        L_0x0110:
            r0 = move-exception
            goto L_0x00b8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    private static int zzb(Context context, String str, boolean z) {
        zzj zzk = zzk(context);
        if (zzk == null) {
            return 0;
        }
        try {
            if (zzk.zzak() >= 2) {
                return zzk.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzk.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzc(android.content.Context r7, java.lang.String r8, boolean r9) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r6 = 0
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            if (r9 == 0) goto L_0x006a
            java.lang.String r1 = "api_force_staging"
        L_0x0009:
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2 + 42
            java.lang.String r3 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r3 = r3.length()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r2 = "content://com.google.android.gms.chimera/"
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r2 = "/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00b3, all -> 0x00b0 }
            if (r1 == 0) goto L_0x004d
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x005d }
            if (r0 != 0) goto L_0x006d
        L_0x004d:
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r2 = "Failed to retrieve remote module version."
            android.util.Log.w(r0, r2)     // Catch:{ Exception -> 0x005d }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x005d }
            java.lang.String r2 = "Failed to connect to dynamite module ContentResolver."
            r3 = 0
            r0.<init>((java.lang.String) r2, (com.google.android.gms.dynamite.zza) r3)     // Catch:{ Exception -> 0x005d }
            throw r0     // Catch:{ Exception -> 0x005d }
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            boolean r2 = r0 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x0063 }
            if (r2 == 0) goto L_0x00a7
            throw r0     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            if (r1 == 0) goto L_0x0069
            r1.close()
        L_0x0069:
            throw r0
        L_0x006a:
            java.lang.String r1 = "api"
            goto L_0x0009
        L_0x006d:
            r0 = 0
            int r2 = r1.getInt(r0)     // Catch:{ Exception -> 0x005d }
            if (r2 <= 0) goto L_0x009e
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r3 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r3)     // Catch:{ Exception -> 0x005d }
            r0 = 2
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x00a4 }
            zzix = r0     // Catch:{ all -> 0x00a4 }
            java.lang.String r0 = "loaderVersion"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ all -> 0x00a4 }
            if (r0 < 0) goto L_0x008c
            int r0 = r1.getInt(r0)     // Catch:{ all -> 0x00a4 }
            zziy = r0     // Catch:{ all -> 0x00a4 }
        L_0x008c:
            monitor-exit(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r0 = zziz     // Catch:{ Exception -> 0x005d }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x005d }
            com.google.android.gms.dynamite.DynamiteModule$zza r0 = (com.google.android.gms.dynamite.DynamiteModule.zza) r0     // Catch:{ Exception -> 0x005d }
            if (r0 == 0) goto L_0x009e
            android.database.Cursor r3 = r0.zzjd     // Catch:{ Exception -> 0x005d }
            if (r3 != 0) goto L_0x009e
            r0.zzjd = r1     // Catch:{ Exception -> 0x005d }
            r1 = r6
        L_0x009e:
            if (r1 == 0) goto L_0x00a3
            r1.close()
        L_0x00a3:
            return r2
        L_0x00a4:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00a4 }
            throw r0     // Catch:{ Exception -> 0x005d }
        L_0x00a7:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r2 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x0063 }
            java.lang.String r3 = "V2 version check failed"
            r4 = 0
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x0063 }
            throw r2     // Catch:{ all -> 0x0063 }
        L_0x00b0:
            r0 = move-exception
            r1 = r6
            goto L_0x0064
        L_0x00b3:
            r0 = move-exception
            r1 = r6
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.dynamite.DynamiteModule zza(android.content.Context r4, java.lang.String r5, int r6) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r3 = 0
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.Boolean r0 = zziu     // Catch:{ all -> 0x001b }
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0020
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r1 = "Failed to determine which loading route to use."
            r2 = 0
            r0.<init>((java.lang.String) r1, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            throw r0     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x0012:
            r0 = move-exception
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r1 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            java.lang.String r2 = "Failed to load remote module."
            r1.<init>(r2, r0, r3)
            throw r1
        L_0x001b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r0     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x001e:
            r0 = move-exception
            throw r0
        L_0x0020:
            boolean r0 = r0.booleanValue()     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.dynamite.DynamiteModule r0 = zzb((android.content.Context) r4, (java.lang.String) r5, (int) r6)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x002a:
            return r0
        L_0x002b:
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r5)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            int r1 = r1.length()     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            int r1 = r1 + 51
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            r2.<init>(r1)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r1 = "Selected remote version of "
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r2 = ", version >= "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.StringBuilder r1 = r1.append(r6)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r1 = r1.toString()     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            android.util.Log.i(r0, r1)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            com.google.android.gms.dynamite.zzj r0 = zzk(r4)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            if (r0 != 0) goto L_0x0072
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r1 = "Failed to create IDynamiteLoader."
            r2 = 0
            r0.<init>((java.lang.String) r1, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            throw r0     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x0066:
            r0 = move-exception
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r4, r0)
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r1 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            java.lang.String r2 = "Failed to load remote module."
            r1.<init>(r2, r0, r3)
            throw r1
        L_0x0072:
            int r1 = r0.zzak()     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            r2 = 2
            if (r1 < r2) goto L_0x0090
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r4)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zzb((com.google.android.gms.dynamic.IObjectWrapper) r1, (java.lang.String) r5, (int) r6)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x0081:
            java.lang.Object r1 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            if (r1 != 0) goto L_0x00a0
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.String r1 = "Failed to load remote module."
            r2 = 0
            r0.<init>((java.lang.String) r1, (com.google.android.gms.dynamite.zza) r2)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            throw r0     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
        L_0x0090:
            java.lang.String r1 = "DynamiteModule"
            java.lang.String r2 = "Dynamite loader version < 2, falling back to createModuleContext"
            android.util.Log.w(r1, r2)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r4)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r0.zza((com.google.android.gms.dynamic.IObjectWrapper) r1, (java.lang.String) r5, (int) r6)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            goto L_0x0081
        L_0x00a0:
            com.google.android.gms.dynamite.DynamiteModule r1 = new com.google.android.gms.dynamite.DynamiteModule     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            java.lang.Object r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            r1.<init>(r0)     // Catch:{ RemoteException -> 0x0012, LoadingException -> 0x001e, Throwable -> 0x0066 }
            r0 = r1
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, int):com.google.android.gms.dynamite.DynamiteModule");
    }

    private static zzj zzk(Context context) {
        zzj zzi;
        synchronized (DynamiteModule.class) {
            if (zziv != null) {
                zzj zzj = zziv;
                return zzj;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzi = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof zzj) {
                        zzi = (zzj) queryLocalInterface;
                    } else {
                        zzi = new zzi(iBinder);
                    }
                }
                if (zzi != null) {
                    zziv = zzi;
                    return zzi;
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
                return null;
            }
        }
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzjc;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzl zzl;
        IObjectWrapper zza2;
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            zzl = zziw;
        }
        if (zzl == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
        }
        zza zza3 = zziz.get();
        if (zza3 == null || zza3.zzjd == null) {
            throw new LoadingException("No result cursor", (zza) null);
        }
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = zza3.zzjd;
        ObjectWrapper.wrap(null);
        if (zzaj().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            zza2 = zzl.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            zza2 = zzl.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        }
        Context context2 = (Context) ObjectWrapper.unwrap(zza2);
        if (context2 != null) {
            return new DynamiteModule(context2);
        }
        throw new LoadingException("Failed to get module context", (zza) null);
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zziy >= 2);
        }
        return valueOf;
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzl zzk;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzk = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzl) {
                    zzk = (zzl) queryLocalInterface;
                } else {
                    zzk = new zzk(iBinder);
                }
            }
            zziw = zzk;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, (zza) null);
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzjc.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, (zza) null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzjc = (Context) Preconditions.checkNotNull(context);
    }
}
