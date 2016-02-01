package model;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Author liangbx
 * Date 2016/2/1.
 */
public class ActivityDelegate {

    private WeakReference<Activity> mActivityRef;
    private NullContext mNullContext;
    private NullActivity mNullActivity;

    public ActivityDelegate(Activity activity) {
        mActivityRef = new WeakReference<>(activity);
    }

    public Context getContext() {
        if(activityIsNull()) {
            return getNullContext();
        } else {
            return mActivityRef.get().getBaseContext();
        }
    }

    public Activity getActivity() {
        if(activityIsNull()) {
            return getNullActivity();
        } else {
            return mActivityRef.get();
        }
    }

    private Context getNullContext() {
        if(mNullContext == null) {
            mNullContext = new NullContext();
        }
        return mNullContext;
    }

    private Activity getNullActivity() {
        if(mNullActivity == null) {
            mNullActivity = new NullActivity();
        }
        return mNullActivity;
    }

    private boolean activityIsNull() {
        return mActivityRef.isEnqueued();
    }
}
