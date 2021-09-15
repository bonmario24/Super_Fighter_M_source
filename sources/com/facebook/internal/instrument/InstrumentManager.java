package com.facebook.internal.instrument;

import android.support.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.crashreport.CrashHandler;
import com.facebook.internal.instrument.errorreport.ErrorReportHandler;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class InstrumentManager {
    public static void start() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            FeatureManager.checkFeature(FeatureManager.Feature.CrashReport, new FeatureManager.Callback() {
                public void onCompleted(boolean enabled) {
                    if (enabled) {
                        CrashHandler.enable();
                    }
                }
            });
            FeatureManager.checkFeature(FeatureManager.Feature.ErrorReport, new FeatureManager.Callback() {
                public void onCompleted(boolean enabled) {
                    if (enabled) {
                        ErrorReportHandler.enable();
                    }
                }
            });
        }
    }
}
