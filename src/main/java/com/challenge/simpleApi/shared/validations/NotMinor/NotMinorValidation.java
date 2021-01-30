package com.challenge.simpleApi.shared.validations.NotMinor;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotMinorValidation implements ConstraintValidator<NotMinor,Integer> {
  @Override
  public boolean isValid(Integer receivedAge, ConstraintValidatorContext constraintValidatorContext) {
    return receivedAge>=18;
  }

  @Override
  public void initialize(NotMinor constraintAnnotation) {

  }
}
