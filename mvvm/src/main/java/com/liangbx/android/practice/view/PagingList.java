package com.liangbx.android.practice.view;

import android.view.View;

import java.util.List;

/**
 * Author liangbx
 * Date 2016/1/16
 * DESC 通用分页列表
 * 提供给外部调用的方法：
 * 1。 刷新事件
 * 2。 加载更多事件
 * 3。 item创建viewholder事件
 * 4。 item点击事件
 * 5.  item绑定view事件
 */
public class PagingList<T> {

    private List<T> mDatas;
    private PagingListListener mListListener;
    private ListInfo mListInfo;

    private int pageSize;
    private int pageNum;

    public PagingList(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    public void addDatas(List<T> datas) {
        mDatas.addAll(datas);
    }

    public void setListListener(PagingListListener listListener) {
        mListListener = listListener;
    }

    public interface PagingListListener {
        /**
         * 刷新事件
         */
        void onRefresh();

        /**
         * 加载更多事件
         */
        void onLoadMore();
    }

    public interface ViewHolder<T> {

        View getView();

        void bindView(T data);
    }

    public class ListInfo {
        private int pageSize;
        private int pageNum;

        public ListInfo(int pageSize, int pageNum) {
            this.pageSize = pageSize;
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }
}
