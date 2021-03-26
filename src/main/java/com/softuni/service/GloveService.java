package com.softuni.service;

import com.softuni.model.entity.GloveEntity;
import com.softuni.model.service.GloveServiceModel;
import com.softuni.model.view.GloveViewModel;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface GloveService {
    
    
    List<GloveViewModel> findAllGloves();

    void initGloves() throws JAXBException;

    GloveViewModel findById(String id);

    GloveEntity getOne();

    List<GloveViewModel> findByBrand(String brandName);

    void buy(String id, String name);

    boolean gloveExists(String name);

    void save(GloveServiceModel map) throws IOException;
}
