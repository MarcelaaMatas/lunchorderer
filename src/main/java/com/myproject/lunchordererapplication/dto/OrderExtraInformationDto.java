package com.myproject.lunchordererapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderExtraInformationDto {

    private Long mealId;
    private int mealCount;
}
