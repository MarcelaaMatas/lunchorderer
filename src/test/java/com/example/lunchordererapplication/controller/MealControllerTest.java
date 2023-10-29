package com.example.lunchordererapplication.controller;

import static org.mockito.Mockito.when;

import com.example.lunchordererapplication.dto.MealDto;
import com.example.lunchordererapplication.infrastructure.Constants;
import com.example.lunchordererapplication.model.Meal;
import com.example.lunchordererapplication.service.MealService;
import com.example.lunchordererapplication.mapper.MealMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;


@WebMvcTest(MealController.class)
public class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @MockBean
    private MealMapper mealMapper;

    @Test
    public void testGetDailyMenu() throws Exception {
        // Mock data
        Meal meal1 = new Meal();
        meal1.setMealId(1L);
        meal1.setMealName("Pohani oslić");
        meal1.setPrice(8.99);

        Meal meal2 = new Meal();
        meal2.setMealId(2L);
        meal2.setMealName("Salad");
        meal2.setPrice(6.99);

        List<Meal> meals = Arrays.asList(meal1, meal2);

        MealDto mealDto1 = new MealDto(1L, "Pohani oslić", 8.99);
        MealDto mealDto2 = new MealDto(2L, "Salata", 6.99);

        List<MealDto> mealDtoList = Arrays.asList(mealDto1, mealDto2);

        when(mealService.getDailyMenu()).thenReturn(meals);
        when(mealMapper.mealsToMealDtos(meals)).thenReturn(mealDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.URL_DAILY_MENU)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[{\"mealId\":1,\"mealName\":\"Pohani oslić\",\"price\":8.99}," +
                        "{\"mealId\":2,\"mealName\":\"Salata\",\"price\":6.99}]"));
    }

}
