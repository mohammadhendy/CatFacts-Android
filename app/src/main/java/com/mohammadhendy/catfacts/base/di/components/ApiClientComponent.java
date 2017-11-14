package com.mohammadhendy.catfacts.base.di.components;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.di.modules.ApiClientModule;
import com.mohammadhendy.catfacts.base.model.api.ApiClient;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Component(modules = ApiClientModule.class)
public interface ApiClientComponent {

    @NonNull
    ApiClient apiClient();
}
