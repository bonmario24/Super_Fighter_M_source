package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zaru;
    /* access modifiers changed from: private */
    public Bundle zarv;
    /* access modifiers changed from: private */
    public LinkedList<zaa> zarw;
    private final OnDelegateCreatedListener<T> zarx = new zaa(this);

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    private interface zaa {
        int getState();

        void zaa(LifecycleDelegate lifecycleDelegate);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    @KeepForSdk
    public T getDelegate() {
        return this.zaru;
    }

    private final void zal(int i) {
        while (!this.zarw.isEmpty() && this.zarw.getLast().getState() >= i) {
            this.zarw.removeLast();
        }
    }

    private final void zaa(Bundle bundle, zaa zaa2) {
        if (this.zaru != null) {
            zaa2.zaa(this.zaru);
            return;
        }
        if (this.zarw == null) {
            this.zarw = new LinkedList<>();
        }
        this.zarw.add(zaa2);
        if (bundle != null) {
            if (this.zarv == null) {
                this.zarv = (Bundle) bundle.clone();
            } else {
                this.zarv.putAll(bundle);
            }
        }
        createDelegate(this.zarx);
    }

    @KeepForSdk
    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zaa(bundle2, (zaa) new zac(this, activity, bundle, bundle2));
    }

    @KeepForSdk
    public void onCreate(Bundle bundle) {
        zaa(bundle, (zaa) new zab(this, bundle));
    }

    @KeepForSdk
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zaa(bundle, (zaa) new zae(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zaru == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public void handleGooglePlayUnavailable(FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    @KeepForSdk
    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String errorMessage = ConnectionErrorMessages.getErrorMessage(context, isGooglePlayServicesAvailable);
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(errorMessage);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, (String) null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(errorDialogButtonMessage);
            linearLayout.addView(button);
            button.setOnClickListener(new zad(context, errorResolutionIntent));
        }
    }

    @KeepForSdk
    public void onStart() {
        zaa((Bundle) null, (zaa) new zag(this));
    }

    @KeepForSdk
    public void onResume() {
        zaa((Bundle) null, (zaa) new zaf(this));
    }

    @KeepForSdk
    public void onPause() {
        if (this.zaru != null) {
            this.zaru.onPause();
        } else {
            zal(5);
        }
    }

    @KeepForSdk
    public void onStop() {
        if (this.zaru != null) {
            this.zaru.onStop();
        } else {
            zal(4);
        }
    }

    @KeepForSdk
    public void onDestroyView() {
        if (this.zaru != null) {
            this.zaru.onDestroyView();
        } else {
            zal(2);
        }
    }

    @KeepForSdk
    public void onDestroy() {
        if (this.zaru != null) {
            this.zaru.onDestroy();
        } else {
            zal(1);
        }
    }

    @KeepForSdk
    public void onSaveInstanceState(Bundle bundle) {
        if (this.zaru != null) {
            this.zaru.onSaveInstanceState(bundle);
        } else if (this.zarv != null) {
            bundle.putAll(this.zarv);
        }
    }

    @KeepForSdk
    public void onLowMemory() {
        if (this.zaru != null) {
            this.zaru.onLowMemory();
        }
    }
}
