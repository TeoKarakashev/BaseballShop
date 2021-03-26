package com.softuni.service.impl;

import com.softuni.error.TeamNotFoundException;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.service.TeamServiceModel;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.view.TeamViewModel;
import com.softuni.repository.TeamRepository;
import com.softuni.service.CloudinaryService;
import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;


    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, UserService userService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.teamRepository = teamRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
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
    public void saveTeam(TeamServiceModel teamServiceModel, String name) throws IOException {
        TeamEntity teamEntity = this.modelMapper.map(teamServiceModel, TeamEntity.class);
        teamEntity.setCreator(this.modelMapper.map(this.userService.findByUsername(name), UserEntity.class));
        teamEntity.setImageUrl(this.cloudinaryService.uploadImage(teamServiceModel.getImageUrl()));
        teamEntity.setCreated(LocalDate.now());

        this.teamRepository.save(teamEntity);
    }


}
