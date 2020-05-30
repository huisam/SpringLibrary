package com.huisam.springstudy.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStreamTest {

    @Test
    @DisplayName("Order List에서 id가 같고 이름이 다른 Order찾기")
    void find_order_same_id_and_different_name() {
        /* given */
        List<Order> sourceOrder = List.of(new Order(1L, "hi", "going", 123, "Seoul", Status.CREATED),
                new Order(2L, "computer", "line", 2222, "home", Status.CREATED));
        List<Order> targetOrder = List.of(new Order(1L, "hehe", "going", 456, "Seoul", Status.CREATED),
                new Order(2L, "computer", "line", 2222, "home", Status.CREATED));

        /* when */
        OrderStream.setNameOnDifferentName(sourceOrder, targetOrder);
        /* then */
        assertThat(sourceOrder.get(0).getName()).isEqualTo("hehe");
    }

    @Test
    @DisplayName("Order List에서 status 별로 분류하기")
    void partition_by_order_status() {
        /* given */
        List<Order> orders = List.of(new Order(1L, "hi", "going", 123, "Seoul", Status.CREATED),
                new Order(2L, "computer", "line", 2222, "home", Status.CREATED),
                new Order(1L, "hehe", "going", 456, "Seoul", Status.DUPLICATED),
                new Order(2L, "computer", "line", 2222, "home", Status.DUPLICATED));

        /* when */
        final Map<Status, List<Order>> statusByOrder = OrderStream.getStatusByOrder(orders);
        /* then */
        assertThat(statusByOrder.get(Status.CREATED)).hasSize(2);
        assertThat(statusByOrder.get(Status.DUPLICATED)).hasSize(2);
        assertThat(statusByOrder.getOrDefault(Status.FAILED, Collections.emptyList())).hasSize(0);
    }
}