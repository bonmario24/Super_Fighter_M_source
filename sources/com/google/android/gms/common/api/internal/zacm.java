package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zar;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
final class zacm extends zar {
    private final /* synthetic */ zack zaky;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zacm(zack zack, Looper looper) {
        super(looper);
        this.zaky = zack;
    }

    public final void handleMessage(Message message) {
        String str;
        switch (message.what) {
            case 0:
                PendingResult pendingResult = (PendingResult) message.obj;
                synchronized (this.zaky.zadp) {
                    if (pendingResult == null) {
                        this.zaky.zaks.zad(new Status(13, "Transform returned null"));
                    } else if (pendingResult instanceof zacc) {
                        this.zaky.zaks.zad(((zacc) pendingResult).getStatus());
                    } else {
                        this.zaky.zaks.zaa(pendingResult);
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String valueOf = String.valueOf(runtimeException.getMessage());
                if (valueOf.length() != 0) {
                    str = "Runtime exception on the transformation worker thread: ".concat(valueOf);
                } else {
                    str = new String("Runtime exception on the transformation worker thread: ");
                }
                Log.e("TransformedResultImpl", str);
                throw runtimeException;
            default:
                Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(message.what).toString());
                return;
        }
    }
}
