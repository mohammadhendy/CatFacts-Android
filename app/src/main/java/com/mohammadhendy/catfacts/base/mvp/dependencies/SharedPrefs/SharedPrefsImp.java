package com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class SharedPrefsImp implements SharedPrefs {

    public static final String SHARED_PREFS_CONNECTION = "SHARED_PREF_NAME";
    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPrefsImp(Context context) {
        this.context = context;
    }

    @Override
    public boolean saveString(String key, String value) {
        getSharedPreferences().edit().putString(key, value).apply();
        return true;
    }

    @Override
    public boolean saveInt(String key, int value) {
        getSharedPreferences().getInt(key, value);
        return true;
    }

    @Override
    public boolean saveLong(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).apply();
        return true;
    }

    @Override
    public boolean saveBoolean(String key, Boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).apply();
        return true;
    }

    @Override
    public String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    @Override
    public Boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFS_CONNECTION, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public Integer getInt(String key, int fallback) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFS_CONNECTION, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, fallback);
    }

    @Override
    public Integer getInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    @Override
    public Long getLong(String key) {
        return getSharedPreferences().getLong(key, 0L);
    }

    @Override
    public Long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    @Override
    public boolean containsKey(String key) {
        return getSharedPreferences().contains(key);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(
                    SHARED_PREFS_CONNECTION, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    @Override
    public boolean clearKey(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFS_CONNECTION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
        return true;
    }

    @Override
    public void clearAll() {
        getSharedPreferences().edit().clear().apply();
    }

}
