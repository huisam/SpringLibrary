package com.huisam.springstudy.aop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
class Order {
    private final Long id;

    private final String name;

    private final String product;

    private final Integer price;

    private final String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime orderedTime;
}
