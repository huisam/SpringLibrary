package com.huisam.springstudy.stream;


import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

class OrderStream {
    public static void setNameOnDifferentName(List<Order> sourceOrder, List<Order> targetOrder) {
        final Map<Long, Order> targetOrders = targetOrder.stream()
                .collect(toMap(Order::getId, order -> order));

        sourceOrder.stream()
                .filter(o -> targetOrders.containsKey(o.getId()))
                .forEach(o -> o.setName(targetOrders.get(o.getId()).getName())
                );
    }
}
