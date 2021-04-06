package com.softuni.web;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.repository.RoleRepository;
import com.softuni.repository.TeamRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.CloudinaryService;
import com.softuni.service.TeamService;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    private final static String TEAM_PREFIX = "/teams";
    private static String teamId;

    private static String userId;
    @MockBean
    CloudinaryService mockCloudinaryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamService teamService;


    @BeforeEach
    void setUp() {
        init();
    }


    @AfterEach
    void tearDown() {

        if (teamRepository.count() != 0) {
            teamService.delete(teamId);
        }

        userRepository.deleteById(userId);

    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewAdd() throws Exception {
        this.mockMvc.perform(get(TEAM_PREFIX + "/create"))
                .andExpect(view().name("/teams/team-create"));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testTeamAllShouldReturnCorrectView() throws Exception {
        this.mockMvc.perform(get(TEAM_PREFIX))
                .andExpect(view().name("/teams/teams-all"))
                .andExpect(model().attributeExists("teams"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testDetailsShouldReturnCorrectView() throws Exception {
        this.mockMvc.perform(get(TEAM_PREFIX + "/details/{id}", teamId))
                .andExpect(view().name("/teams/team-details"))
                .andExpect(model().attributeExists("names"))
                .andExpect(model().attributeExists("team"))
                .andExpect(model().attributeExists("canLeave"))
                .andExpect(model().attributeExists("canJoin"))
                .andExpect(model().attributeExists("isCreator"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldJoinTeam() throws Exception {
        this.mockMvc.perform(post(TEAM_PREFIX + "/join/{id}", teamId)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, teamService.findById(teamId).getPlayers().size());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldLeaveTeam() throws Exception {
        this.mockMvc.perform(post(TEAM_PREFIX + "/join/{id}", teamId)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(post(TEAM_PREFIX + "/leave/{id}", teamId)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(0, teamService.findById(teamId).getPlayers().size());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"ADMIN", "USER"})
    void testShouldDeleteTeam() throws Exception {
        mockMvc.perform(post(TEAM_PREFIX + "/delete/{id}", teamId)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(0, teamRepository.count());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldAddTeam() throws Exception {

        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "imageUrl",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        this.mockMvc.perform(multipart(TEAM_PREFIX + "/create")
                .file(mockImgFile)
                .param("name", "validName")
                .param("address", "validAddress")
                .param("description", "validDesc")
                .param("capacity", "10")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, this.teamRepository.count());
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
        userRepository.save(user);
        userId = user.getId();

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("testTeam");
        teamEntity.setCreated(LocalDate.now());
        teamEntity.setDescription("testDescription");
        teamEntity.setImageUrl("testImage");
        teamEntity.setAddress("testAddress");
        teamEntity.setCapacity(5);
        teamEntity.setCreator(userRepository.findByUsername("testUser").orElseThrow());
        teamEntity = this.teamRepository.save(teamEntity);
        teamId = teamEntity.getId();
    }


}