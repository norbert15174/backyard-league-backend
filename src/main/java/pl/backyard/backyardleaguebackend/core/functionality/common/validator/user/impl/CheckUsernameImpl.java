package pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.annotation.CheckUsername;

import java.util.regex.Pattern;

public class CheckUsernameImpl implements ConstraintValidator<CheckUsername, String> {

    @Override
    public void initialize(CheckUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(username)) {
            return true;
        }

        var regex = "^[a-zA-Z0-9]{5,20}$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
