package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasNoTeamCode;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

import java.util.Objects;


public class UserHasNoTeamCodeImpl implements ConstraintValidator<UserHasNoTeamCode, String> {

    private final TeamQueryService teamQueryService;
    private final UserQueryService userQueryService;

    public UserHasNoTeamCodeImpl(TeamQueryService teamQueryService, UserQueryService userQueryService) {
        this.teamQueryService = teamQueryService;
        this.userQueryService = userQueryService;
    }

    @Override
    public void initialize(UserHasNoTeamCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(code)) {
            return true;
        }

        var type = teamQueryService.getByCode(code).getType();
        var userId = UserContextHolder.getAuthenticatedUser().id();
        var user = userQueryService.getByIdWithTeams(userId);


        if (user.getTeams().stream().anyMatch(t -> Objects.equals(t.getTeam().getType(), type))) {
            return false;
        }

        return true;
    }

}
