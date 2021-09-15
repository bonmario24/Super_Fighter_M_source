package com.android.billingclient.api;

import android.support.annotation.Nullable;
import java.util.List;

public interface PurchasesUpdatedListener {
    void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list);
}
