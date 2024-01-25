package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasNoTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.GameType;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

import java.util.Objects;


public class UserHasNoTeamImpl implements ConstraintValidator<UserHasNoTeam, GameType> {

    private final UserQueryService userQueryService;

    public UserHasNoTeamImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public void initialize(UserHasNoTeam constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(GameType type, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(type)) {
            return true;
        }

        var userId = UserContextHolder.getAuthenticatedUser().id();
        var user = userQueryService.getByIdWithTeams(userId);

        if (user.getTeams().stream().anyMatch(team -> Objects.equals(team.getTeam().getType(), type))) {
            return false;
        }

        return true;
    }

}
