package com.huisam.springstudy.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    @DisplayName("Order에서 custom Function 테스트")
    void order_function_test() {
        /* given */
        Order order = new Order(1L, "hi", "book", 123, "seoul");
        /* when */
        String nameWithProduct = order.nameWithProduct((name, book) -> name + " " + book);
        /* then */
        assertThat(nameWithProduct).isEqualTo("hi book");
    }


}