package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.backyard.backyardleaguebackend.core.functionality.common.domain.CheckUnique;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl.CheckUniqueEmailImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckUniqueEmailImpl.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface CheckUniqueEmail {

    Class<? extends CheckUnique> queryService();

    String idPathVariableName() default "";

    String message() default "annotation.checkUniqueEmail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
