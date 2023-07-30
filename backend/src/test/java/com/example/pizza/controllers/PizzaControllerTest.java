package com.example.pizza.controllers;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@Sql(value = {"/sql/init.sql", "/sql/data.sql"})
@RequiredArgsConstructor
@AutoConfigureMockMvc
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllModels() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizza/model/all")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(10))
                .andReturn();
    }

    @Test
    void testGetTypes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizza/types")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                // Add more assertions based on the expected response
                .andReturn();
    }

    @Test
    void testGetCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizza/categories")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(5))
                // Add more assertions based on the expected response
                .andReturn();
    }

    @Test
    void testGetAllData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizza/model/all_pizzes")
                                .param("category_id", "1") // Example category_id parameter value
                                .param("orderingValue", "price")
                                .param("ordering_type", "asc")
                                .param("search_value", "")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.pizzaModelList.length()").value(2))
                // Add more assertions based on the expected response
                .andReturn();
    }

    @Test
    void testGetAllPizzasWithPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizza/model/only_pizzes_page=1limit=10")
                                .param("category_id", "1") // Example category_id parameter value
                                .param("orderingValue", "price")
                                .param("ordering_type", "asc")
                                .param("search_value", "")
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                // Add more assertions based on the expected response
                .andReturn();
    }
}