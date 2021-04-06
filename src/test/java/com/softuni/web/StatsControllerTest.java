package com.softuni.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;




    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN", "USER"})
    void testShouldReturnCorrectView() throws Exception {
        this.mockMvc.perform(get("/stats"))
                .andExpect(model().attributeExists("logs"))
                .andExpect(view().name("stats/stats"));
    }

}