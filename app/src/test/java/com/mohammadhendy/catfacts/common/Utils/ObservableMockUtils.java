package com.mohammadhendy.catfacts.common.Utils;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by mohammadhendy on 11/15/17.
 */

public class ObservableMockUtils {

    public static <T> Observable<T> createHttpErrorObservableResponse(int responseCode, String message) {
        final HttpException notAuthorizedException =
                new HttpException(Response.error(responseCode, ResponseBody.create(MediaType.parse("application/json"),
                        "{\"error\":[\"" + message + "\"]}"
                )));
        return Observable.error(notAuthorizedException);
    }

    public static <T> Observable<T> createErrorConnectingObservableResponse() {
        return Observable.error(new IOException());
    }
}
