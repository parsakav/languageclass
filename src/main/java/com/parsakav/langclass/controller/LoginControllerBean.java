package com.parsakav.langclass.controller;


import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@PreAuthorize("!isAuthenticated()")
//https://github.com/HiSpringMan/SpringBoot-Jsf-PrimeFaces-Hibernate/tree/master/src/main/webapp/user

@Controller
@Join(path = "/login", to = "/login.jsf")
public class LoginControllerBean {

}
