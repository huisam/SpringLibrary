package com.huisam.springstudy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderReqeustValidator implements ConstraintValidator<ValidName, String> {
   public void initialize(ValidName constraint) {
   }

   public boolean isValid(String name, ConstraintValidatorContext context) {
      return name.length() >= 2 && name.length() <= 4;
   }
}
