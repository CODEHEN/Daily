package com.day.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    DataSource dataSource;
    @Autowired
    AuthSuccessHandler successHandler;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,groupname,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/lib/**", "/webjars/**",
                "/css/**", "/images/**", "/js/**","/data/**");
    }
    @Override
    //授权
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index","/console","/").hasAnyRole("Teacher","Student", "GroupLeader")
                .antMatchers("/loginpage","loginverify").permitAll()
                .antMatchers("/pages/often/message.html").hasRole("Teacher")
                .antMatchers("/pages/often/message1.html").hasRole("GroupLeader")
                .antMatchers("/pages/member/**").hasRole("Teacher")
                .antMatchers("/pages/txtedit/**").hasAnyRole("Student", "GroupLeader")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/loginpage").permitAll()
                .loginProcessingUrl("/authentication/form")
                .successHandler(successHandler)
//
                .and().logout().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }








}
