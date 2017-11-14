package com.mohammadhendy.catfacts.base.di.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Module
public class ApplicationModule {
    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    public Context provideContext() {
        return application.getApplicationContext();
    }
}
