package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import com.google.common.base.Strings;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckFileType;


import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckFileTypeImpl implements ConstraintValidator<CheckFileType, MultipartFile> {

    private Set<String> fileTypes;

    @Override
    public void initialize(CheckFileType constraintAnnotation) {
        this.fileTypes = Stream.of(constraintAnnotation.types()).collect(Collectors.toSet());
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(file)) {
            return setError(constraintValidatorContext);
        }

        var fileName = file.getOriginalFilename();
        if (Strings.isNullOrEmpty(fileName)) {
            return setError(constraintValidatorContext);
        }

        var isValidFormat = fileTypes.stream().anyMatch(fileName::endsWith);
        if (!isValidFormat) {
            return setError(constraintValidatorContext);
        }

        return true;
    }

    private boolean setError(ConstraintValidatorContext constraintValidatorContext) {
        var fileTypesAsString = String.join(", ", fileTypes);
        var context = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        context.addMessageParameter("formats", fileTypesAsString);
        return false;
    }

}
