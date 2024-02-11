package com.parsakav.langclass.repository;

import com.parsakav.langclass.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Course,Long> {
    public Course findCourseById(long id);

}
