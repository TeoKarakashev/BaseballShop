package com.softuni.service.impl;

import com.softuni.model.view.GloveViewModel;
import com.softuni.repository.GloveRepository;
import com.softuni.service.GloveService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GloveServiceImpl implements GloveService {

    private final GloveRepository gloveRepository;
    private final ModelMapper modelMapper;

    public GloveServiceImpl(GloveRepository gloveRepository, ModelMapper modelMapper) {
        this.gloveRepository = gloveRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GloveViewModel> findAllGloves() {
        return this.gloveRepository.findAll().stream()
                .map(gloveEntity -> {
                    return this.modelMapper.map(gloveEntity, GloveViewModel.class);
                }).collect(Collectors.toList());
    }

    @Override
    public void initGloves() {
        if(this.gloveRepository.count() == 0 ){

        }
    }
}
