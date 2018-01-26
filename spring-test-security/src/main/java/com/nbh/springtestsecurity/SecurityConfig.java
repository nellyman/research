package com.nbh.springtestsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity // Provides the functionality below, HttpSecurity object, etc
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // provides method level security
@ComponentScan("com.nbh.springtestsecurity")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private StudentRepo studentRepo;

    @PostConstruct
    public void loadSomeStudents() {
        System.out.println("Loading students...");
        Student s1 = new Student("bob");
        studentRepo.save(s1);
        s1 = new Student("tom");
        studentRepo.save(s1);
        s1 = new Student("Jeff");
        studentRepo.save(s1);
    }

    public void configureGlobalAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .anyRequest() // matches all requests...
                //.antMatchers("/api/**") // only secure api endpoint
                .antMatchers("/admin/**")
                //.denyAll()
                //.authenticated()
                .hasAnyAuthority("ADMIN")
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
