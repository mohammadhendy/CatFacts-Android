package com.mohammadhendy.catfacts.base.model.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class ApiConfig {

    //states whether log interceptor should be added or not for dubugging purpose
    private boolean debuggable;
    // function to create and customize OKHttpClient
    @Nullable
    private final Function<OkHttpClient.Builder, OkHttpClient.Builder> onOkHttpClient;
    //Base url of the backend api
    @NonNull
    private String baseUrl;
    @NonNull
    private ApiClient apiClient;
    //additional interceptors to be added while configuring api
    @Nullable
    private Interceptor[] interceptors;

    public ApiConfig(boolean debuggable, Function<OkHttpClient.Builder, OkHttpClient.Builder> onOkHttpClient, @NonNull String baseUrl, @NonNull ApiClient apiClient, Interceptor[] interceptors) {
        this.debuggable = debuggable;
        this.onOkHttpClient = onOkHttpClient;
        this.baseUrl = baseUrl;
        this.apiClient = apiClient;
        this.interceptors = interceptors;
    }

    public boolean isDebuggable() {
        return debuggable;
    }

    @NonNull
    public String getBaseUrl() {
        return baseUrl;
    }

    @NonNull
    public ApiClient getApiClient() {
        return apiClient;
    }

    @Nullable
    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    @Nullable
    public Function<OkHttpClient.Builder, OkHttpClient.Builder> getOnOkHttpClient() {
        return onOkHttpClient;
    }
}
