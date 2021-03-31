package com.softuni.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIndexShouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "test")
    public void testHomeShouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("home"));
    }
}
