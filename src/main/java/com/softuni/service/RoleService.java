package com.softuni.service;

import com.softuni.model.entity.RoleEntity;

public interface RoleService {
    void initRoles();

    RoleEntity findByName(String name);
}
