package com.softuni.service.impl;

import com.softuni.error.BatNotFoundException;
import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.service.BatServiceModel;
import com.softuni.model.service.BrandServiceModel;
import com.softuni.model.service.UserServiceModel;
import com.softuni.model.view.BatViewModel;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.BatService;
import com.softuni.service.BrandService;
import com.softuni.service.CloudinaryService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatServiceImpl implements BatService {

    private final BatRepository batRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final BrandService brandService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public BatServiceImpl(BatRepository batRepository, ModelMapper modelMapper, BrandRepository brandRepository, BrandService brandService, UserService userService, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.batRepository = batRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
        this.brandService = brandService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<BatViewModel> findAllBats() {
        return this.batRepository.findAll().stream()
                .map(batEntity -> this.modelMapper.map(batEntity, BatViewModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public BatViewModel findById(String id) {
        return this.modelMapper.map(this.batRepository.findById(id).orElseThrow(() -> new BatNotFoundException("No bat found")), BatViewModel.class);
    }

    @Override
    public List<BatViewModel> findByBrand(String brandName) {
        BrandServiceModel brand = this.brandService.findByName(brandName);
        return this.batRepository.findByBrand(this.modelMapper.map(brand, BrandEntity.class))
                .stream()
                .map(batEntity -> this.modelMapper.map(batEntity, BatViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buy(String id, String username) {
        UserServiceModel user = this.userService.findByUsername(username);
        BatEntity bat = this.batRepository.findById(id).orElseThrow(() -> new BatNotFoundException("no bat found"));
        user.setBat(bat);
        this.userService.save(user);
        bat.setQuantity(bat.getQuantity() - 1);
        this.batRepository.save(bat);


    }

    @Override
    public boolean batExists(String name) {
        return this.batRepository.findByName(name).isPresent();
    }

    @Override
    public void save(BatServiceModel batServiceModel) throws IOException {

        BrandServiceModel brand = this.brandService.findByName(batServiceModel.getBrand());

        BatEntity batEntity = this.modelMapper.map(batServiceModel, BatEntity.class);
        batEntity.setImageUrl(this.cloudinaryService.uploadImage(batServiceModel.getImageUrl()));
        batEntity.setQuantity(10);
        batEntity.setBrand(this.modelMapper.map(brand, BrandEntity.class));

        this.batRepository.save(batEntity);
    }

}
