package com.huisam.springstudy.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

class OrderTest {
    @Test
    @DisplayName("테스트이름")
    void date_test() {
        /* given */
        Order order = new Order(1L, "computer", LocalDateTime.now());
        Order order1 = new Order(2L, "computer",
                LocalDateTime.of(2001, 3, 22, 5, 32, 20));

        /* when */
        final Duration between = Duration.between(order1.getOrderedTime(),
                order.getOrderedTime());
        /* then */
        System.out.println(between);
    }
}