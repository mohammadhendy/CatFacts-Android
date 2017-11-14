package com.mohammadhendy.catfacts.base.app;

import android.app.Application;
import android.content.Context;

import com.mohammadhendy.catfacts.BuildConfig;
import com.mohammadhendy.catfacts.base.di.components.ApiClientComponent;
import com.mohammadhendy.catfacts.base.di.components.ApplicationComponent;
import com.mohammadhendy.catfacts.base.di.components.DaggerApiClientComponent;
import com.mohammadhendy.catfacts.base.di.components.DaggerApplicationComponent;
import com.mohammadhendy.catfacts.base.di.components.DaggerPresenterDependenciesComponent;
import com.mohammadhendy.catfacts.base.di.components.DaggerSchedulersComponent;
import com.mohammadhendy.catfacts.base.di.components.DaggerSharedPrefsComponent;
import com.mohammadhendy.catfacts.base.di.components.PresenterDependenciesComponent;
import com.mohammadhendy.catfacts.base.di.components.SchedulersComponent;
import com.mohammadhendy.catfacts.base.di.components.SharedPrefsComponent;
import com.mohammadhendy.catfacts.base.di.modules.ApiClientModule;
import com.mohammadhendy.catfacts.base.di.modules.ApplicationModule;
import com.mohammadhendy.catfacts.base.di.modules.PresenterDependenciesModule;
import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.base.model.api.ApiConfig;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;

import timber.log.Timber;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public abstract class BaseApp extends Application {

    public static Context applicationContext;
    private PresenterDependenciesComponent presenterDependenciesComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApp.applicationContext = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Timber.tag(BaseApp.class.getSimpleName());
        Timber.d("onCreate");
    }

    private void initInjections() {
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        SchedulersComponent schedulersComponent =
                DaggerSchedulersComponent.create();

        SharedPrefsComponent sharedPrefsComponent = DaggerSharedPrefsComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        presenterDependenciesComponent = DaggerPresenterDependenciesComponent.builder()
                .schedulersComponent(schedulersComponent)
                .sharedPrefsComponent(sharedPrefsComponent)
                .build();

    }

    public PresenterDependenciesComponent getPresenterDependenciesComponent() {
        if (presenterDependenciesComponent == null)
            initInjections();
        return presenterDependenciesComponent;
    }

}
