package com.huisam.springstudy.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String product;

    @NotNull
    @NotBlank
    private String address;

}
