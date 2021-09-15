package com.eagle.mixsdk.sdk.base;

import com.eagle.mixsdk.sdk.ProductQueryResult;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import java.util.List;

@Deprecated
public interface IEagleSDKListener {
    void onAuthResult(EagleToken eagleToken);

    void onLoginFail(int i, String str);

    void onLoginResult(String str);

    void onLogout();

    void onProductQueryResult(List<ProductQueryResult> list);

    void onResult(int i, String str);

    void onSwitchAccount();

    void onSwitchAccount(String str);
}
