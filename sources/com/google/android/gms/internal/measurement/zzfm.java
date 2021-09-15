package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.0 */
public class zzfm extends IOException {
    private zzgm zza = null;

    public zzfm(String str) {
        super(str);
    }

    static zzfm zza() {
        return new zzfm("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzfm zzb() {
        return new zzfm("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzfm zzc() {
        return new zzfm("CodedInputStream encountered a malformed varint.");
    }

    static zzfm zzd() {
        return new zzfm("Protocol message contained an invalid tag (zero).");
    }

    static zzfm zze() {
        return new zzfm("Protocol message end-group tag did not match expected tag.");
    }

    static zzfp zzf() {
        return new zzfp("Protocol message tag had invalid wire type.");
    }

    static zzfm zzg() {
        return new zzfm("Failed to parse the message.");
    }

    static zzfm zzh() {
        return new zzfm("Protocol message had invalid UTF-8.");
    }
}
