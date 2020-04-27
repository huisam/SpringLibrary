package com.huisam.springstudy.optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {
    @Test
    @DisplayName("Optional로 자동차의 보험회사 이름 찾기")
    void find_car_insurance_name() {
        /* given */
        Person person = new Person(new Car(new Insurance("haha")));
        Person nullNamePerson = new Person(new Car(new Insurance(null)));

        /* when */
        final String name = Person.getCarInsuranceName(Optional.of(person));
        final String nullName = Person.getCarInsuranceName(Optional.of(nullNamePerson));
        /* then */
        assertThat(name).isEqualTo("haha");
        assertThat(nullName).isEqualTo("Not Found");
    }

    @Test
    @DisplayName("Optional로 자동차의 보험회사 이름들 찾기")
    void find_car_insurance_names() {
        /* given */
        Person person = new Person(new Car(new Insurance("haha")));
        Person nullNamePerson = new Person(new Car(new Insurance(null)));

        /* when */
        Set<String> names = Person.getCarInsuranceNames(List.of(person, nullNamePerson));
        /* then */
        assertThat(names.contains("haha")).isTrue();
        assertThat(names.contains("Not Found")).isTrue();
    }

    @Test
    @DisplayName("둘다 비어있으면 Optional empty를 리턴")
    void find_cheapest_insurance_test() {
        /* when */
        final Optional<Insurance> insurance = Person.nullSafeFindCheapestInsurance(
                Optional.empty(), Optional.empty());

        /* then */
        assertThat(insurance).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("하나만 비어있으면 Optional empty를 리턴")
    void find_cheapest_insurance_test2() {
        /* given */
        final Car car = new Car(new Insurance("hi"));

        /* when */
        final Optional<Insurance> insurance = Person.nullSafeFindCheapestInsurance(
                Optional.empty(), Optional.of(car));

        /* then */
        assertThat(insurance).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("둘다 있으면 Optional empty를 리턴")
    void find_cheapest_insurance_test3() {
        /* given */
        final Car car = new Car(new Insurance("hi"));
        final Person person = new Person(car);
        /* when */
        final Insurance insurance = Person.nullSafeFindCheapestInsurance(
                Optional.of(person), Optional.of(car))
                .orElseThrow(IllegalArgumentException::new);

        /* then */
        assertThat(insurance).isEqualTo(new Insurance("find"));
    }
}