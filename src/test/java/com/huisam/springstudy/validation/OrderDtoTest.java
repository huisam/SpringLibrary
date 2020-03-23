package com.huisam.springstudy.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Order Dto의 전부 null이고 안되는 경우 생성 테스트")
    void orderDto_create_test() {
        /* given */
        final OrderDto orderDto = OrderDto.builder()
                .name(null)
                .product(null)
                .price(-1)
                .img(null)
                .address(null)
                .orderedTime(null)
                .build();

        /* when */
        Set<ConstraintViolation<OrderDto>> constraintViolations = validator.validate(orderDto);
        /* then */
        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .contains("반드시 값이 있어야 합니다.");
    }

    @Test
    @DisplayName("Order Dto의 price만 음수인 경우 테스트")
    void orderDto_create_test2() {
        /* given */
        final OrderDto orderDto = OrderDto.builder()
                .name("안녕")
                .product("몰라")
                .price(-1)
                .img("hi.img")
                .address("서울시")
                .orderedTime(LocalDateTime.now())
                .build();

        /* when */
        Set<ConstraintViolation<OrderDto>> constraintViolations = validator.validate(orderDto);
        /* then */
        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .contains("must be greater than or equal to 0");
    }

    @Test
    @DisplayName("Order Dto의 이름이 공백인 경우 테스트")
    void orderDto_create_test3() {
        /* given */
        final OrderDto orderDto = OrderDto.builder()
                .name("")
                .product("몰라")
                .price(100)
                .img("hi.img")
                .address("서울시")
                .orderedTime(LocalDateTime.now())
                .build();

        /* when */
        Set<ConstraintViolation<OrderDto>> constraintViolations = validator.validate(orderDto);
        /* then */
        assertThat(constraintViolations)
                .extracting(ConstraintViolation::getMessage)
                .contains("반드시 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.");
    }
}