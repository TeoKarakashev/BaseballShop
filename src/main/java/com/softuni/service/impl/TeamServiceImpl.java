package com.softuni.service.impl;

import com.softuni.error.TeamNotFoundException;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.service.TeamServiceModel;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.view.TeamViewModel;
import com.softuni.repository.TeamRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public TeamServiceImpl(TeamRepository teamRepository, UserService userService, ModelMapper modelMapper, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void initTeams() {
        if (teamRepository.count() == 0) {

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
            team2.setCapacity(1);
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

    @Override
    public void addPlayerToTeam(String id, String name) {
        UserServiceModel user = this.userService.findByUsername(name);
        TeamEntity teamEntity = this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("team not found"));
        user.setTeam(teamEntity);
        this.userService.save(user);
    }

    @Override
    public List<String> findAllPlayersNames(String id) {
        List<String> names = new ArrayList<>();
        TeamEntity teamEntity = this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        teamEntity.getPlayers().forEach(userEntity -> names.add(userEntity.getUsername()));
        return names;
    }

    @Override
    public boolean userIsPartOfTheTeam(String id, String name) {
        UserServiceModel user = this.userService.findByUsername(name);
        TeamServiceModel team = this.modelMapper.map(this.teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("team not found")), TeamServiceModel.class);
        for (UserEntity player : team.getPlayers()) {
            if (player.getId().equals(user.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userCanJoin(String id, String name) {
        if(this.userService.findByUsername(name).getTeam() != null){
            return false;
        }
        TeamEntity team = this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("no team found"));

        return team.getPlayers().size() < team.getCapacity();
    }

    @Override
    public boolean isCreator(String id, String name) {
        UserServiceModel user = this.userService.findByUsername(name);
        TeamServiceModel team = this.modelMapper.map(this.teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("team not found")), TeamServiceModel.class);
        return user.getId().equals(team.getCreator().getId());
    }

    @Override
    public void delete(String id) {
        TeamServiceModel team = this.modelMapper.map(this.teamRepository.findById(id).
                orElseThrow(() -> new TeamNotFoundException("not team found")), TeamServiceModel.class);
        for (UserEntity player : team.getPlayers()) {
            player.setTeam(null);
            this.userService.save(this.modelMapper.map(player, UserServiceModel.class));
        }

        this.teamRepository.deleteById(id);
    }

    @Override
    public void removePlayerFromTeam(String id, String name) {
        UserServiceModel user = this.userService.findByUsername(name);
        user.setTeam(null);
        this.userService.save(user);

    }

    @Override
    public boolean teamExists(String name) {
        return this.teamRepository.findByName(name).isPresent();
    }

    @Override
    public void saveTeam(TeamServiceModel teamServiceModel, String name) {
        TeamEntity teamEntity = this.modelMapper.map(teamServiceModel, TeamEntity.class);
        teamEntity.setCreator(this.modelMapper.map(this.userService.findByUsername(name), UserEntity.class));
        teamEntity.setCreated(LocalDate.now());

        this.teamRepository.save(teamEntity);
    }


}
