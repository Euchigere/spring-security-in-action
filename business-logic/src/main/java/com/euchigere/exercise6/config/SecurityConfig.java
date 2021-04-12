package com.euchigere.exercise6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Uses a Customizer to set the ClientRegistrationRepository instance
        http.oauth2Login(c -> {
            c.clientRegistrationRepository(clientRepository());
        });
        http.authorizeRequests().anyRequest().authenticated();
    }

    // method can be directly added to spring context using bean annotation or
    // added as above
    private ClientRegistrationRepository clientRepository() {
        // Adds a bean of type ClientRegistrationRepository to the Spring context.
        // The bean contains the reference to a ClientRegistration.
        ClientRegistration c = clientRegistration();
        return new InMemoryClientRegistrationRepository(c);
    }

    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB
//                .getBuilder("github") //Provides an ID for the client registration
//                .clientId("9e8ce529f96de159ae19")
//                .clientSecret("3aa13b60420d0aeae17139cc584dc78686d4eb45")
//                .build();

        return ClientRegistration.withRegistrationId("github")
                .clientId("9e8ce529f96de159ae19")
                .clientSecret("3aa13b60420d0aeae17139cc584dc78686d4eb45")
                .scope("read:user")
                .authorizationUri("https://github.com/login/oauth/authorize")
                .tokenUri("https://github.com/login/oauth/access_token")
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("id")
                .clientName("GitHub")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .build();
    }
}
