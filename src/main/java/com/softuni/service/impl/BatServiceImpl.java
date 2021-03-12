package com.softuni.service.impl;

import com.softuni.model.view.BatViewModel;
import com.softuni.repository.BatRepository;
import com.softuni.service.BatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatServiceImpl implements BatService {

    private final BatRepository batRepository;
    private final ModelMapper modelMapper;

    public BatServiceImpl(BatRepository batRepository, ModelMapper modelMapper) {
        this.batRepository = batRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BatViewModel> findAllBats() {
        return this.batRepository.findAll().stream()
                .map(batEntity -> {
                    return this.modelMapper.map(batEntity, BatViewModel.class);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void initBats() {

    }
}
