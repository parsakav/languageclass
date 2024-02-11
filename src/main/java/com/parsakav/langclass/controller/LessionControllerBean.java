package com.parsakav.langclass.controller;


import com.parsakav.langclass.model.Course;
import com.parsakav.langclass.model.Lession;
import com.parsakav.langclass.service.CourseService;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Scope("session")
@Named(value = "lessionController")
@Join(path = "/lessions", to = "/lessions.jsf")
public class LessionControllerBean {
    @Autowired
    private CourseService courseService;

    private Set<Lession> lessionSet ;

    public String lessionpage() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String periodid = request.getParameter("periodid");
   Course pr=   courseService.findPeriodById(Integer.valueOf(periodid));

;
        List<Lession> list = new ArrayList<>(pr.getLession());
        if(list != null || list.size() != 0) {
            Collections.reverse(list);
            this.lessionSet = new HashSet<>(list);
        }
        //?faces-redirect=true
        return"/lessions.xhtml";
    }

    public Set<Lession> getLessionSet() {

        return lessionSet;

    }
}
