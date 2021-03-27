package com.softuni.service;

import com.softuni.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, String id);

    List<LogServiceModel> findAll();

}
