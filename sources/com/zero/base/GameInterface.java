package com.zero.base;

public interface GameInterface {
    String GetSdkChannel();

    void Init();

    void InitInUnity();

    void SdkSubmitExtendData(UserData userData);

    void SwitchAccount();

    boolean UseFeiliuSdk();

    void _GuideFinish();

    void _PurchaseSuccess(int i);

    void _RoleUpgrade(String str);

    boolean _UseSdk();

    void _charge(PayBeans payBeans);

    void _createRole(String str);

    void _facebookFriendsInvite();

    void _getFacebookFriends();

    boolean _isCPLogin();

    void _popCommunityView();

    void _reConnection();

    void _sdkLogout();

    void _shareToFacebook(String str, String str2, String str3, String str4);

    boolean _showContactWay();

    void _showFacebook();

    String getAccountServer();

    String getBackGroundServer();

    String getUpdateServer();

    String getUserActionServer();

    String isAppstore();

    String isChannelNone();

    void sdkExit();

    void sdkLogin();
}
