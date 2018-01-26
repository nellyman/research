package com.nbh.springtestsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepo.findByName(username);
        if (student == null) {
            throw new RuntimeException("Cannot find principle!!");
        }
        List<GrantedAuthority> auths;
        if ("tom".equalsIgnoreCase(student.name)) {
            auths = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        } else {
            auths = new ArrayList<>(0);
        }
        User details = new User(
                student.name
                , "123"
                , auths
        );
        return details;
    }
}
