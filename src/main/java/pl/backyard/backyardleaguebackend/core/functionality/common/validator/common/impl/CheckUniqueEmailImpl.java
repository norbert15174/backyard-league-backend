package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.domain.CheckUnique;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckUniqueEmail;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.util.ValidatorRequestExtractor;

public class CheckUniqueEmailImpl implements ConstraintValidator<CheckUniqueEmail, String> {

    private final ApplicationContext applicationContext;
    private final ValidatorRequestExtractor extractor;
    private CheckUnique queryService;
    private String idPathVariableName;

    public CheckUniqueEmailImpl(ApplicationContext applicationContext, ValidatorRequestExtractor extractor) {
        this.applicationContext = applicationContext;
        this.extractor = extractor;
    }

    @Override
    public void initialize(CheckUniqueEmail constraintAnnotation) {
        queryService = applicationContext.getBean(constraintAnnotation.queryService());
        idPathVariableName = constraintAnnotation.idPathVariableName();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(s)) {
            return true;
        }

        return !queryService.isEmailExist(s, extractor.getIdFromRequest(idPathVariableName));
    }
}
