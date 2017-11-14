package com.mohammadhendy.catfacts.model.api;

import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.model.core.CatFact;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactsApiClient extends ApiClient {

    private CatFactsApi catFactsApi;

    /**
     * Create Api instance using Retrofit
     * @param retrofit
     * @return
     */
    @Override
    public ApiClient initApiClient(Retrofit retrofit) {
        catFactsApi = retrofit.create(CatFactsApi.class);
        return this;
    }

    /**
     * Fetch Random fact of max length from server
     * @param maxLength optional max length of the required fact
     * @return Observable of CatFact
     */
    public Observable<CatFact> fetchRandomFact(Integer maxLength) {
        return catFactsApi.fetchRandomFact(maxLength);
    }

    /**
     * Fetch list of facts with limit or max length restriction
     * @param limit optional number of facts required
     * @param maxLength optional max length of facts required
     * @return Observable of ListResponse of CatFacts
     */
    public Observable<ListResponse<CatFact>> fetchFacts(Integer limit, Integer maxLength) {
        return catFactsApi.fetchFacts(limit, maxLength);
    }

}
