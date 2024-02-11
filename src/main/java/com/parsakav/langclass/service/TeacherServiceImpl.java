package com.parsakav.langclass.service;

import com.parsakav.langclass.model.Course;
import com.parsakav.langclass.model.Teacher;
import com.parsakav.langclass.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional

public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherDao;
    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.findAll();
    }

    @Override
    public void saveTeacher(String teachername,String phonenumber, String educationlevel ,int age) {
        Teacher teacher = new Teacher();
        teacher.setTeachrname(teachername);

        teacher.setPhonenumber(phonenumber);
        teacher.setEducationlevel(educationlevel);
        teacher.setAge(age);
teacherDao.save(teacher);
    }

    @Override
    public Teacher findTeacherByName(String teachername) {
        return teacherDao.findTeacherByTeachrname(teachername);
    }

    @Override
    public void setPeriodforTeacher(long teacherid, Set<Course> periodSet) {


    }
}
