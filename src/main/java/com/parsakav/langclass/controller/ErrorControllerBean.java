package com.parsakav.langclass.controller;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Named(value = "errorController")
@Join(path = "/error",to = "/error.jsf")

public class ErrorControllerBean implements ErrorController {
    private static int statuscode;

    private String error;
    @Override
    public String getErrorPath() {
        return "/error";
    }
public void getErrorContentFromHttpRequest() {

    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    Object requestObj = context.getRequest();
    HttpServletRequest httpRequest = (HttpServletRequest) requestObj;

    Throwable exception = (Throwable) httpRequest.getAttribute("javax.servlet.error.exception");
    Integer statuscode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    System.out.println("Error code:"+statuscode);
    setStatuscode(statuscode);
    String errorcontent=exception==null?"N/A":exception.getMessage();
    setError(errorcontent);
}
    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

   /* @Override
    public void afterPropertiesSet() throws Exception {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object requestObj = context.getRequest();
        HttpServletRequest httpRequest = (HttpServletRequest) requestObj;

        Exception exception = (Exception) httpRequest.getAttribute("javax.servlet.error.exception");
        Integer statuscode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        System.out.println("Error code:"+statuscode);
        setStatuscode(statuscode);
        String errorcontent=exception==null?"N/A":exception.getMessage();
        setError(errorcontent);
    }*/
}

