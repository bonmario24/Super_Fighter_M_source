package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.util.BillingHelper;
import java.util.ArrayList;

final class PurchaseApiResponseChecker {
    PurchaseApiResponseChecker() {
    }

    static BillingResult checkPurchasesBundleValidity(Bundle bundle, String logTag, String methodName) {
        BillingResult internalErrorResult = BillingResults.INTERNAL_ERROR;
        if (bundle == null) {
            BillingHelper.logWarn(logTag, String.format("%s got null owned items list", new Object[]{methodName}));
            return internalErrorResult;
        }
        int responseCode = BillingHelper.getResponseCodeFromBundle(bundle, logTag);
        BillingResult billingResult = BillingResult.newBuilder().setResponseCode(responseCode).setDebugMessage(BillingHelper.getDebugMessageFromBundle(bundle, logTag)).build();
        if (responseCode != 0) {
            BillingHelper.logWarn(logTag, String.format("%s failed. Response code: %s", new Object[]{methodName, Integer.valueOf(responseCode)}));
            return billingResult;
        } else if (!bundle.containsKey(BillingHelper.RESPONSE_INAPP_ITEM_LIST) || !bundle.containsKey(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST) || !bundle.containsKey(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST)) {
            BillingHelper.logWarn(logTag, String.format("Bundle returned from %s doesn't contain required fields.", new Object[]{methodName}));
            return internalErrorResult;
        } else {
            ArrayList<String> ownedSkus = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
            ArrayList<String> purchaseDataList = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
            ArrayList<String> signatureList = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
            if (ownedSkus == null) {
                BillingHelper.logWarn(logTag, String.format("Bundle returned from %s contains null SKUs list.", new Object[]{methodName}));
                return internalErrorResult;
            } else if (purchaseDataList == null) {
                BillingHelper.logWarn(logTag, String.format("Bundle returned from %s contains null purchases list.", new Object[]{methodName}));
                return internalErrorResult;
            } else if (signatureList != null) {
                return BillingResults.f71OK;
            } else {
                BillingHelper.logWarn(logTag, String.format("Bundle returned from %s contains null signatures list.", new Object[]{methodName}));
                return internalErrorResult;
            }
        }
    }
}
