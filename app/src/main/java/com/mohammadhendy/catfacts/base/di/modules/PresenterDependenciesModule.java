package com.mohammadhendy.catfacts.base.di.modules;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;
import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefs;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Module
public class PresenterDependenciesModule {

    @Provides
    @NonNull
    public PresenterDependencies providePresenterDependencies(@NonNull Schedulers schedulers, @NonNull SharedPrefs sharedPrefs) {
        return new PresenterDependencies(schedulers, sharedPrefs);
    }
}
