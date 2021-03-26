package com.softuni.service;

import com.softuni.model.service.BatServiceModel;
import com.softuni.model.view.BatViewModel;

import java.io.IOException;
import java.util.List;

public interface BatService {
    
    List<BatViewModel> findAllBats();

    BatViewModel findById(String id);

    List<BatViewModel> findByBrand(String brandName);

    void buy(String id, String name);

    boolean batExists(String name);

    void save(BatServiceModel map) throws IOException;
}
