package com.huisam.springstudy.future;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FutureController {

    private final FutureRepository repository;

    @GetMapping("/future/sync")
    public ResponseEntity<Integer> syncPrice() {
        final Integer syncPrice = repository.getSyncPrice();
        log.info("sync price : " + syncPrice);
        return ResponseEntity.ok(syncPrice);
    }

    @GetMapping("/future/async")
    public Future<Integer> asyncPrice() {
        final Future<Integer> asyncPrice = repository.getAsyncPrice();
        log.info("async price: " + asyncPrice);
        return asyncPrice;
    }
}
