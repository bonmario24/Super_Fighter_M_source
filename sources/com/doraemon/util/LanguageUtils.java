package com.doraemon.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Locale;

public class LanguageUtils {
    private static final String KEY_LOCALE = "KEY_LOCALE";
    private static final String VALUE_FOLLOW_SYSTEM = "VALUE_FOLLOW_SYSTEM";

    private LanguageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void applySystemLanguage(Class<? extends Activity> activityClz) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, activityClz, true);
    }

    public static void applySystemLanguage(String activityClassName) {
        applyLanguage(Resources.getSystem().getConfiguration().locale, activityClassName, true);
    }

    public static void applyLanguage(@NonNull Locale locale, Class<? extends Activity> activityClz) {
        applyLanguage(locale, activityClz, false);
    }

    public static void applyLanguage(@NonNull Locale locale, String activityClassName) {
        applyLanguage(locale, activityClassName, false);
    }

    private static void applyLanguage(@NonNull Locale locale, Class<? extends Activity> activityClz, boolean isFollowSystem) {
        if (activityClz == null) {
            applyLanguage(locale, "", isFollowSystem);
        } else {
            applyLanguage(locale, activityClz.getName(), isFollowSystem);
        }
    }

    private static void applyLanguage(@NonNull Locale locale, String activityClassName, boolean isFollowSystem) {
        String realActivityClassName;
        if (isFollowSystem) {
            Utils.getSpUtils4Utils().put(KEY_LOCALE, VALUE_FOLLOW_SYSTEM);
        } else {
            String localLanguage = locale.getLanguage();
            Utils.getSpUtils4Utils().put(KEY_LOCALE, localLanguage + "$" + locale.getCountry());
        }
        updateLanguage(Utils.getApp(), locale);
        Intent intent = new Intent();
        if (TextUtils.isEmpty(activityClassName)) {
            realActivityClassName = getLauncherActivity();
        } else {
            realActivityClassName = activityClassName;
        }
        intent.setComponent(new ComponentName(Utils.getApp(), realActivityClassName));
        intent.addFlags(335577088);
        Utils.getApp().startActivity(intent);
    }

    static void applyLanguage(@NonNull Activity activity) {
        String spLocale = Utils.getSpUtils4Utils().getString(KEY_LOCALE);
        if (!TextUtils.isEmpty(spLocale)) {
            if (VALUE_FOLLOW_SYSTEM.equals(spLocale)) {
                Locale sysLocale = Resources.getSystem().getConfiguration().locale;
                updateLanguage(Utils.getApp(), sysLocale);
                updateLanguage(activity, sysLocale);
                return;
            }
            String[] language_country = spLocale.split("\\$");
            if (language_country.length != 2) {
                Log.e("LanguageUtils", "The string of " + spLocale + " is not in the correct format.");
                return;
            }
            Locale settingLocale = new Locale(language_country[0], language_country[1]);
            updateLanguage(Utils.getApp(), settingLocale);
            updateLanguage(activity, settingLocale);
        }
    }

    private static void updateLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        Locale contextLocale = config.locale;
        if (!equals(contextLocale.getLanguage(), locale.getLanguage()) || !equals(contextLocale.getCountry(), locale.getCountry())) {
            DisplayMetrics dm = resources.getDisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale);
                context.createConfigurationContext(config);
            } else {
                config.locale = locale;
            }
            resources.updateConfiguration(config, dm);
        }
    }

    private static boolean equals(CharSequence s1, CharSequence s2) {
        int length;
        if (s1 == s2) {
            return true;
        }
        if (s1 == null || s2 == null || (length = s1.length()) != s2.length()) {
            return false;
        }
        if ((s1 instanceof String) && (s2 instanceof String)) {
            return s1.equals(s2);
        }
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String getLauncherActivity() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(Utils.getApp().getPackageName());
        ResolveInfo next = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            return next.activityInfo.name;
        }
        return "no launcher activity";
    }
}
