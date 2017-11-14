package com.mohammadhendy.catfacts.base.di.modules;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.mohammadhendy.catfacts.base.Utils.Preconditions;
import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.base.model.api.ApiConfig;

import dagger.Module;
import dagger.Provides;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Module
public class ApiClientModule {

    //states whether log interceptor should be added or not for dubugging purpose
    private final boolean debuggable;
    // function to create and customize OKHttpClient
    @Nullable
    private final Function<OkHttpClient.Builder, OkHttpClient.Builder> onOkHttpClient;
    //Base url of the backend api
    @NonNull
    private final String baseUrl;
    @NonNull
    private ApiClient apiClient;
    //additional interceptors to be added while configuring api
    @Nullable
    private Interceptor[] interceptors;

    public ApiClientModule(boolean debuggable, Function<OkHttpClient.Builder, OkHttpClient.Builder> onOkHttpClient, @NonNull String baseUrl, @NonNull ApiClient apiClient, Interceptor ...interceptors) {
        this.debuggable = debuggable;
        this.onOkHttpClient = onOkHttpClient;
        this.baseUrl = baseUrl;
        this.apiClient = apiClient;
        this.interceptors = interceptors;
    }

    public ApiClientModule(ApiConfig apiConfig) {
        this(apiConfig.isDebuggable(),
                apiConfig.getOnOkHttpClient(),
                apiConfig.getBaseUrl(),
                apiConfig.getApiClient(),
                apiConfig.getInterceptors());
    }

    @Provides
    @NonNull
    public OkHttpClient provideOkHttpClient(@NonNull HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //interceptors if any
        if (interceptors != null && interceptors.length > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        if (onOkHttpClient != null) {
            builder = apply(onOkHttpClient, builder);
        }
        if (debuggable) {
            // Add HttpLoggingInterceptor
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    @Provides
    @NonNull
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @NonNull
    public Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    public ApiClient provideApiClient(@NonNull Retrofit retrofit) {
        return apiClient.initApiClient(retrofit);
    }

    @NonNull
    private <T, R> R apply(@NonNull Function<T, R> f, @NonNull T t) {
        try {
            return Preconditions.checkNotNull(f.apply(t));
        } catch (Throwable ex) {
            throw new IllegalStateException("Applying function for " + t.toString() + " Threw " + ex);
        }
    }



}
