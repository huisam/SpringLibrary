package com.huisam.springstudy.pagination;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int price;
}
