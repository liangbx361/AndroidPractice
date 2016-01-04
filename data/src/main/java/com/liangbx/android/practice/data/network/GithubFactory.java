package com.liangbx.android.practice.data.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Author liangbx
 * Date 2016/1/3
 */
public class GithubFactory {

    public static GithubService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(GithubService.class);
    }
}
