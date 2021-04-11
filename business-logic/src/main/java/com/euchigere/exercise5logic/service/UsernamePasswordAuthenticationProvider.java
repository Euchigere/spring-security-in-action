package com.euchigere.exercise5logic.service;

import com.euchigere.exercise5logic.model.auth.UsernamePasswordAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationServerProxy proxy;

    public UsernamePasswordAuthenticationProvider(AuthenticationServerProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Uses the proxy to call the authentication server.
        // It sends the OTP to the client through SMS.
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        proxy.sendAuth(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(aClass);
    }
}
