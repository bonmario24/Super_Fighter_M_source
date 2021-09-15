package com.eagle.mixsdk.sdk.plugin;

import android.text.TextUtils;
import com.eagle.mixsdk.sdk.IExtPlugin;
import com.eagle.mixsdk.sdk.IPlugin;
import com.eagle.mixsdk.sdk.base.Constants;
import com.eagle.mixsdk.sdk.listeners.EagleInvokeListener;
import com.eagle.mixsdk.sdk.log.Log;
import java.util.HashMap;
import java.util.Map;

public class EagleExtPlugin implements IPlugin {
    private static EagleExtPlugin instance;
    private final HashMap<String, IExtPlugin> mExtPlugin = new HashMap<>();
    private HashMap<String, EagleInvokeListener> mInvokeListeners = new HashMap<>();

    public static EagleExtPlugin getInstance() {
        if (instance == null) {
            instance = new EagleExtPlugin();
        }
        return instance;
    }

    public boolean isSupportMethod(String methodName) {
        return this.mExtPlugin.get(methodName) != null;
    }

    public void registerExtPlugin(String methodName, IExtPlugin plugin) {
        if (plugin != null && !TextUtils.isEmpty(methodName)) {
            this.mExtPlugin.put(methodName, plugin);
        }
    }

    public void registerExtPlugins(IExtPlugin plugin, String... methodName) {
        if (plugin != null && methodName != null && methodName.length != 0) {
            for (String s : methodName) {
                registerExtPlugin(s, plugin);
            }
        }
    }

    public void invoke(String methodName, Map<String, Object> params) {
        Log.m602w(Constants.TAG, "invoke " + methodName);
        if (!TextUtils.isEmpty(methodName)) {
            if (params != null) {
                for (String key : params.keySet()) {
                    Log.m602w(Constants.TAG, "invoke params key " + key);
                    Log.m602w(Constants.TAG, "invoke params value " + params.get(key));
                }
            }
            IExtPlugin iExtPlugin = this.mExtPlugin.get(methodName);
            if (iExtPlugin != null) {
                iExtPlugin.invoke(methodName, params);
            } else {
                Log.m602w(Constants.TAG, "invoke listener is null");
            }
        }
    }

    public void addInvokeListener(String methodName, EagleInvokeListener callback) {
        if (this.mInvokeListeners != null && !TextUtils.isEmpty(methodName)) {
            this.mInvokeListeners.put(methodName, callback);
        }
    }

    public void onInvokeSuccess(String methodName, Map<String, Object> params, EagleInvokeListener.CallbackListener callbackListener) {
        if (TextUtils.isEmpty(methodName)) {
            methodName = Constants.DefaultMethod;
        }
        Log.m602w(Constants.TAG, "onInvokeSuccess  " + methodName);
        EagleInvokeListener invokeCallback = this.mInvokeListeners.get(methodName);
        if (invokeCallback != null) {
            invokeCallback.onSuccess(methodName, params, callbackListener);
        }
    }

    public void onInvokeFailed(String methodName, String msg) {
        if (TextUtils.isEmpty(methodName)) {
            methodName = Constants.DefaultMethod;
        }
        Log.m602w(Constants.TAG, "onInvokeSuccess  " + methodName);
        EagleInvokeListener invokeCallback = this.mInvokeListeners.get(methodName);
        if (invokeCallback != null) {
            invokeCallback.onFailed(methodName, msg);
        }
    }
}
