package com.liangbx.android.common.state.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/4/26.
 */
public class CommonStateHelper {

    public CommonStateView inject(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return (CommonStateView) inflater.inflate(R.layout.common_state, parent, true);
    }

    public CommonStateView inject(ViewGroup parent, int emptyViewId, int loadingViewId, int loadFailViewId) {
        CommonStateView commonStateView = inject(parent);
        commonStateView.addEmptyView(emptyViewId);
        commonStateView.addLoadingView(loadingViewId);
        commonStateView.addLoadFailView(loadFailViewId);
        return commonStateView;
    }
}
