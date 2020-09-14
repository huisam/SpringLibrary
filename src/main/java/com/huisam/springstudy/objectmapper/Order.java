package com.huisam.springstudy.objectmapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(exclude = "orderedTime")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Order {

    private final Long id;

    private final String name;

    private final String product;

    private final Integer price;

    private final String address;

    private final LocalDateTime orderedTime;

    @JsonCreator
    @Builder
    public Order(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("product") String product,
                 @JsonProperty("price") Integer price, @JsonProperty("address") String address, @JsonProperty("orderedTime") LocalDateTime orderedTime) {
        this.id = id;
        this.name = name;
        this.product = product;
        this.price = price;
        this.address = address;
        this.orderedTime = orderedTime;
    }
}
