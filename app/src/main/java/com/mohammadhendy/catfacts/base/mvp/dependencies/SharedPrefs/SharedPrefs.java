package com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs;

import android.content.SharedPreferences;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public interface SharedPrefs {

    boolean saveString(String key, String value);

    boolean saveInt(String key, int value);

    boolean saveLong(String key, long value);

    boolean saveBoolean(String key, Boolean value);

    String getString(String key);

    Boolean getBoolean(String key);

    Integer getInt(String key, int fallback);

    Integer getInt(String key);

    Long getLong(String key);

    Long getLong(String key, long defaultValue);

    boolean containsKey(String key);

    boolean clearKey(String key);

    void clearAll();

    SharedPreferences getSharedPreferences();

}
