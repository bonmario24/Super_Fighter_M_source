package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzbz;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzjf;
import com.lzy.okgo.OkGo;
import com.teamtop3.Defenders.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.0 */
public final class zzaq {
    public static zzel<Long> zza = zza("measurement.ad_id_cache_time", 10000L, 10000L, zzat.zza);
    public static zzel<Long> zzaa = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbk.zza);
    public static zzel<Integer> zzab = zza("measurement.upload.retry_count", 6, 6, zzbn.zza);
    public static zzel<Long> zzac = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbm.zza);
    public static zzel<Integer> zzad = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbp.zza);
    public static zzel<Integer> zzae;
    public static zzel<Integer> zzaf = zza("measurement.upload.max_public_user_properties", 25, 25, (zzej) null);
    public static zzel<Integer> zzag = zza("measurement.upload.max_event_name_cardinality", 500, 500, (zzej) null);
    public static zzel<Integer> zzah = zza("measurement.upload.max_public_event_params", 25, 25, (zzej) null);
    public static zzel<Long> zzai = zza("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, zzbq.zza);
    public static zzel<Boolean> zzaj = zza("measurement.test.boolean_flag", false, false, zzbt.zza);
    public static zzel<String> zzak = zza("measurement.test.string_flag", "---", "---", zzbs.zza);
    public static zzel<Long> zzal = zza("measurement.test.long_flag", -1L, -1L, zzbv.zza);
    public static zzel<Integer> zzam = zza("measurement.test.int_flag", -2, -2, zzbu.zza);
    public static zzel<Double> zzan;
    public static zzel<Integer> zzao = zza("measurement.experiment.max_ids", 50, 50, zzbw.zza);
    public static zzel<Integer> zzap = zza("measurement.max_bundles_per_iteration", 2, 2, zzbz.zza);
    public static zzel<Boolean> zzaq = zza("measurement.validation.internal_limits_internal_event_params", false, false, zzby.zza);
    public static zzel<Boolean> zzar = zza("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", true, true, zzca.zza);
    public static zzel<Boolean> zzas = zza("measurement.collection.firebase_global_collection_flag_enabled", true, true, zzcd.zza);
    public static zzel<Boolean> zzat = zza("measurement.collection.efficient_engagement_reporting_enabled_2", true, true, zzcc.zza);
    public static zzel<Boolean> zzau = zza("measurement.collection.redundant_engagement_removal_enabled", false, false, zzcf.zza);
    public static zzel<Boolean> zzav = zza("measurement.client.freeride_engagement_fix", true, true, zzce.zza);
    public static zzel<Boolean> zzaw = zza("measurement.experiment.enable_experiment_reporting", true, true, zzch.zza);
    public static zzel<Boolean> zzax = zza("measurement.collection.log_event_and_bundle_v2", true, true, zzcg.zza);
    public static zzel<Boolean> zzay = zza("measurement.quality.checksum", false, false, (zzej) null);
    public static zzel<Boolean> zzaz = zza("measurement.sdk.dynamite.use_dynamite3", true, true, zzcj.zza);
    public static zzel<Long> zzb = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzas.zza);
    public static zzel<Boolean> zzba = zza("measurement.sdk.dynamite.allow_remote_dynamite", false, false, zzci.zza);
    public static zzel<Boolean> zzbb = zza("measurement.sdk.collection.validate_param_names_alphabetical", true, true, zzcl.zza);
    public static zzel<Boolean> zzbc = zza("measurement.collection.event_safelist", true, true, zzcn.zza);
    public static zzel<Boolean> zzbd = zza("measurement.service.audience.fix_skip_audience_with_failed_filters", true, true, zzcp.zza);
    public static zzel<Boolean> zzbe = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, false, zzco.zza);
    public static zzel<Boolean> zzbf = zza("measurement.audience.refresh_event_count_filters_timestamp", false, false, zzcr.zza);
    public static zzel<Boolean> zzbg = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, false, zzcq.zza);
    public static zzel<Boolean> zzbh = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", true, true, zzct.zza);
    public static zzel<Boolean> zzbi = zza("measurement.sdk.collection.last_deep_link_referrer2", true, true, zzcs.zza);
    public static zzel<Boolean> zzbj = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, false, zzcv.zza);
    public static zzel<Boolean> zzbk = zza("measurement.sdk.collection.last_gclid_from_referrer2", false, false, zzcu.zza);
    public static zzel<Boolean> zzbl = zza("measurement.sdk.collection.enable_extend_user_property_size", true, true, zzcw.zza);
    public static zzel<Boolean> zzbm = zza("measurement.upload.file_lock_state_check", false, false, zzcz.zza);
    public static zzel<Boolean> zzbn = zza("measurement.sampling.calculate_bundle_timestamp_before_sampling", true, true, zzcy.zza);
    public static zzel<Boolean> zzbo = zza("measurement.ga.ga_app_id", false, false, zzdb.zza);
    public static zzel<Boolean> zzbp = zza("measurement.lifecycle.app_backgrounded_tracking", true, true, zzda.zza);
    public static zzel<Boolean> zzbq = zza("measurement.lifecycle.app_in_background_parameter", false, false, zzdd.zza);
    public static zzel<Boolean> zzbr = zza("measurement.integration.disable_firebase_instance_id", false, false, zzdc.zza);
    public static zzel<Boolean> zzbs = zza("measurement.lifecycle.app_backgrounded_engagement", false, false, zzdf.zza);
    public static zzel<Boolean> zzbt = zza("measurement.collection.service.update_with_analytics_fix", false, false, zzde.zza);
    public static zzel<Boolean> zzbu = zza("measurement.service.use_appinfo_modified", false, false, zzdh.zza);
    public static zzel<Boolean> zzbv = zza("measurement.client.firebase_feature_rollout.v1.enable", true, true, zzdj.zza);
    public static zzel<Boolean> zzbw = zza("measurement.client.sessions.check_on_reset_and_enable2", true, true, zzdi.zza);
    public static zzel<Boolean> zzbx = zza("measurement.config.string.always_update_disk_on_set", true, true, zzdl.zza);
    public static zzel<Boolean> zzby = zza("measurement.scheduler.task_thread.cleanup_on_exit", false, false, zzdk.zza);
    public static zzel<Boolean> zzbz = zza("measurement.upload.file_truncate_fix", false, false, zzdn.zza);
    public static zzel<Long> zzc = zza("measurement.config.cache_time", 86400000L, 3600000L, zzbf.zza);
    public static zzel<Boolean> zzca = zza("measurement.engagement_time_main_thread", true, true, zzdm.zza);
    public static zzel<Boolean> zzcb = zza("measurement.sdk.referrer.delayed_install_referrer_api", false, false, zzdp.zza);
    public static zzel<Boolean> zzcc = zza("measurement.sdk.screen.disabling_automatic_reporting", true, true, zzdo.zza);
    public static zzel<Boolean> zzcd = zza("measurement.sdk.screen.manual_screen_view_logging", true, true, zzdr.zza);
    public static zzel<Boolean> zzce = zza("measurement.gold.enhanced_ecommerce.format_logs", false, false, zzdq.zza);
    public static zzel<Boolean> zzcf = zza("measurement.gold.enhanced_ecommerce.nested_param_daily_event_count", false, false, zzds.zza);
    public static zzel<Boolean> zzcg = zza("measurement.gold.enhanced_ecommerce.upload_nested_complex_events", false, false, zzdv.zza);
    public static zzel<Boolean> zzch = zza("measurement.gold.enhanced_ecommerce.log_nested_complex_events", false, false, zzdu.zza);
    public static zzel<Boolean> zzci = zza("measurement.gold.enhanced_ecommerce.updated_schema.client", false, false, zzdx.zza);
    public static zzel<Boolean> zzcj = zza("measurement.gold.enhanced_ecommerce.updated_schema.service", false, false, zzdw.zza);
    public static zzel<Boolean> zzck = zza("measurement.service.configurable_service_limits", false, false, zzdy.zza);
    public static zzel<Boolean> zzcl = zza("measurement.client.configurable_service_limits", false, false, zzeb.zza);
    public static zzel<Boolean> zzcm = zza("measurement.androidId.delete_feature", true, true, zzea.zza);
    public static zzel<Boolean> zzcn = zza("measurement.client.global_params.dev", false, false, zzed.zza);
    public static zzel<Boolean> zzco = zza("measurement.client.string_reader", true, true, zzef.zza);
    public static zzel<Boolean> zzcp = zza("measurement.sdk.attribution.cache", true, true, zzee.zza);
    public static zzel<Long> zzcq = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzeh.zza);
    /* access modifiers changed from: private */
    public static List<zzel<?>> zzcr = Collections.synchronizedList(new ArrayList());
    private static Set<zzel<?>> zzcs = Collections.synchronizedSet(new HashSet());
    private static zzel<Boolean> zzct = zza("measurement.service.audience.invalidate_config_cache_after_app_unisntall", true, true, zzcm.zza);
    private static zzel<Boolean> zzcu = zza("measurement.collection.synthetic_data_mitigation", false, false, zzdz.zza);
    public static zzel<String> zzd = zza("measurement.config.url_scheme", "https", "https", zzbo.zza);
    public static zzel<String> zze = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzcb.zza);
    public static zzel<Integer> zzf = zza("measurement.upload.max_bundles", 100, 100, zzck.zza);
    public static zzel<Integer> zzg = zza("measurement.upload.max_batch_size", 65536, 65536, zzcx.zza);
    public static zzel<Integer> zzh = zza("measurement.upload.max_bundle_size", 65536, 65536, zzdg.zza);
    public static zzel<Integer> zzi = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzdt.zza);
    public static zzel<Integer> zzj = zza("measurement.upload.max_events_per_day", 100000, 100000, zzec.zza);
    public static zzel<Integer> zzk = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzav.zza);
    public static zzel<Integer> zzl = zza("measurement.upload.max_public_events_per_day", 50000, 50000, zzau.zza);
    public static zzel<Integer> zzm = zza("measurement.upload.max_conversions_per_day", 10000, 10000, zzax.zza);
    public static zzel<Integer> zzn = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzaw.zza);
    public static zzel<Integer> zzo = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzaz.zza);
    public static zzel<String> zzp = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzay.zza);
    public static zzel<Long> zzq = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzbb.zza);
    public static zzel<Long> zzr = zza("measurement.upload.window_interval", 3600000L, 3600000L, zzba.zza);
    public static zzel<Long> zzs = zza("measurement.upload.interval", 3600000L, 3600000L, zzbd.zza);
    public static zzel<Long> zzt = zza("measurement.upload.realtime_upload_interval", 10000L, 10000L, zzbc.zza);
    public static zzel<Long> zzu = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzbe.zza);
    public static zzel<Long> zzv = zza("measurement.upload.minimum_delay", 500L, 500L, zzbh.zza);
    public static zzel<Long> zzw;
    public static zzel<Long> zzx = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbj.zza);
    public static zzel<Long> zzy = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbi.zza);
    public static zzel<Long> zzz = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbl.zza);

    public static Map<String, String> zza(Context context) {
        zzbz zza2 = zzbz.zza(context.getContentResolver(), zzck.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }

    @VisibleForTesting
    private static <V> zzel<V> zza(@Nullable String str, @Nullable V v, @Nullable V v2, @Nullable zzej<V> zzej) {
        zzel<V> zzel = new zzel<>(str, v, v2, zzej);
        zzcr.add(zzel);
        return zzel;
    }

    static final /* synthetic */ Long zzcm() {
        if (zzei.zza != null) {
            zzx zzx2 = zzei.zza;
        }
        return Long.valueOf(zzjf.zzc());
    }

    static {
        Long valueOf = Long.valueOf(OkGo.DEFAULT_MILLISECONDS);
        zzw = zza("measurement.alarm_manager.minimum_interval", valueOf, valueOf, zzbg.zza);
        Integer valueOf2 = Integer.valueOf(HttpResponse.f88OK);
        zzae = zza("measurement.audience.filter_result_max_count", valueOf2, valueOf2, zzbr.zza);
        Double valueOf3 = Double.valueOf(-3.0d);
        zzan = zza("measurement.test.double_flag", valueOf3, valueOf3, zzbx.zza);
    }
}
