package com.softuni.service;

import com.softuni.model.service.BrandServiceModel;

import java.io.IOException;

public interface BrandService {
    void initBrands() throws IOException;

     String readAuthorsFileContent() throws IOException;

    BrandServiceModel findByName(String brand);
}
