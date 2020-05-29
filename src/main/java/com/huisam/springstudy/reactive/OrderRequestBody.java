package com.huisam.springstudy.reactive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class OrderRequestBody {
    @NotNull
    @NotBlank
    private String name;
}
