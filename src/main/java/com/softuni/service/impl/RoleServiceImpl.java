package com.softuni.service.impl;

import com.softuni.model.entity.RoleEntity;
import com.softuni.repository.RoleRepository;
import com.softuni.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if(this.roleRepository.count() == 0){
            RoleEntity user = new RoleEntity();
            user.setName("user");
            this.roleRepository.save(user);
            RoleEntity admin = new RoleEntity();
            admin.setName("admin");
            this.roleRepository.save(admin);
        }
    }

    @Override
    public RoleEntity findByName(String name) {
        return this.roleRepository.findByName(name).get();

    }
}
