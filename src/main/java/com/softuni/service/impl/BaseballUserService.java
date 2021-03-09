package com.softuni.service.impl;

import com.softuni.model.entity.UserEntity;
import com.softuni.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseballUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public BaseballUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println();
        UserEntity userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username with " + username + "was not found"));
        return mapToUserDetails(userEntity);
    }


    private UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());

        UserDetails result = new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );

        return result;
    }
}
