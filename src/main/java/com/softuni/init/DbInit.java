package com.softuni.init;

import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DbInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initAdminUser();
    }
}
