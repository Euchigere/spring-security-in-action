package com.euchigere.exercise1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Override
    @Deprecated
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
//        var userDetailService =
//                new InMemoryUserDetailsManager();
//        var user = User.withUsername("Patrick")
//                .password("password")
//                .authorities("read")
//                .build();
//        userDetailService.createUser(user);
//
//        auth.userDetailsService(userDetailService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//    }

}
