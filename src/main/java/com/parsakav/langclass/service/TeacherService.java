package com.parsakav.langclass.service;


import com.parsakav.langclass.model.Course;
import com.parsakav.langclass.model.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService {
     List<Teacher> getAllTeacher();
     void saveTeacher(String teachername, String phonenumber, String educationlevel, int age);
    Teacher findTeacherByName(String teachername);
    void setPeriodforTeacher(long teacherid, Set<Course> periodSet);

}
