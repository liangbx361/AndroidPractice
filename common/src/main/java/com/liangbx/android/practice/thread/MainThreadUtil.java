package com.liangbx.android.practice.thread;

import android.os.Looper;

/**
 * Author liangbx
 * Date 2016/2/5.
 */
public class MainThreadUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
