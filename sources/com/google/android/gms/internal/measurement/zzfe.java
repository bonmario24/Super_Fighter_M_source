package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzfe.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public abstract class zzfe<MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdm<MessageType, BuilderType> {
    private static Map<Object, zzfe<?, ?>> zzd = new ConcurrentHashMap();
    protected zzhw zzb = zzhw.zza();
    private int zzc = -1;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static class zzc<T extends zzfe<T, ?>> extends zzdn<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static class zzd<ContainingType extends zzgm, Type> extends zzep<ContainingType, Type> {
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    static final class zze implements zzew<zze> {
        public final int zza() {
            throw new NoSuchMethodError();
        }

        public final zzik zzb() {
            throw new NoSuchMethodError();
        }

        public final zzir zzc() {
            throw new NoSuchMethodError();
        }

        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        public final zzgp zza(zzgp zzgp, zzgm zzgm) {
            throw new NoSuchMethodError();
        }

        public final zzgv zza(zzgv zzgv, zzgv zzgv2) {
            throw new NoSuchMethodError();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static final class zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        public static final int zzh = 1;
        public static final int zzi = 2;
        public static final int zzj = 1;
        public static final int zzk = 2;
        private static final /* synthetic */ int[] zzl = {zza, zzb, zzc, zzd, zze, zzf, zzg};
        private static final /* synthetic */ int[] zzm = {zzh, zzi};
        private static final /* synthetic */ int[] zzn = {zzj, zzk};

        public static int[] zza() {
            return (int[]) zzl.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzfe<MessageType, BuilderType> implements zzgo {
        protected zzeu<zze> zzc = zzeu.zza();

        /* access modifiers changed from: package-private */
        public final zzeu<zze> zza() {
            if (this.zzc.zzc()) {
                this.zzc = (zzeu) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public String toString() {
        return zzgr.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzhb.zza().zza(this).zza(this);
        return this.zza;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
    public static abstract class zza<MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdl<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        protected zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (zzfe) messagetype.zza(zzf.zzd, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzq() {
            MessageType messagetype = (zzfe) this.zza.zza(zzf.zzd, (Object) null, (Object) null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        /* renamed from: g_ */
        public final boolean mo23725g_() {
            return zzfe.zza(this.zza, false);
        }

        /* renamed from: zzs */
        public MessageType zzu() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzhb.zza().zza(messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        /* renamed from: zzt */
        public final MessageType zzv() {
            MessageType messagetype = (zzfe) zzu();
            if (messagetype.mo23725g_()) {
                return messagetype;
            }
            throw new zzhu(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzhb.zza().zza(messagetype).zzb(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzer zzer) throws zzfm {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhb.zza().zza(this.zza).zza(this.zza, bArr, 0, i2 + 0, new zzdr(zzer));
                return this;
            } catch (zzfm e) {
                throw e;
            } catch (IndexOutOfBoundsException e2) {
                throw zzfm.zza();
            } catch (IOException e3) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzei zzei, zzer zzer) throws IOException {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhb.zza().zza(this.zza).zza(this.zza, zzej.zza(zzei), zzer);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public final /* synthetic */ zzdl zza(byte[] bArr, int i, int i2, zzer zzer) throws zzfm {
            return zzb(bArr, 0, i2, zzer);
        }

        public final /* synthetic */ zzdl zza(byte[] bArr, int i, int i2) throws zzfm {
            return zzb(bArr, 0, i2, zzer.zza());
        }

        public final /* synthetic */ zzdl zzp() {
            return (zza) clone();
        }

        /* renamed from: h_ */
        public final /* synthetic */ zzgm mo23726h_() {
            return this.zzc;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza2 = (zza) ((zzfe) this.zzc).zza(zzf.zze, (Object) null, (Object) null);
            zza2.zza((zzfe) zzu());
            return zza2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzhb.zza().zza(this).zza(this, (zzfe) obj);
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzfe<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzbk() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    /* renamed from: g_ */
    public final boolean mo23725g_() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    public final BuilderType zzbl() {
        BuilderType buildertype = (zza) zza(zzf.zze, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    /* access modifiers changed from: package-private */
    public final int zzbj() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i) {
        this.zzc = i;
    }

    public final void zza(zzel zzel) throws IOException {
        zzhb.zza().zza(this).zza(this, (zziq) zzeo.zza(zzel));
    }

    public final int zzbm() {
        if (this.zzc == -1) {
            this.zzc = zzhb.zza().zza(this).zzb(this);
        }
        return this.zzc;
    }

    static <T extends zzfe<?, ?>> T zza(Class<T> cls) {
        T t = (zzfe) zzd.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzfe) zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzfe) ((zzfe) zzid.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null);
            if (t == null) {
                throw new IllegalStateException();
            }
            zzd.put(cls, t);
        }
        return t;
    }

    protected static <T extends zzfe<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    protected static Object zza(zzgm zzgm, String str, Object[] objArr) {
        return new zzhd(zzgm, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzfe<T, ?>> boolean zza(T t, boolean z) {
        T t2;
        byte byteValue = ((Byte) t.zza(zzf.zza, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzd2 = zzhb.zza().zza(t).zzd(t);
        if (z) {
            int i = zzf.zzb;
            if (zzd2) {
                t2 = t;
            } else {
                t2 = null;
            }
            t.zza(i, (Object) t2, (Object) null);
        }
        return zzd2;
    }

    protected static zzfl zzbn() {
        return zzff.zzd();
    }

    protected static zzfk zzbo() {
        return zzga.zzd();
    }

    protected static zzfk zza(zzfk zzfk) {
        int size = zzfk.size();
        return zzfk.zzc(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzfn<E> zzbp() {
        return zzha.zzd();
    }

    protected static <E> zzfn<E> zza(zzfn<E> zzfn) {
        int size = zzfn.size();
        return zzfn.zza(size == 0 ? 10 : size << 1);
    }

    public final /* synthetic */ zzgp zzbq() {
        zza zza2 = (zza) zza(zzf.zze, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzgp zzbr() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    /* renamed from: h_ */
    public final /* synthetic */ zzgm mo23726h_() {
        return (zzfe) zza(zzf.zzf, (Object) null, (Object) null);
    }
}
