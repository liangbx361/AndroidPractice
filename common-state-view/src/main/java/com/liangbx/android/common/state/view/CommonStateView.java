package com.liangbx.android.common.state.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * <p>Title: 通用状态视图<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/4/26.
 */
public class CommonStateView extends FrameLayout {

    public static final int COMMON_STATE_CONTENT = 0;
    public static final int COMMON_STATE_LOADING = COMMON_STATE_CONTENT + 1;
    public static final int COMMON_STATE_EMPTY = COMMON_STATE_CONTENT + 2;
    public static final int COMMON_STATE_LOAD_FAIL = COMMON_STATE_CONTENT + 3;

    private ViewModel mStateVm;
    private int mState;

    public CommonStateView(Context context) {
        super(context);
    }

    public CommonStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttr(context, attrs);
    }

    public CommonStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttr(context, attrs);
    }

    private void parseAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonState);
        int emptyViewId = a.getResourceId(R.styleable.CommonState_empty_view, 0);
        int loadingViewId = a.getResourceId(R.styleable.CommonState_loading_view, 0);
        int loadFailViewId = a.getResourceId(R.styleable.CommonState_loadFail_view, 0);

        if(emptyViewId > 0) {
            addEmptyView(emptyViewId);
        }
        if(loadingViewId > 0) {
            addLoadingView(loadingViewId);
        }
        if(loadFailViewId > 0) {
            addLoadFailView(loadFailViewId);
        }
        a.recycle();
    }

    public void addEmptyView(View view) {
        getStateVm().vEmpty = view;
        addView(getStateVm().vEmpty);
    }

    public void addEmptyView(int viewId) {
        getStateVm().vEmpty = LayoutInflater.from(getContext()).inflate(viewId, this, false);
        addView(getStateVm().vEmpty);
    }

    public void addLoadingView(View view) {
        getStateVm().vLoading = view;
        addView(getStateVm().vLoading);
    }

    public void addLoadingView(int viewId) {
        getStateVm().vLoading = LayoutInflater.from(getContext()).inflate(viewId, this, false);
        addView(getStateVm().vLoading);
    }

    public void addLoadFailView(View view) {
        getStateVm().vLoadFail = view;
        addView(getStateVm().vLoadFail);
    }

    public void addLoadFailView(int viewId) {
        getStateVm().vLoadFail = LayoutInflater.from(getContext()).inflate(viewId, this, false);
        addView(getStateVm().vLoadFail);
    }

    public void setContentView(View view) {
        getStateVm().vContent = view;
    }

    public void setState(int state) {
        mState = state;
    }

    public ViewModel getStateVm() {
        if(mStateVm == null) {
            mStateVm = new ViewModel();
        }
        return mStateVm;
    }

    public int getState() {
        return mState;
    }

    private class ViewModel {
        public View vEmpty;
        public View vLoading;
        public View vLoadFail;
        public View vContent;
    }

    public void showState(int state) {

        switch (state) {
            case COMMON_STATE_CONTENT:
                setViewVisible(getStateVm().vContent);
                setViewGone(getStateVm().vEmpty);
                setViewGone(getStateVm().vLoading);
                setViewGone(getStateVm().vLoadFail);
                break;

            case COMMON_STATE_EMPTY:
                setViewGone(getStateVm().vContent);
                setViewVisible(getStateVm().vEmpty);
                setViewGone(getStateVm().vLoading);
                setViewGone(getStateVm().vLoadFail);
                break;

            case COMMON_STATE_LOADING:
                setViewGone(getStateVm().vContent);
                setViewGone(getStateVm().vEmpty);
                setViewVisible(getStateVm().vLoading);
                setViewGone(getStateVm().vLoadFail);
                break;

            case COMMON_STATE_LOAD_FAIL:
                setViewGone(getStateVm().vContent);
                setViewGone(getStateVm().vEmpty);
                setViewGone(getStateVm().vLoading);
                setViewVisible(getStateVm().vLoadFail);
                break;
        }
    }

    private void setViewGone(View view) {
        if(view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private void setViewVisible(View view) {
        if(view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }
}
