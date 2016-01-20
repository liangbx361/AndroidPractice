package com.liangbx.android.practice.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liangbx.android.practice.R;
import com.liangbx.android.practice.viewmodel.StudentVM;

import java.util.List;

/**
 * Author liangbx
 * Date 2016/1/16
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<StudentVM> mStudents;

    public StudentAdapter(List<StudentVM> students) {
        mStudents = students;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudentVM student = mStudents.get(position);
        holder.mTvName.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return mStudents == null ? 0 : mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvName = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
