package com.parsakav.langclass.repository;

import com.parsakav.langclass.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    public Teacher findTeacherByTeachrname(String teachername);
}
