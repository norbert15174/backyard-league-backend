package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckByRegexp;

import java.util.regex.Pattern;

public class CheckByRegexpImpl implements ConstraintValidator<CheckByRegexp, String> {

    private Pattern pattern;

    @Override
    public void initialize(CheckByRegexp constraintAnnotation) {
        this.pattern = Pattern.compile(constraintAnnotation.regexp());
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }

        return pattern.matcher(value).matches();
    }

}
