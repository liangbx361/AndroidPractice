package com.liangbx.android.practice.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/2/24.
 */
@RunWith(AndroidJUnit4.class)
public class TestSplashActivity extends ActivityInstrumentationTestCase2<SplashActivity> {

    private SplashActivity mSplashActivity;

    public TestSplashActivity() {
        super(SplashActivity.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        mSplashActivity = getActivity();


    }

    @Test
    public void testActivity() throws Exception {
        assertNotNull("SplashActivity is null", mSplashActivity);
    }
}
