package com.mohammadhendy.catfacts.model.api;

import com.mohammadhendy.catfacts.base.model.api.ApiClient;

import retrofit2.Retrofit;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactsApiClient extends ApiClient {

    private CatFactsApi catFactsApi;

    @Override
    public ApiClient initApiClient(Retrofit retrofit) {
        catFactsApi = retrofit.create(CatFactsApi.class);
        return this;
    }

}
