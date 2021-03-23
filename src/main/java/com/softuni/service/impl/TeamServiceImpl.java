package com.softuni.service.impl;

import com.softuni.error.TeamNotFoundException;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.view.TeamViewModel;
import com.softuni.repository.TeamRepository;
import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, UserService userService, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initTeams() {
        if(teamRepository.count() == 0) {

            UserServiceModel admin = this.userService.findByUsername("admin");

            TeamEntity team1 = new TeamEntity();
            team1.setName("Boston Red Sox");
            team1.setImageUrl("https://digital.hbs.edu/platform-digit/wp-content/uploads/sites/2/2015/09/1024px-RedSoxSecondary_Circle.svg_2.png");
            team1.setCreated(LocalDate.now());
            team1.setCapacity(40);
            team1.setCreator(this.userService.findAll().get(0));
            team1.setDescription("The Boston Red Sox are an American professional baseball team based in Boston. They compete in Major League Baseball (MLB) as a member club of the American League (AL) East division. Founded in 1901");
            team1.setAddress("4 Jersey St, Boston, MA 02215");

            TeamEntity team2 = new TeamEntity();
            team2.setName("New York Yankees");
            team2.setImageUrl("https://a3.espncdn.com/combiner/i?img=%2Fi%2Fteamlogos%2Fmlb%2F500%2Fnyy.png");
            team2.setCreated(LocalDate.now());
            team2.setCapacity(20);
            team2.setCreator(this.userService.findAll().get(0));
            team2.setDescription("The New York Yankees are an American professional baseball team based in the New York City borough of the Bronx. They compete in Major League Baseball (MLB) as a member club of the American League (AL) East division.");
            team2.setAddress("One East 161st Street Bronx, NY 10451");

            this.teamRepository.saveAll(List.of(team1, team2));
        }
    }

    @Override
    public List<TeamViewModel> findAllTeams() {
        return this.teamRepository.findAll().stream()
                .map(teamEntity -> this.modelMapper.map(teamEntity, TeamViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeamViewModel findById(String id) {
        return this.modelMapper
                .map(this.teamRepository.findById(id)
                        .orElseThrow(() -> new TeamNotFoundException("No team found")),
                        TeamViewModel.class);
    }
}
