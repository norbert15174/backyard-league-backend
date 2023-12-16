package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.NotNullOrEmpty;

public class NotNullOrEmptyImpl implements ConstraintValidator<NotNullOrEmpty, String> {

    @Override
    public void initialize(NotNullOrEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !Strings.isNullOrEmpty(value);
    }

}
