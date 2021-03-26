package com.softuni.service;

import com.softuni.model.service.TeamServiceModel;
import com.softuni.model.view.TeamViewModel;

import java.io.IOException;
import java.util.List;

public interface TeamService {

    List<TeamViewModel> findAllTeams();

    TeamViewModel findById(String id);

    void addPlayerToTeam(String id, String name);

    List<String> findAllPlayersNames(String id);

    boolean userIsPartOfTheTeam(String id, String name);

    boolean userCanJoin(String id, String name);

    boolean isCreator(String id, String name);

    void delete(String id);

    void removePlayerFromTeam(String id, String name);

    boolean teamExists(String name);

    void saveTeam(TeamServiceModel map, String name) throws IOException;
}
