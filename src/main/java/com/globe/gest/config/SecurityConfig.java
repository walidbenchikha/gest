package com.globe.gest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.globe.gest.exception.AccessDeniedExceptionHandler;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses=com.globe.gest.service.UserServiceImpl.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService);
    }
   
    @Autowired
    AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/strategy/**").hasRole("ADMIN")
                .antMatchers("/user/**").permitAll()
                .antMatchers("/auditor/**").permitAll()
                .antMatchers("/tovisit/**").permitAll()
                .antMatchers("/visited/**").permitAll()
                .antMatchers("/shops/**").permitAll()
                .antMatchers("/map/**").permitAll()
                .antMatchers("/institution/**").permitAll()
                .antMatchers("/camion/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/permission/**").permitAll()
                .antMatchers("/auditors/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedExceptionHandler);
    }
}