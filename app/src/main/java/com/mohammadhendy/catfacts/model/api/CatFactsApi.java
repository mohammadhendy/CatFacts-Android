package com.mohammadhendy.catfacts.model.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import com.mohammadhendy.catfacts.model.core.CatFact;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public interface CatFactsApi {

    String ACCEPT_HEADER = "Accept:application/json";
    String CONTENT_TYPE_HEADER = "Content-Type:application/json";


    @Headers({ACCEPT_HEADER, CONTENT_TYPE_HEADER})
    @GET("facts")
    Observable<ListResponse<CatFact>> fetchFacts(@Query("limit") Integer limit, @Query("page") Integer page, @Query("max_length") Integer maxLength);

    @Headers({ACCEPT_HEADER, CONTENT_TYPE_HEADER})
    @GET("fact")
    Observable<CatFact> fetchRandomFact(@Query("max_length") Integer maxLength);

}
