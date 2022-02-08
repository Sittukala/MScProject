package com.buzzfeed.project.Service;

import com.buzzfeed.project.Repository.UserRepository;
import com.buzzfeed.project.domain.JWTUserDetails;
import com.buzzfeed.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//Loads user details with username for jwt authentication

@Service
public class JWTUserDetailsService<AppRole> implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    private JWTUserDetails jwtUserDetails;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);


        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }


}
