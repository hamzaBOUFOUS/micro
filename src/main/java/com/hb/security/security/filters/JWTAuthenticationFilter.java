package com.hb.security.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.security.dto.Login;
import com.hb.security.security.jwt.JwtUtil;
import com.hb.security.security.services.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ApplicationUserService applicationUserService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Login login = null;

        try {
            login = new ObjectMapper().readValue(request.getInputStream(),Login.class);
            log.debug("Login with email: {}", login.getEmail());
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        } catch (AuthenticationException e) {
            log.error(String.format("Authentication failed for email: %s and password: %s", login.getEmail(), login.getPassword()));
            throw  e;
        } catch (Exception e){
            throw new RuntimeException(String.format("Error in attemptAuthentication with email %s and password %s", login.getEmail(), login.getPassword(), e));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        final UserDetails userDetails = applicationUserService.loadUserByUsername(user.getUsername());
        String accessToken = jwtUtil.genrateToken(userDetails);
        response.addHeader("access_token", accessToken);
    }
}
