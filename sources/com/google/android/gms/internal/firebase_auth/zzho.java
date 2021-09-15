package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.2 */
final class zzho implements zzke {
    private final zzhh zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    public static zzho zza(zzhh zzhh) {
        if (zzhh.zzd != null) {
            return zzhh.zzd;
        }
        return new zzho(zzhh);
    }

    private zzho(zzhh zzhh) {
        this.zza = (zzhh) zzii.zza(zzhh, "input");
        this.zza.zzd = this;
    }

    public final int zza() throws IOException {
        if (this.zzd != 0) {
            this.zzb = this.zzd;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zza();
        }
        if (this.zzb == 0 || this.zzb == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return this.zzb >>> 3;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final boolean zzc() throws IOException {
        if (this.zza.zzt() || this.zzb == this.zzc) {
            return false;
        }
        return this.zza.zzb(this.zzb);
    }

    private final void zza(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzir.zzf();
        }
    }

    public final double zzd() throws IOException {
        zza(1);
        return this.zza.zzb();
    }

    public final float zze() throws IOException {
        zza(5);
        return this.zza.zzc();
    }

    public final long zzf() throws IOException {
        zza(0);
        return this.zza.zzd();
    }

    public final long zzg() throws IOException {
        zza(0);
        return this.zza.zze();
    }

    public final int zzh() throws IOException {
        zza(0);
        return this.zza.zzf();
    }

    public final long zzi() throws IOException {
        zza(1);
        return this.zza.zzg();
    }

    public final int zzj() throws IOException {
        zza(5);
        return this.zza.zzh();
    }

    public final boolean zzk() throws IOException {
        zza(0);
        return this.zza.zzi();
    }

    public final String zzl() throws IOException {
        zza(2);
        return this.zza.zzj();
    }

    public final String zzm() throws IOException {
        zza(2);
        return this.zza.zzk();
    }

    public final <T> T zza(zzkh<T> zzkh, zzht zzht) throws IOException {
        zza(2);
        return zzc(zzkh, zzht);
    }

    public final <T> T zzb(zzkh<T> zzkh, zzht zzht) throws IOException {
        zza(3);
        return zzd(zzkh, zzht);
    }

    private final <T> T zzc(zzkh<T> zzkh, zzht zzht) throws IOException {
        int zzm = this.zza.zzm();
        if (this.zza.zza >= this.zza.zzb) {
            throw new zzir("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int zzc2 = this.zza.zzc(zzm);
        T zza2 = zzkh.zza();
        this.zza.zza++;
        zzkh.zza(zza2, this, zzht);
        zzkh.zzb(zza2);
        this.zza.zza(0);
        zzhh zzhh = this.zza;
        zzhh.zza--;
        this.zza.zzd(zzc2);
        return zza2;
    }

    private final <T> T zzd(zzkh<T> zzkh, zzht zzht) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T zza2 = zzkh.zza();
            zzkh.zza(zza2, this, zzht);
            zzkh.zzb(zza2);
            if (this.zzb == this.zzc) {
                return zza2;
            }
            throw zzir.zzh();
        } finally {
            this.zzc = i;
        }
    }

    public final zzgv zzn() throws IOException {
        zza(2);
        return this.zza.zzl();
    }

    public final int zzo() throws IOException {
        zza(0);
        return this.zza.zzm();
    }

    public final int zzp() throws IOException {
        zza(0);
        return this.zza.zzn();
    }

    public final int zzq() throws IOException {
        zza(5);
        return this.zza.zzo();
    }

    public final long zzr() throws IOException {
        zza(1);
        return this.zza.zzp();
    }

    public final int zzs() throws IOException {
        zza(0);
        return this.zza.zzq();
    }

    public final long zzt() throws IOException {
        zza(0);
        return this.zza.zzr();
    }

    public final void zza(List<Double> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzhr) {
            zzhr zzhr = (zzhr) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zzm = this.zza.zzm();
                    zzb(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzhr.zza(this.zza.zzb());
                    } while (this.zza.zzu() < zzu);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzhr.zza(this.zza.zzb());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzu() < zzu2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Double.valueOf(this.zza.zzb()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzb(List<Float> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            switch (this.zzb & 7) {
                case 2:
                    int zzm = this.zza.zzm();
                    zzc(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzif.zza(this.zza.zzc());
                    } while (this.zza.zzu() < zzu);
                    return;
                case 5:
                    break;
                default:
                    throw zzir.zzf();
            }
            do {
                zzif.zza(this.zza.zzc());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzu() < zzu2);
                return;
            case 5:
                break;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Float.valueOf(this.zza.zzc()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzc(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzjf.zza(this.zza.zzd());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzjf.zza(this.zza.zzd());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Long.valueOf(this.zza.zzd()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Long.valueOf(this.zza.zzd()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzd(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzjf.zza(this.zza.zze());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzjf.zza(this.zza.zze());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Long.valueOf(this.zza.zze()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Long.valueOf(this.zza.zze()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zze(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzf());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzf());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzf()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzf(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zzm = this.zza.zzm();
                    zzb(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzjf.zza(this.zza.zzg());
                    } while (this.zza.zzu() < zzu);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzjf.zza(this.zza.zzg());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Long.valueOf(this.zza.zzg()));
                } while (this.zza.zzu() < zzu2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Long.valueOf(this.zza.zzg()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzg(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 2:
                    int zzm = this.zza.zzm();
                    zzc(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzh());
                    } while (this.zza.zzu() < zzu);
                    return;
                case 5:
                    break;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzh());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzu() < zzu2);
                return;
            case 5:
                break;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzh()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzgt.zza(this.zza.zzi());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzgt.zza(this.zza.zzi());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Boolean.valueOf(this.zza.zzi()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Boolean.valueOf(this.zza.zzi()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzi(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzj(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zza2;
        int zza3;
        if ((this.zzb & 7) != 2) {
            throw zzir.zzf();
        } else if (!(list instanceof zziy) || z) {
            do {
                list.add(z ? zzm() : zzl());
                if (!this.zza.zzt()) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == this.zzb);
            this.zzd = zza2;
        } else {
            zziy zziy = (zziy) list;
            do {
                zziy.zza(zzn());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
        }
    }

    public final <T> void zza(List<T> list, zzkh<T> zzkh, zzht zzht) throws IOException {
        int zza2;
        if ((this.zzb & 7) != 2) {
            throw zzir.zzf();
        }
        int i = this.zzb;
        do {
            list.add(zzc(zzkh, zzht));
            if (!this.zza.zzt() && this.zzd == 0) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == i);
        this.zzd = zza2;
    }

    public final <T> void zzb(List<T> list, zzkh<T> zzkh, zzht zzht) throws IOException {
        int zza2;
        if ((this.zzb & 7) != 3) {
            throw zzir.zzf();
        }
        int i = this.zzb;
        do {
            list.add(zzd(zzkh, zzht));
            if (!this.zza.zzt() && this.zzd == 0) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == i);
        this.zzd = zza2;
    }

    public final void zzk(List<zzgv> list) throws IOException {
        int zza2;
        if ((this.zzb & 7) != 2) {
            throw zzir.zzf();
        }
        do {
            list.add(zzn());
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzm());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzm());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzm()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzm()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzn());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzn());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzn()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzn(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 2:
                    int zzm = this.zza.zzm();
                    zzc(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzo());
                    } while (this.zza.zzu() < zzu);
                    return;
                case 5:
                    break;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzo());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 2:
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzo()));
                } while (this.zza.zzu() < zzu2);
                return;
            case 5:
                break;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzo()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzo(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            switch (this.zzb & 7) {
                case 1:
                    break;
                case 2:
                    int zzm = this.zza.zzm();
                    zzb(zzm);
                    int zzu = zzm + this.zza.zzu();
                    do {
                        zzjf.zza(this.zza.zzp());
                    } while (this.zza.zzu() < zzu);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzjf.zza(this.zza.zzp());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 1:
                break;
            case 2:
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = zzm2 + this.zza.zzu();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzu() < zzu2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Long.valueOf(this.zza.zzp()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzp(List<Integer> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzij.zzd(this.zza.zzq());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzij.zzd(this.zza.zzq());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Integer.valueOf(this.zza.zzq()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Integer.valueOf(this.zza.zzq()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    public final void zzq(List<Long> list) throws IOException {
        int zza2;
        int zza3;
        if (list instanceof zzjf) {
            zzjf zzjf = (zzjf) list;
            switch (this.zzb & 7) {
                case 0:
                    break;
                case 2:
                    int zzm = this.zza.zzm() + this.zza.zzu();
                    do {
                        zzjf.zza(this.zza.zzr());
                    } while (this.zza.zzu() < zzm);
                    zzd(zzm);
                    return;
                default:
                    throw zzir.zzf();
            }
            do {
                zzjf.zza(this.zza.zzr());
                if (!this.zza.zzt()) {
                    zza3 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza3 == this.zzb);
            this.zzd = zza3;
            return;
        }
        switch (this.zzb & 7) {
            case 0:
                break;
            case 2:
                int zzm2 = this.zza.zzm() + this.zza.zzu();
                do {
                    list.add(Long.valueOf(this.zza.zzr()));
                } while (this.zza.zzu() < zzm2);
                zzd(zzm2);
                return;
            default:
                throw zzir.zzf();
        }
        do {
            list.add(Long.valueOf(this.zza.zzr()));
            if (!this.zza.zzt()) {
                zza2 = this.zza.zza();
            } else {
                return;
            }
        } while (zza2 == this.zzb);
        this.zzd = zza2;
    }

    private static void zzb(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzir.zzh();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r7, com.google.android.gms.internal.firebase_auth.zzji<K, V> r8, com.google.android.gms.internal.firebase_auth.zzht r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 2
            r6.zza((int) r0)
            com.google.android.gms.internal.firebase_auth.zzhh r0 = r6.zza
            int r0 = r0.zzm()
            com.google.android.gms.internal.firebase_auth.zzhh r1 = r6.zza
            int r2 = r1.zzc(r0)
            K r1 = r8.zzb
            V r0 = r8.zzd
        L_0x0014:
            int r3 = r6.zza()     // Catch:{ all -> 0x0045 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x0062
            com.google.android.gms.internal.firebase_auth.zzhh r4 = r6.zza     // Catch:{ all -> 0x0045 }
            boolean r4 = r4.zzt()     // Catch:{ all -> 0x0045 }
            if (r4 != 0) goto L_0x0062
            switch(r3) {
                case 1: goto L_0x004c;
                case 2: goto L_0x0055;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r6.zzc()     // Catch:{ zziq -> 0x0036 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.firebase_auth.zzir r3 = new com.google.android.gms.internal.firebase_auth.zzir     // Catch:{ zziq -> 0x0036 }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zziq -> 0x0036 }
            throw r3     // Catch:{ zziq -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            boolean r3 = r6.zzc()     // Catch:{ all -> 0x0045 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.firebase_auth.zzir r0 = new com.google.android.gms.internal.firebase_auth.zzir     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "Unable to parse map entry."
            r0.<init>(r1)     // Catch:{ all -> 0x0045 }
            throw r0     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r0 = move-exception
            com.google.android.gms.internal.firebase_auth.zzhh r1 = r6.zza
            r1.zzd(r2)
            throw r0
        L_0x004c:
            com.google.android.gms.internal.firebase_auth.zzlq r3 = r8.zza     // Catch:{ zziq -> 0x0036 }
            r4 = 0
            r5 = 0
            java.lang.Object r1 = r6.zza((com.google.android.gms.internal.firebase_auth.zzlq) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.firebase_auth.zzht) r5)     // Catch:{ zziq -> 0x0036 }
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.firebase_auth.zzlq r3 = r8.zzc     // Catch:{ zziq -> 0x0036 }
            V r4 = r8.zzd     // Catch:{ zziq -> 0x0036 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zziq -> 0x0036 }
            java.lang.Object r0 = r6.zza((com.google.android.gms.internal.firebase_auth.zzlq) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.firebase_auth.zzht) r9)     // Catch:{ zziq -> 0x0036 }
            goto L_0x0014
        L_0x0062:
            r7.put(r1, r0)     // Catch:{ all -> 0x0045 }
            com.google.android.gms.internal.firebase_auth.zzhh r0 = r6.zza
            r0.zzd(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzho.zza(java.util.Map, com.google.android.gms.internal.firebase_auth.zzji, com.google.android.gms.internal.firebase_auth.zzht):void");
    }

    private final Object zza(zzlq zzlq, Class<?> cls, zzht zzht) throws IOException {
        switch (zzhn.zza[zzlq.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzk());
            case 2:
                return zzn();
            case 3:
                return Double.valueOf(zzd());
            case 4:
                return Integer.valueOf(zzp());
            case 5:
                return Integer.valueOf(zzj());
            case 6:
                return Long.valueOf(zzi());
            case 7:
                return Float.valueOf(zze());
            case 8:
                return Integer.valueOf(zzh());
            case 9:
                return Long.valueOf(zzg());
            case 10:
                zza(2);
                return zzc(zzkd.zza().zza(cls), zzht);
            case 11:
                return Integer.valueOf(zzq());
            case 12:
                return Long.valueOf(zzr());
            case 13:
                return Integer.valueOf(zzs());
            case 14:
                return Long.valueOf(zzt());
            case 15:
                return zzm();
            case 16:
                return Integer.valueOf(zzo());
            case 17:
                return Long.valueOf(zzf());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzir.zzh();
        }
    }

    private final void zzd(int i) throws IOException {
        if (this.zza.zzu() != i) {
            throw zzir.zza();
        }
    }
}
