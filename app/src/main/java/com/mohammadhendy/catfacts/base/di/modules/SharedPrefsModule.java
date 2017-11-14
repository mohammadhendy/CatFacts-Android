package com.mohammadhendy.catfacts.base.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefs;
import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefsImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Module
public class SharedPrefsModule {

    @Provides
    @NonNull
    public SharedPrefs provideSharedPrefs(@NonNull Context context) {
        return new SharedPrefsImp(context);
    }
}
