package com.softuni.init;

import com.softuni.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final BrandService brandService;
    private final GloveService gloveService;


    @Autowired
    public DbInit(RoleService roleService, UserService userService, BrandService brandService, BatService batService, GloveService gloveService, TeamService teamService) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
        this.gloveService = gloveService;

    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.brandService.initBrands();
        this.gloveService.initGloves();
        this.userService.initAdminUser();
    }
}
