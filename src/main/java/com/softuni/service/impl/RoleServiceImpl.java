package com.softuni.service.impl;

import com.softuni.error.RoleNotFoundException;
import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.repository.RoleRepository;
import com.softuni.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(this.roleRepository.count() == 0){
            RoleEntity admin = new RoleEntity();
            admin.setRole(UserRole.ADMIN);
            RoleEntity user = new RoleEntity();
            user.setRole(UserRole.USER);

            this.roleRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public RoleEntity getRoleByName(UserRole userRole) {
        return this.roleRepository.findByRole(userRole).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));
    }

}
