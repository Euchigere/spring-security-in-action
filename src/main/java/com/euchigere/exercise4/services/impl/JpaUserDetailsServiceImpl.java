package com.euchigere.exercise4.services.impl;

import com.euchigere.exercise4.Dto.CustomUserDetails;
import com.euchigere.exercise4.models.User;
import com.euchigere.exercise4.repository.UserRepository;
import com.euchigere.exercise4.services.JpaUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsServiceImpl implements JpaUserDetailsService {
    private final UserRepository userRepo;

    public JpaUserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");
        User u = userRepo
                .findUserByUsername(username)
                .orElseThrow(s);
        return new CustomUserDetails(u);
    }
}
