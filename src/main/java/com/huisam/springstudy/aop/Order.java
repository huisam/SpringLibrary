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
    private Long id;

    private String name;

    private String product;

    private Integer price;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderedTime;
}
