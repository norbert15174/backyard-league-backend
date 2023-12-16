package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.custom;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class CustomValidator<T> implements Validator {

    protected abstract void validateRequest(T request, Errors errors);

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        validateRequest((T) target, errors);
    }
}
