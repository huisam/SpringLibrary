package com.huisam.springstudy.stream;

@FunctionalInterface
public interface TwoParameterFunction<T, U, R> {
    R apply(T t, U u);
}
