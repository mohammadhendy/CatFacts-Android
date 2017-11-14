package com.mohammadhendy.catfacts.base.di.components;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.di.modules.ApplicationModule;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @NonNull
    Context context();
}
