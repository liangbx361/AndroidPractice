package com.liangbx.android.practice.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.liangbx.android.practice.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author liangbx
 * Date 2016/1/2
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(l -> {
                    startActivity(new Intent(SplashActivity.this, SearchActivity.class));
                    finish();
                    return null;
                })
                .subscribe();
    }
}
