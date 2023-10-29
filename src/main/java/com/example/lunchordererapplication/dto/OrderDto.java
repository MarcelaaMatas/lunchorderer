package com.example.lunchordererapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<MealDto> meals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean ordered;
}
