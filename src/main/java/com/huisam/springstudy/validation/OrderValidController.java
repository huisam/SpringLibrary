package com.huisam.springstudy.validation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class OrderValidController {

    @PostMapping(value = "valid/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrder(
            @RequestBody @Valid OrderRequest orderRequest) {

        OrderResponse orderResponse = OrderResponse.builder()
                .name(orderRequest.getName())
                .product(orderRequest.getProduct())
                .address(orderRequest.getAddress())
                .price(1000)
                .orderedTime(LocalDateTime.now())
                .img(orderRequest.getProduct() + ".jpg")
                .build();

        return ResponseEntity.ok(orderResponse);
    }

}
