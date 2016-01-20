package com.liangbx.android.practice.data;

import com.liangbx.android.practice.data.model.Student;

import java.util.List;
import rx.Observable;

/**
 * Author liangbx
 * Date 2016/1/16
 */
public interface School {

    Observable<List<Student>> getAllStudent(int pageSize, int pageNumber);
}
