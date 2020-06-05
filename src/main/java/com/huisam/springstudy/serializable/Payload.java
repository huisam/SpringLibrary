package com.huisam.springstudy.serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Payload {

    private String name;

    @JsonRawValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String data;
}
