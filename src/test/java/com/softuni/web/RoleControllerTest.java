package com.softuni.web;


import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.repository.RoleRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    private final static String ROLE_PREFIX = "/roles";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private String username;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        init();
    }


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testAddRoleShouldReturnViewModelAndContainAttribute() throws Exception {
        mockMvc.perform(get(ROLE_PREFIX + "/add"))
                .andExpect(view().name("/users/admin-panel"))
                .andExpect(model().attributeExists("usernames"));
    }

    // TODO @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testPromoteUserShouldAddAdminRole() throws Exception {
        mockMvc.perform(post(ROLE_PREFIX + "/add")
                .with(csrf())
                .param("username", username));

        Assertions.assertEquals(2, userService.findByUsername(username).getRoles().size());

    }






    private void init() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(UserRole.USER);
        roleEntity = roleRepository.save(roleEntity);

        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        user.setFirstName("test");
        user.setLastName("testov");
        user.setEmail("test@mail.com");
        user.setRoles(Set.of(roleEntity));
        user.setPassword("123");
        this.userRepository.save(user);
        username = user.getUsername();

    }
}