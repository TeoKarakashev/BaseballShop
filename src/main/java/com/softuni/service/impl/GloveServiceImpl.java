package com.softuni.service.impl;

import com.softuni.error.GloveNotFoundException;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.GloveEntity;
import com.softuni.model.service.*;
import com.softuni.model.view.GloveViewModel;
import com.softuni.repository.GloveRepository;
import com.softuni.service.BrandService;
import com.softuni.service.CloudinaryService;
import com.softuni.service.GloveService;
import com.softuni.service.UserService;
import com.softuni.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GloveServiceImpl implements GloveService {

    private static final String GLOVES_PATH = "src/main/resources/init/gloves.xml";
    private final GloveRepository gloveRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final BrandService brandService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Autowired
    public GloveServiceImpl(GloveRepository gloveRepository, ModelMapper modelMapper, XmlParser xmlParser, BrandService brandService, CloudinaryService cloudinaryService, UserService userService) {
        this.gloveRepository = gloveRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.brandService = brandService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @Override
    public List<GloveViewModel> findAllGloves() {
        return this.gloveRepository.findAll().stream()
                .map(gloveEntity -> this.modelMapper.map(gloveEntity, GloveViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void initGloves() throws JAXBException {
        if (this.gloveRepository.count() == 0) {
            ImportGloveRootService importGloveRootService = this.xmlParser.parseXml(ImportGloveRootService.class, GLOVES_PATH);

            for (ImportGloveService glove : importGloveRootService.getGloves()) {
                GloveEntity gloveEntity = this.modelMapper.map(glove, GloveEntity.class);
                gloveEntity.setBrand(this.modelMapper.map(this.brandService.findByName(glove.getBrand()), BrandEntity.class));
                this.gloveRepository.save(gloveEntity);
            }

        }
    }

    @Override
    public GloveViewModel findById(String id) {

        return this.modelMapper.map(this.gloveRepository.findById(id)
                .orElseThrow(() -> new GloveNotFoundException("Glove not found!")), GloveViewModel.class);
    }


    @Override
    public List<GloveViewModel> findByBrand(String brandName) {
        BrandServiceModel brand = this.brandService.findByName(brandName);
        return this.gloveRepository.findByBrand(this.modelMapper.map(brand, BrandEntity.class)).stream()
                .map(gloveEntity -> this.modelMapper.map(gloveEntity, GloveViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public void buy(String id, String username) {
        UserServiceModel user = this.userService.findByUsername(username);
        GloveEntity glove = this.gloveRepository.findById(id).orElseThrow(() -> new GloveNotFoundException("no glove found"));
        user.setGlove(glove);
        this.userService.save(user);
        glove.setQuantity(glove.getQuantity() - 1);
        this.gloveRepository.save(glove);

    }

    @Override
    public boolean gloveExists(String name) {
        return this.gloveRepository.findByName(name).isPresent();
    }

    @Override
    public void save(GloveServiceModel gloveServiceModel) throws IOException {
        GloveEntity gloveEntity = this.modelMapper.map(gloveServiceModel, GloveEntity.class);
        gloveEntity.setQuantity(10);
        gloveEntity.setBrand(this.modelMapper.map(this.brandService.findByName(gloveServiceModel.getBrand()), BrandEntity.class));
        gloveEntity.setImageUrl(this.cloudinaryService.uploadImage(gloveServiceModel.getImageUrl()));
        this.gloveRepository.save(gloveEntity);
    }
}
