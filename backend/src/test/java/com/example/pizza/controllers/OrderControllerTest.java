package com.example.pizza.controllers;
import com.example.pizza.entity.OrderPizza;
import com.example.pizza.models.OrderModel;
import com.example.pizza.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@Sql(value = {"/sql/init.sql", "/sql/data.sql"})
@RequiredArgsConstructor
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testRegisterOrder() throws Exception {
        // Create a sample OrderModel with dummy data
        OrderModel orderModel = OrderModel.builder()
                .orderedPizzas(List.of(OrderPizza.builder()
                                               .pizzaId(1)
                                               .type("string")
                                               .size(30)
                                               .count(2)
                                               .build()))
                .finalPrice(25.0f)
                .phoneNumber("1234567890")
                .build();

        // Convert OrderModel to JSON string
        String orderModelJson = asJsonString(orderModel);

        // Perform the POST request with the JSON representation of OrderModel
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(orderModelJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andReturn();
    }
    // Helper method to convert an object to JSON string
    private static String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}