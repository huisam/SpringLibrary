package com.huisam.springstudy.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
class Order {
    private Long id;

    private String name;

    private String product;

    private Integer price;

    private String address;

    public String nameWithProduct(TwoParameterFunction<String, String, String> function) {
        return function.apply(name, product);
    }

}
