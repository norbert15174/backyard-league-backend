package pl.backyard.backyardleaguebackend.core.functionality.match.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import pl.backyard.backyardleaguebackend.api.functionality.match.model.MatchRequest;
import pl.backyard.backyardleaguebackend.api.functionality.team.model.TeamCreateRequest;
import pl.backyard.backyardleaguebackend.core.functionality.match.dto.MatchDTO;

public interface MatchService {
    MatchDTO create(MatchRequest request);
}
