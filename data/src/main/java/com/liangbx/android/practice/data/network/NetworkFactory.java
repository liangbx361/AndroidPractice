package com.liangbx.android.practice.data.network;

import com.liangbx.android.practice.data.BuildConfig;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Author liangbx
 * Date 2016/1/3
 */
public class NetworkFactory {

    public static GithubService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.API_BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(GithubService.class);
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();

        // set time out interval
        client.setReadTimeout(10, TimeUnit.MINUTES);
        client.setConnectTimeout(10, TimeUnit.MINUTES);
        client.setWriteTimeout(10, TimeUnit.MINUTES);

        // print Log
        client.interceptors().add(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message.startsWith("{")) {
                    Logger.json(message);
                } else {
                    Logger.i("Api", message);
                }
            }
        }));

        // add custom headers
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("platform", "android")
                        .addHeader("appVersion", BuildConfig.VERSION_NAME)
                        .build();
                return chain.proceed(newRequest);
            }
        });

        return client;
    }
}
