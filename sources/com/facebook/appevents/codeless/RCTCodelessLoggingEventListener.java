package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import java.lang.ref.WeakReference;

public class RCTCodelessLoggingEventListener {
    private static final String TAG = RCTCodelessLoggingEventListener.class.getCanonicalName();

    public static AutoLoggingOnTouchListener getOnTouchListener(EventBinding mapping, View rootView, View hostView) {
        return new AutoLoggingOnTouchListener(mapping, rootView, hostView);
    }

    public static class AutoLoggingOnTouchListener implements View.OnTouchListener {
        @Nullable
        private View.OnTouchListener existingOnTouchListener;
        private WeakReference<View> hostView;
        private EventBinding mapping;
        private WeakReference<View> rootView;
        private boolean supportCodelessLogging = false;

        public AutoLoggingOnTouchListener(EventBinding mapping2, View rootView2, View hostView2) {
            if (mapping2 != null && rootView2 != null && hostView2 != null) {
                this.existingOnTouchListener = ViewHierarchy.getExistingOnTouchListener(hostView2);
                this.mapping = mapping2;
                this.hostView = new WeakReference<>(hostView2);
                this.rootView = new WeakReference<>(rootView2);
                this.supportCodelessLogging = true;
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                logEvent();
            }
            if (this.existingOnTouchListener == null || !this.existingOnTouchListener.onTouch(view, motionEvent)) {
                return false;
            }
            return true;
        }

        private void logEvent() {
            if (this.mapping != null) {
                final String eventName = this.mapping.getEventName();
                Bundle parameters = CodelessMatcher.getParameters(this.mapping, (View) this.rootView.get(), (View) this.hostView.get());
                if (parameters.containsKey(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)) {
                    parameters.putDouble(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, AppEventUtility.normalizePrice(parameters.getString(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)));
                }
                parameters.putString(Constants.IS_CODELESS_EVENT_KEY, AppEventsConstants.EVENT_PARAM_VALUE_YES);
                final Bundle params = parameters;
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        AppEventsLogger.newLogger(FacebookSdk.getApplicationContext()).logEvent(eventName, params);
                    }
                });
            }
        }

        public boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }
    }
}
