package com.huisam.springstudy.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
class Order {
    private final Long id;

    private final String product;

    private final LocalDateTime orderedTime;

}
