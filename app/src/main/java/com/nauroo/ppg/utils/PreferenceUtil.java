package com.nauroo.ppg.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    private static final String PREF_NAME = "NAUROO_PPG";

    public static void addStringToPref(Context context, String key, String value) {
        if (context != null) {
            SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static void addBooleanToPref(Context context, String key, boolean value) {
        if (context != null) {
            SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static String getStringFromPref(Context context, String key) {
        if (context != null) {
            SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedpreferences.getString(key, null);
        }
        return null;
    }

    public static boolean getBooleanFromPref(Context context, String key, boolean defaultValue) {
        if (context != null) {
            SharedPreferences sharedpreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedpreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    public static void clearPreference(Context context) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            settings.edit().clear().apply();
        }
    }

    public static void clearPreference(Context context, String key) {
        if (context != null) {
            SharedPreferences settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            settings.edit().remove(key).apply();
        }
    }
}
