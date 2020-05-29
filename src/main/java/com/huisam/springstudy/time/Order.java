package com.huisam.springstudy.time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
class Order {
    private Long id;

    private String product;

    private LocalDateTime orderedTime;

}
