package com.huisam.springstudy.objectmapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class OrderController {
    @GetMapping("/orders")
    public Order order() {
        return Order.builder()
                .id(1L)
                .name("huisam")
                .address("seoul")
                .orderedTime(LocalDateTime.now())
                .price(123)
                .product("computer")
                .build();
    }
}
