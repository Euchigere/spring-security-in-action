package com.euchigere.exercise5logic.config;

import com.euchigere.exercise5logic.middleware.InitialAuthenticationFilter;
import com.euchigere.exercise5logic.middleware.JwtAuthenticationFilter;
import com.euchigere.exercise5logic.service.OtpAuthenticationProvider;
import com.euchigere.exercise5logic.service.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final InitialAuthenticationFilter initialAuthenticationFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final OtpAuthenticationProvider otpAuthenticationProvider;

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    // Autowires the filters and the authentication providers that we set up in the configuration
    public SecurityConfig(InitialAuthenticationFilter initialAuthenticationFilter,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          OtpAuthenticationProvider otpAuthenticationProvider,
                          UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.initialAuthenticationFilter = initialAuthenticationFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(usernamePasswordAuthenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Adds both custom filters into the filter chain
        http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        // Ensures that all requests are authenticated
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
