package com.softuni.service;

import com.softuni.model.view.TeamViewModel;

import java.util.List;

public interface TeamService {
    void initTeams();

    List<TeamViewModel> findAllTeams();

    TeamViewModel findById(String id);

    void addPlayerToTeam(String id, String name);

    List<String> findAllPlayersNames(String id);

    boolean userIsPartOfTheTeam(String id, String name);

    boolean userCanJoin(String id, String name);

    Object isCreator(String id, String name);

    void delete(String id);

    void removePlayerFromTeam(String id, String name);
}
