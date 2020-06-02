package com.huisam.springstudy.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ObjectMapperConfig.class)
class OrderTest {

    @Autowired
    private ObjectMapper objectMapper;

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
        final String jsonValue = objectMapper.writeValueAsString(order);
        final Order readOrder = objectMapper.readValue(jsonValue, Order.class);

        /* then */
        assertThat(jsonValue).doesNotContain("\"id\":\"1\"");
        assertThat(jsonValue).contains("huisam");
        assertThat(jsonValue).contains("라면");
        assertThat(jsonValue).contains("200");
        assertThat(jsonValue).contains("Seoul");
        assertThat(jsonValue).contains("2020");
        assertThat(readOrder).isEqualTo(order);
    }
}