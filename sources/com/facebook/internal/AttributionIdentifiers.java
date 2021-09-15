package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookSdk;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers identifiers = getAndroidIdViaReflection(context);
        if (identifiers != null) {
            return identifiers;
        }
        AttributionIdentifiers identifiers2 = getAndroidIdViaService(context);
        if (identifiers2 == null) {
            return new AttributionIdentifiers();
        }
        return identifiers2;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (!isGooglePlayServicesAvailable(context)) {
                return null;
            }
            Method getAdvertisingIdInfo = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
            if (getAdvertisingIdInfo == null) {
                return null;
            }
            Object advertisingInfo = Utility.invokeMethodQuietly((Object) null, getAdvertisingIdInfo, context);
            if (advertisingInfo == null) {
                return null;
            }
            Method getId = Utility.getMethodQuietly(advertisingInfo.getClass(), "getId", (Class<?>[]) new Class[0]);
            Method isLimitAdTrackingEnabled = Utility.getMethodQuietly(advertisingInfo.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
            if (getId == null || isLimitAdTrackingEnabled == null) {
                return null;
            }
            AttributionIdentifiers identifiers = new AttributionIdentifiers();
            identifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(advertisingInfo, getId, new Object[0]);
            identifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(advertisingInfo, isLimitAdTrackingEnabled, new Object[0])).booleanValue();
            return identifiers;
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static boolean isGooglePlayServicesAvailable(Context context) {
        Method method = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
        if (method == null) {
            return false;
        }
        Object connectionResult = Utility.invokeMethodQuietly((Object) null, method, context);
        if (!(connectionResult instanceof Integer) || ((Integer) connectionResult).intValue() != 0) {
            return false;
        }
        return true;
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection connection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, connection, 1)) {
            try {
                GoogleAdInfo adInfo = new GoogleAdInfo(connection.getBinder());
                AttributionIdentifiers identifiers = new AttributionIdentifiers();
                identifiers.androidAdvertiserId = adInfo.getAdvertiserId();
                identifiers.limitTracking = adInfo.isTrackingLimited();
                return identifiers;
            } catch (Exception exception) {
                Utility.logd("android_id", exception);
            } finally {
                context.unbindService(connection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009b A[Catch:{ Exception -> 0x0017, all -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009f A[Catch:{ Exception -> 0x0017, all -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be A[Catch:{ Exception -> 0x0017, all -> 0x0119 }] */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r20) {
        /*
            com.facebook.internal.AttributionIdentifiers r13 = getAndroidId(r20)
            r10 = 0
            android.os.Looper r2 = android.os.Looper.myLooper()     // Catch:{ Exception -> 0x0017 }
            android.os.Looper r5 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x0017 }
            if (r2 != r5) goto L_0x003b
            com.facebook.FacebookException r2 = new com.facebook.FacebookException     // Catch:{ Exception -> 0x0017 }
            java.lang.String r5 = "getAttributionIdentifiers cannot be called on the main thread."
            r2.<init>((java.lang.String) r5)     // Catch:{ Exception -> 0x0017 }
            throw r2     // Catch:{ Exception -> 0x0017 }
        L_0x0017:
            r12 = move-exception
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0119 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0119 }
            r5.<init>()     // Catch:{ all -> 0x0119 }
            java.lang.String r6 = "Caught unexpected exception in getAttributionId(): "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0119 }
            java.lang.String r6 = r12.toString()     // Catch:{ all -> 0x0119 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0119 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0119 }
            com.facebook.internal.Utility.logd((java.lang.String) r2, (java.lang.String) r5)     // Catch:{ all -> 0x0119 }
            r2 = 0
            if (r10 == 0) goto L_0x003a
            r10.close()
        L_0x003a:
            return r2
        L_0x003b:
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x005a
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0017 }
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x0017 }
            long r0 = r2.fetchTime     // Catch:{ Exception -> 0x0017 }
            r18 = r0
            long r6 = r6 - r18
            r18 = 3600000(0x36ee80, double:1.7786363E-317)
            int r2 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r2 >= 0) goto L_0x005a
            com.facebook.internal.AttributionIdentifiers r2 = recentlyFetchedIdentifiers     // Catch:{ Exception -> 0x0017 }
            if (r10 == 0) goto L_0x003a
            r10.close()
            goto L_0x003a
        L_0x005a:
            r2 = 3
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0017 }
            r2 = 0
            java.lang.String r5 = "aid"
            r4[r2] = r5     // Catch:{ Exception -> 0x0017 }
            r2 = 1
            java.lang.String r5 = "androidid"
            r4[r2] = r5     // Catch:{ Exception -> 0x0017 }
            r2 = 2
            java.lang.String r5 = "limit_tracking"
            r4[r2] = r5     // Catch:{ Exception -> 0x0017 }
            r3 = 0
            android.content.pm.PackageManager r2 = r20.getPackageManager()     // Catch:{ Exception -> 0x0017 }
            java.lang.String r5 = "com.facebook.katana.provider.AttributionIdProvider"
            r6 = 0
            android.content.pm.ProviderInfo r11 = r2.resolveContentProvider(r5, r6)     // Catch:{ Exception -> 0x0017 }
            android.content.pm.PackageManager r2 = r20.getPackageManager()     // Catch:{ Exception -> 0x0017 }
            java.lang.String r5 = "com.facebook.wakizashi.provider.AttributionIdProvider"
            r6 = 0
            android.content.pm.ProviderInfo r16 = r2.resolveContentProvider(r5, r6)     // Catch:{ Exception -> 0x0017 }
            if (r11 == 0) goto L_0x00a9
            java.lang.String r2 = r11.packageName     // Catch:{ Exception -> 0x0017 }
            r0 = r20
            boolean r2 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r0, r2)     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x00a9
            java.lang.String r2 = "content://com.facebook.katana.provider.AttributionIdProvider"
            android.net.Uri r3 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x0017 }
        L_0x0095:
            java.lang.String r14 = getInstallerPackageName(r20)     // Catch:{ Exception -> 0x0017 }
            if (r14 == 0) goto L_0x009d
            r13.androidInstallerPackage = r14     // Catch:{ Exception -> 0x0017 }
        L_0x009d:
            if (r3 != 0) goto L_0x00be
            com.facebook.internal.AttributionIdentifiers r2 = cacheAndReturnIdentifiers(r13)     // Catch:{ Exception -> 0x0017 }
            if (r10 == 0) goto L_0x003a
            r10.close()
            goto L_0x003a
        L_0x00a9:
            if (r16 == 0) goto L_0x0095
            r0 = r16
            java.lang.String r2 = r0.packageName     // Catch:{ Exception -> 0x0017 }
            r0 = r20
            boolean r2 = com.facebook.internal.FacebookSignatureValidator.validateSignature(r0, r2)     // Catch:{ Exception -> 0x0017 }
            if (r2 == 0) goto L_0x0095
            java.lang.String r2 = "content://com.facebook.wakizashi.provider.AttributionIdProvider"
            android.net.Uri r3 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x0017 }
            goto L_0x0095
        L_0x00be:
            android.content.ContentResolver r2 = r20.getContentResolver()     // Catch:{ Exception -> 0x0017 }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0017 }
            if (r10 == 0) goto L_0x00d1
            boolean r2 = r10.moveToFirst()     // Catch:{ Exception -> 0x0017 }
            if (r2 != 0) goto L_0x00dc
        L_0x00d1:
            com.facebook.internal.AttributionIdentifiers r2 = cacheAndReturnIdentifiers(r13)     // Catch:{ Exception -> 0x0017 }
            if (r10 == 0) goto L_0x003a
            r10.close()
            goto L_0x003a
        L_0x00dc:
            java.lang.String r2 = "aid"
            int r9 = r10.getColumnIndex(r2)     // Catch:{ Exception -> 0x0017 }
            java.lang.String r2 = "androidid"
            int r8 = r10.getColumnIndex(r2)     // Catch:{ Exception -> 0x0017 }
            java.lang.String r2 = "limit_tracking"
            int r15 = r10.getColumnIndex(r2)     // Catch:{ Exception -> 0x0017 }
            java.lang.String r2 = r10.getString(r9)     // Catch:{ Exception -> 0x0017 }
            r13.attributionId = r2     // Catch:{ Exception -> 0x0017 }
            if (r8 <= 0) goto L_0x010e
            if (r15 <= 0) goto L_0x010e
            java.lang.String r2 = r13.getAndroidAdvertiserId()     // Catch:{ Exception -> 0x0017 }
            if (r2 != 0) goto L_0x010e
            java.lang.String r2 = r10.getString(r8)     // Catch:{ Exception -> 0x0017 }
            r13.androidAdvertiserId = r2     // Catch:{ Exception -> 0x0017 }
            java.lang.String r2 = r10.getString(r15)     // Catch:{ Exception -> 0x0017 }
            boolean r2 = java.lang.Boolean.parseBoolean(r2)     // Catch:{ Exception -> 0x0017 }
            r13.limitTracking = r2     // Catch:{ Exception -> 0x0017 }
        L_0x010e:
            if (r10 == 0) goto L_0x0113
            r10.close()
        L_0x0113:
            com.facebook.internal.AttributionIdentifiers r2 = cacheAndReturnIdentifiers(r13)
            goto L_0x003a
        L_0x0119:
            r2 = move-exception
            if (r10 == 0) goto L_0x011f
            r10.close()
        L_0x011f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public static AttributionIdentifiers getCachedIdentifiers() {
        return recentlyFetchedIdentifiers;
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers identifiers) {
        identifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = identifiers;
        return identifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        if (!FacebookSdk.isInitialized() || !FacebookSdk.getAdvertiserIDCollectionEnabled()) {
            return null;
        }
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                try {
                    this.queue.put(service);
                } catch (InterruptedException e) {
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder binder2) {
            this.binder = binder2;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, data, reply, 0);
                reply.readException();
                return reply.readString();
            } finally {
                reply.recycle();
                data.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            boolean limitAdTracking = true;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                data.writeInt(1);
                this.binder.transact(2, data, reply, 0);
                reply.readException();
                if (reply.readInt() == 0) {
                    limitAdTracking = false;
                }
                return limitAdTracking;
            } finally {
                reply.recycle();
                data.recycle();
            }
        }
    }
}
