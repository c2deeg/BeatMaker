package com.beatmaker.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CSPrefernces {

    public static final String PREF = "pref";

    public static SharedPreferences getPref(Activity activity) {
        return activity.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public static void putString(Activity activity, String key, String value) {
        SharedPreferences.Editor editor = getPref(activity).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String readString(Activity activity, String key) {
        return getPref(activity).getString(key, "");
    }

    public static void clearAll(Activity activity) {
        getPref(activity).edit().clear().commit();
    }
}
