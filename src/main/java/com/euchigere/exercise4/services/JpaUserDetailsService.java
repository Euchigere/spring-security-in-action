package com.euchigere.exercise4.services;

import com.euchigere.exercise4.Dto.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface JpaUserDetailsService extends UserDetailsService {
    @Override
    CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
