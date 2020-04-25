package com.huisam.springstudy.optional;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }


}
