package com.huisam.springstudy.serializable;

import com.huisam.springstudy.mapstruct.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;

    private final String password;

    private final Order Order;
}
