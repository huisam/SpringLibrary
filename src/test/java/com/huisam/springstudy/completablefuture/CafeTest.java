package com.huisam.springstudy.completablefuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class CafeTest {

    private final Cafe cafe = new Cafe();

    @Test
    @Timeout(value = 200L, unit = TimeUnit.MILLISECONDS)
    @DisplayName("동기 처리 테스트")
    void sync_test() {
        /* given */
        List<String> coffeeNames = List.of("americano", "latte", "moca", "water");

        /* when */
        final List<Coffee> coffees = cafe.order(coffeeNames);

        /* then */
        coffees.stream()
                .map(Coffee::getName)
                .forEach(name ->
                        assertThat(coffeeNames.contains(name))
                );
    }

    @Test
    @Timeout(value = 200L, unit = TimeUnit.MILLISECONDS)
    @DisplayName("비동기 처리 테스트")
    void async_test() {
        /* given */
        List<String> coffeeNames = List.of("americano", "latte", "moca", "water");

        /* when */
        final List<Coffee> coffees = cafe.asyncOrder(coffeeNames);

        /* then */
        coffees.stream()
                .map(Coffee::getName)
                .forEach(name ->
                        assertThat(coffeeNames.contains(name))
                );
    }

    @Test
    @DisplayName("runAsync 테스트")
    void runAsync_test() {
        Executor executor = Executors.newFixedThreadPool(30);
        final List<CompletableFuture<Void>> futures = IntStream.range(0, 100)
                .limit(10)
                .boxed()
                .map(time -> CompletableFuture.runAsync(() -> {
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread().getName() + ": hi");
                        }, executor)
                )
                .collect(Collectors.toList());

        futures.stream()
                .map(CompletableFuture::join);
    }

    @Test
    @DisplayName("supplyAsync 테스트")
    void supplyAsync_test() {
        Executor executor = Executors.newFixedThreadPool(30);
        final List<CompletableFuture<String>> futures = IntStream.range(0, 100)
                .limit(10)
                .boxed()
                .map(time -> CompletableFuture.supplyAsync(() -> {
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return Thread.currentThread().getName() + ": hi";
                        }, executor)
                                .orTimeout(2L, TimeUnit.MILLISECONDS)
                                .exceptionally(e -> Thread.currentThread().getName() + ": Failed")
                )
                .collect(Collectors.toList());

        futures.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("allOf 테스트")
    void allOf_test() {
        Executor executor = Executors.newFixedThreadPool(30);
        final CompletableFuture<Void> future = CompletableFuture.allOf(
                IntStream.range(0, 10)
                        .limit(5)
                        .boxed()
                        .map(time -> CompletableFuture.supplyAsync(() -> {
                                    try {
                                        Thread.sleep(time);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return Thread.currentThread().getName() + ": hi";
                                }, executor)
                        )
                        .toArray(CompletableFuture[]::new)
        );
        future.join();
    }
}