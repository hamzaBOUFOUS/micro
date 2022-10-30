package com.hb.security.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb.security.security.jwt.JwtConfig;
import com.hb.security.security.jwt.JwtUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Log4j2
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtConfig jwtConfig;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && !header.startsWith(this.jwtConfig.getTokenPrefix())){
            try {
                token = header.substring("Bearer ".length());
                UsernamePasswordAuthenticationToken authenticationToken = jwtUtil.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch(JOSEException | BadJOSEException | ParseException e){
                log.error(String.format("Error auth token: %s", token), e);
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
