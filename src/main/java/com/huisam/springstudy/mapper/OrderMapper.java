package com.huisam.springstudy.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", constant = "0L")
    Order orderDtoToEntity(OrderDto orderDto);

    @Mapping(target = "img", expression = "java(order.getProduct() + \".jpg\")")
    OrderDto orderToDto(Order order);
}
