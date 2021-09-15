package com.xsdk.doraemon.apkreflect;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import dalvik.system.DexClassLoader;

public class ProxyContextImpl extends ContextWrapper {
    public DexClassLoader classLoader;
    private boolean debug = true;
    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;

    public boolean isDebug() {
        return this.debug;
    }

    public ProxyContextImpl(Context mContext, String dexPath, boolean debug2) {
        super(mContext);
        this.debug = debug2;
        if (!debug2) {
            this.classLoader = new DexClassLoader(dexPath, mContext.getApplicationContext().getFilesDir().getAbsolutePath(), (String) null, mContext.getClassLoader());
            try {
                this.mAssetManager = AssetManager.class.newInstance();
                this.mAssetManager.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(this.mAssetManager, new Object[]{dexPath});
            } catch (Exception e) {
                e.printStackTrace();
            }
            Resources superRes = mContext.getResources();
            this.mResources = new Resources(this.mAssetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
        }
    }

    public ClassLoader getClassLoader() {
        if (!this.debug) {
            return this.classLoader == null ? super.getClassLoader() : this.classLoader;
        }
        return super.getClassLoader();
    }

    public Resources getResources() {
        if (!this.debug) {
            return this.mResources == null ? super.getResources() : this.mResources;
        }
        return super.getResources();
    }

    public AssetManager getAssets() {
        if (!this.debug) {
            return this.mAssetManager == null ? super.getAssets() : this.mAssetManager;
        }
        return super.getAssets();
    }

    public Object getSystemService(String name) {
        if (!this.debug) {
            try {
                if (name.equals("layout_inflater")) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        Object obj = HidenApiRef.newHideObject("com.android.internal.policy.PhoneLayoutInflater", new Class[]{Context.class}, new Object[]{this});
                        Log.d("shen", "HidenApiRef " + obj);
                        return (LayoutInflater) obj;
                    } else if (Build.VERSION.SDK_INT >= 23) {
                        return (LayoutInflater) Class.forName("com.android.internal.policy.PhoneLayoutInflater").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this});
                    } else {
                        return (LayoutInflater) Class.forName("com.android.internal.policy.PolicyManager").getMethod("makeNewLayoutInflater", new Class[]{Context.class}).invoke((Object) null, new Object[]{this});
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.getSystemService(name);
    }

    public Resources.Theme getTheme() {
        if (this.debug) {
            return super.getTheme();
        }
        this.mTheme = getResources().newTheme();
        Resources.Theme theme = getBaseContext().getTheme();
        if (theme != null) {
            this.mTheme.setTo(theme);
        }
        return this.mTheme == null ? super.getTheme() : this.mTheme;
    }

    public String getPackageName() {
        if (!this.debug) {
            return ReflectResource.getKpResPackageName();
        }
        return super.getPackageName();
    }

    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return super.createPackageContext(packageName, flags);
    }
}
