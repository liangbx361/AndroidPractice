package com.liangbx.android.practice.base.empty;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/2/15.
 */
public class TestEmptyActivity {

    private static EmptyActivity mActivity;

    @BeforeClass
    public static void initClass() {
        mActivity = new EmptyActivity();
    }

    @Test
    public void testGetIntent() throws Exception {
        assertNotEquals(mActivity.getIntent(), null);
    }


}
