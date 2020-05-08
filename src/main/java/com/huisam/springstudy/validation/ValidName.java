package com.huisam.springstudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Constraint(validatedBy = OrderReqeustValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@NotNull
@NotBlank
public @interface ValidName {
    String message() default "2자 이상 4자 이하의 이름을 입력해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
