package com.huisam.springstudy.stream;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

class Collector {

    public List<String> search(List<String> list) {
        return list.stream()
                .filter(l -> l.startsWith("a"))
                .filter(l -> l.length() == 3)
                .collect(Collectors.toList());
    }

    public String getString(List<Integer> list) {
        return list.stream()
                .map(l -> l % 2 == 0 ? "e" + l : "o" + l)
                .collect(joining(","));
    }
}
