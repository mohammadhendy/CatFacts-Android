package com.mohammadhendy.catfacts.base.model.api;

import retrofit2.Retrofit;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public abstract class ApiClient {

    public abstract ApiClient initApiClient(Retrofit retrofit);
    public <T>T getApiClient() {
        return (T) this;
    }

}
