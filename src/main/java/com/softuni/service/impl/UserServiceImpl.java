package com.softuni.service.impl;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.model.service.UserRegisterServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final BaseballUserService baseballUserService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, BaseballUserService baseballUserService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.baseballUserService = baseballUserService;
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity newUser = this.modelMapper.map(userRegisterServiceModel, UserEntity.class);
        System.out.println();
        RoleEntity userRoleEntity = this.roleService.getRoleByName(UserRole.USER);
        newUser.setRoles(Set.of(userRoleEntity));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.userRepository.save(newUser);

        UserDetails principal = this.baseballUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Override
    public boolean userExists(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void initAdminUser() {
        if(this.userRepository.count() == 0){
            RoleEntity adminRole = this.roleService.getRoleByName(UserRole.ADMIN);
            RoleEntity useRole   = this.roleService.getRoleByName(UserRole.USER);


            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setEmail("admin@mail.com");
            admin.setAge(25);
            admin.setDescription("This is an admin account");
            admin.setRoles(Set.of(adminRole, useRole));
            admin.setPassword(passwordEncoder.encode("123"));
            this.userRepository.save(admin);
        }
    }

}
