package android.support.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.CustomTabsSessionToken;
import android.widget.RemoteViews;
import java.util.List;

public final class CustomTabsSession {
    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final Object mLock = new Object();
    private final ICustomTabsService mService;

    @VisibleForTesting
    @NonNull
    public static CustomTabsSession createMockSessionForTesting(@NonNull ComponentName componentName) {
        return new CustomTabsSession((ICustomTabsService) null, new CustomTabsSessionToken.MockCallback(), componentName);
    }

    CustomTabsSession(ICustomTabsService service, ICustomTabsCallback callback, ComponentName componentName) {
        this.mService = service;
        this.mCallback = callback;
        this.mComponentName = componentName;
    }

    public boolean mayLaunchUrl(Uri url, Bundle extras, List<Bundle> otherLikelyBundles) {
        try {
            return this.mService.mayLaunchUrl(this.mCallback, url, extras, otherLikelyBundles);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean setActionButton(@NonNull Bitmap icon, @NonNull String description) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.customtabs.customaction.ICON", icon);
        bundle.putString("android.support.customtabs.customaction.DESCRIPTION", description);
        Bundle metaBundle = new Bundle();
        metaBundle.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean setSecondaryToolbarViews(@Nullable RemoteViews remoteViews, @Nullable int[] clickableIDs, @Nullable PendingIntent pendingIntent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews);
        bundle.putIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", clickableIDs);
        bundle.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    @Deprecated
    public boolean setToolbarItem(int id, @NonNull Bitmap icon, @NonNull String description) {
        Bundle bundle = new Bundle();
        bundle.putInt("android.support.customtabs.customaction.ID", id);
        bundle.putParcelable("android.support.customtabs.customaction.ICON", icon);
        bundle.putString("android.support.customtabs.customaction.DESCRIPTION", description);
        Bundle metaBundle = new Bundle();
        metaBundle.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
        try {
            return this.mService.updateVisuals(this.mCallback, metaBundle);
        } catch (RemoteException e) {
            return false;
        }
    }

    public boolean requestPostMessageChannel(Uri postMessageOrigin) {
        try {
            return this.mService.requestPostMessageChannel(this.mCallback, postMessageOrigin);
        } catch (RemoteException e) {
            return false;
        }
    }

    public int postMessage(String message, Bundle extras) {
        int i;
        synchronized (this.mLock) {
            try {
                i = this.mService.postMessage(this.mCallback, message, extras);
            } catch (RemoteException e) {
                i = -2;
            }
        }
        return i;
    }

    public boolean validateRelationship(int relation, @NonNull Uri origin, @Nullable Bundle extras) {
        if (relation < 1 || relation > 2) {
            return false;
        }
        try {
            return this.mService.validateRelationship(this.mCallback, relation, origin, extras);
        } catch (RemoteException e) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    /* access modifiers changed from: package-private */
    public ComponentName getComponentName() {
        return this.mComponentName;
    }
}
