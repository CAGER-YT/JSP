package com.spring.security.config;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component(value = "customer")
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    UserRepository ur1;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=ur1.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User not Found");
        }
        return new org.springframework.security.core.userdetails.User(
        		user.getName(),
        		user.getPassword(),
        		Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
