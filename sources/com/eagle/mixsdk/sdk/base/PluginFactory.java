package com.eagle.mixsdk.sdk.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import com.eagle.mixsdk.sdk.EagleSDK;
import com.eagle.mixsdk.sdk.IPlugin;
import com.eagle.mixsdk.sdk.SDKParams;
import com.eagle.mixsdk.sdk.SDKTools;
import com.eagle.mixsdk.sdk.log.Log;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@SuppressLint({"UseSparseArrays"})
public class PluginFactory {
    private static PluginFactory instance;
    private HashMap<String, String> mExtValues = new HashMap<>();
    private Map<Integer, IPlugin> plugins = new HashMap();
    private HashMap<Integer, String> supportedPlugins = new HashMap<>();

    private PluginFactory() {
    }

    public static PluginFactory getInstance() {
        if (instance == null) {
            instance = new PluginFactory();
        }
        return instance;
    }

    private boolean isSupportPlugin(int type) {
        return this.supportedPlugins.containsKey(Integer.valueOf(type));
    }

    private String getPluginName(int type) {
        if (this.supportedPlugins.containsKey(Integer.valueOf(type))) {
            return this.supportedPlugins.get(Integer.valueOf(type));
        }
        return null;
    }

    public IPlugin getPlugin(int key) {
        IPlugin iPlugin = this.plugins.get(Integer.valueOf(key));
        if (iPlugin != null) {
            return iPlugin;
        }
        try {
            Object o = initPlugin(key);
            if (o != null) {
                iPlugin = (IPlugin) o;
            }
            if (iPlugin == null) {
                iPlugin = new IPlugin() {
                    public boolean isSupportMethod(String methodName) {
                        return false;
                    }
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (iPlugin == null) {
                iPlugin = new IPlugin() {
                    public boolean isSupportMethod(String methodName) {
                        return false;
                    }
                };
            }
        } catch (Throwable th) {
            if (iPlugin == null) {
                new IPlugin() {
                    public boolean isSupportMethod(String methodName) {
                        return false;
                    }
                };
            }
            throw th;
        }
        this.plugins.put(Integer.valueOf(key), iPlugin);
        return iPlugin;
    }

    public Bundle getMetaData(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(appInfo == null || appInfo.metaData == null)) {
                return appInfo.metaData;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return new Bundle();
    }

    public SDKParams getSDKParams(Context context) {
        return new SDKParams(SDKTools.getAssetPropConfig(context, "eagle_developer_config.properties"));
    }

    public Object initPlugin(int type) {
        String pluginName = "";
        try {
            if (isSupportPlugin(type)) {
                pluginName = getPluginName(type);
                Class localClass = Class.forName(pluginName);
                try {
                    return localClass.getDeclaredConstructor(new Class[]{Activity.class}).newInstance(new Object[]{EagleSDK.getInstance().getContext()});
                } catch (Exception e) {
                    try {
                        return localClass.newInstance();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        e.printStackTrace();
                        return null;
                    }
                }
            } else if (type == 1 || type == 2) {
                Log.m599e(Constants.TAG, "The config of the EagleSDK is not support plugin type:" + type);
                return null;
            } else {
                Log.m602w(Constants.TAG, "The config of the EagleSDK is not support plugin type:" + type);
                return null;
            }
        } catch (ClassNotFoundException e2) {
            Log.m599e(Constants.TAG, "not found the plugin name " + pluginName);
            e2.printStackTrace();
            return null;
        }
    }

    public void loadPluginInfo(Context context) {
        String xmlPlugins = SDKTools.getAssetConfigs(context, "eagle_plugin_config.xml");
        if (xmlPlugins == null) {
            Log.m599e(Constants.TAG, "fail to load plugin_config.xml");
            return;
        }
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xmlPlugins));
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                switch (eventType) {
                    case 2:
                        if (!"plugin".equals(parser.getName())) {
                            break;
                        } else {
                            String name = parser.getAttributeValue(0);
                            int type = Integer.parseInt(parser.getAttributeValue(1));
                            this.supportedPlugins.put(Integer.valueOf(type), name);
                            Log.m598d(Constants.TAG, "Curr Supported Plugin: " + type + "; name:" + name);
                            break;
                        }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public String getExtValues(Context context, String key) {
        if (TextUtils.isEmpty(key)) {
            return "";
        }
        if (this.mExtValues.isEmpty()) {
            loadExtValues(context);
        }
        return this.mExtValues.get(key);
    }

    private void loadExtValues(Context context) {
        String xmlPlugins = SDKTools.getAssetConfigs(context, "eagle_values.xml");
        if (xmlPlugins == null) {
            Log.m599e(Constants.TAG, "fail to load eagle_values.xml");
            return;
        }
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xmlPlugins));
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                switch (eventType) {
                    case 2:
                        String tag = parser.getName();
                        if ("resources".equals(tag)) {
                            break;
                        } else {
                            String name = parser.getAttributeValue(0);
                            String value = parser.nextText();
                            if (!TextUtils.isEmpty(name)) {
                                this.mExtValues.put(name, value);
                            }
                            Log.m598d(Constants.TAG, "loadExtValues: " + tag + "; name:" + name + "; value:" + value);
                            break;
                        }
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
