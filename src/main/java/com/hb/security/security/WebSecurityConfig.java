package com.hb.security.security;

import com.hb.security.security.filters.JWTAuthenticationFilter;
import com.hb.security.security.filters.JWTAuthorizationFilter;
import com.hb.security.security.jwt.JwtConfig;
import com.hb.security.security.jwt.JwtUtil;
import com.hb.security.security.services.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final JwtConfig jwtConfig;
    @Autowired
    private ApplicationUserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers().authenticated();
        http.authorizeRequests().antMatchers("/api/order/add").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/api/order/add").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/login").permitAll();

        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean(), jwtUtil, userDetailsService));
        http.addFilterBefore(new JWTAuthorizationFilter(jwtConfig, jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
