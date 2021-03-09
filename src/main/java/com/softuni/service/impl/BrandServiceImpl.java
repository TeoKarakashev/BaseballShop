package com.softuni.service.impl;

import com.google.gson.Gson;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.service.BrandInitServiceModel;
import com.softuni.repository.BrandRepository;
import com.softuni.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BrandServiceImpl implements BrandService {

    private static final String BRANDS_PATH = "src/main/resources/init/brands.json";
    private final BrandRepository brandRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(Gson gson
                            ,ModelMapper modelMapper, BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initBrands() throws IOException {
        if(this.brandRepository.count() == 0){
                BrandInitServiceModel[] brands = this.gson.fromJson(readAuthorsFileContent(), BrandInitServiceModel[].class);
            for (BrandInitServiceModel brand : brands) {
                this.brandRepository.save(this.modelMapper.map(brand, BrandEntity.class));
            }

        }
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        String join = String.join("", Files.readAllLines(Path.of(BRANDS_PATH)));

        return String.join("", Files.readAllLines(Path.of(BRANDS_PATH)));
    }
}
