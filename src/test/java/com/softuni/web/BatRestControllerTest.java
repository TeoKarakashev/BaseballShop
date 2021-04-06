package com.softuni.web;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.enums.BatMaterial;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BatRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BatRepository batRepository;

    @BeforeEach()
    public void setUp(){
        this.init();
    }

    @AfterEach
    public void tearDown(){
        batRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void testFetchGloves() throws Exception {
        mockMvc.perform(get("/bats/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("712"))
                .andExpect(jsonPath("[0].quantity").value(10))
                .andExpect(jsonPath("[0].material").value(BatMaterial.WOOD.name()));
    }

    private void init() {
        BrandEntity E7 = new BrandEntity();
        E7.setName("E7");
        E7.setDescription("some description");
        E7 = brandRepository.save(E7);


        BatEntity batEntity = new BatEntity();
        batEntity.setName("712");
        batEntity.setQuantity(10);
        batEntity.setSize(33);
        batEntity.setImageUrl("https://res.cloudinary.com/b1gted0/image/upload/v161678212/Screenshot_2021-03-26_200839_datuco.png");
        batEntity.setDescription("test description");
        batEntity.setMaterial(BatMaterial.WOOD);
        batEntity.setWeight(30);
        batEntity.setPrice(BigDecimal.valueOf(300));
        batEntity.setBrand(E7);
        batRepository.save(batEntity);

    }
}
