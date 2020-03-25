package com.huisam.springstudy.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    @DisplayName("Order 객체 json serialize 테스트")
    void json_serialize_test() throws JsonProcessingException {
        /* given */
        Order order = Order.builder()
                .id(1L)
                .name("huisam")
                .product("라면")
                .price(200)
                .address("Seoul")
                .orderedTime(LocalDateTime.now())
                .build();

        /* when */
        final String jsonValue = new ObjectMapper().writeValueAsString(order);

        /* then */
        assertThat(jsonValue).doesNotContain("\"id\":\"1\"");
        assertThat(jsonValue).contains("huisam");
        assertThat(jsonValue).contains("라면");
        assertThat(jsonValue).contains("200");
        assertThat(jsonValue).contains("Seoul");
        assertThat(jsonValue).contains("2020");
        System.out.println(jsonValue);
    }
}