package com.huisam.springstudy.aop;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Log4j2
public class AopController {
    @GetMapping("/order")
    @LogExecutionTime
    public ResponseEntity<Order> order() {
        Order order = Order.builder()
                .id(1L)
                .name("huisam")
                .address("서울")
                .price(200)
                .product("ice cream")
                .orderedTime(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(order);
    }

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("ok");
    }

    @ExceptionCatcher
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        throw new IllegalArgumentException();
    }
}
