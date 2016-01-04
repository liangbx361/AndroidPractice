package com.liangbx.android.practice;

import android.app.Application;
import android.content.Context;

import com.liangbx.android.practice.data.network.GithubFactory;
import com.liangbx.android.practice.data.network.GithubService;

/**
 * Author liangbx
 * Date 2016/1/2
 */
public class App extends Application{

    private GithubService mGithubService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static App getApplication(Context context) {
        return (App) context.getApplicationContext();
    }

    public GithubService getGithubService() {
        if(mGithubService == null) {
            mGithubService = GithubFactory.create();
        }
        return mGithubService;
    }

    public void setGithubService(GithubService githubService) {
        mGithubService = githubService;
    }
}
