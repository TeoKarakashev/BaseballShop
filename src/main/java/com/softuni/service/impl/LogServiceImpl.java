package com.softuni.service.impl;

import com.softuni.model.entity.LogEntity;
import com.softuni.model.entity.UserEntity;
import com.softuni.model.service.LogServiceModel;
import com.softuni.repository.LogRepository;
import com.softuni.service.LogService;
import com.softuni.service.TeamService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        logEntity.setTeamName(this.teamService.findById(id).getName());
        logEntity.setUserEntity(this.modelMapper.map(this.userService.findByUsername(authentication.getName()), UserEntity.class));
        this.logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAll() {
        return this.logRepository.findAll().stream()
                .map(logEntity -> this.modelMapper.map(logEntity, LogServiceModel.class))
                .collect(Collectors.toList());
    }
}
