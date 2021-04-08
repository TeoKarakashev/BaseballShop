package com.softuni.service;

import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.impl.BatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

class BatServiceImplTest {

    @MockBean
    BatRepository mockBatRepository;
    @MockBean
    ModelMapper modelMapper;
    @MockBean
    BrandRepository brandRepository;
    @MockBean
    BrandService brandService;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    CloudinaryService cloudinaryService;
    private BatService serviceToTest;

    @BeforeEach
    void setUp() {
        {
            serviceToTest = new BatServiceImpl(mockBatRepository, modelMapper, brandRepository, brandService, userService, userRepository, cloudinaryService)
        }
    }


    
}