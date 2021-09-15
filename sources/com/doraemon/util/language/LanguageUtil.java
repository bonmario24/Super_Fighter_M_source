package com.doraemon.util.language;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Locale;

public class LanguageUtil {
    private static final String CUR_LANGUAGE = "cur_eagle_language";
    private static final String TAG = LanguageUtil.class.getSimpleName();
    private static HashMap<String, Locale> mAllLanguages = new HashMap<String, Locale>(23) {
        {
            put(Language.f807en, Locale.ENGLISH);
            put(Language.f813ko, new Locale(Language.f813ko));
            put(Language.f821th, new Locale(Language.f821th));
            put(Language.f824vi, new Locale(Language.f824vi));
            put(Language.f804ar, new Locale(Language.f804ar));
            put(Language.f819ru, new Locale(Language.f819ru));
            put(Language.f811it, new Locale(Language.f811it));
            put(Language.f817pt, new Locale(Language.f817pt));
            put(Language.f810in, new Locale(Language.f810in));
            put(Language.f805da, new Locale(Language.f805da));
            put(Language.f808es, new Locale(Language.f808es));
            put(Language.f809fr, new Locale(Language.f809fr));
            put(Language.f815nl, new Locale(Language.f815nl));
            put(Language.f823uk, new Locale(Language.f823uk));
            put(Language.f822tr, new Locale(Language.f822tr));
            put(Language.f820se, new Locale(Language.f820se));
            put(Language.f816pl, new Locale(Language.f816pl));
            put(Language.f814ms, new Locale(Language.f814ms));
            put(Language.f818ro, new Locale(Language.f818ro));
            put(Language.f812ja, Locale.JAPAN);
            put(Language.f806de, Locale.GERMAN);
            put(Language.zh_hans, Locale.SIMPLIFIED_CHINESE);
            put(Language.zh_hant, Locale.TRADITIONAL_CHINESE);
        }
    };
    private static LanguageUtil mInstance;
    private String mDefaultLanguage = "";
    private SharedPreferences mSP;

    private LanguageUtil() {
    }

    public static LanguageUtil getInstance() {
        if (mInstance == null) {
            synchronized (LanguageUtil.class) {
                if (mInstance == null) {
                    mInstance = new LanguageUtil();
                }
            }
        }
        return mInstance;
    }

    private void changeAppLanguage(Context context, Locale newLanguage) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = newLanguage;
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public boolean isSupportLanguage(String language) {
        return mAllLanguages.containsKey(language);
    }

    public String getSupportLanguage(String language) {
        if (isSupportLanguage(language)) {
            return language;
        }
        return TextUtils.isEmpty(this.mDefaultLanguage) ? Language.zh_hant : this.mDefaultLanguage;
    }

    @TargetApi(24)
    private Context updateResources(Context context) {
        Resources resources = context.getResources();
        Locale locale = getConfigLanguage(context);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(new Locale[]{locale}));
        return context.createConfigurationContext(configuration);
    }

    private Locale getConfigLanguage(Context context) {
        String language = getSPConfigLanguage(context);
        if (TextUtils.isEmpty(language) || language.equals("sys")) {
            return Locale.getDefault();
        }
        return mAllLanguages.get(getSupportLanguage(language));
    }

    public void setLanguage2PropertiesFile(Context context, String assetsPropertiesFileName) {
        LanConfig.getInstance().loadConfig(context, assetsPropertiesFileName);
        if (TextUtils.isEmpty(getSPConfigLanguage(context))) {
            String language = LanConfig.getInstance().getLanConfig("Eagle_Language");
            if (!TextUtils.isEmpty(language) && !language.equals("sys")) {
                saveCurrentLanguage(context, language);
            }
        }
    }

    public void setLanguage(Context context, String language) {
        if (TextUtils.isEmpty(getSPConfigLanguage(context)) && !TextUtils.isEmpty(language) && !language.equals("sys")) {
            saveCurrentLanguage(context, language);
        }
    }

    public boolean switchAppLanguage(String language, Activity activity) {
        boolean isCommit = saveCurrentLanguage(activity, language);
        if (isCommit && Build.VERSION.SDK_INT < 24) {
            changeAppLanguage(activity, getConfigLanguage(activity));
        }
        return isCommit;
    }

    public void restartApp(Activity activity) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
        if (intent != null && intent.getComponent() != null) {
            ComponentName component = intent.getComponent();
            intent.addFlags(67108864);
            activity.startActivity(intent);
            activity.finish();
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public void setLanguageChange(Context context) {
        changeAppLanguage(context, getConfigLanguage(context));
    }

    public void loadConfig(Context context, String assetsPropertiesFileName) {
        setLanguage2PropertiesFile(context, assetsPropertiesFileName);
    }

    public Context attachBaseContext(Context context) {
        String eagleIsOverseas = LanConfig.getInstance().getLanConfig("Eagle_IsOverseas");
        if (TextUtils.isEmpty(eagleIsOverseas) || !eagleIsOverseas.equals("true") || Build.VERSION.SDK_INT < 24) {
            return context;
        }
        return updateResources(context);
    }

    public String getLanguageInfo(Locale locale) {
        return locale.getLanguage() + (TextUtils.isEmpty(locale.getCountry()) ? "" : "_") + locale.getCountry();
    }

    public void setDefaultLanguage(String language) {
        this.mDefaultLanguage = language;
    }

    private boolean saveCurrentLanguage(Context context, String language) {
        SharedPreferences.Editor edit = getSharedPreferences(context).edit();
        edit.putString(CUR_LANGUAGE, language);
        return edit.commit();
    }

    public String getSPConfigLanguage(Context context) {
        return getSharedPreferences(context).getString(CUR_LANGUAGE, "");
    }

    public Locale getCurrentLanguage(Context context) {
        String language = getSPConfigLanguage(context);
        return TextUtils.isEmpty(language) ? Locale.getDefault() : mAllLanguages.get(getSupportLanguage(language));
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (this.mSP == null) {
            this.mSP = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return this.mSP;
    }
}
