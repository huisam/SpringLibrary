package com.huisam.springstudy.optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Car {
    private final Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }


}
