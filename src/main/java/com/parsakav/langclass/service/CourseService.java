package com.parsakav.langclass.service;


import com.parsakav.langclass.model.Course;
import com.parsakav.langclass.model.Lession;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CourseService {
    void savePeriod(Date startdate, String second, int houer, int number, Date enddate, String teachername, Set<Lession> lessionSet, String language);
    void delPeriod(long id);
    Course findPeriodById(long id);

    List<Course> getAllPeriod();
}
