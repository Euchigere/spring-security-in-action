package com.euchigere.exercise3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
//        String usersByUsernameQuery =
//            "select username, password, enabled" +
//            "from users where username = ?";
//        String authsByUserQuery =
//            "select username, authority " +
//            "from spring.authorities where username = ?";
//
//        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
//        return userDetailsManager;
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    @Deprecated
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
