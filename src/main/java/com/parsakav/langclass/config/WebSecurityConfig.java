package com.parsakav.langclass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
private UserDetailsService userDetailsService;
@Autowired
public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
}
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder,PasswordEncoder passwordEncoder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/login","/","/webjars/**","/signup").permitAll()
        .antMatchers("/**").authenticated().and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password").successForwardUrl("/").permitAll()
             .and()
                .logout().deleteCookies("JSESSIONID","remember-me").invalidateHttpSession(true).clearAuthentication(true).permitAll()
                .and()
                .rememberMe().key("remember-me").rememberMeParameter("remember-me").tokenValiditySeconds(86400).and()
                .sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .expiredUrl("/");
        ;
    }
@Bean("passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    return passwordEncoder;
}

}
 /*   @Autowired
    public UserDetailsService userDetailsService;



    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
  *//*auth.inMemoryAuthentication().withUser("p").password("p").roles("USER");
        auth.inMemoryAuthentication().withUser("parsa").password("p").roles("ADMIN"); *//*

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       *//* http.sessionManagement()
                .sessionConcurrency(concurrencyControlConfigurer -> {
concurrencyControlConfigurer.maximumSessions(1).maxSessionsPreventsLogin(true);
                });*//*

        http.csrf().disable().authorizeRequests().antMatchers("/signup/**", "/signup","/downloadalbumsampl/", "/", "/webjars/bootstrap/4.4.1-1/css/bootstrap.min.css").permitAll()
                .antMatchers("/**").authenticated()
                .and().formLogin().loginPage("/login")

                .usernameParameter("username").passwordParameter("password").successForwardUrl("/").permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true).permitAll()
                .deleteCookies("remember-me")
                .and()
                .sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .expiredUrl("/");

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/