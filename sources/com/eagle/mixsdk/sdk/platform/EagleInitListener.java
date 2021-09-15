package com.eagle.mixsdk.sdk.platform;

import com.eagle.mixsdk.sdk.ProductQueryResult;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import java.util.List;

@Deprecated
public interface EagleInitListener {
    void onInitResult(int i, String str);

    void onLoginFail(int i, String str);

    void onLoginResult(int i, EagleToken eagleToken);

    void onLogout();

    void onPayResult(int i, String str);

    void onProductQueryResult(List<ProductQueryResult> list);

    void onSwitchAccount(EagleToken eagleToken);
}
