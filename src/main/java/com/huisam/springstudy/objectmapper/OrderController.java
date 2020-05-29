package com.huisam.springstudy.objectmapper;

import com.huisam.springstudy.mapstruct.Order;
import com.huisam.springstudy.reactive.OrderRequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Log4j2
public class OrderController {

    @PostMapping("/orders")
    public Order order(@RequestBody @Valid OrderRequestBody body) {
        log.info("Order Controller : {}", body);
        return Order.builder()
                .id(1L)
                .name(body.getName())
                .address("seoul")
                .price(123)
                .product("computer")
                .orderedTime(LocalDateTime.now())
                .build();
    }
}
