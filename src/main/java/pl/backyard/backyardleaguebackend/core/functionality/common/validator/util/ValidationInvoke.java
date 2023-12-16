package pl.backyard.backyardleaguebackend.core.functionality.common.validator.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class ValidationInvoke<T> implements Validator {

    protected abstract void validateRequest(T request, Errors errors);

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        //noinspection unchecked
        validateRequest((T) target, errors);
    }
}
