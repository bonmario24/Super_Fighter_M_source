package android.support.customtabs;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.p000v4.app.BundleCompat;

public class TrustedWebUtils {
    public static final String EXTRA_LAUNCH_AS_TRUSTED_WEB_ACTIVITY = "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY";

    private TrustedWebUtils() {
    }

    public static void launchAsTrustedWebActivity(@NonNull Context context, @NonNull CustomTabsIntent customTabsIntent, @NonNull Uri uri) {
        if (BundleCompat.getBinder(customTabsIntent.intent.getExtras(), "android.support.customtabs.extra.SESSION") == null) {
            throw new IllegalArgumentException("Given CustomTabsIntent should be associated with a valid CustomTabsSession");
        }
        customTabsIntent.intent.putExtra("android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", true);
        customTabsIntent.launchUrl(context, uri);
    }
}
