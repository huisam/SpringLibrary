package com.huisam.springstudy.mapstruct;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Order implements Serializable {

    public static final long serialVersionUID = 42L;

    private Long id;

    private String name;

    private String product;

    private Integer price;

    private String address;

    private LocalDateTime orderedTime;
}
