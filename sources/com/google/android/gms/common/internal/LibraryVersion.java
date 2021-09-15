package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@17.1.0 */
public class LibraryVersion {
    private static final GmsLogger zzfc = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzfd = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzfe = new ConcurrentHashMap<>();

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzfd;
    }

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public String getVersion(@NonNull String str) {
        String str2;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzfe.containsKey(str)) {
            return this.zzfe.get(str);
        }
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", new Object[]{str}));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                String property = properties.getProperty(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, (String) null);
                zzfc.mo21115v("LibraryVersion", new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(property).length()).append(str).append(" version is ").append(property).toString());
                str2 = property;
            } else {
                GmsLogger gmsLogger = zzfc;
                String valueOf = String.valueOf(str);
                gmsLogger.mo21117w("LibraryVersion", valueOf.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf) : new String("Failed to get app version for libraryName: "));
                str2 = null;
            }
        } catch (IOException e) {
            IOException iOException = e;
            GmsLogger gmsLogger2 = zzfc;
            String valueOf2 = String.valueOf(str);
            gmsLogger2.mo21109e("LibraryVersion", valueOf2.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf2) : new String("Failed to get app version for libraryName: "), iOException);
            str2 = null;
        }
        if (str2 == null) {
            str2 = "UNKNOWN";
            zzfc.mo21106d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzfe.put(str, str2);
        return str2;
    }
}
