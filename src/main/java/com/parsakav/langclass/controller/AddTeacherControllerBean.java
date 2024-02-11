package com.parsakav.langclass.controller;

import com.parsakav.langclass.model.Teacher;
import com.parsakav.langclass.service.LanguageService;
import com.parsakav.langclass.service.TeacherService;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@Named(value = "addTeacherController")
@Join(path = "/addteacher", to = "/teacher.jsf")
public class AddTeacherControllerBean {
    @Autowired
    private LanguageService languageService;
   // @Inject
    @Autowired
    private TeacherService teacherService;
    List<Teacher> teacherList = new ArrayList<>();
private String error;
    public List<Teacher> getTeacherList() {
        this.teacherList = teacherService.getAllTeacher();

        return teacherList;
    }

    private String teachrname;
    private int age;

    private String educationlevel;



    private String phonenumber;

    public String getTeachrname() {
        return teachrname;
    }

    public void setTeachrname(String teachrname) {
        this.teachrname = teachrname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }



    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void submit() {

        teacherService.saveTeacher(teachrname,phonenumber,educationlevel,age);
        this.teacherList =         teacherService.getAllTeacher();

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
