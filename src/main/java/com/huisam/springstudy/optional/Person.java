package com.huisam.springstudy.optional;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Not Found");
    }

    public static Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Optional::of)
                .map(Person::getCarInsuranceName)
                .collect(toSet());
    }

    public static Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance("find");
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
}
