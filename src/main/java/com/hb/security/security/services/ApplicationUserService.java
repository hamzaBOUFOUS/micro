package com.hb.security.security.services;

import com.hb.security.entity.Role;
import com.hb.security.entity.User;
import com.hb.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Email %s don't found",email)));

        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName().toUpperCase())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
