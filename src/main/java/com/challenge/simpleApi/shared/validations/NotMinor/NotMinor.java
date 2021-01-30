package com.challenge.simpleApi.shared.validations.NotMinor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotMinorValidation.class)
public @interface NotMinor{
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
  String message() default "A valid age should be provided!";
}