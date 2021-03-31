package com.softuni.web;

import com.softuni.model.entity.BatEntity;
import com.softuni.model.entity.BrandEntity;
import com.softuni.model.entity.enums.BatMaterial;
import com.softuni.repository.BatRepository;
import com.softuni.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class BatControllerTest {

    private static final String BAT_PREFIX = "/bats";
    @Autowired
    BatRepository batRepository;
    @Autowired
    BrandRepository brandRepository;

    private String testBatId;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.init();
    }


    @AfterEach
    public void clear() {
        this.batRepository.deleteAll();
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testDetailsShouldReturnStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/details/{id}", testBatId))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-details"))
                .andExpect(model().attributeExists("bat"));
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testAddShouldReturnStatusCorrectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-create"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testShouldReturnViewAllPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/viewAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testBuyBat() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post(BAT_PREFIX + "/buy/{id}", testBatId)
                    .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("/home"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testShouldReturnViewAllRawlings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/showRawlings"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));

    }
    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testShouldReturnViewAllE7() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/showE7"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testShouldReturnViewAllWilson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BAT_PREFIX + "/showWilson"))
                .andExpect(status().isOk())
                .andExpect(view().name("/bats/bats-all"))
                .andExpect(model().attributeExists("bats"));

    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testAddBatPost() throws Exception {

        MockMultipartFile imageUrl = new MockMultipartFile(
                "imageUrl",
                "file.png",
                MediaType.TEXT_PLAIN_VALUE,
                "This is the file content".getBytes());


        mockMvc.perform(MockMvcRequestBuilders.multipart(BAT_PREFIX + "/add")
                .file(imageUrl)
                .param("name", "testBat")
                .param("material", BatMaterial.WOOD.name())
                .param("description", "Test4ence")
                .param("size", "33")
                .param("weight", "30")
                .param("price", "300")
                .param("brand", "E7")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, this.batRepository.count());
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    public void testAddBatShouldReturnBatExists() throws Exception {

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "photos",
                "file.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "This is the file content".getBytes());


        mockMvc.perform(MockMvcRequestBuilders.post(BAT_PREFIX + "/add")
                .param("name", "272")
                .param("imageUrl", "IMAGEURL.txt")
                .param("material", BatMaterial.WOOD.name())
                .param("description", "Test4e")
                .param("size", "33")
                .param("weight", "30")
                .param("price", "300")
                .param("brand", "E7")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("batCreateBindingModel"))
                .andExpect(model().attributeExists("batExists"));
    }


    private void init() {

        BrandEntity E7 = new BrandEntity();
        E7.setName("E7");
        E7.setDescription("some description");
        E7 = brandRepository.save(E7);


        BatEntity batEntity = new BatEntity();
        batEntity.setName("272");
        batEntity.setQuantity(10);
        batEntity.setWeight(30);
        batEntity.setSize(33);
        batEntity.setImageUrl("https://res.cloudinary.com/b1gted0/image/upload/v1616781303/dtmtlagrgetwbd5uujkn.jpg");
        batEntity.setDescription("test description");
        batEntity.setMaterial(BatMaterial.WOOD);
        batEntity.setPrice(BigDecimal.valueOf(300));
        batEntity.setBrand(E7);
        batRepository.save(batEntity);
        testBatId = batEntity.getId();
    }


}
