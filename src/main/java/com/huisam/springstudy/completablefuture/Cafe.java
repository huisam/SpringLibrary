package com.huisam.springstudy.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

class Cafe {

    List<Coffee> asyncOrder(List<String> coffeeNames) {
        final List<CompletableFuture<Coffee>> futures = coffeeNames.stream()
                .map(coffeeName ->
                        CompletableFuture.supplyAsync(()
                                -> Coffee.of(coffeeName))
                                .orTimeout(1L, TimeUnit.SECONDS)
                                .exceptionally(e -> Coffee.of("Failed Coffee"))
                )
                .collect(Collectors.toUnmodifiableList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toUnmodifiableList());
    }

    List<Coffee> order(List<String> coffeeNames) {
        return coffeeNames.stream()
                .map(Coffee::of)
                .collect(Collectors.toUnmodifiableList());
    }

}
