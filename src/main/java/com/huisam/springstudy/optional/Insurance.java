package com.huisam.springstudy.optional;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
class Insurance {
    private final String name;

    public String getName() {
        return name;
    }
}
