package com.mohammadhendy.catfacts.common.di.components;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.common.di.modules.PresenterDependenciesTestModule;
import com.mohammadhendy.catfacts.main.MainPresenterTest;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Component(modules = PresenterDependenciesTestModule.class)
public interface PresenterDependenciesTestComponent {

    @NonNull
    PresenterDependencies presenterDependencies();

    void inject(MainPresenterTest mainPresenterTest);
}
