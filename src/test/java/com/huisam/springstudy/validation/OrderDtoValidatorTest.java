package com.huisam.springstudy.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoValidatorTest {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Order의 이름이 0글자이면 검증 하는 테스트")
    void order_name_length_0_validation_test() {
        /* given */
        OrderDto orderDto = getOrderDto("");
        /* when */
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        /* then */
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 1글자이면 검증 하는 테스트")
    void order_name_length_1_validation_test() {
        /* given */
        OrderDto orderDto = getOrderDto("홍");
        /* when */
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        /* then */
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 2글자이면 검증 하는 테스트")
    void order_name_length_2_validation_test() {
        /* given */
        OrderDto orderDto = getOrderDto("진수");
        /* when */
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        /* then */
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 4글자이면 검증 하는 테스트")
    void order_name_length_4_validation_test() {
        /* given */
        OrderDto orderDto = getOrderDto("황보진수");
        /* when */
        Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        /* then */
        assertThat(violations).isEmpty();
    }

    private OrderDto getOrderDto(String name) {
        return OrderDto.builder()
                .name(name)
                .address("Seoul")
                .img("test.img")
                .orderedTime(LocalDateTime.now())
                .price(100)
                .product("상품")
                .build();
    }
}