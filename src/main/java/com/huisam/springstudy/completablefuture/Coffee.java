package com.huisam.springstudy.completablefuture;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class Coffee {
    private final String name;

    static Coffee of(String coffeeName) {
        delay();
        return new Coffee(coffeeName);
    }

    private static void delay() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
