package com.myproject.lunchordererapplication.dto;

import com.myproject.lunchordererapplication.model.OrderExtraInformation;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Set<MealDto> meals;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Boolean ordered;
    private Set<OrderExtraInformationDto> extraInformationSet;
}
