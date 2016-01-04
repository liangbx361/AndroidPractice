package com.liangbx.android.practice.data.network;


import com.liangbx.android.practice.data.entity.GithubRepository;
import com.liangbx.android.practice.data.entity.GithubUser;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Url;
import rx.Observable;

/**
 * Author liangbx
 * Date 2016/1/3
 */
public interface GithubService {

    String API_BASE_URL = "https://api.github.com/";

    @GET("users/{username}/repos")
    Observable<List<GithubRepository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<GithubUser> userFromUrl(@Url String userUrl);
}
