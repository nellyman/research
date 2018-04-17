package com.nbh.springmvc2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityApplication extends WebSecurityConfigurerAdapter {


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

       /* auth.inMemoryAuthentication()
                .withUser("bob").password("pwd").roles("ADMIN");*/
    }


    @Override
    public void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/api/upload/").denyAll(); //hasRole("ROLE_ADMIN");
                //.antMatchers("/*/**").hasRole("ROLE_USER");
    }

}
