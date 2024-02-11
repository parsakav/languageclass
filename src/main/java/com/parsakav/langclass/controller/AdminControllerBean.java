package com.parsakav.langclass.controller;


import com.parsakav.langclass.service.*;
import com.parsakav.langclass.model.*;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormatSymbols;
import java.util.*;

@PreAuthorize("hasRole('ADMIN')")
//@ManagedBean
@Scope(value = "session")
//@Component(value = "adminController")
//@ELBeanName(value = "adminController")
@Named(value = "adminController")
@Join(path = "/admin", to = "/adminpanel.jsf")
public class AdminControllerBean {

    @Autowired
    private CourseService periodDao;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private TeacherService teacherService;
    private List<Course> periods;
    private List<Language> languages;
    private Date startdate;
    private String teachername;
    private String language;
    private int houer;
    private String secondday;
    private int number;
    private String lessionname;
    private String error;

    private Set<Lession> lessionSet = new HashSet<>();


    public List<Language> getLanguages() {
        return languageService.getAllLanguage();
    }

    public String getLessionname() {
        return lessionname;
    }

    public void setLessionname(String lessionname) {

        this.lessionname = lessionname;
    }


    public int getHouer() {
        return houer;
    }

    public void setHouer(int houer) {
        this.houer = houer;
    }

    public String getSecondday() {
        return secondday;
    }

    public void setSecondday(String secondday) {
        String[] weekdays = {"Sat", "Mon", "Wed", "Fri", "Thurs", "Sun", "Thues"};
        boolean ok = true;
        for (String d : weekdays) {
            if (d.contains(secondday)) {

                String dayNames[] = new DateFormatSymbols().getWeekdays();

                Calendar cal = Calendar.getInstance();
                cal.setTime(startdate);
                String dayn = dayNames[cal.get(Calendar.DAY_OF_WEEK)];
                int m = DiffrentTwoDay(dayn) - DiffrentTwoDay(secondday);
                if (m < 0) {
                    ok = true;
                    this.secondday = secondday;
                }

            }

        }
        if (!ok) {
            setError("Second Day Error");
        }


}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {

        this.startdate = startdate;
    }



    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        if(teacherService.findTeacherByName(teachername) != null) {
            this.teachername = teachername;
        }
        else {
            setError("Not Found Teacher name");

        }
    }



    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        boolean ok=false;
        for (Language lang : getLanguages()) {
            if(lang.getLangname().equalsIgnoreCase(language)) {
                ok=true;
                this.language = language;
            }
        }
        if(!ok) {
            setError("Language Not Found");

        }
    }




    @PreAuthorize("hasAnyRole('USER','ADMIN')")

    public List<Course> getPeriods() {

        System.out.println("P on :"+secondday);
periods=periodDao.getAllPeriod();


        return periods;
    }
    public void submitLession() {
        Lession lession = new Lession();
        lession.setSubject(lessionname);
        lessionSet.add(lession);
    }

public void submit() {

    //  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
    Calendar cal = Calendar.getInstance();
    cal.setTime(startdate);
    System.out.println("Month:"+cal.get(Calendar.MONTH));
    //cal.add(Calendar.DAY_OF_WEEK,1);
  //  String dayNames[] = new DateFormatSymbols().getWeekdays();
    cal.add(Calendar.DAY_OF_WEEK, 1);

    Date st = cal.getTime();
//    String dayname= dayNames[cal.get(Calendar.DAY_OF_WEEK)];

    cal.add(Calendar.MONTH, 2);

/*int dayone=DiffrentTwoDay(dayname);
int daytwo=DiffrentTwoDay(secondday);
    cal.add(Calendar.DAY_OF_WEEK, dayone-daytwo);*/
    System.out.println(secondday);
        periodDao.savePeriod(st, secondday, houer, number, cal.getTime(), teachername, lessionSet, language);
    lessionSet.clear();

}



public void del(){
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
    String action = request.getParameter("id");
       periodDao.delPeriod(Long.valueOf(action));
}



    public  int DiffrentTwoDay(String day) {
        int daynum1=0;
        if(day.contains("Sat")) {
            daynum1=1;

        } else if(day.contains("Sun")) {
            daynum1=2;


        } else if(day.contains("Mon")) {
            daynum1=3;


        } else if(day.contains("Thues")) {
            daynum1=4;


        }else if(day.contains("Wed")) {
            daynum1=5;


        }else if(day.contains("Thurs")) {
            daynum1=6;


        }else if(day.contains("Fri")) {
            daynum1=7;


        }
        return daynum1;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
