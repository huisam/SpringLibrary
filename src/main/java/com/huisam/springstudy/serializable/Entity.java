package com.huisam.springstudy.serializable;

import com.huisam.springstudy.mapper.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String password;

    private Order Order;
}
