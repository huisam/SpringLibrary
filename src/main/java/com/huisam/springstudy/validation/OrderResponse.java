package com.huisam.springstudy.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderResponse {

    private String name;

    private String product;

    private int price;

    private String address;

    private String img;

    private LocalDateTime orderedTime;
}
