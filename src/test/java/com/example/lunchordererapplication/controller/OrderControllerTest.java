package com.example.lunchordererapplication.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lunchordererapplication.infrastructure.Constants;
import com.example.lunchordererapplication.mapper.OrderMapper;
import com.example.lunchordererapplication.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;


    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testCreateOrder() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(Constants.URL_ORDER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2, 3]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateOrder() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(Constants.URL_ORDER_UPDATE, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2, 3]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

}

