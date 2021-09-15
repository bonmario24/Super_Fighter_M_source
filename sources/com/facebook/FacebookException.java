package com.facebook;

import com.facebook.internal.FeatureManager;
import com.facebook.internal.instrument.errorreport.ErrorReportHandler;
import java.util.Random;

public class FacebookException extends RuntimeException {
    static final long serialVersionUID = 1;

    public FacebookException() {
    }

    public FacebookException(final String message) {
        super(message);
        Random rand = new Random();
        if (message != null && FacebookSdk.isInitialized() && rand.nextInt(100) > 50) {
            FeatureManager.checkFeature(FeatureManager.Feature.ErrorReport, new FeatureManager.Callback() {
                public void onCompleted(boolean enabled) {
                    if (enabled) {
                        try {
                            ErrorReportHandler.save(message);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }
    }

    public FacebookException(String format, Object... args) {
        this(String.format(format, args));
    }

    public FacebookException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FacebookException(Throwable throwable) {
        super(throwable);
    }

    public String toString() {
        return getMessage();
    }
}
