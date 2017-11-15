package com.mohammadhendy.catfacts.common.di.modules;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;
import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefs;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.mohammadhendy.catfacts.common.Utils.SchedulersMock;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Module
public class PresenterDependenciesTestModule {

    @Provides
    @NonNull
    public PresenterDependencies providePresenterDependencies(@NonNull Schedulers schedulers, @NonNull SharedPrefs sharedPrefs) {
        return new PresenterDependencies(schedulers, sharedPrefs);
    }

    @Provides
    public Schedulers provideSchedulers() {
        return new SchedulersMock();
    }

    @Provides
    public SharedPrefs provideSharedPrefs() {
        return Mockito.mock(SharedPrefs.class);
    }

    @Provides
    public CatFactsApiClient provideApiClient() {
        return Mockito.mock(CatFactsApiClient.class);
    }
}
