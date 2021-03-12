package com.softuni.service.impl;

import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.GloveEntity;
import com.softuni.model.service.ImportGloveRootService;
import com.softuni.model.service.ImportGloveService;
import com.softuni.model.view.GloveViewModel;
import com.softuni.repository.GloveRepository;
import com.softuni.service.BrandService;
import com.softuni.service.GloveService;
import com.softuni.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GloveServiceImpl implements GloveService {

    private static final String GLOVES_PATH = "src/main/resources/init/gloves.xml";
    private final GloveRepository gloveRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final BrandService brandService;

    @Autowired
    public GloveServiceImpl(GloveRepository gloveRepository, ModelMapper modelMapper, XmlParser xmlParser, BrandService brandService) {
        this.gloveRepository = gloveRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.brandService = brandService;
    }

    @Override
    public List<GloveViewModel> findAllGloves() {
        return this.gloveRepository.findAll().stream()
                .map(gloveEntity -> {
                    return this.modelMapper.map(gloveEntity, GloveViewModel.class);
                }).collect(Collectors.toList());
    }

    @Override
    public void initGloves() throws JAXBException {
        if(this.gloveRepository.count() == 0 ){
            ImportGloveRootService importGloveRootService = this.xmlParser.parseXml(ImportGloveRootService.class, GLOVES_PATH);

            for (ImportGloveService glove : importGloveRootService.getGloves()) {
                GloveEntity gloveEntity = this.modelMapper.map(glove, GloveEntity.class);
                gloveEntity.setBrand(this.modelMapper.map(this.brandService.findByName(glove.getBrand()), BrandEntity.class));
                this.gloveRepository.save(gloveEntity);
            }

        }
    }
}
