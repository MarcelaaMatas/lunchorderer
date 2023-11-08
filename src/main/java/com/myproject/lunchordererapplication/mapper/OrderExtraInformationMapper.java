package com.myproject.lunchordererapplication.mapper;

import com.myproject.lunchordererapplication.dto.OrderExtraInformationDto;
import com.myproject.lunchordererapplication.model.OrderExtraInformation;
import java.util.Set;

public interface OrderExtraInformationMapper {

    OrderExtraInformationDto mapToDto(OrderExtraInformation orderExtraInformation);

    Set<OrderExtraInformationDto> mapAllToDto(Set<OrderExtraInformation> orderExtraInformationSet);
}
