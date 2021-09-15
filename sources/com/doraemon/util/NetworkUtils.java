package com.doraemon.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.doraemon.util.Utils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class NetworkUtils {

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isConnected() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableAsync(@NonNull Utils.Callback<Boolean> callback) {
        return Utils.doAsync(new Utils.Task<Boolean>(callback) {
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailable());
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing((String) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByPingAsync(Utils.Callback<Boolean> callback) {
        isAvailableByPingAsync("", callback);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableByPingAsync(final String ip, @NonNull Utils.Callback<Boolean> callback) {
        return Utils.doAsync(new Utils.Task<Boolean>(callback) {
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByPing(ip));
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing(String ip) {
        String realIp;
        if (TextUtils.isEmpty(ip)) {
            realIp = "223.5.5.5";
        } else {
            realIp = ip;
        }
        return ShellUtils.execCmd(String.format("ping -c 1 %s", new Object[]{realIp}), false).result == 0;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByDnsAsync(Utils.Callback<Boolean> callback) {
        isAvailableByDnsAsync("", callback);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task isAvailableByDnsAsync(final String domain, @NonNull Utils.Callback<Boolean> callback) {
        return Utils.doAsync(new Utils.Task<Boolean>(callback) {
            @RequiresPermission("android.permission.INTERNET")
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByDns(domain));
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns(String domain) {
        String realDomain;
        if (TextUtils.isEmpty(domain)) {
            realDomain = "www.baidu.com";
        } else {
            realDomain = domain;
        }
        try {
            if (InetAddress.getByName(realDomain) != null) {
                return true;
            }
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getMobileDataEnabled() {
        try {
            TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService("phone");
            if (tm == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return tm.isDataEnabled();
            }
            Method getMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (getMobileDataEnabledMethod != null) {
                return ((Boolean) getMobileDataEnabledMethod.invoke(tm, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            Log.e("NetworkUtils", "getMobileDataEnabled: ", e);
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isMobileData() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.getType() == 0;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is4G() {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean getWifiEnabled() {
        WifiManager manager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (manager == null) {
            return false;
        }
        return manager.isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void setWifiEnabled(boolean enabled) {
        WifiManager manager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (manager != null && enabled != manager.isWifiEnabled()) {
            manager.setWifiEnabled(enabled);
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static String getWifiName() {
        String name = "";
        if (getActiveNetworkInfo() != null) {
            name = getActiveNetworkInfo().getExtraInfo();
        }
        return TextUtils.isEmpty(name) ? "" : name;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected() {
        boolean z = true;
        ConnectivityManager cm = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (cm == null) {
            return false;
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || ni.getType() != 1) {
            z = false;
        }
        return z;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static Utils.Task<Boolean> isWifiAvailableAsync(@NonNull Utils.Callback<Boolean> callback) {
        return Utils.doAsync(new Utils.Task<Boolean>(callback) {
            @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isWifiAvailable());
            }
        });
    }

    public static String getNetworkOperatorName() {
        TelephonyManager tm = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (tm == null) {
            return "";
        }
        return tm.getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo info = getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return NetworkType.NETWORK_NO;
        }
        if (info.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (info.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (info.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            default:
                String subtypeName = info.getSubtypeName();
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return NetworkType.NETWORK_3G;
                }
                return NetworkType.NETWORK_UNKNOWN;
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static boolean isEthernet() {
        NetworkInfo info;
        NetworkInfo.State state;
        ConnectivityManager cm = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (cm == null || (info = cm.getNetworkInfo(9)) == null || (state = info.getState()) == null) {
            return false;
        }
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return true;
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager cm = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (cm == null) {
            return null;
        }
        return cm.getActiveNetworkInfo();
    }

    public static Utils.Task<String> getIPAddressAsync(final boolean useIPv4, @NonNull Utils.Callback<String> callback) {
        return Utils.doAsync(new Utils.Task<String>(callback) {
            @RequiresPermission("android.permission.INTERNET")
            public String doInBackground() {
                return NetworkUtils.getIPAddress(useIPv4);
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getIPAddress(boolean useIPv4) {
        boolean isIPv4;
        String hostAddress;
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            LinkedList<InetAddress> adds = new LinkedList<>();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isUp() && !ni.isLoopback()) {
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        adds.addFirst(addresses.nextElement());
                    }
                }
            }
            Iterator it = adds.iterator();
            while (it.hasNext()) {
                InetAddress add = (InetAddress) it.next();
                if (!add.isLoopbackAddress()) {
                    String hostAddress2 = add.getHostAddress();
                    if (hostAddress2.indexOf(58) < 0) {
                        isIPv4 = true;
                    } else {
                        isIPv4 = false;
                    }
                    if (useIPv4) {
                        if (isIPv4) {
                            return hostAddress2;
                        }
                    } else if (!isIPv4) {
                        int index = hostAddress2.indexOf(37);
                        if (index < 0) {
                            hostAddress = hostAddress2.toUpperCase();
                        } else {
                            hostAddress = hostAddress2.substring(0, index).toUpperCase();
                        }
                        return hostAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isUp() && !ni.isLoopback()) {
                    List<InterfaceAddress> ias = ni.getInterfaceAddresses();
                    int size = ias.size();
                    for (int i = 0; i < size; i++) {
                        InetAddress broadcast = ias.get(i).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                    continue;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<String> getDomainAddressAsync(final String domain, @NonNull Utils.Callback<String> callback) {
        return Utils.doAsync(new Utils.Task<String>(callback) {
            @RequiresPermission("android.permission.INTERNET")
            public String doInBackground() {
                return NetworkUtils.getDomainAddress(domain);
            }
        });
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getDomainAddress(String domain) {
        try {
            return InetAddress.getByName(domain).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getIpAddressByWifi() {
        WifiManager wm = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().ipAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getGatewayByWifi() {
        WifiManager wm = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getNetMaskByWifi() {
        WifiManager wm = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().netmask);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getServerAddressByWifi() {
        WifiManager wm = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wm == null) {
            return "";
        }
        return Formatter.formatIpAddress(wm.getDhcpInfo().serverAddress);
    }

    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener listener) {
        NetworkChangedReceiver.getInstance().registerListener(listener);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener listener) {
        NetworkChangedReceiver.getInstance().unregisterListener(listener);
    }

    public static final class NetworkChangedReceiver extends BroadcastReceiver {
        /* access modifiers changed from: private */
        public Set<OnNetworkStatusChangedListener> mListeners = new HashSet();
        /* access modifiers changed from: private */
        public NetworkType mType;

        /* access modifiers changed from: private */
        public static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        /* access modifiers changed from: package-private */
        public void registerListener(final OnNetworkStatusChangedListener listener) {
            if (listener != null) {
                Utils.runOnUiThread(new Runnable() {
                    @SuppressLint({"MissingPermission"})
                    public void run() {
                        int preSize = NetworkChangedReceiver.this.mListeners.size();
                        NetworkChangedReceiver.this.mListeners.add(listener);
                        if (preSize == 0 && NetworkChangedReceiver.this.mListeners.size() == 1) {
                            NetworkType unused = NetworkChangedReceiver.this.mType = NetworkUtils.getNetworkType();
                            Utils.getApp().registerReceiver(NetworkChangedReceiver.getInstance(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        public void unregisterListener(final OnNetworkStatusChangedListener listener) {
            if (listener != null) {
                Utils.runOnUiThread(new Runnable() {
                    public void run() {
                        int preSize = NetworkChangedReceiver.this.mListeners.size();
                        NetworkChangedReceiver.this.mListeners.remove(listener);
                        if (preSize == 1 && NetworkChangedReceiver.this.mListeners.size() == 0) {
                            Utils.getApp().unregisterReceiver(NetworkChangedReceiver.getInstance());
                        }
                    }
                });
            }
        }

        @SuppressLint({"MissingPermission"})
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                Utils.runOnUiThreadDelayed(new Runnable() {
                    public void run() {
                        NetworkType networkType = NetworkUtils.getNetworkType();
                        if (NetworkChangedReceiver.this.mType != networkType) {
                            SimpleLogUtil.m581e(networkType.toString());
                            NetworkType unused = NetworkChangedReceiver.this.mType = networkType;
                            if (networkType == NetworkType.NETWORK_NO) {
                                for (OnNetworkStatusChangedListener listener : NetworkChangedReceiver.this.mListeners) {
                                    listener.onDisconnected();
                                }
                                return;
                            }
                            for (OnNetworkStatusChangedListener listener2 : NetworkChangedReceiver.this.mListeners) {
                                listener2.onConnected(networkType);
                            }
                        }
                    }
                }, 1000);
            }
        }

        private static class LazyHolder {
            /* access modifiers changed from: private */
            public static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();

            private LazyHolder() {
            }
        }
    }
}
