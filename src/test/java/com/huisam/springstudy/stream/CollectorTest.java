package com.huisam.springstudy.stream;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

class CollectorTest {

    @Test
    @DisplayName("search 메서드 테스트")
    void search_method_test() {
        /* given */
        Collector collector = new Collector();
        List<String> letters = List.of("abc", "dfef", "aeeee", "aot");
        /* when */
        final List<String> result = collector.search(letters);
        /* then */
        assertThat(result).isEqualTo(List.of("abc", "aot"));
    }

    @Test
    @DisplayName("getString 메서드 테스트")
    void getString_method_test() {
        /* given */
        Collector collector = new Collector();
        List<Integer> numbers = List.of(3, 44);
        /* when */
        String result = collector.getString(numbers);
        /* then */
        assertThat(result).isEqualTo("o3,e44");
    }

    @Test
    @DisplayName("reduce 테스트")
    void reduce_test() {
        /* given */
        List<Integer> numbers = List.of(2, 3, 4, 5, 6, 7);
        /* when */
        final int sumation = numbers.stream()
                .reduce(0, Integer::sum);
        /* then */
        assertThat(sumation).isEqualTo(27);
    }

    @Test
    @DisplayName("grouping 테스트")
    void grouping_test() {
        /* given */
        List<Integer> numbers = List.of(2, 3, 4, 5, 6, 7);
        /* when */
        final Map<Long, List<Integer>> sumation = numbers.stream()
                .collect(groupingBy(Integer::longValue));
        /* then */
        assertThat(sumation.get(2L)).isEqualTo(List.of(2));
        assertThat(sumation.get(3L)).isEqualTo(List.of(3));
        assertThat(sumation.get(4L)).isEqualTo(List.of(4));
        assertThat(sumation.get(0L)).isNull();
    }
}
