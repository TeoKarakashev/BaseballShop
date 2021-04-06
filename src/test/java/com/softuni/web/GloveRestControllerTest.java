package com.softuni.web;

import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.GloveEntity;
import com.softuni.model.entity.enums.GloveMaterial;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class GloveRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private GloveRepository gloveRepository;


    @BeforeEach()
    public void setUp(){
        gloveRepository.deleteAll();
        brandRepository.deleteAll();
        this.init();
    }

    @AfterEach
    public void tearDown(){
        gloveRepository.deleteAll();
        brandRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void testFetchGloves() throws Exception {
        mockMvc.perform(get("/gloves/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("712"))
                .andExpect(jsonPath("[0].quantity").value(10))
                .andExpect(jsonPath("[0].material").value(GloveMaterial.LEATHER.name()));
    }




    private void init() {
        BrandEntity E7 = new BrandEntity();
        E7.setName("E7");
        E7.setDescription("some description");
        E7 = brandRepository.save(E7);


        GloveEntity gloveEntity = new GloveEntity();
        gloveEntity.setName("712");
        gloveEntity.setQuantity(10);
        gloveEntity.setSize(Double.parseDouble("13"));
        gloveEntity.setImageUrl("https://res.cloudinary.com/b1gted0/image/upload/v161678212/Screenshot_2021-03-26_200839_datuco.png");
        gloveEntity.setDescription("test description");
        gloveEntity.setMaterial(GloveMaterial.LEATHER);
        gloveEntity.setPrice(BigDecimal.valueOf(300));
        gloveEntity.setBrand(E7);
        gloveRepository.save(gloveEntity);
    }
}
