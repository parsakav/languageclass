package com.parsakav.langclass.controller;

import com.parsakav.langclass.service.UserService;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/*@ManagedBean
@Scope(value = "request")
@Controller(value = "signupController")
@ELBeanName(value = "signupController")*/

@Named("signupController")
@Join(path = "/signup", to = "/signup.jsf")

public class SignupControllerBean {
    @Autowired
    private UserService userService;
    @Autowired
    @Lazy
    private AuthenticationManager authManager;

    private String username;
    private String password;
    private String fullname;
    private Long phonenumber;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {


        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String onSubmit() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object requestObj = context.getRequest();


        if (requestObj instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) requestObj;
            try {
                addUser(username, password, fullname, phonenumber, httpRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "/index.xhtml";
    }

    public void addUser(String username, String password, String fullname, Long phonenumber, HttpServletRequest request) {

        userService.saveUser(username, fullname, phonenumber, password);


        authWithAuthManager(request, username, password);


    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
