package com.softuni.service.impl;

import com.softuni.model.entity.LogEntity;
import com.softuni.model.entity.TeamEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.repository.LogRepository;
import com.softuni.service.LogService;
import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, TeamService teamService, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.teamService = teamService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLog(String action, String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LogEntity logEntity = new LogEntity();
        logEntity.setAction(action);
        logEntity.setDateTime(LocalDateTime.now());
        logEntity.setTeamEntity(this.modelMapper.map(this.teamService.findById(id), TeamEntity.class));
        logEntity.setUserEntity(this.modelMapper.map(this.userService.findByUsername(authentication.getName()), UserEntity.class));
        System.out.println();
        this.logRepository.save(logEntity);
    }
}
