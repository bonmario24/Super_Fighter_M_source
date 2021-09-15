package com.example.testrun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.eagle.mixsdk.sdk.PayParams;
import com.eagle.mixsdk.sdk.UserExtraData;
import com.eagle.mixsdk.sdk.did.DIDV4Proxy;
import com.eagle.mixsdk.sdk.listeners.EagleAntiAddictionListener;
import com.eagle.mixsdk.sdk.listeners.EagleInitListener;
import com.eagle.mixsdk.sdk.listeners.EagleInvokeListener;
import com.eagle.mixsdk.sdk.listeners.EagleLoginListener;
import com.eagle.mixsdk.sdk.listeners.EaglePayListener;
import com.eagle.mixsdk.sdk.platform.EaglePlatform;
import com.eagle.mixsdk.sdk.presenter.EagleExtPluginImpl;
import com.eagle.mixsdk.sdk.verify.EagleAntiAddictionInfo;
import com.eagle.mixsdk.sdk.verify.EagleToken;
import com.facebook.appevents.AppEventsConstants;
import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.NotchScreenManager;
import com.teamtop3.Defenders.UnityPlayerActivity;
import com.zero.base.PayBeans;
import com.zero.base.UserData;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class MainActivity extends UnityPlayerActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    Point AreaSize;
    public String app_user_id = "";
    private String cpid = "100079";
    private ProgressDialog dialog = null;
    private String game_secret = "ec6baf96876fe198";
    /* access modifiers changed from: private */
    public String gameid = "100221";
    private String gamekey = "375d6cdd569928ef";
    private String gamename = "wmdmx";
    public String grant_type = "authorization_code";
    Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    MainActivity.this.sdkloginCallBack(MainActivity.this.verifyInfo);
                    MainActivity.this.AntiAddition();
                    return;
                case 1:
                    MainActivity.this.submitExtraUserData(5);
                    MainActivity.this.GameSwitchAccount();
                    return;
                case 2:
                    MainActivity.this.submitExtraUserData(5);
                    MainActivity.this.GameSwitchAccount();
                    return;
                default:
                    return;
            }
        }
    };
    private boolean hasExitBox = true;
    private boolean isNewRole = false;
    private String isShowForum = "false";
    private String isShowShare = "false";
    /* access modifiers changed from: private */
    public boolean isinit;
    protected long lastClickTime;
    MainActivity m_activity = this;
    ShareData m_data;
    private NotchScreenManager notchScreenManager;
    ProgressDialog progressDialog = null;
    UserData userData;
    String userId;
    String userinfo = "";
    String verifyInfo;

    public static class ShareData {
        private static ShareData _data;
        public String path = "";
        public String summary = "";
        public String title = "";
        public String url = "";

        private static ShareData init() {
            if (_data == null) {
                _data = new ShareData();
            }
            return _data;
        }

        public static ShareData resolve(String str) {
            ShareData init = init();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("title")) {
                    init.title = jSONObject.getString("title");
                }
                if (jSONObject.has("summary")) {
                    init.summary = jSONObject.getString("summary");
                }
                if (jSONObject.has("url")) {
                    init.url = jSONObject.getString("url");
                }
                if (!jSONObject.has("path")) {
                    return init;
                }
                init.path = jSONObject.getString("path");
                return init;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void RequestPermission() {
        Log.e("Unity", "RequestPermission1");
        if (ContextCompat.checkSelfPermission(this, DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE) != 0 || ContextCompat.checkSelfPermission(this, DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE) != 0) {
            Log.e("Unity", "RequestPermission2222");
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_PHONE_STATE", DIDV4Proxy.PERMISSION_READ_EXTERNAL_STORAGE, DIDV4Proxy.PERMISSION_WRITE_EXTERNAL_STORAGE}, 1);
            Log.e("Unity", "RequestPermission3333");
        }
    }

    public static int[] getNotchSize(Context context) {
        int[] iArr = new int[2];
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0]);
        } catch (ClassNotFoundException e) {
            Log.e("test", "getNotchSize ClassNotFoundException");
            return iArr;
        } catch (NoSuchMethodException e2) {
            Log.e("test", "getNotchSize NoSuchMethodException");
            return iArr;
        } catch (Exception e3) {
            Log.e("test", "getNotchSize Exception");
            return iArr;
        } catch (Throwable th) {
            return iArr;
        }
    }

    /* access modifiers changed from: package-private */
    public void AntiAddition() {
        EaglePlatform.getInstance().getPlayerAntiAddictionInfo(new EagleAntiAddictionListener() {
            public void onResult(EagleAntiAddictionInfo eagleAntiAddictionInfo) {
                int antiAddictionState = eagleAntiAddictionInfo.getAntiAddictionState();
                if (antiAddictionState != -1 && antiAddictionState == 1) {
                    String identityPlate = eagleAntiAddictionInfo.getIdentityPlate();
                    eagleAntiAddictionInfo.getPlayerAge();
                }
            }
        });
    }

    public void CreateRole() {
        this.isNewRole = true;
    }

    public void GetBaiWanWeb() {
        Log.e("Unity", "服务器ID = " + this.userData.getZoneId() + " 角色名字 = " + this.userData.getRoleName());
    }

    public String GetGameBgName() {
        return "";
    }

    public String GetGameLogoName() {
        return GameConfig.logo_name;
    }

    public String GetGameSecret() {
        return this.game_secret;
    }

    public String GetGameSplashName() {
        return "";
    }

    public String GetLVL() {
        return "";
    }

    public String GetLogoIconRaw() {
        return "";
    }

    public String GetSafeAreaSize() {
        return this.AreaSize.toString();
    }

    public String GetSdkChannel() {
        return GameConfig.channel;
    }

    public String GetSplashImageRaw() {
        return "";
    }

    public String GetSplashImageSecondRaw() {
        return "";
    }

    public String GetinitChannel() {
        Log.e("Unity", "获取渠道号成功 = xc_xm_an_en");
        return GameConfig.channel;
    }

    public void Init() {
        Log.e("Unity", "SDK初始化成功");
    }

    public void InitInUnity() {
    }

    /* access modifiers changed from: package-private */
    public void InitOrderInfo(PayBeans payBeans) {
        PayParams payParams = new PayParams();
        payParams.setBuyNum(1);
        payParams.setExtension(new StringBuilder(String.valueOf(System.currentTimeMillis())).toString());
        BigDecimal bigDecimal = new BigDecimal(Float.toString(payBeans.getAmmount()));
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(100.0f));
        payParams.setMoney(new StringBuilder(String.valueOf(bigDecimal.multiply(bigDecimal2).intValue())).toString());
        payParams.setCurrency("USD");
        payParams.setPrice(bigDecimal.multiply(bigDecimal2).intValue());
        payParams.setProductId(payBeans.getSdk_pro_id());
        payParams.setProductName(payBeans.getPro_name());
        payParams.setProductDesc("");
        payParams.setGameName("亂鬥學院");
        payParams.setCoinNum(this.userData.getMoney());
        payParams.setRoleId(new StringBuilder(String.valueOf(this.userData.getRoleId())).toString());
        payParams.setRoleLevel(this.userData.getRoleLv());
        payParams.setRoleName(this.userData.getRoleName());
        payParams.setServerId(new StringBuilder(String.valueOf(this.userData.getZoneId())).toString());
        payParams.setServerName(this.userData.getZoneName());
        payParams.setVip(new StringBuilder(String.valueOf(this.userData.getVipLevel())).toString());
        payParams.setGameOrderID(new StringBuilder(String.valueOf(payBeans.getOrderId())).toString());
        payParams.setPayNotifyUrl("http://pay.funallstar.com/master/GM/payment/gzcxtc/android");
        payParams.setRatio(1);
        EaglePlatform.getInstance().pay((Activity) this, payParams, (EaglePayListener) new EaglePayListener() {
            public void onCancel() {
                Log.e("Unity", "onPayResult onCancel");
            }

            public void onFailed(String str) {
                Log.e("Unity", "onPayResult onFailed " + str);
            }

            public void onSuccess() {
                Log.e("Unity", "onPayResult onSuccess");
            }

            public void onUnknown() {
                Log.e("Unity", "onPayResult onUnknown");
            }
        });
    }

    public String IsShowForum() {
        return this.isShowForum;
    }

    public String IsShowPrivacyPolicy() {
        return "true";
    }

    public String IsShowShare() {
        return this.isShowShare;
    }

    public void LoginForum() {
    }

    /* access modifiers changed from: package-private */
    public void LoginSDK() {
        EaglePlatform.getInstance().login(this, new EagleLoginListener() {
            public void onLoginFail(String str) {
                Toast.makeText(MainActivity.this, str, 0).show();
            }

            public void onLoginSuccess(EagleToken eagleToken) {
                MainActivity.this.verifyInfo = String.valueOf("loginvalid.php?") + "accountid=" + eagleToken.getSdkUserID() + "&gameid=" + MainActivity.this.gameid + "&sessionid=" + eagleToken.getToken();
                Log.e("Unity", MainActivity.this.verifyInfo);
                MainActivity.this.handler.sendEmptyMessage(0);
            }

            public void onLogout() {
                Toast.makeText(MainActivity.this, "退出帐号成功", 0).show();
                MainActivity.this.handler.sendEmptyMessage(1);
            }

            public void onSwitchAccount(EagleToken eagleToken) {
                MainActivity.this.userId = eagleToken.getSdkUserID();
                MainActivity.this.handler.sendEmptyMessage(2);
            }
        });
    }

    public void LoginSuccess() {
        try {
            JSONObject jSONObject = new JSONObject(this.userinfo);
            Log.e("Unity", "游戏登录成功");
            Log.e("Unity", this.userinfo);
            submitExtraUserData(3);
            if (this.isNewRole) {
                Log.e("Unity", "新角色创建成功");
                if (jSONObject.has("sceneValue")) {
                    jSONObject.put("sceneValue", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                }
                this.userinfo = jSONObject.toString();
                Log.e("Unity", this.userinfo);
                this.isNewRole = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void RoleLvUp() {
        Log.e("Unity", this.userinfo);
    }

    public void SdkSubmitExtendData(UserData userData2) {
        this.userData = userData2;
    }

    public void ShareImageToQQ(String str) {
    }

    public void ShareToQQ(String str) {
    }

    public void ShareToQzone(String str) {
    }

    public void SubmitDataForJosn(String str) {
        this.userinfo = str;
        Log.e("Unity", this.userinfo);
    }

    public void SwitchAccount() {
        if (EaglePlatform.getInstance().isSupportMethod("switchLogin")) {
            EaglePlatform.getInstance().switchLogin();
        } else if (EaglePlatform.getInstance().isSupportMethod("logout")) {
            EaglePlatform.getInstance().logout();
        } else {
            sdkLogin();
        }
    }

    public boolean UseFeiliuSdk() {
        return false;
    }

    public void _GuideFinish() {
        HashMap hashMap = new HashMap();
        hashMap.put("eventName", "GuideFinish");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("role_id", Integer.valueOf(this.userData.getRoleId()));
        hashMap2.put("role_name", this.userData.getRoleName());
        hashMap.put("eventValue", hashMap2);
        EaglePlatform.getInstance().reportEvent(this, "doTrackEvent", hashMap);
    }

    public void _PurchaseSuccess(int i) {
    }

    public void _RoleUpgrade(String str) {
        submitExtraUserData(4);
    }

    public boolean _UseSdk() {
        return true;
    }

    public void _charge(PayBeans payBeans) {
        if (isValidHits() && this.isinit) {
            Log.e("Unity", "orderId = " + payBeans.getOrderId() + " 金额 = " + payBeans.getAmmount() + "物品名字 = " + payBeans.getPro_name());
            InitOrderInfo(payBeans);
        }
    }

    public void _createRole(String str) {
        submitExtraUserData(2);
    }

    public void _facebookFriendsInvite() {
    }

    public void _getFacebookFriends() {
    }

    public boolean _isCPLogin() {
        return false;
    }

    public void _popCommunityView() {
    }

    public void _reConnection() {
    }

    public void _sdkLogout() {
    }

    public void _shareToFacebook(String str, String str2, String str3, String str4) {
        Log.e("Unity", "call facebook share");
        EaglePlatform.getInstance().invoke(this, EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE, (Map<String, Object>) null, new EagleInvokeListener() {
            public void onFailed(String str, String str2) {
                switch (str.hashCode()) {
                    case -1737511442:
                        if (str.equals(EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE)) {
                            Log.e("Unity", "share fail result:" + str2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }

            public void onSuccess(String str, Map<String, Object> map, EagleInvokeListener.CallbackListener callbackListener) {
                switch (str.hashCode()) {
                    case -1737511442:
                        if (str.equals(EagleExtPluginImpl.EXT_PLUGIN_FACEBOOK_SHARE)) {
                            Log.e("Unity", "share success result:" + map.toString());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public boolean _showContactWay() {
        return false;
    }

    public void _showFacebook() {
    }

    public void doLogin(boolean z) {
        sdkLogin();
    }

    public String getAccountServer() {
        return "login.funallstar.com";
    }

    public String getBackGroundServer() {
        return "login.funallstar.com";
    }

    public String getUpdateServer() {
        return GameConfig.Hosts_updateServer;
    }

    public String getUserActionServer() {
        return "login.funallstar.com";
    }

    public void getUser_Me() {
    }

    public String isAppstore() {
        return null;
    }

    public String isChannelNone() {
        return "false";
    }

    /* access modifiers changed from: protected */
    public boolean isValidHits() {
        if (System.currentTimeMillis() - this.lastClickTime <= 3000) {
            return false;
        }
        this.lastClickTime = System.currentTimeMillis();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EaglePlatform.getInstance().onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.hasExitBox) {
            EaglePlatform.getInstance().exitSDK();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确定退出游戏吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        EaglePlatform.getInstance().onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.m_activity = this;
        super.onCreate(bundle);
        Log.e("Unity", "SDK Create1");
        RequestPermission();
        this.notchScreenManager = NotchScreenManager.getInstance();
        int[] notchSize = getNotchSize(this.m_activity);
        Log.e("Unity", "areaSize[0]:" + notchSize[0] + "  areaSize[1]:" + notchSize[1]);
        this.AreaSize = new Point();
        this.notchScreenManager.setDisplayInNotch(this);
        this.notchScreenManager.getNotchInfo(this, new INotchScreen.NotchScreenCallback() {
            public void onResult(INotchScreen.NotchScreenInfo notchScreenInfo) {
                if (notchScreenInfo.hasNotch) {
                    for (Rect next : notchScreenInfo.notchRects) {
                        MainActivity.this.AreaSize.x = next.width() * 2;
                        MainActivity.this.AreaSize.y = next.height();
                    }
                }
                Log.e("Unity", "GetAreaSize:" + MainActivity.this.GetSafeAreaSize());
            }
        });
        EaglePlatform.getInstance().init((Activity) this, (EagleInitListener) new EagleInitListener() {
            public void onFailed(String str) {
                Toast.makeText(MainActivity.this, "初始化失败:" + str, 1).show();
            }

            public void onSuccess() {
                System.out.println("onInitResult: success");
                MainActivity.this.isinit = true;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        EaglePlatform.getInstance().onDestroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        EaglePlatform.getInstance().onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        EaglePlatform.getInstance().onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        Log.e("Unity", "onRequestPermissionsResult000");
        EaglePlatform.getInstance().onRequestPermissionsResult(i, strArr, iArr);
        switch (i) {
            case 1:
                if (iArr.length <= 0 || iArr[0] != 0) {
                    finish();
                    System.exit(0);
                    Log.e("Unity", "onRequestPermissionsResult222");
                    return;
                }
                Log.e("Unity", "onRequestPermissionsResult111");
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        EaglePlatform.getInstance().onRestart();
        super.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        EaglePlatform.getInstance().onResume();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        EaglePlatform.getInstance().onStart();
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        EaglePlatform.getInstance().onStop();
        super.onStop();
    }

    public void sdkExit() {
        GameQuit();
        onBackPressed();
    }

    public void sdkLogin() {
        Log.e("Unity", "SDK登陆成功");
        if (this.isinit) {
            LoginSDK();
        }
    }

    public void submitExtraUserData(int i) {
        if (this.userData != null) {
            UserExtraData userExtraData = new UserExtraData();
            userExtraData.setDataType(i);
            userExtraData.setMoneyNum(this.userData.getMoney());
            userExtraData.setRoleID(new StringBuilder(String.valueOf(this.userData.getRoleId())).toString());
            userExtraData.setRoleName(this.userData.getRoleName());
            userExtraData.setRoleLevel(new StringBuilder(String.valueOf(this.userData.getRoleLv())).toString());
            userExtraData.setVip(new StringBuilder(String.valueOf(this.userData.getVipLevel())).toString());
            userExtraData.setServerID(this.userData.getZoneId());
            userExtraData.setServerName(this.userData.getZoneName());
            userExtraData.setRoleGender(0);
            userExtraData.setPartyName(this.userData.getGangName());
            EaglePlatform.getInstance().submitExtraData(userExtraData);
        }
    }
}
