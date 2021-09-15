package com.appsflyer;

import android.support.annotation.Nullable;

final class AFFacebookDeferredDeeplink {

    public interface AppLinkFetchEvents {
        void onAppLinkFetchFailed(String str);

        void onAppLinkFetchFinished(@Nullable String str, @Nullable String str2, @Nullable String str3);
    }

    AFFacebookDeferredDeeplink() {
    }
}
