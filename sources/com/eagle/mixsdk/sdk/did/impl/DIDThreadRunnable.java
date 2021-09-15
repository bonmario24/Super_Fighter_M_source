package com.eagle.mixsdk.sdk.did.impl;

import android.content.Context;
import android.util.Log;
import com.doraemon.p027eg.CheckUtils;
import com.doraemon.util.PermissionUtils;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.eagle.mixsdk.sdk.did.config.DIDConfig;
import com.eagle.mixsdk.sdk.did.listener.IDIDObtainListener;
import com.eagle.mixsdk.sdk.did.utils.DeviceIDUtil;
import com.eagle.mixsdk.sdk.did.utils.ExternalDidDisposeUtil;
import com.eagle.mixsdk.sdk.did.utils.NewDevDidSpUtil;
import com.eagle.mixsdk.sdk.did.utils.NewDevDidUtil;

public class DIDThreadRunnable implements Runnable {
    private Context context;
    private String mVersionTag;
    private IDIDObtainListener obtainListener;

    public DIDThreadRunnable(Context context2, String versionTag, IDIDObtainListener obtainListener2) {
        this.context = context2;
        this.mVersionTag = versionTag;
        this.obtainListener = obtainListener2;
    }

    public void run() {
        String matcherDID;
        int i;
        try {
            String hiddenDID = NewDevDidUtil.getInstance().readDidFromHidden(this.context, DIDConfig.UUID_FILE_TAG + this.mVersionTag);
            Log.d("UniqueID-" + this.mVersionTag, "didFromHiddenDID:" + hiddenDID);
            String didToPicture = ExternalDidDisposeUtil.readDidToPicture(this.context, "ocean" + this.mVersionTag);
            String didToVideo = ExternalDidDisposeUtil.readDidToVideo(this.context, "ocean" + this.mVersionTag);
            String didToAudio = ExternalDidDisposeUtil.readDidToAudio(this.context, ExternalDidDisposeUtil.AUDIO_FILE_NAME + this.mVersionTag);
            String cacheDID = NewDevDidSpUtil.getInstance().read(this.context, DIDConfig.SP_DID_TAG + this.mVersionTag);
            Log.d("UniqueID-" + this.mVersionTag, "didFromCacheDID:" + cacheDID);
            String[] didArray = {hiddenDID, didToAudio, didToVideo, didToPicture, cacheDID};
            String matcherDID2 = null;
            int length = didArray.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String did = didArray[i2];
                if (!CheckUtils.isNullOrEmpty(did) && DIDConfig.didPattern.matcher(did).matches()) {
                    matcherDID2 = did;
                    break;
                }
                i2++;
            }
            boolean isValid = !CheckUtils.isNullOrEmpty(matcherDID);
            if (!PermissionUtils.isGranted(DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                IDIDObtainListener iDIDObtainListener = this.obtainListener;
                if (isValid) {
                    i = 2;
                } else {
                    i = 0;
                }
                iDIDObtainListener.onResult(matcherDID, i);
                return;
            }
            if (isValid) {
                this.obtainListener.onResult(matcherDID, 2);
            } else {
                if (this.mVersionTag.equals(DeviceIDUtil.V2_VERSION_TAG)) {
                    matcherDID = NewDevDidUtil.getInstance().newDidToV2(this.context);
                } else {
                    matcherDID = NewDevDidUtil.getInstance().newDidToV3(this.context);
                }
                this.obtainListener.onResult(matcherDID, 1);
            }
            if (CheckUtils.isNullOrEmpty(hiddenDID) || !matcherDID.equals(hiddenDID)) {
                NewDevDidUtil.getInstance().writeDidToHidden(this.context, DIDConfig.UUID_FILE_TAG + this.mVersionTag, matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(didToPicture) || !matcherDID.equals(didToPicture)) {
                ExternalDidDisposeUtil.saveDidToPicture(this.context, "ocean" + this.mVersionTag, matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(didToVideo) || !matcherDID.equals(didToVideo)) {
                ExternalDidDisposeUtil.saveDidToVideo(this.context, "ocean" + this.mVersionTag, matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(didToAudio) || !matcherDID.equals(didToAudio)) {
                ExternalDidDisposeUtil.saveDidToAudio(this.context, ExternalDidDisposeUtil.AUDIO_FILE_NAME + this.mVersionTag, matcherDID);
            }
            if (CheckUtils.isNullOrEmpty(cacheDID) || !matcherDID.equals(cacheDID)) {
                NewDevDidSpUtil.getInstance().save(this.context, DIDConfig.SP_DID_TAG + this.mVersionTag, matcherDID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.obtainListener.onResult((String) null, 0);
        }
    }
}
