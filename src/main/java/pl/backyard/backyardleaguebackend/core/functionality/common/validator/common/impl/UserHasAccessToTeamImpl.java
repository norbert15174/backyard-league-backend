package pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.UserHasAccessToTeam;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.exception.UserNoAccessException;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.TeamRoleType;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

import java.util.Objects;


public class UserHasAccessToTeamImpl implements ConstraintValidator<UserHasAccessToTeam, Long> {

    private final TeamQueryService teamQueryService;

    public UserHasAccessToTeamImpl(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    @Override
    public void initialize(UserHasAccessToTeam constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(id)) {
            return true;
        }

        var userId = UserContextHolder.getAuthenticatedUser().id();
        var team = teamQueryService.getByIdWithMembers(id);
        var memberOpt = team.getUsers().stream()
                .filter(u -> Objects.equals(u.getUser().getId(), userId))
                .findFirst();

        if (memberOpt.isEmpty()) {
            throw new UserNoAccessException("annotation.userHasAccessNoAccess");
        }

        var isOwner = Objects.equals(memberOpt.get().getRole(), TeamRoleType.OWNER);
        if (isOwner) {
            return true;
        }

        throw new UserNoAccessException("annotation.userHasAccessNoAccess");
    }

}
