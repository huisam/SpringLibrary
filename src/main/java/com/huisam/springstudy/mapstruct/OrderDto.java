package com.huisam.springstudy.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
class OrderDto {

    private final String name;

    private final String product;

    private final Integer price;

    private final String address;

    private final String img;

    private final LocalDateTime orderedTime;
}
