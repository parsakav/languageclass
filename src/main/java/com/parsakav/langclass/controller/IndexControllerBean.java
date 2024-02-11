package com.parsakav.langclass.controller;

import com.parsakav.langclass.service.*;
import com.parsakav.langclass.model.*;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;


@Scope(value = "session")
@Named("productController")
@Join(path = "/", to = "/index.jsf")
public class IndexControllerBean {

    private String error;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LanguageService languageService;

    public void buy() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String id = request.getParameter("id");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username;
        if(principal instanceof UserDetails) {
            username=((UserDetails) principal) .getUsername();
        } else {
            username=principal.toString();
        }

        Course  period= courseService.findPeriodById(Integer.valueOf(id));
     User user= userService.findUserByUsername(username);
     if(period.getMaxstudent() != period.getUsers().size()) {


         if (checkDate(user, period)) {
             System.out.println("Buy");

             Set<Course> periods = user.getCourses();
             periods.add(period);
             user.setCourses(periods);

             System.out.println("User ID:"+user.getId());
             userService.saveUser(user);
         } else {
             setError("Date Error!");
         }
     } else {
         setError("Max Student Error");
     }
      //  periodDao.delPeriod(Long.valueOf(action));
    }
/*   private String s;
public String getHello() {
    return s;
}
public void setHello(String hellp) {
    System.out.println(hellp);
    this.s=hellp;

}
public void getAction() {


    System.out.println(s);
}*/

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

public boolean checkDate(User user, Course period) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
    String dayNames[] = new DateFormatSymbols().getWeekdays();

    Date p=period.getStartdate();
    Calendar calendaru = new GregorianCalendar();
    calendaru.setTime(p);
    int year = calendaru.get(Calendar.YEAR);
    int month = calendaru.get(Calendar.MONTH);
int day = calendaru.get(Calendar.DAY_OF_YEAR);
    String dayn= dayNames[calendaru.get(Calendar.DAY_OF_WEEK)];
    Calendar calendar = new GregorianCalendar();

  //  int diff2= day + DiffrentTwoDay(period.getSeconddayinweek()) ;

    for(Course per :user.getCourses()) {

        int minushour = Math.abs(per.getHour() - period.getHour());
        if (minushour <= 2) {

            Date u = per.getStartdate();
            calendar.setTime(u);
            String dayn2= dayNames[calendar.get(Calendar.DAY_OF_WEEK)];

            int uyear = calendar.get(Calendar.YEAR);
            int umonth = calendar.get(Calendar.MONTH);
         int uday = calendar.get(Calendar.DAY_OF_YEAR);
            if (year == uyear) {
              //  boolean r=true;
                int minus = Math.abs(day - uday);

                if (minus <=60) {


                    if (dayn.equalsIgnoreCase(dayn2)) {


                        return false;
                    } else if(dayn.toLowerCase().contains(per.getSeconddayinweek().toLowerCase())) {
                        return false;

                    }

                    else if(period.getSeconddayinweek().equalsIgnoreCase(per.getSeconddayinweek())){
                        int minusd = Math.abs(DiffrentTwoDay(per.getSeconddayinweek()) - DiffrentTwoDay(period.getSeconddayinweek()));
                        calendar.add(Calendar.DAY_OF_WEEK, minusd);
                        if(minusd <=60) {
                            return false;
                        }

                    }/*else if(period.getSeconddayinweek().equalsIgnoreCase(per.getSeconddayinweek())){
                        int minusd = Math.abs(DiffrentTwoDay(per.getSeconddayinweek()) - DiffrentTwoDay(period.getSeconddayinweek()));
                        calendar.add(Calendar.DAY_OF_WEEK, minusd);
                        if(minusd <=60) {
                            return false;
                        }

                    }*/
                    else if(dayn2.toLowerCase().contains(per.getSeconddayinweek().toLowerCase())) {
                        return false;

                    }


                }

            }
        }
    }
    return true;
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
    @PostConstruct
    public void initDb() {
     //   userService.saveUser("Pa","Pa",Long.valueOf(111),"Pa");
        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");
        roleService.saveRole(role);
        role.setId(2);
        role.setName("ROLE_ADMIN");
        roleService.saveRole(role);
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("$2a$10$QaJnG1fa7/F2IREG72dAIue05MbwdL5xWTT3vqV0pFEumnsTNErjq");

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);

        Language language = new Language();
        language.setId(1);
        language.setLangname("Russian");
        languageService.saveLanguage(language);

        language.setId(2);
        language.setLangname("English");
        languageService.saveLanguage(language);

        language.setId(3);
        language.setLangname("Chinese");
        languageService.saveLanguage(language);

        language.setId(4);
        language.setLangname("French");
        languageService.saveLanguage(language);

        language.setId(5);
        language.setLangname("German");
        languageService.saveLanguage(language);

    }

}
