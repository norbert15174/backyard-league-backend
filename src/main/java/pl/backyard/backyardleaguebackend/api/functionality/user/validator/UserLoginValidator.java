package pl.backyard.backyardleaguebackend.api.functionality.user.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import pl.backyard.backyardleaguebackend.api.functionality.user.request.LoginRequest;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.custom.CustomValidator;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.helper.PasswordHelper;

@Component
@AllArgsConstructor
public class UserLoginValidator extends CustomValidator<LoginRequest> {

    private final UserQueryService userQueryService;
    private final PasswordHelper passwordHelper;

    @Override
    protected void validateRequest(LoginRequest request, Errors errors) {
        var userOpt = userQueryService.getOptByUsernameOrEmail(request.getUsername());
        if (userOpt.isEmpty()) {
            errors.reject("validation.user.invalidUsernameOrPassword");
            return;
        }

        var user = userOpt.get();
        if (!passwordHelper.matchPassword(request.getPassword(), user.getPassword())) {
            errors.reject("validation.user.invalidUsernameOrPassword");
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequest.class.isAssignableFrom(clazz);
    }

}
