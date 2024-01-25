package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl.UserHasNoTeamCodeImpl;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserHasNoTeamCodeImpl.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, METHOD, PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface UserHasNoTeamCode {

    String message() default "annotation.hasTeam";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
