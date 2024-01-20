package pl.backyard.backyardleaguebackend.core.functionality.team.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamCreateRequest;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamJoinRequest;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.Team;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.TeamRoleType;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeam;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeamId;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamDTO;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamCudService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.TeamQueryService;
import pl.backyard.backyardleaguebackend.core.functionality.team.service.crud.UserTeamCudService;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;
import pl.backyard.backyardleaguebackend.security.functionality.user.context.UserContextHolder;

import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
class TeamServiceImpl implements TeamService {

    private final TeamCudService teamCudService;
    private final UserTeamCudService userTeamCudService;
    private final UserQueryService userQueryService;
    private final TeamQueryService teamQueryService;

    @Override
    public TeamDTO create(TeamCreateRequest request) {
        var team = new Team();
        var user = userQueryService.getById(UserContextHolder.getAuthenticatedUser().id());
        team.setName(request.getName());
        team.setType(request.getType());
        var created = teamCudService.create(team);


        var userTeam = new UserTeam(new UserTeamId(user.getId(), team.getId()), user, team, TeamRoleType.OWNER);
        userTeamCudService.create(userTeam);

        return TeamDTO.builder()
                .id(created.getId())
                .points(created.getPoints())
                .name(created.getName())
                .type(created.getType())
                .owner(true)
                .build();
    }

    @Override
    public TeamDTO join(TeamJoinRequest request) {
        var user = userQueryService.getById(UserContextHolder.getAuthenticatedUser().id());
        var team = teamQueryService.getByCode(request.getCode());

        var userTeam = new UserTeam(new UserTeamId(user.getId(), team.getId()), user, team, TeamRoleType.TEAMMATE);
        userTeamCudService.create(userTeam);
        return TeamDTO.builder()
                .id(team.getId())
                .points(team.getPoints())
                .name(team.getName())
                .type(team.getType())
                .owner(false)
                .build();
    }

    @Override
    public void delete(Long id) {
        //TODO DO IMPLEMENTACJI
    }

    @Override
    public void leave(Long id) {
        var userTeam = userQueryService.getByIdWithTeams(UserContextHolder.getAuthenticatedUser().id())
                .getTeams()
                .stream()
                .filter(team -> Objects.equals(team.getTeam().getId(), id))
                .findFirst();
        userTeam.ifPresent(userTeamCudService::delete);
    }

}
