package com.softuni.service;

import com.softuni.model.entity.RoleEntity;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.entity.enums.UserRole;
import com.softuni.model.view.TeamViewModel;
import com.softuni.repository.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    private UserEntity user1;
    private TeamEntity team1, team2;
    private RoleEntity roleUser;

    @MockBean
    TeamRepository teamRepository;
    @Autowired
    TeamService teamService;

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void testFindByName(){
        when(teamRepository.findByName("testTeam1")).thenReturn(Optional.of(team1));

        Assertions.assertTrue(this.teamService.teamExists("testTeam1"));
    }

    @Test
    void testFindAll(){
        when(teamRepository.findAll()).thenReturn(List.of(team1, team2));

        List<TeamViewModel> allTeams = teamService.findAllTeams();

        Assertions.assertEquals(2, allTeams.size());

        TeamViewModel model1 = allTeams.get(0);
        TeamViewModel model2 = allTeams.get(1);

        Assertions.assertEquals(team1.getName(), model1.getName());
        Assertions.assertEquals(team1.getImageUrl(), model1.getImageUrl());
        Assertions.assertEquals(team1.getAddress(), model1.getAddress());
        Assertions.assertEquals(team1.getDescription(), model1.getDescription());
        Assertions.assertEquals(team1.getCapacity(), model1.getCapacity());
        Assertions.assertEquals(team1.getCreator(), model1.getCreator());

        Assertions.assertEquals(team2.getName(), model2.getName());
        Assertions.assertEquals(team2.getImageUrl(), model2.getImageUrl());
        Assertions.assertEquals(team2.getAddress(), model2.getAddress());
        Assertions.assertEquals(team2.getDescription(), model2.getDescription());
        Assertions.assertEquals(team2.getCapacity(), model2.getCapacity());
        Assertions.assertEquals(team2.getCreator(), model2.getCreator());
    }



    private void init() {

        roleUser = new RoleEntity();
        roleUser.setRole(UserRole.USER);

        user1 = new UserEntity();
        user1.setUsername("testUser");
        user1.setFirstName("test");
        user1.setLastName("testov");
        user1.setEmail("test@mail.com");
        user1.setRoles(Set.of(roleUser));
        user1.setPassword("123");

        team1 = new TeamEntity();
        team1.setName("testTeam1");
        team1.setCreated(LocalDate.now());
        team1.setDescription("testDescription1");
        team1.setImageUrl("testImage1");
        team1.setAddress("testAddress1");
        team1.setCapacity(5);
        team1.setCreator(user1);

        team2 = new TeamEntity();
        team2.setName("testTeam2");
        team2.setCreated(LocalDate.now());
        team2.setDescription("testDescription2");
        team2.setImageUrl("testImage2");
        team2.setAddress("testAddress2");
        team2.setCapacity(6);
        team2.setCreator(user1);
    }
}