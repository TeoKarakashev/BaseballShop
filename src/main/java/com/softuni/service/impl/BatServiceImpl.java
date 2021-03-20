package com.softuni.service.impl;

import com.softuni.error.BatNotFoundException;
import com.softuni.error.BrandNotFoundException;
import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.enums.BatMaterial;
import com.softuni.model.view.BatViewModel;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import com.softuni.service.BatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatServiceImpl implements BatService {

    private final BatRepository batRepository;
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    public BatServiceImpl(BatRepository batRepository, ModelMapper modelMapper, BrandRepository brandRepository) {
        this.batRepository = batRepository;
        this.modelMapper = modelMapper;

        this.brandRepository = brandRepository;
    }

    @Override
    public List<BatViewModel> findAllBats() {
        return this.batRepository.findAll().stream()
                .map(batEntity -> this.modelMapper.map(batEntity, BatViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void initBats() {
        if (this.batRepository.count() == 0) {
            BatEntity bat1 = new BatEntity();
            bat1.setName("271");
            bat1.setPrice(BigDecimal.valueOf(110));
            bat1.setMaterial(BatMaterial.WOOD);
            bat1.setSize(33);
            bat1.setWeight(30);
            bat1.setBrand(this.brandRepository.findByName("E7").orElseThrow(() -> new BrandNotFoundException("No such brand")));
            bat1.setImageUrl("https://static.wixstatic.com/media/4343c6_88627082ac6a4aad9677b52e650a7679~mv2_d_5200_3200_s_4_2.jpg/v1/fill/w_978,h_598,al_c,q_85,usm_0.66_1.00_0.01/4343c6_88627082ac6a4aad9677b52e650a7679~mv2_d_5200_3200_s_4_2.webp");
            bat1.setDescription("The 271 feels very similar to the 110, but it has a quicker taper between the barrel and handle. This model can comfortably be used by contact or power hitters and has a slightly end loaded swing weight.");
            this.batRepository.save(bat1);
            BatEntity bat2 = new BatEntity();
            bat2.setName("243");
            bat2.setPrice(BigDecimal.valueOf(120));
            bat2.setMaterial(BatMaterial.WOOD);
            bat2.setSize(34);
            bat2.setWeight(31);
            bat2.setBrand(this.brandRepository.findByName("E7").orElseThrow(() -> new BrandNotFoundException("No such brand")));
            bat2.setImageUrl("https://static.wixstatic.com/media/4343c6_c0f7567f9dee47db9af0cc3fc7860492~mv2_d_5200_3200_s_4_2.jpg/v1/fill/w_977,h_598,al_c,q_85,usm_0.66_1.00_0.01/4343c6_c0f7567f9dee47db9af0cc3fc7860492~mv2_d_5200_3200_s_4_2.webp");
            bat2.setDescription("The 243 model features the largest barrel diameter. It's a great model for a power hitter who's looking for that end loaded swing feel.");
            this.batRepository.save(bat2);
            BatEntity bat3 = new BatEntity();
            bat3.setName("110");
            bat3.setPrice(BigDecimal.valueOf(110));
            bat3.setMaterial(BatMaterial.WOOD);
            bat3.setSize(32);
            bat3.setWeight(29);
            bat3.setBrand(this.brandRepository.findByName("E7").orElseThrow(() -> new BrandNotFoundException("No such brand")));
            bat3.setImageUrl("https://static.wixstatic.com/media/4343c6_f7b08d8a9b98436b9ac5ec544c020d6d~mv2_d_5200_3200_s_4_2.jpg/v1/fill/w_979,h_599,al_c,q_85,usm_0.66_1.00_0.01/4343c6_f7b08d8a9b98436b9ac5ec544c020d6d~mv2_d_5200_3200_s_4_2.webp");
            bat3.setDescription("The 110 model gives you the most balanced swing weight of all the standard models. It is the perfect fit for contact hitters looking for more bat speed.");
            this.batRepository.save(bat3);
        }
    }

    @Override
    public BatViewModel findById(String id) {
        return this.modelMapper.map(this.batRepository.findById(id).orElseThrow(() -> new BatNotFoundException("No bat found")), BatViewModel.class);
    }
}
