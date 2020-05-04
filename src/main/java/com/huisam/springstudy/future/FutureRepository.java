package com.huisam.springstudy.future;

import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class FutureRepository {
    public Integer getSyncPrice() {
        delay();
        return ThreadLocalRandom.current().nextInt(100);
    }

    public Future<Integer> getAsyncPrice() {
        CompletableFuture<Integer> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            futurePrice.complete(ThreadLocalRandom.current().nextInt(100));
        }).start();
        return futurePrice;
    }

    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
