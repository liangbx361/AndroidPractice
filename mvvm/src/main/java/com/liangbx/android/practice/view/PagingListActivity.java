package com.liangbx.android.practice.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.liangbx.android.practice.R;
import com.liangbx.android.practice.data.DataManager;
import com.liangbx.android.practice.databinding.PagingListViewModel;
import com.liangbx.android.practice.mapper.StudentListMapper;
import com.liangbx.android.practice.model.Student;
import com.liangbx.android.practice.viewmodel.StudentVM;
import com.liangbx.android.practice.widget.PagingListAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Author liangbx
 * Date 2016/1/16
 */
public class PagingListActivity extends Activity implements PagingListAdapter.PagingListListener{

    private PagingListViewModel mViewModel;
    private PagingList<Student> mPagingList;
    private int mPageSize = 10;
    private int mPageNum = 0;

    private StudentAdapter mStudentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = DataBindingUtil.setContentView(this, R.layout.activity_paging_list);
        mPagingList = new PagingList<>(20, 0);
        mPagingList.setListListener(new PagingList.PagingListListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });

        loadData();

        mViewModel.refresh.setOnRefreshListener(() -> mViewModel.refresh.setRefreshing(false));
    }

    private void loadData() {
        loadData(mPageSize, mPageNum);
    }

    private void loadData(int pageSize, int pageNum) {
        DataManager.sSchool.getAllStudent(mPagingList.getPageSize(), mPagingList.getPageNum())
                .map(students -> new StudentListMapper().mapper(students))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setList, Throwable::printStackTrace);

    }

    private void setList(List<StudentVM> studentVMs) {
        mViewModel.list.setLayoutManager(new LinearLayoutManager(this));
        mStudentAdapter = new StudentAdapter(studentVMs);
        mViewModel.list.setAdapter(mStudentAdapter);

        mStudentAdapter.notifyDataSetChanged();

        mPageNum++;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public boolean hasMoreData() {
        return false;
    }

    public class NewStudentAdapter extends PagingListAdapter {

        public NewStudentAdapter(RecyclerView recyclerView, List datas, PagingList.PagingListListener listener) {
            super(recyclerView, datas, listener);
        }
    }
}
