package com.softuni.web;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.enums.BatMaterial;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import com.softuni.repository.UserRepository;
import com.softuni.service.CloudinaryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class BatControllerTest {

    private static final String Bat_PREFIX = "/bats";
    private static String batId;
    private static String brandId;
    @MockBean
    CloudinaryService mockCloudinaryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BatRepository batRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    void setUp() {
        init();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        batRepository.deleteAll();
        brandRepository.deleteById(brandId);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnDetails() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/details/{id}", batId))
                .andExpect(view().name("/bats/bats-details"))
                .andExpect(model().attributeExists("bat"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnViewAll() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/viewAll"))
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnViewRawlings() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/showRawlings"))
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnViewWilson() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/showWilson"))
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnViewE7() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/showE7"))
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectViewOnAdd() throws Exception {
        this.mockMvc.perform(get(Bat_PREFIX + "/add"))
                .andExpect(view().name("/bats/bats-create"));
    }


    //TODO @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldBuyBat() throws Exception {

        this.mockMvc.perform(post(Bat_PREFIX + "/buy/{id}", batId)
                .with(csrf()))
                .andExpect(view().name("redirect:/"));

        Assertions.assertEquals(4, this.batRepository.findById(batId).orElseThrow().getQuantity());

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldRedirectBecauseOfInvalidImage() throws Exception {

        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "image_url",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        this.mockMvc.perform(multipart(Bat_PREFIX + "/add")
                .file(mockImgFile)
                .param("name", "invalid")
                .param("material", "invalid")
                .param("description", "invalid")
                .param("size", "invalid")
                .param("weight", "invalid")
                .param("price", "invalid")
                .param("brand", "invalid")
                .with(csrf()))
                .andExpect(view().name("redirect:add"));


    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldAddBat() throws Exception {

        MockMultipartFile mockImgFile
                = new MockMultipartFile(
                "imageUrl",
                "hello.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        this.mockMvc.perform(multipart(Bat_PREFIX + "/add")
                .file(mockImgFile)
                .param("name", "test4e")
                .param("material", "WOOD")
                .param("description", "desc4e")
                .param("size", "30")
                .param("weight", "33")
                .param("price", "330")
                .param("brand", "testBrand")
                .with(csrf()))
                .andExpect(view().name("redirect:/"));

        Assertions.assertEquals(2, this.batRepository.count());

    }

    private void init() {

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("testBrand");
        brandEntity.setDescription("testDesc");
        brandEntity = this.brandRepository.save(brandEntity);
        brandId = brandEntity.getId();


        BatEntity batEntity = new BatEntity();
        batEntity.setName("testBate");
        batEntity.setMaterial(BatMaterial.WOOD);
        batEntity.setWeight(33);
        batEntity.setDescription("testDescription");
        batEntity.setSize(30);
        batEntity.setQuantity(5);
        batEntity.setImageUrl("imageNaBate");
        batEntity.setPrice(BigDecimal.valueOf(300));
        batEntity.setBrand(brandEntity);
        batEntity = this.batRepository.save(batEntity);
        batId = batEntity.getId();

    }
}