package com.mohammadhendy.catfacts.model.api;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.BuildConfig;
import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.base.model.api.ApiConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class DefaultApiConfig extends ApiConfig {
    /**
     * Private Constructor, use @link{getInstance} method to create instance
     * @param debuggable
     * @param onOkHttpClient
     * @param baseUrl
     * @param apiClient
     * @param interceptors
     */
    private DefaultApiConfig(boolean debuggable, Function<OkHttpClient.Builder, OkHttpClient.Builder> onOkHttpClient, @NonNull String baseUrl, @NonNull ApiClient apiClient, Interceptor... interceptors) {
        super(debuggable, onOkHttpClient, baseUrl, apiClient, interceptors);
    }

    /**
     * Create instance of default api configurations
     * @return
     */
    public static DefaultApiConfig getInstance() {
        CatFactsApiClient catFactsApiClient = new CatFactsApiClient();
        return new DefaultApiConfig(BuildConfig.DEBUG, builder -> {
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(15, TimeUnit.SECONDS);
            builder.writeTimeout(15, TimeUnit.SECONDS);
            return builder;
        }, BuildConfig.BASE_URL, catFactsApiClient);
    }
}
