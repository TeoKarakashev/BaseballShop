package com.softuni.service;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.enums.UserRole;

public interface RoleService {
    void initRoles();


    RoleEntity getRoleByName(UserRole role);
}
