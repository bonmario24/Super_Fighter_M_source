package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
public final class PendingResults {
    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    private static final class zab<R extends Result> extends BasePendingResult<R> {
        public zab(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    private static final class zaa<R extends Result> extends BasePendingResult<R> {
        private final R zack;

        public zaa(R r) {
            super(Looper.getMainLooper());
            this.zack = r;
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            if (status.getStatusCode() == this.zack.getStatus().getStatusCode()) {
                return this.zack;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    private static final class zac<R extends Result> extends BasePendingResult<R> {
        private final R zacl;

        public zac(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.zacl = r;
        }

        /* access modifiers changed from: protected */
        public final R createFailedResult(Status status) {
            return this.zacl;
        }
    }

    @KeepForSdk
    public static PendingResult<Status> immediatePendingResult(Status status, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }

    @KeepForSdk
    public static <R extends Result> PendingResult<R> immediateFailedResult(R r, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r, "Result must not be null");
        Preconditions.checkArgument(!r.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zac zac2 = new zac(googleApiClient, r);
        zac2.setResult(r);
        return zac2;
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        Preconditions.checkNotNull(r, "Result must not be null");
        zab zab2 = new zab((GoogleApiClient) null);
        zab2.setResult(r);
        return new OptionalPendingResultImpl(zab2);
    }

    @KeepForSdk
    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(r, "Result must not be null");
        zab zab2 = new zab(googleApiClient);
        zab2.setResult(r);
        return new OptionalPendingResultImpl(zab2);
    }

    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult statusPendingResult = new StatusPendingResult(Looper.getMainLooper());
        statusPendingResult.cancel();
        return statusPendingResult;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        Preconditions.checkNotNull(r, "Result must not be null");
        Preconditions.checkArgument(r.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        zaa zaa2 = new zaa(r);
        zaa2.cancel();
        return zaa2;
    }

    @KeepForSdk
    private PendingResults() {
    }
}
