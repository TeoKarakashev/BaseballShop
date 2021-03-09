package com.softuni.init;

import com.softuni.service.BrandService;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final BrandService brandService;

    @Autowired
    public DbInit(RoleService roleService, UserService userService, BrandService brandService) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initAdminUser();
        this.brandService.initBrands();

    }
}
