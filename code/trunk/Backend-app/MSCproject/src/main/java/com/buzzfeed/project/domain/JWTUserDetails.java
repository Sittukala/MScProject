package com.buzzfeed.project.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//JWT mechanism effectively secures the api with tokens -
// Cite: Madhab, Nil. "Let's Implement Basic JWT Based Authentication in Spring Boot."
// DEV Community. March 20, 2021. Accessed September 09, 2021.
// https://dev.to/nilmadhabmondal/let-s-implement-basic-jwt-based-authentication-in-spring-boot-5ec4.
public class JWTUserDetails implements UserDetails {
    private User user;

    public JWTUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}


