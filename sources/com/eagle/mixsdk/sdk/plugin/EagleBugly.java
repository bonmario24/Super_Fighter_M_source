package com.eagle.mixsdk.sdk.plugin;

import com.eagle.mixsdk.sdk.IBuglyPlugin;
import com.eagle.mixsdk.sdk.base.PluginFactory;
import com.eagle.mixsdk.sdk.log.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EagleBugly {
    /* access modifiers changed from: private */
    public static EagleBugly instance;
    /* access modifiers changed from: private */
    public IBuglyPlugin buglyPlugin;

    private EagleBugly() {
        init();
    }

    private void init() {
        this.buglyPlugin = (IBuglyPlugin) PluginFactory.getInstance().initPlugin(8);
        Log.m598d("shen", "bugly--->" + this.buglyPlugin);
    }

    public static EagleBugly getInstance() {
        if (instance == null) {
            instance = new EagleBugly();
        }
        return instance;
    }

    public static IBuglyPlugin getPlugin() {
        getInstance();
        return (IBuglyPlugin) Proxy.newProxyInstance(EagleBugly.class.getClassLoader(), new Class[]{IBuglyPlugin.class}, new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (EagleBugly.instance.buglyPlugin != null) {
                    return method.invoke(EagleBugly.instance.buglyPlugin, objects);
                }
                return null;
            }
        });
    }

    public boolean enable() {
        return this.buglyPlugin != null;
    }
}
