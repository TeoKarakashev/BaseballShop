package com.softuni.service;

import com.softuni.model.entity.GloveEntity;
import com.softuni.model.view.GloveViewModel;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface GloveService {
    
    
    List<GloveViewModel> findAllGloves();

    void initGloves() throws JAXBException;

    GloveViewModel findById(String id);

    GloveEntity getOne();

    List<GloveViewModel> findByBrand(String brandName);

    void buy(String id, String name);
}
