package com.softuni.service;

import com.softuni.model.view.GloveViewModel;

import java.util.List;

public interface GloveService {
    
    
    List<GloveViewModel> findAllGloves();

    void initGloves();
}
