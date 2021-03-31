package com.softuni.service;


import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.repository.UserRepository;
import com.softuni.service.impl.BaseballUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class BaseballUserServiceTest {

    private BaseballUserService serviceToTest;

    @BeforeEach
    public void setUp() {
        serviceToTest = new BaseballUserService(mockUserRepository);
    }

    @Mock
    UserRepository mockUserRepository;


    @Test
    public void testUserNotFound(){
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            serviceToTest.loadUserByUsername("invalidUser");
        });
    }

    @Test
    public void testExistingUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("123");
        RoleEntity user = new RoleEntity();
        RoleEntity admin = new RoleEntity();
        user.setRole(UserRole.USER);
        admin.setRole(UserRole.ADMIN);
        userEntity.setRoles(Set.of(user, admin));

        Mockito.when(mockUserRepository.findByUsername("test")).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = serviceToTest.loadUserByUsername("test");

        Assertions.assertEquals(userEntity.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Assertions.assertTrue(authorities.contains("ROLE_ADMIN"));
        Assertions.assertTrue(authorities.contains("ROLE_USER"));
    }
}
