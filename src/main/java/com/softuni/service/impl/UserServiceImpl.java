package com.softuni.service.impl;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.repository.UserRepository;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserEntity userEntity) {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(this.roleService.findByName("user"));
        if (this.userRepository.count() == 0) {
            roles.add(this.roleService.findByName("admin"));
        }
        userEntity.setRoles(roles);

        System.out.println();

          this.userRepository.save(userEntity);

    }
}
