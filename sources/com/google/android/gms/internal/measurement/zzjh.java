package com.google.android.gms.internal.measurement;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.lzy.okgo.OkGo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzjh implements zzje {
    private static final zzcn<Long> zza;
    private static final zzcn<Long> zzaa;
    private static final zzcn<Long> zzab;
    private static final zzcn<Long> zzac;
    private static final zzcn<Long> zzad;
    private static final zzcn<Long> zzae;
    private static final zzcn<Long> zzaf;
    private static final zzcn<Long> zzag;
    private static final zzcn<Long> zzah;
    private static final zzcn<String> zzai;
    private static final zzcn<Long> zzaj;
    private static final zzcn<Long> zzb;
    private static final zzcn<String> zzc;
    private static final zzcn<String> zzd;
    private static final zzcn<String> zze;
    private static final zzcn<Long> zzf;
    private static final zzcn<Long> zzg;
    private static final zzcn<Long> zzh;
    private static final zzcn<Long> zzi;
    private static final zzcn<Long> zzj;
    private static final zzcn<Long> zzk;
    private static final zzcn<Long> zzl;
    private static final zzcn<Long> zzm;
    private static final zzcn<Long> zzn;
    private static final zzcn<Long> zzo;
    private static final zzcn<Long> zzp;
    private static final zzcn<Long> zzq;
    private static final zzcn<String> zzr;
    private static final zzcn<Long> zzs;
    private static final zzcn<Long> zzt;
    private static final zzcn<Long> zzu;
    private static final zzcn<Long> zzv;
    private static final zzcn<Long> zzw;
    private static final zzcn<Long> zzx;
    private static final zzcn<Long> zzy;
    private static final zzcn<Long> zzz;

    public final long zza() {
        return zza.zzc().longValue();
    }

    public final long zzb() {
        return zzb.zzc().longValue();
    }

    public final String zzc() {
        return zzd.zzc();
    }

    public final String zzd() {
        return zze.zzc();
    }

    public final long zze() {
        return zzf.zzc().longValue();
    }

    public final long zzf() {
        return zzg.zzc().longValue();
    }

    public final long zzg() {
        return zzh.zzc().longValue();
    }

    public final long zzh() {
        return zzi.zzc().longValue();
    }

    public final long zzi() {
        return zzj.zzc().longValue();
    }

    public final long zzj() {
        return zzk.zzc().longValue();
    }

    public final long zzk() {
        return zzl.zzc().longValue();
    }

    public final long zzl() {
        return zzm.zzc().longValue();
    }

    public final long zzm() {
        return zzn.zzc().longValue();
    }

    public final long zzn() {
        return zzo.zzc().longValue();
    }

    public final long zzo() {
        return zzq.zzc().longValue();
    }

    public final long zzp() {
        return zzs.zzc().longValue();
    }

    public final long zzq() {
        return zzt.zzc().longValue();
    }

    public final long zzr() {
        return zzu.zzc().longValue();
    }

    public final long zzs() {
        return zzv.zzc().longValue();
    }

    public final long zzt() {
        return zzw.zzc().longValue();
    }

    public final long zzu() {
        return zzx.zzc().longValue();
    }

    public final long zzv() {
        return zzy.zzc().longValue();
    }

    public final long zzw() {
        return zzz.zzc().longValue();
    }

    public final long zzx() {
        return zzaa.zzc().longValue();
    }

    public final long zzy() {
        return zzab.zzc().longValue();
    }

    public final long zzz() {
        return zzac.zzc().longValue();
    }

    public final long zzaa() {
        return zzad.zzc().longValue();
    }

    public final long zzab() {
        return zzae.zzc().longValue();
    }

    public final long zzac() {
        return zzaf.zzc().longValue();
    }

    public final long zzad() {
        return zzag.zzc().longValue();
    }

    public final long zzae() {
        return zzah.zzc().longValue();
    }

    public final String zzaf() {
        return zzai.zzc();
    }

    public final long zzag() {
        return zzaj.zzc().longValue();
    }

    static {
        zzct zzct = new zzct(zzck.zza("com.google.android.gms.measurement"));
        zza = zzct.zza("measurement.ad_id_cache_time", 10000);
        zzb = zzct.zza("measurement.config.cache_time", 86400000);
        zzc = zzct.zza("measurement.log_tag", "FA");
        zzd = zzct.zza("measurement.config.url_authority", "app-measurement.com");
        zze = zzct.zza("measurement.config.url_scheme", "https");
        zzf = zzct.zza("measurement.upload.debug_upload_interval", 1000);
        zzg = zzct.zza("measurement.lifetimevalue.max_currency_tracked", 4);
        zzh = zzct.zza("measurement.store.max_stored_events_per_app", 100000);
        zzi = zzct.zza("measurement.experiment.max_ids", 50);
        zzj = zzct.zza("measurement.audience.filter_result_max_count", 200);
        zzk = zzct.zza("measurement.alarm_manager.minimum_interval", (long) OkGo.DEFAULT_MILLISECONDS);
        zzl = zzct.zza("measurement.upload.minimum_delay", 500);
        zzm = zzct.zza("measurement.monitoring.sample_period_millis", 86400000);
        zzn = zzct.zza("measurement.upload.realtime_upload_interval", 10000);
        zzo = zzct.zza("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zzp = zzct.zza("measurement.config.cache_time.service", 3600000);
        zzq = zzct.zza("measurement.service_client.idle_disconnect_millis", 5000);
        zzr = zzct.zza("measurement.log_tag.service", "FA-SVC");
        zzs = zzct.zza("measurement.upload.stale_data_deletion_interval", 86400000);
        zzt = zzct.zza("measurement.upload.backoff_period", 43200000);
        zzu = zzct.zza("measurement.upload.initial_upload_delay_time", 15000);
        zzv = zzct.zza("measurement.upload.interval", 3600000);
        zzw = zzct.zza("measurement.upload.max_bundle_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzx = zzct.zza("measurement.upload.max_bundles", 100);
        zzy = zzct.zza("measurement.upload.max_conversions_per_day", 500);
        zzz = zzct.zza("measurement.upload.max_error_events_per_day", 1000);
        zzaa = zzct.zza("measurement.upload.max_events_per_bundle", 1000);
        zzab = zzct.zza("measurement.upload.max_events_per_day", 100000);
        zzac = zzct.zza("measurement.upload.max_public_events_per_day", 50000);
        zzad = zzct.zza("measurement.upload.max_queue_time", 2419200000L);
        zzae = zzct.zza("measurement.upload.max_realtime_events_per_day", 10);
        zzaf = zzct.zza("measurement.upload.max_batch_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzag = zzct.zza("measurement.upload.retry_count", 6);
        zzah = zzct.zza("measurement.upload.retry_time", 1800000);
        zzai = zzct.zza("measurement.upload.url", "https://app-measurement.com/a");
        zzaj = zzct.zza("measurement.upload.window_interval", 3600000);
    }
}
