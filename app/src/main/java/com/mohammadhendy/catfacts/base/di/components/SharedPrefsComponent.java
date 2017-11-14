package com.mohammadhendy.catfacts.base.di.components;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.di.modules.SharedPrefsModule;
import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefs;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Component(modules = SharedPrefsModule.class, dependencies = ApplicationComponent.class)
public interface SharedPrefsComponent {

    @NonNull
    SharedPrefs sharedPrefs();

    @NonNull
    Context context();
}
