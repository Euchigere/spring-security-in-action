package com.euchigere.exercise5logic.service;

import com.euchigere.exercise5logic.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {
    private final RestTemplate rest;

    private final String baseUrl;

    // Takes the base URL from the application.properties file
    public AuthenticationServerProxy(RestTemplate rest, @Value("${auth.server.base.url}") String baseUrl) {
        this.rest = rest;
        this.baseUrl = baseUrl;
    }


    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        var body = new User();
        body.setUsername(username);
        body.setPassword(password);
        var request = new HttpEntity<>(body);
        rest.postForEntity(url, request, Void.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/otp/check";
        var body = new User();
        // The HTTP request body needs the username and the code for this call.
        body.setUsername(username);
        body.setCode(code);

        var request = new HttpEntity<>(body);
        var response = rest.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
