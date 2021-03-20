package com.softuni.service;

import com.softuni.model.view.BatViewModel;

import java.util.List;

public interface BatService {
    
    List<BatViewModel> findAllBats();

    void initBats();

    BatViewModel findById(String id);
}
