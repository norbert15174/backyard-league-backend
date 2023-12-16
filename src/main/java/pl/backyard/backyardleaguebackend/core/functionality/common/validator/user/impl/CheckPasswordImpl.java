package pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.annotation.CheckPassword;

import java.util.regex.Pattern;

public class CheckPasswordImpl implements ConstraintValidator<CheckPassword, String> {

    @Override
    public void initialize(CheckPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(username)) {
            return true;
        }

        var regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
