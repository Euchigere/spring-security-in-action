package com.euchigere.exercise5logic.middleware;

import com.euchigere.exercise5logic.model.auth.OtpAuthentication;
import com.euchigere.exercise5logic.model.auth.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager manager;

    // Takes the value of the key used to sign the JWT token from the properties file
    @Value("${jwt.signing.key}")
    private String signingKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        // If the HTTP request doesn’t contain an OTP, we assume we have to authenticate based on username and password.
        if (code == null) {
            //Adds the branch for the case in which the OTP code is not null.
            // We consider, in this case, that the client sent an OTP for the second authentication step.
            Authentication a = new UsernamePasswordAuthentication(username, password); manager.authenticate(a);
        } else {
            //For the second authentication step, creates an instance of type OtpAuthentication
            // and sends it to the AuthenticationManager, which finds a proper provider for it
            Authentication a = new OtpAuthentication(username, code);
            a = manager.authenticate(a);
            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            // Builds a JWT and stores the username of the authenticated
            // user as one of its claims. We use the key to sign the token.
            String jwt = Jwts.builder()
                    .setClaims(Map.of("username", username))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Applies this filter only to the /login path
        return !request.getServletPath().equals("/login");
    }
}
