package com.huisam.springstudy.serializable;

import com.huisam.springstudy.mapstruct.Order;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntityTest {
    @Test
    @DisplayName("Entity 직렬화 테스트")
    void test_serializable() {
        /* given */
        Order order = Order.builder()
                .id(1L)
                .name("휘진")
                .address("Seoul")
                .product("사과")
                .price(123)
                .build();
        Entity entity = Entity.builder()
                .id("huisam")
                .password("123456")
                .Order(order)
                .build();

        /* when */
        byte[] serailizeResult = SerializationUtils.serialize(entity);
        Entity result = SerializationUtils.deserialize(serailizeResult);

        /* then */
        assertNotNull(result);
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getPassword()).isEqualTo(entity.getPassword());
        assertThat(result.getOrder()).isEqualTo(order);
    }

    @Test
    @DisplayName("Order 직렬화 테스트")
    void test_order_serializable() {
        /* given */
        Order order = Order.builder()
                .id(1L)
                .name("huisam")
                .address("Seoul")
                .product("사과")
                .price(123)
                .build();
        Order afterOrder;

        /* when */
        // serailize
        final byte[] byteArray = SerializationUtils.serialize(order);
        // deserialize
        afterOrder = SerializationUtils.deserialize(byteArray);

        /* then */
        assertThat(afterOrder).isEqualTo(order);
    }
}