package com.liangbx.android.practice.mapper;

import com.liangbx.android.practice.data.model.Student;
import com.liangbx.android.practice.viewmodel.StudentVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Author liangbx
 * Date 2016/1/16
 */
public class StudentListMapper {

    public List<StudentVM> mapper(List<Student> students) {
        List<StudentVM> studentVMs = new ArrayList<>();

        for(Student student : students) {
            StudentVM studentVM = new StudentVM();
            studentVMs.add(studentVM);
            studentVM.setName(student.getName());
        }

        return studentVMs;
    }
}
