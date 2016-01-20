package com.liangbx.android.practice.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author liangbx
 * Date 2016/1/2
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    final protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        afterCreate();
    }

    /**
     * 布局资源ID
     * @return 资源ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     *
     */
    protected abstract void afterCreate();
}
