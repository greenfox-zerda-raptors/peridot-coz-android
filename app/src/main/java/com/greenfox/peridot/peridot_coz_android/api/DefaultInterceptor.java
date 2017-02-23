package com.greenfox.peridot.peridot_coz_android.api;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class DefaultInterceptor implements Interceptor {

    @Inject
    public DefaultInterceptor() {}

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
        .header("Content-Type", "application/json");

        Request authorisedRequest = builder.build();
        return chain.proceed(authorisedRequest);
    }
}
