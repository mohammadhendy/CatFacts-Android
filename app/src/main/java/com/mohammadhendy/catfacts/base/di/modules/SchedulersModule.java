package com.mohammadhendy.catfacts.base.di.modules;

import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;
import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.SchedulersImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohammadhendy on 11/13/17.
 */

@Module
public class SchedulersModule {

    @Provides
    public Schedulers providesSchedulers() {
        return new SchedulersImp();
    }
}
