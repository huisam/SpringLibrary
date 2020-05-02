package com.huisam.springstudy.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStreamTest {

    @Test
    @DisplayName("Order List에서 id가 같고 이름이 다른 Order찾기")
    void find_order_same_id_and_different_name() {
        /* given */
        List<Order> sourceOrder = List.of(new Order(1L, "hi", "going", 123, "Seoul"),
                new Order(2L, "computer", "line", 2222, "home"));
        List<Order> targetOrder = List.of(new Order(1L, "hehe", "going", 456, "Seoul"),
                new Order(2L, "computer", "line", 2222, "home"));

        /* when */
        OrderStream.setNameOnDifferentName(sourceOrder, targetOrder);
        /* then */
        assertThat(sourceOrder.get(0).getName()).isEqualTo("hehe");
    }
}