package com.huisam.springstudy.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
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

    @Test
    @DisplayName("2개의 숫자 리스트 pair로 합치기")
    void number_pair_test() {
        /* given */
        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(4, 5);
        /* when */
        List<List<Integer>> answer = numbers1.stream()
                .flatMap(n1 -> numbers2.stream()
                        .map(n2 -> List.of(n1, n2))
                )
                .collect(Collectors.toUnmodifiableList());
        /* then */
        assertThat(answer).isEqualTo(List.of(
                List.of(1, 4), List.of(1, 5), List.of(2, 4), List.of(2, 5), List.of(3, 4), List.of(3, 5))
        );
    }

    @Test
    @DisplayName("숫자 리스트 sum 합치기")
    void number_sum_test() {
        /* given */
        List<Integer> numbers = List.of(1, 2, 7);
        /* when */
        final int sum = numbers.stream()
                .reduce(0, Integer::sum);
        /* then */
        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("Order List에서 g로 시작하는 이름 찾기")
    void order_test() {
        /* given */
        Order order1 = new Order(1L, "hi", "book", 123, "seoul");
        Order order2 = new Order(2L, "going", "notebook", 456, "seoul");
        List<Order> orders = List.of(order1, order2);
        /* when */
        Order findOrder = orders.stream()
                .filter(o -> o.getName().startsWith("g"))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        /* then */
        assertThat(findOrder).isEqualTo(order2);
    }

    @Test
    @DisplayName("Order List에서 중복을 제거하고 g로 시작하는 이름 찾기")
    void order_test2() {
        /* given */
        Order order1 = new Order(1L, "hi", "book", 123, "seoul");
        Order order2 = new Order(2L, "going", "notebook", 456, "seoul");
        Order order3 = new Order(2L, "gg", "notebook", 456, "seoul");
        List<Order> orders = List.of(order1, order2, order3);
        /* when */
        List<Order> findOrders = orders.stream()
                .filter(o -> o.getName().startsWith("g"))
                .distinct()
                .collect(Collectors.toList());
        /* then */
        assertThat(findOrders.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Order List에서 이름순으로 찾기")
    void order_test3() {
        /* given */
        Order order1 = new Order(1L, "hi", "book", 123, "seoul");
        Order order2 = new Order(2L, "going", "notebook", 456, "seoul");
        Order order3 = new Order(3L, "abc", "notebook", 456, "seoul");
        List<Order> orders = List.of(order1, order2, order3);
        /* when */
        List<Order> findOrders = orders.stream()
                .sorted(comparing(Order::getName))
                .collect(Collectors.toList());
        /* then */
        assertThat(findOrders.get(0)).isEqualTo(order3);
        assertThat(findOrders.get(1)).isEqualTo(order2);
        assertThat(findOrders.get(2)).isEqualTo(order1);
    }
}