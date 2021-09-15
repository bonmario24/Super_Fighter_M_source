package com.facebook.internal;

import android.support.annotation.RestrictTo;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppGateKeepersManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class FeatureManager {

    public interface Callback {
        void onCompleted(boolean z);
    }

    public static void checkFeature(final Feature feature, final Callback callback) {
        FetchedAppGateKeepersManager.loadAppGateKeepersAsync(new FetchedAppGateKeepersManager.Callback() {
            public void onCompleted() {
                callback.onCompleted(FeatureManager.isEnabled(feature));
            }
        });
    }

    public static boolean isEnabled(Feature feature) {
        if (Feature.Unknown == feature) {
            return false;
        }
        if (Feature.Core == feature) {
            return true;
        }
        Feature parent = feature.getParent();
        if (parent == feature) {
            return getGKStatus(feature);
        }
        if (!isEnabled(parent) || !getGKStatus(feature)) {
            return false;
        }
        return true;
    }

    private static boolean getGKStatus(Feature feature) {
        return FetchedAppGateKeepersManager.getGateKeeperForKey("FBSDKFeature" + feature.toString(), FacebookSdk.getApplicationId(), defaultStatus(feature));
    }

    private static boolean defaultStatus(Feature feature) {
        switch (feature) {
            case RestrictiveDataFiltering:
            case Instrument:
            case CrashReport:
            case ErrorReport:
                return false;
            default:
                return true;
        }
    }

    public enum Feature {
        Unknown(-1),
        Core(0),
        AppEvents(256),
        CodelessEvents(257),
        RestrictiveDataFiltering(258),
        Instrument(512),
        CrashReport(513),
        ErrorReport(514),
        Login(65536),
        Share(131072),
        Places(196608);
        
        private final int code;

        private Feature(int code2) {
            this.code = code2;
        }

        public String toString() {
            switch (this) {
                case RestrictiveDataFiltering:
                    return "RestrictiveDataFiltering";
                case Instrument:
                    return "Instrument";
                case CrashReport:
                    return "CrashReport";
                case ErrorReport:
                    return "ErrorReport";
                case Core:
                    return "CoreKit";
                case AppEvents:
                    return "AppEvents";
                case CodelessEvents:
                    return "CodelessEvents";
                case Login:
                    return "LoginKit";
                case Share:
                    return "ShareKit";
                case Places:
                    return "PlacesKit";
                default:
                    return "unknown";
            }
        }

        static Feature fromInt(int code2) {
            for (Feature feature : values()) {
                if (feature.code == code2) {
                    return feature;
                }
            }
            return Unknown;
        }

        public Feature getParent() {
            if ((this.code & 255) > 0) {
                return fromInt(this.code & 16776960);
            }
            if ((this.code & 65280) > 0) {
                return fromInt(this.code & 16711680);
            }
            return fromInt(0);
        }
    }
}
