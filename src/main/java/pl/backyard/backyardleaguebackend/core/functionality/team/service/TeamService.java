package pl.backyard.backyardleaguebackend.core.functionality.team.service;

import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamCreateRequest;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamJoinRequest;
import pl.backyard.backyardleaguebackend.core.functionality.team.dto.TeamDTO;

public interface TeamService {
    TeamDTO create(TeamCreateRequest request);

    TeamDTO join(TeamJoinRequest request);

    void delete(Long id);

    void leave(Long id);
}
