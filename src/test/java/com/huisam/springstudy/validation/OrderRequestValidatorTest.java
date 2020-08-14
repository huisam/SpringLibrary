package com.huisam.springstudy.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrderRequestValidatorTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Order의 이름이 0글자이면 검증 하는 테스트")
    void order_name_length_0_validation_test() {
        /* given */
        OrderRequest orderRequest = getOrderDto("");
        /* when */
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);
        /* then */
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 1글자이면 검증 하는 테스트")
    void order_name_length_1_validation_test() {
        /* given */
        OrderRequest orderRequest = getOrderDto("홍");
        /* when */
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);
        /* then */
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 2글자이면 검증 하는 테스트")
    void order_name_length_2_validation_test() {
        /* given */
        OrderRequest orderRequest = getOrderDto("진수");
        /* when */
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);
        /* then */
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("Order의 이름이 4글자이면 검증 하는 테스트")
    void order_name_length_4_validation_test() {
        /* given */
        OrderRequest orderRequest = getOrderDto("황보진수");
        /* when */
        Set<ConstraintViolation<OrderRequest>> violations = validator.validate(orderRequest);
        /* then */
        assertThat(violations).isEmpty();
    }

    private OrderRequest getOrderDto(String name) {
        return OrderRequest.builder()
                .name(name)
                .address("Seoul")
                .product("상품")
                .build();
    }
}