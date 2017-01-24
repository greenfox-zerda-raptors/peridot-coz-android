package com.greenfox.peridot.peridot_coz_android.model.api;

import java.io.IOException;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by BB on 2017-01-23.
 */

public abstract class MockCall<T> implements Call<T> {

    @Override
    public Response execute() throws IOException {return null;}

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {}

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
