package com.liangbx.android.practice.data;

import com.liangbx.android.practice.data.model.Student;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Author liangbx
 * Date 2016/1/16
 */
public class SchoolImp implements School {

    @Override
    public Observable<List<Student>> getAllStudent(final int pageSize, final int pageNumber) {

        return Observable.just(null)
                .map(o -> {
                    List<Student> students = new ArrayList<>();
                    for(int i=1; i<=pageSize; i++) {
                        Student student = new Student();
                        students.add(student);
                        student.setName("小明 - " + (pageNumber + i));
                    }
                    return students;
                }).subscribeOn(Schedulers.io());
    }
}
