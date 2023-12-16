package pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.impl.CheckUsernameImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CheckUsernameImpl.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface CheckUsername {

    String message() default "annotation.checkUsername";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
