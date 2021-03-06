package com.huisam.springstudy.mapstruct;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderMapperTest {
    @Test
    @DisplayName("DTO에서 Entity로 변환하는 테스트")
    void test_dto_to_event() {
        /* given */
        final OrderDto orderDto = OrderDto.builder()
                .name("테스트")
                .product("사탕")
                .price(1000)
                .address("Seoul")
                .orderedTime(LocalDateTime.now())
                .build();
        /* when */
        final Order order = OrderMapper.INSTANCE.orderDtoToEntity(orderDto);
        /* then */
        assertNotNull(order);
        assertThat(order.getName()).isEqualTo("테스트");
        assertThat(order.getProduct()).isEqualTo("사탕");
        assertThat(order.getPrice()).isEqualTo(1000);
        assertThat(order.getAddress()).isEqualTo("Seoul");
        assertThat(order.getId()).isEqualTo(0L);
    }
    @Test
    @DisplayName("Entity에서 DTO 변환하는 테스트")
    void test_event_to_dto() {
        /* given */
        final Order order = new Order(1L, "테스트", "사탕", 1000, "Seoul", LocalDateTime.now());
        /* when */
        final OrderDto orderDto = OrderMapper.INSTANCE.orderToDto(order);
        /* then */
        assertNotNull(orderDto);
        assertThat(orderDto.getName()).isEqualTo("테스트");
        assertThat(orderDto.getProduct()).isEqualTo("사탕");
        assertThat(orderDto.getPrice()).isEqualTo(1000);
        assertThat(orderDto.getAddress()).isEqualTo("Seoul");
        assertThat(orderDto.getOrderedTime()).isBefore(LocalDateTime.now());
        assertThat(orderDto.getImg()).isEqualTo("사탕.jpg");
    }

}