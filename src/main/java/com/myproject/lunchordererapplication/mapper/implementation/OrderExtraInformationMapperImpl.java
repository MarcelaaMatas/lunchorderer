package com.myproject.lunchordererapplication.mapper.implementation;

import com.myproject.lunchordererapplication.dto.OrderExtraInformationDto;
import com.myproject.lunchordererapplication.mapper.OrderExtraInformationMapper;
import com.myproject.lunchordererapplication.model.OrderExtraInformation;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderExtraInformationMapperImpl implements OrderExtraInformationMapper {
    @Override
    public OrderExtraInformationDto mapToDto(OrderExtraInformation orderExtraInformation) {
        OrderExtraInformationDto orderExtraInformationDto = new OrderExtraInformationDto();
        orderExtraInformationDto.setMealId(orderExtraInformation.getMealId());
        orderExtraInformationDto.setMealCount(orderExtraInformation.getMealCount());
        return orderExtraInformationDto;
    }

    @Override
    public Set<OrderExtraInformationDto> mapAllToDto(Set<OrderExtraInformation> orderExtraInformationSet) {
        return orderExtraInformationSet.stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }
}
