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
    private final BatService batService;
    private final GloveService gloveService;

    @Autowired
    public DbInit(RoleService roleService, UserService userService, BrandService brandService, BatService batService, GloveService gloveService) {
        this.roleService = roleService;
        this.userService = userService;
        this.brandService = brandService;
        this.batService = batService;
        this.gloveService = gloveService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.initRoles();
        this.userService.initAdminUser();
        this.brandService.initBrands();
        this.batService.initBats();
        this.gloveService.initGloves();

    }
}
