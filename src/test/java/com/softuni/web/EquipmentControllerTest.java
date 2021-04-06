package com.softuni.web;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.GloveEntity;
import com.softuni.model.entity.enums.BatMaterial;
import com.softuni.model.entity.enums.GloveMaterial;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import com.softuni.repository.GloveRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class EquipmentControllerTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private GloveRepository gloveRepository;
    @Autowired
    private BatRepository batRepository;

    @Autowired
    private MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        gloveRepository.deleteAll();
        batRepository.deleteAll();
        brandRepository.deleteAll();
        this.init();
    }



    @AfterEach
    void tearDown() {
        gloveRepository.deleteAll();
        batRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void testViewAllShouldHaveProperties() throws Exception {
        this.mockMvc.perform(get("/equipment"))
                .andExpect(view().name("/equipment/equipment"))
                .andExpect(model().attributeExists("bats"))
                .andExpect(model().attributeExists("gloves"));
    }




    private void init() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("testBrand");
        brandEntity.setDescription("some description");
        brandEntity = brandRepository.save(brandEntity);


        BatEntity batEntity = new BatEntity();
        batEntity.setName("testBat");
        batEntity.setQuantity(10);
        batEntity.setSize(33);
        batEntity.setImageUrl("https://res.cloudinary.com/b1gted0/image/upload/v161678212/Screenshot_2021-03-26_200839_datuco.png");
        batEntity.setDescription("test description");
        batEntity.setMaterial(BatMaterial.WOOD);
        batEntity.setWeight(30);
        batEntity.setPrice(BigDecimal.valueOf(300));
        batEntity.setBrand(brandEntity);
        batRepository.save(batEntity);

        GloveEntity gloveEntity = new GloveEntity();
        gloveEntity.setName("testGlove");
        gloveEntity.setQuantity(10);
        gloveEntity.setSize(Double.parseDouble("13"));
        gloveEntity.setImageUrl("https://res.cloudinary.com/b1gted0/image/upload/v161678212/Screenshot_2021-03-26_200839_datuco.png");
        gloveEntity.setDescription("test description");
        gloveEntity.setMaterial(GloveMaterial.LEATHER);
        gloveEntity.setPrice(BigDecimal.valueOf(300));
        gloveEntity.setBrand(brandEntity);
        gloveRepository.save(gloveEntity);

    }
}