package com.huisam.springstudy.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
class OrderDto {
    @ValidName
    private String name;

    @NotNull @NotBlank
    private String product;

    @NotNull @PositiveOrZero
    private int price;

    @NotNull @NotBlank
    private String address;

    private String img;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime orderedTime;
}
