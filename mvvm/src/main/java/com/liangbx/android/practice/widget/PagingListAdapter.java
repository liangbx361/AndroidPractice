package com.liangbx.android.practice.widget;

import android.support.v7.widget.RecyclerView;

import com.liangbx.android.practice.view.PagingList;

import java.util.List;

/**
 * Author liangbx
 * Date 2016/1/17
 */
public abstract class PagingListAdapter {

    private RecyclerView mRecyclerView;
    private List mDatas;
    private PagingList.PagingListListener mListener;

    public PagingListAdapter(RecyclerView recyclerView, List datas, PagingList.PagingListListener listener) {
        mRecyclerView = recyclerView;
        mDatas = datas;
        mListener = listener;
    }

    public void setListener(PagingList.PagingListListener listener) {
        mListener = listener;
    }

    public void setDatas(List datas) {
        mDatas = datas;
    }

    public void addDatas(List datas) {
        mDatas.addAll(datas);
    }

    protected int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void notifyDataSetChanged() {
        try {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface PagingListListener {

        void refreshData();

        void loadMoreData();

        boolean hasMoreData();
    }
}
