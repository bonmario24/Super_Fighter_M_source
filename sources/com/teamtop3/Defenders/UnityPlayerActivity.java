package com.teamtop3.Defenders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.android.billingclient.api.BillingFlowParams;
import com.facebook.appevents.AppEventsConstants;
import com.unity3d.player.UnityPlayer;
import com.zero.base.GameInterface;
import com.zero.base.LoadMethodEx;
import com.zero.base.PayBeans;
import com.zero.base.UserData;
import com.zero.service.SimpleService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import org.json.JSONObject;

public abstract class UnityPlayerActivity extends Activity implements GameInterface {
    final int CLOSEWEB = 1;
    final int OPENWEB = 0;
    String appKey = "4ad386eef199d094ecbbe969973da0fe";
    boolean is_exit = false;
    protected UnityPlayer mUnityPlayer;
    String mWeburl;
    public UnityPlayerActivity m_GameActivity = null;
    Localization m_Localiza = null;
    public PayBeans m_beans;
    private boolean m_showAccount = true;
    LinearLayout m_topLayout;
    public UserData m_userData;
    FrameLayout m_webLayout;

    public static String Md5Secret(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes("utf-8"));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                if (Integer.toHexString(digest[i] & 255).length() == 1) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO).append(Integer.toHexString(digest[i] & 255));
                } else {
                    stringBuffer.append(Integer.toHexString(digest[i] & 255));
                }
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return str;
        }
    }

    public void AchievedLevel(String str) {
    }

    public void ClearNotifications() {
        Log.d("unity", "�յ�call ClearNotifications������Ϣ��test ");
        SimpleService.ClearNotifications();
    }

    public void ClosePushMessage(String str) {
        Log.d("unity", "�յ�call ClosePushMessage������Ϣ��test " + str);
        SimpleService.closePush(str);
    }

    public void Exit() {
        finish();
    }

    public void ExitBySDK() {
        Log.e("SDK ---- >", "ExitBySDK");
        Log.e("SDK ---- >", "222222222222222222222222222222");
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    UnityPlayerActivity.this.m_GameActivity.sdkExit();
                }
            }
        });
    }

    public void FeiLiuAcceptTaskStatistics(String str) {
    }

    public void FeiLiuAccountInformationStatistics(String str) {
        try {
            if (this.m_GameActivity.UseFeiliuSdk()) {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(BillingFlowParams.EXTRA_PARAM_KEY_ACCOUNT_ID);
                String string2 = jSONObject.getString("accountName");
                int i = jSONObject.getInt("roleLevel");
                String valueOf = String.valueOf(jSONObject.getInt("roleId"));
                String string3 = jSONObject.getString("roleName");
                String.valueOf(jSONObject.getString("zoneName")) + jSONObject.getInt("zoneId");
            }
        } catch (Exception e) {
            Log.e("unity", "��ȡ�����Ϣ����JSON�쳣");
        }
    }

    public void FeiLiuCompleteTaskStatistics(String str) {
    }

    public void FeiLiuConSumptionStatistics(String str) {
    }

    public void FeiLiuLoginStatistics(String str) {
    }

    public void FeiLiuMoneyRewardStatistics(String str) {
    }

    public void FeiLiuOrderStatistics(String str) {
    }

    public void FeiLiuPayFailureStatistics(String str) {
    }

    public void FeiLiuPaySuccessStatistics(String str) {
    }

    public void FeiLiuPropConsumptionStatistics(String str) {
    }

    public void FeiLiuTaskFailureStatistics(String str) {
    }

    /* access modifiers changed from: package-private */
    public void FinishLocalization() {
    }

    public void GameQuit() {
        if (_UseSdk()) {
            UnityPlayer.UnitySendMessage("Platform", "GameQuit", "");
        }
    }

    public void GameSwitchAccount() {
        if (_UseSdk()) {
            Log.e("Unity", "GameSwitchAccount");
            UnityPlayer.UnitySendMessage("Platform", "GameSwitchAccount", "");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = (r1 = (android.telephony.TelephonyManager) getSystemService("phone")).getDeviceId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String GetDeviceID() {
        /*
            r4 = this;
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 28
            if (r2 > r3) goto L_0x0017
            java.lang.String r2 = "phone"
            java.lang.Object r1 = r4.getSystemService(r2)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            if (r1 == 0) goto L_0x0017
            java.lang.String r0 = r1.getDeviceId()
            if (r0 == 0) goto L_0x0017
        L_0x0016:
            return r0
        L_0x0017:
            java.lang.String r0 = "unknown device id"
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teamtop3.Defenders.UnityPlayerActivity.GetDeviceID():java.lang.String");
    }

    public String GetDeviceName() {
        return Build.BRAND + " " + Build.MODEL;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = (android.telephony.TelephonyManager) getSystemService("phone");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String GetIMEI() {
        /*
            r3 = this;
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 > r2) goto L_0x0015
            java.lang.String r1 = "phone"
            java.lang.Object r0 = r3.getSystemService(r1)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L_0x0015
            java.lang.String r1 = r0.getDeviceId()
        L_0x0014:
            return r1
        L_0x0015:
            java.lang.String r1 = "unknow"
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teamtop3.Defenders.UnityPlayerActivity.GetIMEI():java.lang.String");
    }

    public String GetIModel() {
        return Build.MODEL;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = r2.getAbsolutePath();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String GetInternalPath() {
        /*
            r6 = this;
            java.io.File r2 = r6.getFilesDir()     // Catch:{ Exception -> 0x000d }
            if (r2 == 0) goto L_0x0026
            java.lang.String r0 = r2.getAbsolutePath()     // Catch:{ Exception -> 0x000d }
            if (r0 == 0) goto L_0x0026
        L_0x000c:
            return r0
        L_0x000d:
            r1 = move-exception
            java.lang.String r3 = "UnityPlayerNativeActivity"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "GetInternalPath error:"
            r4.<init>(r5)
            java.lang.String r5 = r1.getMessage()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r3, r4)
        L_0x0026:
            java.lang.String r0 = ""
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teamtop3.Defenders.UnityPlayerActivity.GetInternalPath():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = r1.getConnectionInfo().getMacAddress();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String GetMac() {
        /*
            r3 = this;
            java.lang.String r2 = "wifi"
            java.lang.Object r1 = r3.getSystemService(r2)
            android.net.wifi.WifiManager r1 = (android.net.wifi.WifiManager) r1
            if (r1 == 0) goto L_0x0015
            android.net.wifi.WifiInfo r2 = r1.getConnectionInfo()
            java.lang.String r0 = r2.getMacAddress()
            if (r0 == 0) goto L_0x0015
        L_0x0014:
            return r0
        L_0x0015:
            java.lang.String r0 = "unknown mac"
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teamtop3.Defenders.UnityPlayerActivity.GetMac():java.lang.String");
    }

    public String GetMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return String.valueOf(displayMetrics.heightPixels) + "x" + displayMetrics.widthPixels;
    }

    public int GetNetState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        if (connectivityManager == null) {
            return 0;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            return 1;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        return (networkInfo2 == null || networkInfo2.getState() != NetworkInfo.State.CONNECTED) ? 0 : 2;
    }

    public boolean GetShowAccount() {
        return this.m_showAccount;
    }

    public String GetinitChannel() {
        return GetSdkChannel();
    }

    public void GuideFinish() {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._GuideFinish();
                }
            });
        }
    }

    public void Login() {
        Log.e("SuperSdk", "Login..................");
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("SuperSdk", "Login_UseSdk..................");
                    UnityPlayerActivity.this.m_GameActivity.sdkLogin();
                }
            }
        });
    }

    public void OpenDialog(final String str, final String str2, final DialogInterface.OnClickListener onClickListener) {
        runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(UnityPlayerActivity.this.m_GameActivity);
                String str = str2;
                String str2 = "ȷ��";
                String str3 = "ȡ��";
                String[] split = str2.split("_", 3);
                if (split.length > 1) {
                    str = split[0];
                    str2 = split[1];
                    str3 = split[2];
                }
                if (str2 != null && !str2.isEmpty()) {
                    builder.setTitle(str);
                }
                builder.setMessage(str).setCancelable(false).setPositiveButton(str2, onClickListener).setNegativeButton(str3, onClickListener);
                builder.create().show();
            }
        });
    }

    public void OpenPushMessage(String str) {
        Log.d("unity", "�յ�call OpenPushMessage������Ϣ��test " + str);
        SimpleService.openPush(str);
    }

    public void PurchaseSuccess(final String str) {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._PurchaseSuccess(Integer.valueOf(str).intValue());
                }
            });
        }
    }

    public void Quit() {
        Log.e("SDK ---- >", "ExitBySDK");
        Log.e("SDK ---- >", "33333333333333333333333333");
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    UnityPlayerActivity.this.m_GameActivity.sdkExit();
                }
            }
        });
    }

    public void RemovePushMessage(String str) {
        Log.d("unity", "�յ�call RemovePushMessage������Ϣ��test " + str);
        SimpleService.RemovePushMessage(str);
    }

    public void RoleUpgrade(final String str) {
        int intValue = Integer.valueOf(str).intValue();
        if (this.m_userData != null && intValue > 0) {
            this.m_userData.setRoleLv(intValue);
            runOnUiThread(new Runnable() {
                public void run() {
                    if (UnityPlayerActivity.this._UseSdk()) {
                        UnityPlayerActivity.this.m_GameActivity._RoleUpgrade(str);
                    }
                }
            });
        }
    }

    public void SDKSwitchAccount(String str) {
        if (_UseSdk()) {
            Log.e("Unity", "SDKSwitchAccount  &&  accountId = " + str);
            UnityPlayer.UnitySendMessage("Platform", "SDKSwitchAccount", str);
        }
    }

    public void SDkLoginout() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("unity", " SDkLoginout");
                    UnityPlayerActivity.this.m_GameActivity._sdkLogout();
                }
            }
        });
    }

    public void SetChargeUrl(String str) {
        Log.e("Unity", "SetChargeUrl......" + str);
    }

    public void SetPushMessage(String str, int i, int i2, String str2, String str3, boolean z) {
        Log.d("unity", "�յ�call SetPushMessage������Ϣ��test " + i + i2 + str2 + str3 + z);
        SimpleService.setOnePush(str, i, i2, str2, str3, z);
    }

    public void SetShowAccount(boolean z) {
        this.m_showAccount = z;
    }

    public void SetSpecialPushMessage(String str, int i, String str2, String str3, boolean z) {
        Log.d("unity", "�յ�call SetSpecialPushMessage������Ϣ��test " + i + str2 + str3 + z);
        SimpleService.setDisposablePush(str, i, str2, str3, z);
    }

    public void Unity_Account() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    UnityPlayerActivity.this.m_GameActivity.SwitchAccount();
                }
            }
        });
    }

    public void Unity_finish() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("unity", "unity ĳЩԭ����Ҫ finish");
                    UnityPlayerActivity.this.finish();
                }
            }
        });
    }

    public String UseSdk() {
        return new StringBuilder(String.valueOf(_UseSdk())).toString();
    }

    public Object _ZSSDKDispatcher(String str) {
        Log.i("Andoird_log ", "android " + str);
        Object Load = LoadMethodEx.Load((String) null, str, (Object[]) null, this.m_GameActivity);
        Log.i("Andoird_log ", "o =  " + Load);
        return Load;
    }

    public Object _ZSSDKDispatcher(String str, String[] strArr) {
        Log.i("Andoird_log ", "android " + str + " = " + strArr);
        for (int i = 0; i < strArr.length; i++) {
            Log.i("Andoird_log ", "data[" + i + "] = " + strArr[i]);
        }
        Object Load = LoadMethodEx.Load((String) null, str, strArr, this.m_GameActivity);
        Log.i("Andoird_log ", "o =  " + Load);
        return Load;
    }

    public void charge(String str) {
        this.m_beans = PayBeans.resolve(str);
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    UnityPlayerActivity.this.m_GameActivity._charge(UnityPlayerActivity.this.m_beans);
                }
            }
        });
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.getAction() == 2 ? this.mUnityPlayer.injectEvent(keyEvent) : super.dispatchKeyEvent(keyEvent);
    }

    public void extInfo1() {
        Log.e("SuperSdk", "Login..................");
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("SuperSdk", "Login_UseSdk..................");
                }
            }
        });
    }

    public void extInfo2() {
        Log.e("SuperSdk", "Login..................");
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("SuperSdk", "Login_UseSdk..................");
                }
            }
        });
    }

    public void facebookFriendsInvite() {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._facebookFriendsInvite();
                }
            });
        }
    }

    public String getAppVersion() {
        String str = "";
        try {
            PackageManager packageManager = getPackageManager();
            String packageName = getPackageName();
            String str2 = packageManager.getPackageInfo(packageName, 0).versionName;
            if (str2 == null || str2.length() <= 0) {
                return "";
            }
            str = String.valueOf(packageName) + "_" + str2;
            return str;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
    }

    public String getCPUVersion() {
        String str = "unkonw";
        try {
            Process exec = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            if (exec != null) {
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(exec.getInputStream()));
                int i = 1;
                while (true) {
                    if (i < 100) {
                        String readLine = lineNumberReader.readLine();
                        if (readLine != null && readLine.contains("Hardware")) {
                            str = readLine.split("\\:", 2)[1].trim();
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            } else {
                Log.e("Unity", "��ȡcpu�ͺ�ʧ��");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Unity", "��ȡcpu�ͺ����쳣");
        }
        Log.e("Unity", "cpu �ͺ�Ϊ: " + str);
        return str;
    }

    /* access modifiers changed from: package-private */
    public int getCpuCores() {
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            });
            if (listFiles.length == 0) {
                Log.e("unity", "��ȡcpu�����ֶλ�ȡʧ��");
                return 1;
            }
            Log.e("SuperSdk", "CPU Count: " + listFiles.length);
            return listFiles.length;
        } catch (Exception e) {
            Log.e("SuperSdk", "CPU Count: Failed.");
            Log.e("unity", "��ȡcpu����ʧ��");
            e.printStackTrace();
            return 1;
        }
    }

    /* access modifiers changed from: package-private */
    public String getEquipmentVersion() {
        String str = Build.MODEL;
        Log.e("Unity", "�豸�ͺ�Ϊ: " + str);
        return str;
    }

    public String getEquipmentinfo() {
        String equipmentVersion = getEquipmentVersion();
        String str = String.valueOf(equipmentVersion) + "&" + getCPUVersion() + "&" + getMinCpuFreq() + "&" + getTotalMemory() + "&" + String.valueOf(getCpuCores());
        Log.e("SuperSdk", "��ȡ�豸��ϢΪ: " + str);
        return str;
    }

    public void getFacebookFriends() {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._getFacebookFriends();
                }
            });
        }
    }

    public String getGameVersion() {
        return readAssetsTxt(this.m_GameActivity, "packVersion");
    }

    public String getMinCpuFreq() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder(new String[]{"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"}).start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = String.valueOf(str) + new String(bArr);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            Log.e("Unity", "��ȡcpu���Ƶ�����쳣");
        }
        if ("" == str) {
            str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        int i = 0;
        try {
            i = Integer.valueOf(str.trim()).intValue();
        } catch (NumberFormatException e2) {
            Log.e("unity", "��ȡcpu���Ƶ������ת���쳣");
        }
        String str2 = String.valueOf(String.valueOf(new BigDecimal((double) (((float) i) / 1000000.0f)).setScale(2, 4).floatValue())) + "GHZ";
        Log.e("Unity", "cpu ���Ƶ��ΪΪ: " + str2.trim());
        return str2;
    }

    public String getScreenInches() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i = displayMetrics.densityDpi;
        float f2 = displayMetrics.xdpi;
        Log.e("unity", "xdpi=" + f2 + "; ydpi=" + displayMetrics.ydpi);
        Log.e("unity", "density=" + f + "; densityDPI=" + i);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        Log.e("unity", "screenWidthDip=" + i2 + "; screenHeightDip=" + i3);
        double sqrt = Math.sqrt(Math.pow((double) i2, 2.0d) + Math.pow((double) i3, 2.0d)) / ((double) i);
        Log.e("unity", "Inches is  " + sqrt);
        return String.valueOf(sqrt);
    }

    /* access modifiers changed from: package-private */
    public String getTotalMemory() {
        long j = 0;
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            if (fileReader != null) {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
                String[] split = bufferedReader.readLine().split("\\s+");
                if (split.length == 0) {
                    split[1] = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                    Log.e("Unity", "��ȡ�ֻ����ڴ��ֶ�Ϊ��");
                }
                try {
                    j = (long) (Integer.valueOf(split[1]).intValue() * 1024);
                } catch (NumberFormatException e) {
                    Log.e("unity", "��ȡ���ڴ��ֶ�ת���쳣��������");
                    j = 0;
                }
                bufferedReader.close();
            }
        } catch (IOException e2) {
            Log.e("Unity", "��ȡ�ֻ����ڴ�ʧ��,���쳣");
        }
        String formatFileSize = Formatter.formatFileSize(getBaseContext(), j);
        Log.e("SuperSdk", "TotalMemory's space is " + formatFileSize);
        return formatFileSize;
    }

    public long getUsableSpace() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        Log.e("Unity", "onCreate1");
        if ((getIntent().getFlags() & 4194304) != 0) {
            finish();
            return;
        }
        getWindow().takeSurface((SurfaceHolder.Callback2) null);
        setTheme(16973831);
        getWindow().setFormat(2);
        Log.e("Unity", "onCreate2");
        this.mUnityPlayer = new UnityPlayer(this);
        if (this.mUnityPlayer.getSettings().getBoolean("hide_status_bar", true)) {
            getWindow().setFlags(1024, 1024);
        }
        setContentView(this.mUnityPlayer);
        this.mUnityPlayer.requestFocus();
        this.m_GameActivity = this;
        Log.e("Unity", "onCreate3");
        this.m_GameActivity = this;
        this.m_GameActivity.UseFeiliuSdk();
        if (this.m_Localiza == null) {
            this.m_Localiza = new Localization(this.m_GameActivity);
        }
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Log.e("Unity", "Unity_Login_sucess");
                    UnityPlayerActivity.this.m_GameActivity.Init();
                }
            });
        }
        Log.e("Unity", "onCreate4");
    }

    public void onCreateRole(String str) {
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        SimpleService.ActivityDestroy();
        Log.e("SuperSdk", "onDestroy..............");
        super.onDestroy();
        this.mUnityPlayer.quit();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mUnityPlayer.injectEvent(keyEvent);
    }

    public void onLogin(String str) {
    }

    public void onOrderPaySucc(String str) {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mUnityPlayer.pause();
        this.m_GameActivity.UseFeiliuSdk();
    }

    public void onPay() {
    }

    public void onPlaceOrder(String str, String str2, String str3) {
    }

    public void onRegister(String str) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mUnityPlayer.resume();
        this.m_GameActivity.UseFeiliuSdk();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.injectEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mUnityPlayer.windowFocusChanged(z);
    }

    public void popCommunityView() {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._popCommunityView();
                }
            });
        }
    }

    public void quits() {
        Log.e("SDK ---- >", "ExitBySDK");
        Log.e("SDK ---- >", "111111111111111111");
        if (!this.is_exit) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (UnityPlayerActivity.this._UseSdk()) {
                        UnityPlayerActivity.this.m_GameActivity.sdkExit();
                    }
                }
            });
        }
    }

    public String readAssetsTxt(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Unity", "��ȡ�汾�Ŵ��������ļ�assetsĿ¼�µ�packVersion�ļ�!");
            return "0.0.0";
        }
    }

    public void reconnection() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (UnityPlayerActivity.this._UseSdk()) {
                    Log.e("unity", " ��������");
                    UnityPlayerActivity.this.m_GameActivity._reConnection();
                }
            }
        });
    }

    public void sdkloginCallBack(String str) {
        if (_UseSdk()) {
            Log.e("Unity", "��ȡ ID�ɹ� sdklogin account = " + str);
            UnityPlayer.UnitySendMessage("Platform", "Android_login", str);
        }
    }

    public void setArenaAndCamp(String str) {
    }

    public void setEighteenSprite(String str) {
    }

    public void setFreeBox(String str, String str2) {
    }

    public void setFullSprite(String str, String str2) {
    }

    public void setFullToughening(String str, String str2) {
    }

    public void setShopFresh(String str) {
    }

    public void setTwelveSprite(String str) {
    }

    public void setbool() {
        this.is_exit = false;
    }

    public void shareToFacebook(String str, String str2, String str3, String str4) {
        if (_UseSdk()) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._shareToFacebook(str5, str6, str7, str8);
                }
            });
        }
    }

    public String showContactWay() {
        return this.m_GameActivity != null ? new StringBuilder(String.valueOf(this.m_GameActivity._showContactWay())).toString() : "false";
    }

    public void showFacebook() {
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    UnityPlayerActivity.this.m_GameActivity._showFacebook();
                }
            });
        }
    }

    public void submitExtendData(String str) {
        this.m_userData = UserData.resolve(str);
        Log.e("SuperSdk", "CollectData......" + str);
        if (_UseSdk()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (UnityPlayerActivity.this._UseSdk()) {
                        UnityPlayerActivity.this.m_GameActivity.SdkSubmitExtendData(UnityPlayerActivity.this.m_userData);
                    }
                }
            });
            if (this.m_GameActivity.UseFeiliuSdk()) {
                FeiLiuAccountInformationStatistics(str);
            }
        }
    }
}
