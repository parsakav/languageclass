package com.parsakav.langclass.service;

import com.parsakav.langclass.model.Course;
import com.parsakav.langclass.model.Lession;
import com.parsakav.langclass.model.Teacher;
import com.parsakav.langclass.repository.LanguageRepository;
import com.parsakav.langclass.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional

public class CourseServiceImpl implements CourseService {
     @Autowired
     private PeriodRepository periodDao;
     @Autowired
     private LanguageRepository langDao;
     @Autowired
     private TeacherService teacherDao;
   /*  @Autowired
     private LessionDao lessionDao;*/
     @Override
     public void savePeriod(Date startdate, String second, int houer, int number, Date enddate, String teachername, Set<Lession> lessionSet, String language) {
       if(lessionSet !=null || lessionSet.size() !=0) {
           Course period = new Course();
           Teacher teacher1 = teacherDao.findTeacherByName(teachername);
           if (teacher1 != null) {

               period.setTeacher(teacher1);
           } else {
    /*        Teacher teacher = new Teacher();
            teacher.setTeachrname(teachername);
            period.setTeacher(teacher);*/
           }


           period.setEnddate(enddate);
           period.setStartdate(startdate);
           period.setLanguage(langDao.findLanguageByLangname(language));
           period.setMaxstudent(number);

           period.setSeconddayinweek(second);
           period.setHour(houer);
           Set<Lession> lessionSet1 = new HashSet<>();
           for (Lession lession : lessionSet) {
               // Lession lession1=  lessionDao.findLessionByName(lession.getSubject());
               System.out.println("parsa:" + lession.getSubject());

               lession.setCourse(period);
               lessionSet1.add(lession);

           }
           period.setLession(lessionSet1);

           periodDao.save(period);
       }
     }



    @Override
    public void delPeriod(long id) {
        Course period = new Course();
period.setId(id);

        periodDao.delete(period);
    }

    @Override
    public Course findPeriodById(long id) {
        return periodDao.findCourseById(id);
    }

    @Override
     public List<Course> getAllPeriod() {

   return periodDao.findAll();
     }
}
