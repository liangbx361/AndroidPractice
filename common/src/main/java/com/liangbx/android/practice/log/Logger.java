package com.liangbx.android.practice.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * Author liangbx
 * Date 2016/1/29.
 */
public class Logger {

    public static final int ANDROID_LOG = 0;
    public static final int UTIL_TEST_LOG = 1;

    private static final int LOG_TYPE_DEBUG = 1;
    private static final int LOG_TYPE_ERROR = 2;

    private static boolean mLogOutputEnable = true;
    private static int mLogOutputType = ANDROID_LOG;

    public static void init(boolean logOutputEnable, int logOutputType) {
        mLogOutputEnable = logOutputEnable;
        mLogOutputType = logOutputType;
    }

    public static void debug(Object o, String message) {
        outputLog(LOG_TYPE_DEBUG, o, message);
    }

    public static void error(Object o, String message) {
        outputLog(LOG_TYPE_ERROR, o, message);
    }

    private static boolean isNull(Object o, String message) {
        return o != null && !TextUtils.isEmpty(message);
    }

    private static void outputLog(int errorType, Object o, String message) {
        if(mLogOutputEnable && isNull(o, message)) {
            if(mLogOutputType == ANDROID_LOG) {
                outputAndroidLog(errorType, o, message);
            } else {
                outputSystemLog(message);
            }
        }
    }

    private static void outputAndroidLog(int errorType, Object o, String message) {

        switch (errorType) {
            case LOG_TYPE_DEBUG:
                Log.d(o.getClass().getSimpleName(), message);
                break;

            case LOG_TYPE_ERROR:
                Log.e(o.getClass().getSimpleName(), message);
                break;
        }
    }

    private static void outputSystemLog(String message) {
        System.out.println(message);
    }
}
